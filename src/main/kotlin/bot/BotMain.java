package bot;

import bot.kits.DiceKit;
import bot.kits.RolesKit;
import robo.Mask;

public class BotMain {

    public static void main(String[] args) {
        Mask mask = new Mask(
                new RolesKit(),
                new DiceKit()
        );
        mask.startBot(args[0]);
    }

}
