package robo.models.posts

import org.javacord.api.entity.message.Message

/**
 * Holds state for KitText and status of an existing user/robo post.
 *
 * State:
 * messageID
 * channelID
 * serverID
 * userState
 * text
 *
 *
 * @author  aze
 */
class KitPost(val channelID: Long, val kitText: KitText) {

    private var kitMess: Message? = null

    fun getKitMess(): Message? {
        return kitMess
    }

    fun setKitMess(kitMess: Message): KitPost {
        this.kitMess = kitMess
        return this
    }

}
