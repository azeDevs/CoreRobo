package robo.systems.terminal

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import org.slf4j.LoggerFactory
import robo.models.posts.KitLog
import robo.models.posts.KitText
import robo.utils.Az

import java.util.ArrayList
import java.util.HashMap

object Log {

    private var systemLogs: MutableMap<Long, List<KitLog>>? = null

    /*
    // Debug Logging
    */
    private var logger: Logger? = null

    /**
     * Add new KitLog to be displayed in system console and admin console.
     *
     * @param serverID the ID for the KitLog's server source.
     * @param logMask the priority level for this log to be displayed.
     * @param logText the text contents of the log to be displayed.
     * @return the formatted date and time as a `String`
     */
    fun add(serverID: Long, logMask: Int, logText: KitText) {
        if (systemLogs!!.containsKey(serverID)) {
            systemLogs!![serverID].add(KitLog(logMask, logText))
        } else {
            val freshLog = ArrayList<KitLog>()
            freshLog.add(KitLog(logMask, logText))
            systemLogs!![serverID] = freshLog
        }
    }

    internal fun removeAll(): Map<Long, List<KitLog>> {
        val logs = HashMap(systemLogs!!)
        systemLogs!!.clear()
        return logs
    }

    fun init(level: Level) {
        if (logger == null) {
            logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME) as Logger
            logger!!.iteratorForAppenders().forEachRemaining({ loggingEventAppender ->
                Az.prLn(
                    "\n[ logback.classic.Logger ]", "LoggingStrength: " + level.levelStr,
                    "LoggingAppender: " + loggingEventAppender.getName(),
                    "OperatingSystem: " + System.getProperty("os.name"), ""
                )
            })
        }
        setLoggingLevel(level)
        systemLogs = HashMap()
    }

    private fun setLoggingLevel(level: Level) {
        logger!!.setLevel(level)
    }

    fun err(t: Throwable?) {
        if (t != null) logger!!.error(t.message, t)
    }

    fun warn(warning: String) {
        if (!warning.isEmpty()) logger!!.warn(warning)
    }

}
