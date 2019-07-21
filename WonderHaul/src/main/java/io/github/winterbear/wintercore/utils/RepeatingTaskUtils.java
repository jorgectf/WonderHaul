package io.github.winterbear.wintercore.utils;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.concurrent.Callable;

/**
 * Created by WinterBear on 06/06/2019.
 */
public class RepeatingTaskUtils {

    private static final Long SECOND_IN_TICKS = 20L;

    public static void everySeconds(int seconds, Callable<Boolean> r, JavaPlugin plugin){

        Long period = seconds*SECOND_IN_TICKS;

        new BukkitRunnable(){

            @Override
            public void run() {
                try {
                    if(!r.call()){
                        cancel();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }.runTaskTimer(plugin, SECOND_IN_TICKS, period);
    }


}
