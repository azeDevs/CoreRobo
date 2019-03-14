package robo

import ch.qos.logback.classic.Level
import robo.abstracts.Kit
import robo.systems.output.Status
import robo.systems.terminal.Log
import robo.utils.prSp
import robo.utils.trueFuture
import java.util.concurrent.CompletableFuture


class Bot(vararg kits: Kit) {

    private val core: RoboCore = RoboCore()

    init {
        Log.init(Level.INFO)
        this.core.session().installKits(kits.toList())
    }

    fun startBot(keyFilename: String) {
        prSp("Initializing Connection ...")
        core.session().connect(keyFilename).whenComplete({b, t ->
            fun(connected: Boolean, t0: Throwable): CompletableFuture<Boolean> {
                Log.err(t0)
                if (connected) {
                    core.output().setCurrentStatus(Status(0, "Initializing..."))
                    core.terminal().logCoreOnline()
                    core.session().init(core.input())
                    core.pulse().onPulse(Runnable {
                        core.session().onPulse(core.pulse().totalTime)
                        core.output().onPulse(core.pulse().totalTime)
                        core.input().onPulse(core.pulse().totalTime)
                        core.data().onPulse(core.pulse().totalTime)
                        core.terminal().onPulse(core.pulse().totalTime)
                    })
                }
                return trueFuture
            }
        })
    }

}