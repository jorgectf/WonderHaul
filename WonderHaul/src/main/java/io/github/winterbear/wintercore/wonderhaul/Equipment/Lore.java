package io.github.winterbear.wintercore.wonderhaul.Equipment;

import io.github.winterbear.wintercore.ConfigLoader;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by WinterBear on 17/06/2019.
 */
public class Lore {

    public static void onEnable(JavaPlugin plugin){
        ConfigLoader.registerCustomConfig(plugin, "lore", "lore.yml");
    }


}
