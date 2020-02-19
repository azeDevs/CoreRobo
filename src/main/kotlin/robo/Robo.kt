package robo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import robo.systems.data.KitData
import robo.systems.input.KitInput
import robo.systems.output.KitOutput
import robo.systems.session.KitSession
import robo.systems.terminal.KitTerminal
import robo.systems.terminal.Run
import robo.systems.terminal.check
import robo.utils.prLn
import robo.utils.prSp


// The [Mask] tames the [Kit]

class Robo(vararg queries: String) : Kit(*queries) {

    private val session: KitSession = KitSession()
    private val output: KitOutput = KitOutput()
    private val terminal: KitTerminal = KitTerminal()
    private val input: KitInput = KitInput()
    private val data: KitData = KitData()

    init {
        installSubKits(session, output, terminal, input, data)
        cyclePulse()
    }
    private var pulse: Long = -1
    private fun cyclePulse() { GlobalScope.launch(Dispatchers.Main) { delay(1000); onPulse(pulse++) } }

    fun startBot(keyFilename: String) {
        prSp("Connecting ... ")
        api.connect(keyFilename).whenComplete { connected, t0 ->
            check(t0, Run {
                if (connected) {
                    terminal.onStart()
                    session.onStart()
                    output.onStart()
                    input.onStart()
                    data.onStart()
                }

                api.initListeners()
                prLn("COMPLETE")
            })
        }
    }

}