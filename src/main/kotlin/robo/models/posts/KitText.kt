package robo.models.posts

import robo.abstracts.Form
import java.util.*

/**
 * Handles pre/post formatting of text.
 *
 * @author  aze
 */
class KitText @JvmOverloads constructor(string: String = "") {

    private val sb: StringBuilder
    private val forms: MutableMap<Int, String>

    init {
        this.forms = HashMap()
        this.sb = StringBuilder(string)
    }

    fun form(formString: String): KitText {
        forms[sb.length] = formString
        return this
    }

    fun add(string: String): KitText {
        sb.append(string)
        return this
    }

    fun add(format: String, vararg strings: String): KitText {
        sb.append(String.format(format, *strings))
        return this
    }

    fun toFormedString(): String {
        val length = intArrayOf(0)
        val out = StringBuilder(sb.toString())
        forms.forEach { index, form ->
            out.insert(index + length[0], form)
            length[0] += form.length
        }
        return out.toString()
    }

    fun toFormlessString(): String {
        val length = intArrayOf(0)
        val out = StringBuilder(sb.toString())
        forms.forEach { index, form -> if (form == Form.CODE) out.insert(index + length[0]++, "\n") }
        return out.toString()
    }

}
