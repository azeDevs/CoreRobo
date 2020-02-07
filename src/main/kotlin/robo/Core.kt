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
class Core {

    private val apiJavacord: ApiJavacord = ApiJavacord()

    private val session: CoreSession = CoreSession(apiJavacord)
    private val output: CoreOutput = CoreOutput(session)
    private val terminal: CoreTerminal = CoreTerminal(output)
    private val input: CoreInput = CoreInput(session, terminal)
    private val data: CoreData = CoreData()
    private val pulse: CorePulse = CorePulse()

    internal fun output(): CoreOutput { return output }
    internal fun input(): CoreInput { return input }
    internal fun data(): CoreData { return data }
    internal fun pulse(): CorePulse { return pulse }
    internal fun session(): CoreSession { return session }
    internal fun terminal(): CoreTerminal { return terminal }

}
