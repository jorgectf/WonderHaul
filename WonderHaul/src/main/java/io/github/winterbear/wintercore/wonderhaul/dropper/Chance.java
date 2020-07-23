package io.github.winterbear.wintercore.wonderhaul.dropper;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import io.github.winterbear.wintercore.utils.RandomUtils;

/**
 * Created by WinterBear on 07/07/2019.
 */
public class Chance {

    double chance;

    public static boolean roll(double percentage){
        boolean pass = RandomUtils.getDoubleBetween(0, 1) <= percentage / 100.0;
        ChatUtils.info("Rolled: " + pass + " for chance " + percentage);
        return pass;
    }

    public Chance(double percentage){
        this.chance = percentage;
    }

    public boolean roll(){
        return (roll(chance));
    }

    public boolean rollModified(double modifier){
        return (roll(chance * modifier));
    }




}
