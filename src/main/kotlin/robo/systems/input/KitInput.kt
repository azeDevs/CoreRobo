package robo.systems.input

import org.javacord.api.entity.message.Message
import org.javacord.api.entity.user.User
import org.javacord.api.event.message.MessageCreateEvent
import robo.Kit
import robo.models.ServerState
import robo.models.posts.Mess


/**
 * The `CoreInput` class does the following:
 * 1. Capture any incoming [MessageCreateEvent]
 * 2. Parse the [MessageCreateEvent] into a [Mess] object
 */
class KitInput : Kit() {

    fun onMess(message: Message) {
        message.userAuthor.ifPresent { user ->
            val fs = FuzzyKitSolver(session.getSubKits())
            val mess = getMess(message, user)
            val intendedKit = fs.solveIntendedKit(mess.params)
            intendedKit?.onQuery(fs.getParamResults())
            terminal.log(mess)
        }
    }

    private fun getMess(message: Message, user: User): Mess {
        val userState = session.getUserState(user)
        var serverState: ServerState? = null
        if (message.server.isPresent) {
            serverState = session.getServerState(message.server.get())
            userState.focus = serverState
        }
        return Mess(message, userState, serverState)
    }

    override fun onPulse(current: Long) {}

}
