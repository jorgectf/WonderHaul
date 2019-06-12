package io.github.winterbear.wintercore.utils;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

/**
 * Created by WinterBear on 20/05/2019.
 */
public class ChatUtils {

    private static final String MODIFIER_UNCONVERTED = "&";

    private static final String MODIFIER_CONVERTED = "§";

    private static final String REGEX_COLOR_CODES = "[&§][abcdef0123456789lmnok]";

    public static final String EMPTY_STRING = "";

    public static void broadcast(String message){
        Bukkit.getServer().broadcast(message, EMPTY_STRING);
    }

    public static String uncolored(String text){
        return text.replaceAll(REGEX_COLOR_CODES, EMPTY_STRING);
    }

    public static String convert(String message){
        return StringUtils.replace(message, MODIFIER_UNCONVERTED, MODIFIER_CONVERTED);
    }

    public static void send(Player player, String message){
        player.sendMessage(convert(message));
    }

    public static void error(Player player, String message){
        player.sendMessage(convert("&cError&8: &7" + message));
    }

    public static void send(CommandSender sender, String message){
        sender.sendMessage(convert(message));
    }

    public static void error(CommandSender sender, String message){
        sender.sendMessage(convert("&cError&8: &7" + message));
    }
}
