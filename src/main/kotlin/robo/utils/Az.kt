package robo.utils

import java.io.File
import java.io.FileNotFoundException
import java.io.FileReader
import java.nio.file.Path
import java.nio.file.Paths
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

/**
 * The [Az] class is a general utility intended to provide a toolbox of stateless methods.
 *
 *
 * Primarily for my own convenience, and secondarily to shorten the names of common things, in an otherwise verbose language. Neat.
 */
object Az {

    val ON = "♥"
    val OFF = "♡"


    val pathHome: Path
        get() = Paths.get(System.getProperty("user.dir"))

    val trueFuture: CompletableFuture<Boolean>
        get() = CompletableFuture.completedFuture(true)

    fun plural(text: String, size: Int): String {
        return text + if (size > 1) "s" else ""
    }

    /**
     * Az#prLn(String...) is a shortcut method for printing to a row and breaking at the end of the line
     *
     * @param text the `String`, or collection of `Strings`, that will be print to the System console
     */
    @JvmStatic
    fun prLn(text: Array<String>) {
        Arrays.stream(text).forEach { s -> print("\n" + s) }
    }

    /**
     * Az#prSp(String...) is a shortcut method for printing to a row in the System console that does not end with a line break, but does end with a space.
     *
     * @param text the `String`, or collection of `Strings`, that will be printed to the System console
     */
    @JvmStatic
    fun prSp(text: String) {
        Arrays.stream(text).forEach { s -> print(String.format("%s ", s)) }
    }

    /**
     * Az#print(String...) is a shortcut method for printing to a row in the System console that does not end with a line break
     *
     * @param text the `String`, or collection of `Strings`, that will be print to the System console
     */
    @JvmStatic
    fun prnt(text: Array<String>) {
        Arrays.stream(text).forEach(Consumer<String> { print(it) })
    }


    /**
     * Az#input() is used to retrieve a `String` input from the System console
     *
     * @return input from the console as a String
     */
    fun input(): String {
        prnt(arrayOf("▶"))
        val str = Scanner(System.`in`).next()
        return str
    }


    /**
     * Gets an authentication token as a `String` from a remote directory
     *
     * @param path  each directory and finally the name of the file that contains the token to be read
     * @return the token as a `String`
     */
    fun getTokenFromFile(vararg path: String): String? {
        try {
            val pathToFile = File(Paths.get(System.getProperty("user.dir"), *path).toString())
            val `in` = Scanner(FileReader(pathToFile))
            val sb = StringBuilder()
            while (`in`.hasNext()) sb.append(`in`.next())
            `in`.close()
            return sb.toString()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }

        return null
    }

    fun timeMillis(): Long {
        return System.currentTimeMillis()
    }


    /**
     * Format time string.
     *
     * @param epochMilli the time as epoch milli
     * @return the formatted date and time as a `String`
     */
    fun time(epochMilli: Long): String {
        return DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(Locale.US)
            .withZone(ZoneId.systemDefault()).format(
                Instant.ofEpochMilli(epochMilli)
            )
    }

    fun time(): String {
        return DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(Locale.US)
            .withZone(ZoneId.systemDefault()).format(
                Instant.ofEpochMilli(timeMillis())
            )
    }


    /**
     * Table format a `String`.
     *
     * @param width the width of the table
     * @param text  the text to be formatted
     * @return the formatted table as a `String`
     */
    fun table(width: Int, vararg text: String): String {
        val column = StringBuilder()
        for (s in text) {
            column.append(s)
            for (n in 0 until width - s.length)
                column.append(" ")
        }
        return column.toString()
    }

    fun exit(code: Int, text: String) {
        println("\uD83D\uDED1 $text")
        System.exit(code)
    }

    fun addShutdownHook(text: String) {
        Runtime.getRuntime().addShutdownHook(Thread { println(text) })
    }


    /**
     * Loop runnable.
     *
     * @param interval the interval
     * @param runnable the runnable
     */
    fun loopRun(interval: Long, runnable: Runnable) {
        Timer().schedule(object : TimerTask() {
            override fun run() {
                runnable.run()
            }
        }, 1000, interval)
    }

}
