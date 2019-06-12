package io.github.winterbear.wintercore;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class ConfigLoader {

    private static HashMap<String, YamlConfiguration> customConfigs = new HashMap<>();

    public static void registerCustomConfig(JavaPlugin plugin, String name){
        File configFile = new File(plugin.getDataFolder(), name);

        if(!configFile.exists()){
            configFile.getParentFile().mkdirs();
            plugin.saveResource(name, false);
        }

        YamlConfiguration config = new YamlConfiguration();
        try {
            config.load(configFile);
        } catch (InvalidConfigurationException | IOException e) {
            plugin.getServer().getLogger().severe("WonderHaul configuration failed to load! Something is wrong.");
            e.printStackTrace();
        }
    }

    public static YamlConfiguration getConfig(String reference){
        return customConfigs.get(reference);
    }

}
