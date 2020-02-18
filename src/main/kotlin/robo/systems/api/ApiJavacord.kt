package robo.systems.api

import org.javacord.api.DiscordApi
import org.javacord.api.DiscordApiBuilder
import org.javacord.api.entity.activity.ActivityType
import org.javacord.api.entity.user.UserStatus
import robo.Core
import robo.models.ServerState
import robo.models.posts.KitPost
import robo.models.posts.KitText
import robo.systems.output.Status
import robo.systems.terminal.err
import robo.utils.getTokenFromFile
import robo.utils.prLn
import java.util.*
import java.util.concurrent.CompletableFuture


abstract class ApiJavacord : RoboApi {

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

    fun initListeners(core: Core) {
        api!!.addServerMemberJoinListener { event -> core.onEvent(event) }
        api!!.addServerMemberLeaveListener { event -> core.onEvent(event) }
        api!!.addUserChangeNicknameListener { event -> core.onEvent(event) }
        api!!.addUserChangeActivityListener { event -> core.onEvent(event) }
        api!!.addUserChangeStatusListener { event -> core.onEvent(event) }
        api!!.addUserChangeNameListener { event -> core.onEvent(event) }
        api!!.addReactionRemoveListener { event -> core.onEvent(event) }
        api!!.addReactionAddListener { event -> core.onEvent(event) }
        api!!.addMessageEditListener { event -> core.onEvent(event) }
        api!!.addMessageDeleteListener { event -> core.onEvent(event) }
        api!!.addMessageCreateListener { event -> core.onEvent(event) }
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

// TODO: WTF ARE THESE FOR?
//    open fun onEvent(event: ServerMemberJoinEvent) {}
//    open fun onEvent(event: ServerMemberLeaveEvent) {}
//    open fun onEvent(event: UserChangeNicknameEvent) {}
//    open fun onEvent(event: UserChangeActivityEvent) {}
//    open fun onEvent(event: UserChangeStatusEvent) {}
//    open fun onEvent(event: UserChangeNameEvent) {}
//    open fun onEvent(event: ReactionRemoveEvent) {}
//    open fun onEvent(event: MessageDeleteEvent) {}
//    open fun onEvent(event: MessageCreateEvent) {}
//    open fun onEvent(event: MessageEditEvent) {}
//    open fun onEvent(event: ReactionAddEvent) {}

}
