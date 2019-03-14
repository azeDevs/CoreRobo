package robo

import robo.abstracts.Kit
import robo.dagger.*
import robo.utils.Az

class Bot(vararg kits: Kit) {

    private val core: RoboCore

    init {
        Log.init(Level.INFO)
        this.core = DaggerRoboComponent.builder()
            .coreDataModule(CoreDataModule())
            .coreInputModule(CoreInputModule())
            .corePulseModule(CorePulseModule())
            .coreOutputModule(CoreOutputModule())
            .coreSessionModule(CoreSessionModule())
            .coreTerminalModule(CoreTerminalModule())
            .build().getRoboCore()
        this.core.session().installKits(kits)
    }

    fun startBot(keyFilename: String) {
        Az.prSp("Initializing Connection ...")
        core.session().connect(keyFilename).whenComplete(fun(connected: Any, t0: Any): Any {
            if (connected) {
                core.output().setCurrentStatus(Status(0, "Initializing..."))
                core.terminal().logCoreOnline()
                core.session().init(core.input())
                core.pulse().onPulse({
                    core.session().onPulse(core.pulse().getTotalTime())
                    core.output().onPulse(core.pulse().getTotalTime())
                    core.input().onPulse(core.pulse().getTotalTime())
                    core.data().onPulse(core.pulse().getTotalTime())
                    core.terminal().onPulse(core.pulse().getTotalTime())
                })
            }
            return Log.err(t0)
        })
    }

}
