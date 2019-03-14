package robo.models

import org.javacord.api.entity.server.Server
import robo.systems.pulse.Pulse

class ServerState(val server: Server) : Pulse {

    override fun onPulse(current: Long) {

    }

}
