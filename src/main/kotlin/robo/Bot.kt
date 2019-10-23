package robo

import robo.abstracts.Kit
import robo.systems.output.Status
import robo.systems.terminal.Run
import robo.systems.terminal.check
import robo.utils.prLn
import robo.utils.prSp


class Bot(vararg kits: Kit) {

    private val core: RoboCore = RoboCore()

    init { core.session().installKits(listOf(*kits)) }

    private fun initCoreSystems() {
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

    fun startBot(keyFilename: String) {
        prSp("Connecting ... ")
        core.session().connect(keyFilename).whenComplete { connected, t0 -> check(t0, Run {
            prLn("COMPLETE", "Initializing ... ")
            initCoreSystems()
            prLn("COMPLETE")
        }) }
    }
}