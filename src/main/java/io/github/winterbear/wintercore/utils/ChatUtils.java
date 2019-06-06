package io.github.winterbear.wintercore.utils;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by WinterBear on 20/05/2019.
 */
public class ChatUtils {

    public static void broadcast(String message){

        Bukkit.getServer().broadcast(message, "");

    }

    public static String convert(String message){
        return StringUtils.replace(message, "&", "§");
    }

    public static void send(Player player, String message){
        player.sendMessage(message);
    }

    public static void send(CommandSender sender, String message){
        sender.sendMessage(message);
    }
}
