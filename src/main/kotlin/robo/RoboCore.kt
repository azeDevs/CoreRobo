package robo


/**
 * Primary class for the RoboCore library, and [Kit] operations management.
 *
 * @author  aze
 * @since   0.0.2
 */
@Singleton
class RoboCore @Inject
constructor(
    /*
    // "You want me to grant the X role to Y? Please confirm."
    //
    // "You want me ot grant the X role to someone, but I'm not sure who, please be more specific"
    //
    // + Loggable interface for passing objects into a single CoreTerminal method for logging
    // + per server admin channel config command, must use CoreData to store configuration
    // + per server admin mask config command
    //
    // RC query abbreviations, display options when multiple available.
    // FuzzyCommand solves partial queries, roles, channels, and users
    // FuzzyTalker removes pleasantries, punctuation, and solves for key words like "me", "I", "you", and "someone"
    // RolesManager, GamesNotifier, XrdDatabase, ClashCircuit
    */

    private val output: CoreOutput,
    private val input: CoreInput,
    private val data: CoreData,
    private val pulse: CorePulse,
    private val session: CoreSession,
    private val terminal: CoreTerminal
) {

    internal fun output(): CoreOutput {
        return output
    }

    internal fun input(): CoreInput {
        return input
    }

    internal fun data(): CoreData {
        return data
    }

    internal fun pulse(): CorePulse {
        return pulse
    }

    internal fun session(): CoreSession {
        return session
    }

    internal fun terminal(): CoreTerminal {
        return terminal
    }

}
