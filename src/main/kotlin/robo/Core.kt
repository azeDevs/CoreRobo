package robo

import robo.systems.api.ApiJavacord
import robo.systems.data.CoreData
import robo.systems.input.CoreInput
import robo.systems.output.CoreOutput
import robo.systems.pulse.CorePulse
import robo.systems.session.CoreSession
import robo.systems.terminal.CoreTerminal


/**
 * Primary class for the RoboCore library, and [Kit] operations management.
 *
 * @author  aze
 * @since   0.0.2
 *
 */
class Core : ApiJavacord() {

    private val session: CoreSession = CoreSession("session", this)
    private val output: CoreOutput = CoreOutput("output", this)
    private val terminal: CoreTerminal = CoreTerminal("terminal", this)
    private val input: CoreInput = CoreInput("input", this)
    private val data: CoreData = CoreData("data", this)
    private val pulse: CorePulse = CorePulse("pulse", this)

    fun output(): CoreOutput = output
    fun input(): CoreInput = input
    fun data(): CoreData = data
    fun pulse(): CorePulse = pulse
    fun session(): CoreSession = session
    fun terminal(): CoreTerminal = terminal

}
