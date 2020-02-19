package bot

import bot.kits.DiceKit
import bot.kits.PilotKit
import robo.Robo

object BotMain {
    @JvmStatic
    fun main(args: Array<String>) {
        val robo = Robo("robo")
        robo.installSubKits(PilotKit(), DiceKit())
        robo.startBot(args[0])
    }
}