package robo.systems.terminal


import robo.models.Result
import robo.models.posts.KitLog
import robo.models.posts.KitText
import java.util.*

/*
// Debug Logging
*/

var systemLogs: MutableMap<Long, MutableList<KitLog>> = HashMap()

typealias Run = Runnable

fun check(error: Throwable?, runnable: Run) {
    if (error != null) println(error.stackTrace)
    else runnable.run()
}

fun err(error: Throwable?) { if (error != null) println(error.stackTrace) }

fun warn(warning: String) { if (!warning.isEmpty()) println(warning) }

fun addLog(serverID: Long, logMask: Int, logText: KitText) {
    if (systemLogs.containsKey(serverID)) systemLogs.get(serverID)?.add(KitLog(logMask, logText))
    else {
        val freshLog = ArrayList<KitLog>()
        freshLog.add(KitLog(logMask, logText))
        systemLogs[serverID] = freshLog
    }
}

fun retrieveLogs(): Map<Long, List<KitLog>> {
    val logs = HashMap(systemLogs)
    systemLogs.clear()
    return logs
}



//object Log {
//
//    private var logger: Logger? = null
//    private var systemLogs: MutableMap<Long, MutableList<KitLog>> = HashMap()
//
//    /**
//     * Add new KitLog to be displayed in system console and admin console.
//     *
//     * @param serverID the ID for the KitLog's server source.
//     * @param logMask the priority level for this log to be displayed.
//     * @param logText the text contents of the log to be displayed.
//     * @return the formatted date and time as a `String`
//     */
//    fun add(serverID: Long, logMask: Int, logText: KitText) {
//        if (systemLogs.containsKey(serverID)) systemLogs.get(serverID)?.add(KitLog(logMask, logText))
//        else {
//            val freshLog = ArrayList<KitLog>()
//            freshLog.add(KitLog(logMask, logText))
//            systemLogs[serverID] = freshLog
//        }
//    }
//
//    fun removeAll(): Map<Long, List<KitLog>> {
//        val logs = HashMap(systemLogs)
//        systemLogs.clear()
//        return logs
//    }
//
//    fun setupLogger(level: Level) {
//        logger.iteratorForAppenders().forEachRemaining({ loggingEventAppender ->
//            prLn(
//                "\n[ logback.classic.Logger ]",
//                "LoggingStrength: ${level.levelStr}",
//                "LoggingAppender: ${loggingEventAppender.getName()}",
//                "OperatingSystem: ${System.getProperty("os.name")}\n"
//            )
//            setLoggingLevel(level)
//            systemLogs = HashMap()
//            setLoggingLevel(level)
//        })
//    }
//
//    private fun setLoggingLevel(level: Level) = logger!!.setLevel(level)
//
//    fun err(t: Throwable?) { if (t != null) logger!!.error(t.message, t) }
//
//    fun warn(warning: String) { if (!warning.isEmpty()) logger!!.warn(warning) }
//
//}