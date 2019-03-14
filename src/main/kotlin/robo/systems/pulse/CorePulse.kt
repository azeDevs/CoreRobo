package robo.systems.pulse

import robo.utils.Az

import javax.inject.Inject
import javax.inject.Singleton
import java.util.Timer
import java.util.TimerTask

@Singleton
class CorePulse @Inject
constructor() : TimerTask() {

    private var runnable: Runnable? = null
    var startTime: Long = 0
        private set
    var totalPulses: Long = 0
        private set
    val downPulses: Long
    val upPulses: Long
    val totalTime: Long
        get() = Az.timeMillis() - startTime

    init {
        this.startTime = 0
        this.totalPulses = 0
        this.downPulses = 0
        this.upPulses = 0
        Timer().schedule(this, 1000, 1000) // 1000 = 1s or 86400 P/d
    }

    fun onPulse(runnable: Runnable) {
        this.runnable = runnable
    }

    override fun run() {
        if (startTime == 0L) this.startTime = Az.timeMillis()
        if (runnable != null) runnable!!.run()
        totalPulses++
    }

}