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

import javax.inject.Inject
import javax.inject.Singleton
import java.util.*
import java.util.concurrent.CompletableFuture


/**
 * - contains all [Kit]
 * - contains all [UserState]
 * - contains all [ServerState]
 */
@Singleton
class CoreSession @Inject
constructor(private val api: ApiJavacord) : Pulse, RoboApi {

    private val kits: MutableSet<Kit>
    private val users: MutableMap<Long, UserState>
    private var servers: MutableMap<Long, ServerState>? = null
    override var status: Status
        get() = api.status
        set(status) {
            api.status = status
        }
    override val isConnected: Boolean
        get() = api.isConnected

    init {
        this.kits = HashSet()
        this.users = HashMap()
        this.servers = HashMap()
    }

    override fun onPulse(current: Long) {
        kits.forEach(Consumer<Kit> { it.onPulse() })
        users.values.forEach { userState -> userState.onPulse(current) }
        servers!!.values.forEach { serverState -> serverState.onPulse(current) }
    }

    fun getUserState(user: User): UserState {
        if (!users.containsKey(user.id)) users[user.id] = UserState(user)
        return users[user.id]
    }

    fun getServerState(server: Server): ServerState {
        if (!servers!!.containsKey(server.id)) servers!![server.id] = ServerState(server)
        return servers!![server.id]
    }

    fun getKits(): Set<Kit> {
        return kits
    }

    fun installKits(vararg kits: Kit) {
        this.kits.addAll(Arrays.asList(*kits))
    }

    fun init(input: CoreInput) {
        api.initListeners(kits, input)
        servers = api.serversForSession
        for (kit in kits) kit.onStart()
    }

    override fun connect(keyFilename: String): CompletableFuture<Boolean> {
        return api.connect(keyFilename)
    }

    override fun send(kitPost: KitPost): CompletableFuture<KitPost> {
        return api.send(kitPost)
    }

}
