package robo.models


import robo.utils.prLn

class Param {

    private var value: String = ""

    val isInt: Boolean get() = toInt() != null

    val isLong: Boolean get() = toLong() != null

    constructor() {
        this.value = "0"
    }

    constructor(value: Long) {
        this.value = value.toString()
    }

    constructor(value: Int) {
        this.value = value.toString()
    }

    constructor(value: String) {
        this.value = value
    }

    private fun logParseFailure() {
        prLn(String.format("Failed to parse Param: %s", value))
    }


    // String

    fun toStr(): String? {
        return value
    }

    fun filtersTo(regex: String, comparison: String): Boolean {
        return value.replace(regex.toRegex(), "").equals(comparison, ignoreCase = true)
    }


    // Integer

    fun toInt(min: Int, max: Int): Int? {
        return if (isInt)
            Math.min(Math.max(toInt()!!, min), max)
        else
            null
    }

    fun toInt(defaultTo: Int): Int? {
        return if (toInt() != null)
            toInt()
        else
            defaultTo
    }

    fun toInt(): Int? {
        var result: Int? = null
        try {
            result = Integer.valueOf(value.replace("[^\\d]".toRegex(), ""))
        } catch (e: NumberFormatException) {
            logParseFailure()
        }

        return result
    }


    // Long

    fun toLong(): Long? {
        var result: Long? = null
        try {
            result = java.lang.Long.valueOf(value.replace("[^\\d]".toRegex(), ""))
        } catch (e: NumberFormatException) {
            logParseFailure()
        }

        return result
    }

    fun toLong(defaultTo: Long): Long? {
        return if (toLong() != null)
            toLong()
        else
            defaultTo
    }


    // Double

    fun toDouble(): Double? {
        var result: Double? = null
        try {
            result = java.lang.Double.valueOf(value.replace("[^\\d]".toRegex(), ""))
        } catch (e: NumberFormatException) {
            logParseFailure()
        }

        return result
    }

}
