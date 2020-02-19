package robo.systems.api

import org.javacord.api.DiscordApi
import org.javacord.api.DiscordApiBuilder
import org.javacord.api.entity.activity.ActivityType
import org.javacord.api.entity.user.UserStatus
import org.javacord.api.event.message.MessageCreateEvent
import org.javacord.api.event.message.MessageDeleteEvent
import org.javacord.api.event.message.MessageEditEvent
import org.javacord.api.event.message.reaction.ReactionAddEvent
import org.javacord.api.event.message.reaction.ReactionRemoveEvent
import org.javacord.api.event.server.member.ServerMemberJoinEvent
import org.javacord.api.event.server.member.ServerMemberLeaveEvent
import org.javacord.api.event.user.UserChangeActivityEvent
import org.javacord.api.event.user.UserChangeNameEvent
import org.javacord.api.event.user.UserChangeNicknameEvent
import org.javacord.api.event.user.UserChangeStatusEvent
import robo.models.ServerState
import robo.models.posts.KitPost
import robo.models.posts.KitText
import robo.systems.output.Status
import robo.systems.terminal.err
import robo.utils.getTokenFromFile
import robo.utils.prLn
import java.util.*
import java.util.concurrent.CompletableFuture


class ApiJavacord : RoboApi {

    private var api: DiscordApi? = null
    override var isConnected: Boolean = false
        get() = api != null && field

    val serversForSession: Map<Long, ServerState>
        get() {
            val serverStateMap = HashMap<Long, ServerState>()
            api!!.servers.forEach { server -> serverStateMap[server.id] = ServerState(server) }
            return serverStateMap
        }

    override fun connect(keyFilename: String): CompletableFuture<Boolean> {
        val futureConnection = CompletableFuture<Boolean>()

        DiscordApiBuilder().setToken(getTokenFromFile("keys", keyFilename)).login().whenComplete { api, t0 ->
            err(t0)
            this.api = api
            isConnected = true
            this.api!!.addLostConnectionListener { isConnected = false }
            this.api!!.addReconnectListener { isConnected = true }
            prLn("Connected\n")
            futureConnection.complete(isConnected)
        }
        return futureConnection
    }

    override fun initListeners() {
        api!!.addServerMemberJoinListener { event -> onJavacordEvent(event) }
        api!!.addServerMemberLeaveListener { event -> onJavacordEvent(event) }
        api!!.addUserChangeNicknameListener { event -> onJavacordEvent(event) }
        api!!.addUserChangeActivityListener { event -> onJavacordEvent(event) }
        api!!.addUserChangeStatusListener { event -> onJavacordEvent(event) }
        api!!.addUserChangeNameListener { event -> onJavacordEvent(event) }
        api!!.addReactionRemoveListener { event -> onJavacordEvent(event) }
        api!!.addReactionAddListener { event -> onJavacordEvent(event) }
        api!!.addMessageEditListener { event -> onJavacordEvent(event) }
        api!!.addMessageDeleteListener { event -> onJavacordEvent(event) }
        api!!.addMessageCreateListener { event -> onJavacordEvent(event) }
    }

    override var status: Status
        get() {
            val status = Status()
            api!!.activity.ifPresent { activity ->
                activity.details.ifPresent { details -> status.setText(KitText(details)) }
            }
            when (api!!.status) {
                UserStatus.IDLE -> return status.setColor(Status.YELLOW)
                UserStatus.ONLINE -> return status.setColor(Status.GREEN)
                UserStatus.INVISIBLE -> return status.setColor(Status.GREEN)
                else -> return status.setColor(Status.RED)
            }
        }
        set(status) {
            api!!.updateActivity(ActivityType.WATCHING, status.formedString)
            when (status.getColor()) {
                Status.RED -> api!!.updateStatus(UserStatus.DO_NOT_DISTURB)
                Status.YELLOW -> api!!.updateStatus(UserStatus.IDLE)
                Status.GREEN -> api!!.updateStatus(UserStatus.ONLINE)
            }
        }

    override fun send(kitPost: KitPost): CompletableFuture<KitPost> {
        val futurePost = CompletableFuture<KitPost>()
        api!!.getServerTextChannelById(kitPost.channelID)
            .ifPresent { serverTextChannel ->
                serverTextChannel
                    .sendMessage(kitPost.kitText.toFormedString()).whenCompleteAsync { message, t ->
                        futurePost.complete(kitPost.setKitMess(message))
                        err(t)
                    }
            }
        return futurePost
    }

    internal fun onJavacordEvent(event: ServerMemberJoinEvent) {}
    internal fun onJavacordEvent(event: ServerMemberLeaveEvent) {}
    internal fun onJavacordEvent(event: UserChangeNicknameEvent) {}
    internal fun onJavacordEvent(event: UserChangeActivityEvent) {}
    internal fun onJavacordEvent(event: UserChangeStatusEvent) {}
    internal fun onJavacordEvent(event: UserChangeNameEvent) {}
    internal fun onJavacordEvent(event: ReactionRemoveEvent) {}
    internal fun onJavacordEvent(event: MessageDeleteEvent) {}
    internal fun onJavacordEvent(event: MessageCreateEvent) {}
    internal fun onJavacordEvent(event: MessageEditEvent) {}
    internal fun onJavacordEvent(event: ReactionAddEvent) {}

}
