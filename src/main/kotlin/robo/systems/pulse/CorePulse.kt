package robo.systems.pulse

import robo.Core
import robo.abstracts.Kit
import robo.utils.timeMillis
import java.util.*


class CorePulse(query: String, rc: Core) : Kit(query, rc) {

    private var runnable: Runnable? = null
    private var startTime: Long = 0
    private var totalPulses: Long = 0
    val downPulses: Long = 0
    val upPulses: Long = 0
    val totalTime: Long = timeMillis() - startTime

    init { Timer().schedule(this, 1000, 1000) } // 1000 = 1s or 86400 P/d

    fun onPulse(runnable: Runnable) { this.runnable = runnable }

    override fun run() {
        if (startTime == 0L) this.startTime = timeMillis()
        if (runnable != null) runnable!!.run()
        totalPulses++
    }

}