package io.github.winterbear.wintercore.wonderhaul.equipment.generators;

import io.github.winterbear.wintercore.utils.RandomUtils;
import io.github.winterbear.wintercore.wonderhaul.equipment.Tier;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

/**
 * Created by WinterBear on 20/07/2019.
 */
public class RareGenerator implements Generator {

    private Tier TIER = Tier.RARE;

    private List<Material> mats = Arrays.asList(
            Material.DIAMOND_PICKAXE,
            Material.DIAMOND_AXE,
            Material.DIAMOND_HOE,
            Material.DIAMOND_SHOVEL,
            Material.DIAMOND_SWORD,
            Material.DIAMOND_BOOTS,
            Material.DIAMOND_CHESTPLATE,
            Material.DIAMOND_HELMET,
            Material.DIAMOND_LEGGINGS,
            Material.NETHERITE_AXE,
            Material.NETHERITE_HOE,
            Material.NETHERITE_SHOVEL,
            Material.NETHERITE_SWORD,
            Material.NETHERITE_BOOTS,
            Material.NETHERITE_CHESTPLATE,
            Material.NETHERITE_HELMET,
            Material.NETHERITE_LEGGINGS,
            Material.BOW,
            Material.SHIELD,
            Material.ELYTRA
    );


    @Override
    public ItemStack create() {
        Material mat = RandomUtils.getRandomElementOf(mats);
        return GeneratorCommons.generateDefault(mat, TIER);
    }

    @Override
    public String getName() {
        return "Rare Generator";
    }
}
