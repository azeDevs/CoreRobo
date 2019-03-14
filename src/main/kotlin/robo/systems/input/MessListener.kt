package robo.systems.input

import robo.models.posts.Mess

interface MessListener {
    fun onMess(mess: Mess)
}
