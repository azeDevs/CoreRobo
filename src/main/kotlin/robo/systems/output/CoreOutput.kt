package robo.systems.output

import robo.models.posts.KitPost
import robo.systems.pulse.Pulse
import robo.systems.session.CoreSession

import javax.inject.Inject
import javax.inject.Singleton
import java.util.ArrayList
import java.util.Arrays


/**
 * - configures custom [KitPost] objects
 * - manages outgoing [KitPost] objects
 */
@Singleton
class CoreOutput @Inject
constructor(private val session: CoreSession) : Pulse {

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
        if (status.color != currentStatus.color || status.toString() != currentStatus.formedString) {
            session.status = status
            return true
        } else
            return false
    }

}
