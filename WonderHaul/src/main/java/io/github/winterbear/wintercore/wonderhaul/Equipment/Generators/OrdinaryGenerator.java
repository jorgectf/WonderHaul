package io.github.winterbear.wintercore.wonderhaul.Equipment.Generators;

import io.github.winterbear.wintercore.utils.RandomUtils;
import io.github.winterbear.wintercore.wonderhaul.Equipment.Tier;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

/**
 * Created by WinterBear on 17/06/2019.
 */
public class OrdinaryGenerator implements  Generator{

    private Tier TIER = Tier.ORDINARY;

    private List<Material> mats = Arrays.asList(
            Material.IRON_PICKAXE,
            Material.IRON_AXE,
            Material.IRON_HOE,
            Material.IRON_SHOVEL,
            Material.IRON_SWORD,
            Material.IRON_BOOTS,
            Material.IRON_CHESTPLATE,
            Material.IRON_HELMET,
            Material.IRON_HELMET,
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
        return "Ordinary Generator";
    }
}
