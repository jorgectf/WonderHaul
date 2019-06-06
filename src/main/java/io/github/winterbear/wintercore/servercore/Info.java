package io.github.winterbear.wintercore.servercore;

import org.bukkit.command.CommandExecutor;

/**
 * Created by WinterBear on 20/05/2019.
 */
public class Info {

    public static CommandExecutor website(){
        //return CommandRegistry.createCommand((s, c, a, g) -> ChatUtils.sendMessage(s, websiteText()));
        return null;
    }

    private static String websiteText(){
        return "";
    }

}
