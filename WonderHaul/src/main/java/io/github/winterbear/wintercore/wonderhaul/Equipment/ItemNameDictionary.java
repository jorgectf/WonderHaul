package io.github.winterbear.wintercore.wonderhaul.Equipment;

import io.github.winterbear.wintercore.utils.ChatUtils;
import io.github.winterbear.wintercore.utils.RandomUtils;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WinterBear on 17/06/2019.
 */
public class ItemNameDictionary {

    private static Map<MaterialGroup, List<String>> dictionary;

    public ItemNameDictionary(YamlConfiguration config) {
        Map<MaterialGroup, List<String>> itemNames = new HashMap<>();
        for (MaterialGroup mat : MaterialGroup.values()) {
            itemNames.put(mat, config.getStringList(mat.name().toLowerCase()));
            if(itemNames.get(mat).isEmpty()){
                ChatUtils.warn("Material " + mat + " has no configured prefixes.");
            }
        }
        this.dictionary = itemNames;
    }

    public String getRandomName(MaterialGroup material){
        if(!dictionary.containsKey(material) || dictionary.get(material) == null || dictionary.get(material).isEmpty()){
            ChatUtils.warn("No names configured for material " + material.name() + ". Using default name.");
            return material.name();
        }
        return RandomUtils.getRandomElementOf(dictionary.get(material));
    }



}
