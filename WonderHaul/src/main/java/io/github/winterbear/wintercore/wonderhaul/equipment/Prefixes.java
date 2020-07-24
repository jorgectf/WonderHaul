package io.github.winterbear.wintercore.wonderhaul.equipment;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import io.github.winterbear.wintercore.ConfigLoader;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;

/**
 * Created by WinterBear on 17/06/2019.
 */
public class Prefixes {

    private static RandomTieredDictionary dictionary;

    public static void enable(JavaPlugin plugin){
        Prefixes.reload(plugin);
    }

    public static void reload(JavaPlugin plugin){
        ConfigLoader.registerCustomConfig(plugin, "prefixes", "prefixes.yml");
        dictionary = new RandomTieredDictionary("prefixes",ConfigLoader.getConfig("prefixes"));
    }

    public static Optional<String> generatePrefix(Tier tier){
        if(dictionary == null){
            ChatUtils.warn("Prefix dictionary not initialised.");
            return Optional.empty();
        } else {
            return dictionary.getRandomValue(tier);
        }
    }

}
