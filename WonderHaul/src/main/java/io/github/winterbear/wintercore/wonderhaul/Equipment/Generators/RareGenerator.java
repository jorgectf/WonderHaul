package io.github.winterbear.wintercore.wonderhaul.Equipment.Generators;

import io.github.winterbear.wintercore.utils.RandomUtils;
import io.github.winterbear.wintercore.wonderhaul.Equipment.Tier;
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
