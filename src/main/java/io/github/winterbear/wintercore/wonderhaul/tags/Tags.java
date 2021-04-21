package io.github.winterbear.wintercore.wonderhaul.tags;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WinterBear on 26/05/2020.
 */
public class Tags {

    public static final String PREFIX = ChatColor.of("#ffdd54") + "✦ &7Tag&8: ";

    public static final Tag LORE = new LoreTag();

    public static final Tag REPAIR = new RepairTag();

    public static final Tag BOOST = new BoostTag();

    public static final Tag MIMIC = new MimicTag();

    public static final Tag COLOR = new ColorTag();

    private static Map<String, Tag> tagMap = createTagMap();

    private static TagApplicationListener listener;

    public static void registerListeners(JavaPlugin plugin){
        listener = new TagApplicationListener(plugin);
    }

    private static Map<String, Tag> createTagMap(){
        Map<String, Tag> map = new HashMap<>();
        map.put("Repair Tag", REPAIR);
        map.put("Boost Tag", BOOST);
        map.put("Mimic Tag", MIMIC);
        map.put("Lore Tag", LORE);
        map.put("Color Tag", COLOR);
        return map;
    }

    public static Tag get(String reference){
        return tagMap.get(reference);
    }


}
