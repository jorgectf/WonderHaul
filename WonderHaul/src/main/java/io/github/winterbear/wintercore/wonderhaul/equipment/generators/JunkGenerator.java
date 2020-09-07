package io.github.winterbear.wintercore.wonderhaul.equipment.generators;

import io.github.winterbear.wintercore.utils.RandomUtils;
import io.github.winterbear.wintercore.wonderhaul.equipment.Tier;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

/**
 * Created by WinterBear on 17/06/2019.
 */
public class JunkGenerator implements Generator {

    private Tier TIER = Tier.JUNK;

    private List<Material> mats = Arrays.asList(
            Material.STONE_PICKAXE,
            Material.STONE_AXE,
            Material.STONE_HOE,
            Material.STONE_SHOVEL,
            Material.STONE_SWORD,
            Material.LEATHER_BOOTS,
            Material.LEATHER_CHESTPLATE,
            Material.LEATHER_HELMET,
            Material.LEATHER_HELMET,
            Material.BOW,
            Material.SHEARS,
            Material.CARROT_ON_A_STICK,
            Material.FLINT_AND_STEEL
    );


    @Override
    public ItemStack create()  {
        Material mat = RandomUtils.getRandomElementOf(mats);
        return GeneratorCommons.generateDefault(mat, TIER);
    }

    @Override
    public String getName() {
        return "Junk Generator";
    }

}
