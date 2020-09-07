package io.github.winterbear.wintercore.wonderhaul.equipment.generators;

import io.github.winterbear.wintercore.utils.RandomUtils;
import io.github.winterbear.wintercore.wonderhaul.equipment.Tier;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

/**
 * Created by WinterBear on 07/07/2019.
 */
public class UnusualGenerator implements Generator{



    private Tier TIER = Tier.UNUSUAL;

    private List<Material> mats = Arrays.asList(
            Material.GOLDEN_PICKAXE,
            Material.GOLDEN_AXE,
            Material.GOLDEN_HOE,
            Material.GOLDEN_SHOVEL,
            Material.GOLDEN_SWORD,
            Material.GOLDEN_BOOTS,
            Material.GOLDEN_CHESTPLATE,
            Material.GOLDEN_HELMET,
            Material.GOLDEN_LEGGINGS,
            Material.DIAMOND_PICKAXE,
            Material.DIAMOND_AXE,
            Material.DIAMOND_HOE,
            Material.DIAMOND_SHOVEL,
            Material.DIAMOND_SWORD,
            Material.DIAMOND_BOOTS,
            Material.DIAMOND_CHESTPLATE,
            Material.DIAMOND_HELMET,
            Material.DIAMOND_LEGGINGS,
            Material.CHAINMAIL_BOOTS,
            Material.CHAINMAIL_CHESTPLATE,
            Material.CHAINMAIL_LEGGINGS,
            Material.CHAINMAIL_HELMET,
            Material.FISHING_ROD,
            Material.TRIDENT,
            Material.CROSSBOW,
            Material.BOW,
            Material.SHIELD,
            Material.SHEARS
    );


    @Override
    public ItemStack create() {
        Material mat = RandomUtils.getRandomElementOf(mats);
        return GeneratorCommons.generateDefault(mat, TIER);
    }

    @Override
    public String getName() {
        return "Unusual Generator";
    }
}
