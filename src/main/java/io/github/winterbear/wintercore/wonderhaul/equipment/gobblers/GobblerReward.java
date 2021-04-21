package io.github.winterbear.wintercore.wonderhaul.equipment.gobblers;

import io.github.winterbear.wintercore.wonderhaul.dropper.Chance;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Created by WinterBear on 27/09/2020.
 */
public class GobblerReward {

    private Chance chance;

    private ItemStack reward;

    public GobblerReward(Float chance, Material reward) {
        this.chance = new Chance(chance);
        this.reward = new ItemStack(reward);
    }

    public GobblerReward(Chance chance, ItemStack reward) {
        this.chance = chance;
        this.reward = reward;
    }

    public Chance getChance() {
        return chance;
    }

    public ItemStack getReward() {
        return reward;
    }
}
