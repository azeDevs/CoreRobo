package robo.models.posts


import org.javacord.api.entity.message.Message
import robo.models.Param
import robo.models.ServerState
import robo.models.UserState

import java.util.ArrayList

class Mess(private val message: Message, val user: UserState, val serverState: ServerState?) {
    var params: List<Param>
        protected set
    val text: String
        get() = message.content

    val channelID: Long
        get() = if (message.serverTextChannel.isPresent)
            message.serverTextChannel.get().id
        else
            -1L
    val channelAsString: String
        get() = if (message.serverTextChannel.isPresent)
            message.serverTextChannel.get().name
        else
            "null"

    val serverID: Long
        get() = serverState?.server?.id ?: -1L
    val serverAsString: String
        get() = if (serverState != null)
            serverState.server.name
        else
            "null"

    private val parsedParameters: List<Param>
        get() {
            val parametersList = ArrayList<Param>()
            for (value in text.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()) if (!value.isEmpty()) parametersList.add(
                Param(value)
            )
            return parametersList
        }

    val isUser: Boolean
        get() = !message.author.isWebhook && !message.author.isYourself && message.author.isUser

    init {
        this.params = parsedParameters
    }

    fun fromServer(serverId: Long): Boolean {
        return if (message.server.isPresent)
            message.server.get().id == serverId
        else
            false
    }

}