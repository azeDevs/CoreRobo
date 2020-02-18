package robo.systems.session

import org.javacord.api.entity.server.Server
import org.javacord.api.entity.user.User
import robo.Core
import robo.abstracts.Kit
import robo.models.ServerState
import robo.models.UserState


/**
 * - contains all [Kit]
 * - contains all [UserState]
 * - contains all [ServerState]
 */
class CoreSession(query: String, rc: Core) : Kit(query, rc) {

    private val users: MutableMap<Long, UserState> = HashMap()
    private var servers: MutableMap<Long, ServerState> = HashMap()

    init {
        servers = HashMap(core.api().serversForSession)
    }

    fun getUserState(user: User): UserState {
        if (!users.containsKey(user.id)) users[user.id] = UserState(user)
        return users[user.id]!!
    }

    fun getServerState(server: Server): ServerState {
        if (!servers.containsKey(server.id)) servers[server.id] = ServerState(server)
        return servers[server.id]!!
    }

}
