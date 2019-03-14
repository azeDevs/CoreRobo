package robo.systems.output

import robo.models.posts.KitText

class Status {

    private var color: Int = 0
    private var text: KitText = KitText()
    val formedString: String
        get() = text.toFormedString()

    constructor() {
        this.color = 0
        this.text = KitText()
    }

    constructor(color: Int, text: String) {
        this.color = color
        this.text = KitText(text)
    }

    fun setColor(color: Int): Status {
        this.color = color
        return this
    }

    fun getColor(): Int {
        return color
    }

    fun setText(text: KitText): Status {
        this.text = text
        return this
    }

    fun getText(): KitText {
        return text
    }

    companion object {

        val RED = -1
        val YELLOW = 0
        val GREEN = 1
    }

}
