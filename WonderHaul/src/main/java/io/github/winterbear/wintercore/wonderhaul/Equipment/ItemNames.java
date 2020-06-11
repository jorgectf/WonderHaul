package io.github.winterbear.wintercore.wonderhaul.Equipment;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import io.github.winterbear.wintercore.ConfigLoader;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;

/**
 * Created by WinterBear on 17/06/2019.
 */
public class ItemNames {

    public static ItemNameDictionary dictionary;

    public static void enable(JavaPlugin plugin){
        ItemNames.reload(plugin);
    }

    public static void reload(JavaPlugin plugin){
        ConfigLoader.registerCustomConfig(plugin, "itemnames", "itemnames.yml");
        dictionary = new ItemNameDictionary(ConfigLoader.getConfig("itemnames"));
    }

    public static Optional<String> getItemName(MaterialGroup group){
        if(dictionary == null){
            ChatUtils.warn("Item Name dictionary not initialised.");
            return Optional.empty();
        } else {
            return Optional.of(dictionary.getRandomName(group));
        }
    }


}
