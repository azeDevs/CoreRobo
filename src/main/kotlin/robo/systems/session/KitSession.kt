package robo.systems.session

import org.javacord.api.entity.server.Server
import org.javacord.api.entity.user.User
import robo.Kit
import robo.models.ServerState
import robo.models.UserState


/**
 * - contains all [Kit]
 * - contains all [UserState]
 * - contains all [ServerState]
 */
class KitSession : Kit() {

    private val users: MutableMap<Long, UserState> = HashMap()
    private val servers: MutableMap<Long, ServerState> = HashMap()

    override fun onStart() { servers.putAll(api.serversForSession) }

    fun isConnected() = api.isConnected

    fun getUserState(user: User): UserState {
        if (!users.containsKey(user.id)) users[user.id] = UserState(user)
        return users[user.id]!!
    }

    fun getServerState(server: Server): ServerState {
        if (!servers.containsKey(server.id)) servers[server.id] = ServerState(server)
        return servers[server.id]!!
    }

}
