package robo.systems.output

import robo.Kit
import robo.models.posts.KitPost
import java.util.*


/**
 * - configures custom [KitPost] objects
 * - manages outgoing [KitPost] objects
 */
class KitOutput : Kit() {

    private val currentStatus: Status
    private val postHopper: MutableList<KitPost>

    init {
        this.postHopper = ArrayList()
        this.currentStatus = Status(Status.YELLOW, "null")
    }

    fun queuePost(vararg kitPosts: KitPost) {
        postHopper.addAll(listOf(*kitPosts))
    }

    override fun onPulse(current: Long) {
        if (postHopper.size > 0 && api.isConnected) {
            for (i in postHopper.indices) {
                api.send(postHopper.removeAt(i))
            }
        }
    }

    fun setCurrentStatus(status: Status): Boolean {
        return if (status.getColor() != currentStatus.getColor() || status.toString() != currentStatus.formedString) {
            api.status = status
            true
        } else false
    }

}
