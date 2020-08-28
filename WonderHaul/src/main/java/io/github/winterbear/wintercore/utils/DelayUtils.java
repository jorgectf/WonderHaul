package io.github.winterbear.wintercore.utils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by WinterBear on 01/08/2020.
 */
public class DelayUtils {



    public static void after(int ticks, Runnable r, JavaPlugin plugin){

        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, r, ticks);

    }


}
