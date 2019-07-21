package io.github.winterbear.wintercore.wonderhaul.Dropper;

import io.github.winterbear.wintercore.utils.RandomUtils;

/**
 * Created by WinterBear on 07/07/2019.
 */
public class Chance {

    double chance;

    public Chance(double percentage){
        this.chance = percentage;
    }

    public boolean roll(){
        return (RandomUtils.getDoubleBetween(0, 1) <= chance);
    }

    public boolean roll(double modifier){
        return (RandomUtils.getDoubleBetween(0, 1) <= (chance * modifier));
    }




}
