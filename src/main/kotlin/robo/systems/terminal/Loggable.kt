package robo.systems.terminal

import robo.models.posts.KitLog

interface Loggable {
    fun onLog(): KitLog
}
