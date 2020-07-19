package io.github.winterbear.wintercore.wonderhaul.Equipment;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import io.github.winterbear.wintercore.ConfigLoader;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;

/**
 * Created by WinterBear on 17/06/2019.
 */
public class Lore {

    public static void onEnable(JavaPlugin plugin){

    }

    private static RandomTieredDictionary dictionary;

    public static void enable(JavaPlugin plugin){
        Lore.reload(plugin);
    }

    public static void reload(JavaPlugin plugin){
        ConfigLoader.registerCustomConfig(plugin, "lore", "lore.yml");
        dictionary = new RandomTieredDictionary("lore", ConfigLoader.getConfig("lore"));
    }

    public static Optional<String> generateLore(Tier tier){
        if(dictionary == null){
            ChatUtils.warn("Lore dictionary not initialised.");
            return Optional.empty();
        } else {
            return dictionary.getRandomValue(tier);
        }
    }

}
