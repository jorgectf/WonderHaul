package io.github.winterbear.wintercore;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * Created by WinterBear on 16/07/2017.
 */
public interface CommandHandler {

    public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3);
}
