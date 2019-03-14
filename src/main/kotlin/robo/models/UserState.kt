package robo.models

import org.javacord.api.entity.user.User
import robo.systems.pulse.Pulse

class UserState(val user: User) : Pulse {
    var focus: ServerState? = null

    val name: String
        get() = user.discriminatedName

    override fun onPulse(currentPulse: Long) {

    }
}
