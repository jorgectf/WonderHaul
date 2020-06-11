package io.github.winterbear.WinterCoreUtils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by WinterBear on 20/05/2019.
 */
public class ChatUtils {

    private static final Character MODIFIER_UNCONVERTED = '&';

    private static final Character MODIFIER_CONVERTED = '§';

    private static final String REGEX_COLOR_CODES = "[&§][abcdef0123456789lmnok]";

    public static final String EMPTY_STRING = "";

    private static final String PREFIX = "[WonderHaul] ";

    public static void broadcast(String message){
        Bukkit.getServer().broadcast(message, EMPTY_STRING);
    }

    public static String uncolored(String text){
        return text.replaceAll(REGEX_COLOR_CODES, EMPTY_STRING);
    }

    public static String format(String message){
        return ChatColor.translateAlternateColorCodes(MODIFIER_UNCONVERTED, message);
    }

    public static void warn(String message){
        Bukkit.getServer().getLogger().warning(PREFIX + message);
    }

    public static void info(String message){
        Bukkit.getServer().getLogger().info(PREFIX + message);
    }

    public static void error(String message){
        Bukkit.getServer().getLogger().severe(PREFIX + message);
    }

    public static void send(Player player, String message){
        player.sendMessage(format(message));
    }

    public static void error(Player player, String message){
        player.sendMessage(format("&cError&8: &7" + message));
    }

    public static void send(CommandSender sender, String message){
        sender.sendMessage(format(message));
    }

    public static void error(CommandSender sender, String message){
        sender.sendMessage(format("&cError&8: &7" + message));
    }
}
