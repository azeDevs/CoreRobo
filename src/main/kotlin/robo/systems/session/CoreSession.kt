package robo.systems.session

import org.javacord.api.entity.server.Server
import org.javacord.api.entity.user.User
import robo.abstracts.Kit
import robo.models.ServerState
import robo.models.UserState
import robo.models.posts.KitPost
import robo.systems.api.ApiJavacord
import robo.systems.api.RoboApi
import robo.systems.input.CoreInput
import robo.systems.output.Status
import robo.systems.pulse.Pulse
import java.util.*
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


/**
 * - contains all [Kit]
 * - contains all [UserState]
 * - contains all [ServerState]
 */
class CoreSession(private val api: ApiJavacord = ApiJavacord()) : Pulse, RoboApi {

    private val kits: MutableSet<Kit> = HashSet()
    private val users: MutableMap<Long, UserState> = HashMap()
    private var servers: MutableMap<Long, ServerState> = HashMap()

    override val isConnected: Boolean
        get() = api.isConnected

    override var status: Status
        get() = api.status
        set(status) { api.status = status }

    override fun onPulse(current: Long) {
        kits.forEach(Consumer<Kit> { it.onPulse() })
        users.values.forEach { userState -> userState.onPulse(current) }
        servers.values.forEach { serverState -> serverState.onPulse(current) }
    }

    fun getUserState(user: User): UserState {
        if (!users.containsKey(user.id)) users[user.id] = UserState(user)
        return users[user.id]!!
    }

    fun getServerState(server: Server): ServerState {
        if (!servers.containsKey(server.id)) servers[server.id] = ServerState(server)
        return servers[server.id]!!
    }

    fun getKits(): Set<Kit> { return kits }

    fun installKits(kits: List<Kit>) {
        this.kits.addAll(kits)
    }

    fun init(input: CoreInput) {
        api.initListeners(kits, input)
        servers = HashMap(api.serversForSession)
        kits.forEach(Kit::onStart)
    }

    override fun connect(keyFilename: String): CompletableFuture<Boolean> {
        return api.connect(keyFilename)
    }

    override fun send(kitPost: KitPost): CompletableFuture<KitPost> {
        return api.send(kitPost)
    }

}
