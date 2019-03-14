package robo.systems.output

import robo.models.posts.KitPost
import robo.systems.pulse.Pulse
import robo.systems.session.CoreSession
import java.util.*


/**
 * - configures custom [KitPost] objects
 * - manages outgoing [KitPost] objects
 */
class CoreOutput(private val session: CoreSession) : Pulse {

    private val currentStatus: Status
    private val postHopper: MutableList<KitPost>

    init {
        this.postHopper = ArrayList()
        this.currentStatus = Status(Status.YELLOW, "null")
    }

    fun queuePost(vararg kitPosts: KitPost) {
        postHopper.addAll(Arrays.asList(*kitPosts))
    }

    override fun onPulse(current: Long) {
        if (postHopper.size > 0 && session.isConnected) {
            for (i in postHopper.indices) {
                session.send(postHopper.removeAt(i))
            }
        }
        // Check Status every 10 seconds and refresh it if current doesn't match
        if (current % 10 == 0L && session.status !== currentStatus) session.status = currentStatus
    }

    fun setCurrentStatus(status: Status): Boolean {
        if (status.getColor() != currentStatus.getColor() || status.toString() != currentStatus.formedString) {
            session.status = status
            return true
        } else
            return false
    }

}
