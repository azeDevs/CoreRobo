package bot;

import bot.kits.RolesKit;
import bot.kits.DiceKit;
import robo.Bot;

public class BotMain {

    public static void main(String[] args) {
        Bot bot = new Bot(
                new RolesKit(),
                new DiceKit());
        bot.startBot(args[0]);
    }

}
