package io.github.winterbear.wintercore.utils;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

/**
 * Created by WinterBear on 12/06/2019.
 */
public class SoundUtils {

    public static void playSound(Player p, Sound sound){
        p.playSound(p.getLocation(), sound,100F, 100F);
    }

}
