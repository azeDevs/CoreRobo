package robo.systems.api

import org.javacord.api.DiscordApi
import org.javacord.api.DiscordApiBuilder
import org.javacord.api.entity.activity.ActivityType
import org.javacord.api.entity.user.UserStatus
import robo.abstracts.Kit
import robo.models.ServerState
import robo.models.posts.KitPost
import robo.models.posts.KitText
import robo.systems.input.CoreInput
import robo.systems.output.Status
import robo.systems.terminal.Log
import robo.utils.getTokenFromFile
import robo.utils.prLn
import java.util.*
import java.util.concurrent.CompletableFuture


class ApiJavacord : RoboApi {

    private var api: DiscordApi? = null
    override var isConnected: Boolean = false
        get() = api != null && field
        private set

    val serversForSession: Map<Long, ServerState>
        get() {
            val serverStateMap = HashMap<Long, ServerState>()
            api!!.servers.forEach { server -> serverStateMap[server.id] = ServerState(server) }
            return serverStateMap
        }

    override fun connect(keyFilename: String): CompletableFuture<Boolean> {
        val futureConnection = CompletableFuture<Boolean>()

        DiscordApiBuilder().setToken(getTokenFromFile("keys", keyFilename)).login().whenComplete { api, t0 ->
            Log.err(t0)
            this.api = api
            isConnected = true
            this.api!!.addLostConnectionListener { event -> isConnected = false }
            this.api!!.addReconnectListener { event -> isConnected = true }
            prLn("Connected\n")
            futureConnection.complete(isConnected)
        }
        return futureConnection
    }

    fun initListeners(kits: Set<Kit>, coreInput: CoreInput) {
        api!!.addServerMemberJoinListener { event -> kits.forEach { m -> m.onEvent(event) } }
        api!!.addServerMemberLeaveListener { event -> kits.forEach { m -> m.onEvent(event) } }
        api!!.addUserChangeNicknameListener { event -> kits.forEach { m -> m.onEvent(event) } }
        api!!.addUserChangeActivityListener { event -> kits.forEach { m -> m.onEvent(event) } }
        api!!.addUserChangeStatusListener { event -> kits.forEach { m -> m.onEvent(event) } }
        api!!.addUserChangeNameListener { event -> kits.forEach { m -> m.onEvent(event) } }
        api!!.addReactionRemoveListener { event -> kits.forEach { m -> m.onEvent(event) } }
        api!!.addReactionAddListener { event -> kits.forEach { m -> m.onEvent(event) } }
        api!!.addMessageEditListener { event -> kits.forEach { m -> m.onEvent(event) } }
        api!!.addMessageDeleteListener { event -> kits.forEach { m -> m.onEvent(event) } }
        api!!.addMessageCreateListener { event -> coreInput.onMess(event.message) }
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
                        Log.err(t)
                    }
            }
        return futurePost
    }

}
