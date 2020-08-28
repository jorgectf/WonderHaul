package io.github.winterbear.WinterCoreUtils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WinterBear on 20/05/2019.
 */
public class ChatUtils {

    public static final Map<String, String> COLOR_NAMES = colorNames();

    private static Map<String, String> colorNames(){

        Map<String, String> colors = new HashMap<>();

        colors.put("Black", "&0");
        colors.put("Blue", "&1");
        colors.put("Green", "&2");
        colors.put("Cyan", "&3");
        colors.put("Red", "&4");
        colors.put("Purple", "&5");
        colors.put("Gold", "&6");
        colors.put("Gray", "&7");
        colors.put("Dark Gray", "&8");
        colors.put("Light Blue", "&9");
        colors.put("Lime", "&a");
        colors.put("Aqua", "&b");
        colors.put("Light Red", "&c");
        colors.put("Pink", "&d");
        colors.put("Yellow", "&e");
        colors.put("White", "&f");

        return colors;

    }

    private static final Character MODIFIER_UNCONVERTED = '&';

    private static final Character MODIFIER_CONVERTED = '§';

    private static final String REGEX_COLOR_CODES = "[&§][a-z,A-Z,0-9]";

    public static final List<String> COLOR_CODES = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f");

    public static final List<String> FORMAT_CODES = Arrays.asList("k", "l", "m", "n", "o");

    public static final String RESET = "r";

    public static final String EMPTY_STRING = "";

    private static final String PREFIX = "[WonderHaul] ";

    public static void broadcast(String message){
        Bukkit.getServer().broadcast(message, EMPTY_STRING);
    }

    public static String uncolored(String text){
        return ChatColor.stripColor(text.replaceAll(REGEX_COLOR_CODES, EMPTY_STRING));
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
