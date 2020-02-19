package robo.models

import org.javacord.api.entity.user.User
import robo.abstracts.Pulse

class UserState(val user: User) : Pulse {
    var focus: ServerState? = null

    val name: String
        get() = user.discriminatedName

    override fun onPulse(current: Long) {

    }
}
