package io.github.winterbear.wintercore.wonderhaul.packs;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WinterBear on 22/11/2020.
 */
public class Packs {

    public static final List<PackListener> PACKS = new ArrayList<>();

    public static void registerAll(JavaPlugin plugin){
        PACKS.add(new ExperiencePackListener(plugin));
    }

}
