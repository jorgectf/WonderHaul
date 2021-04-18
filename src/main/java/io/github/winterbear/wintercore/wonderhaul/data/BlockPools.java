package io.github.winterbear.wintercore.wonderhaul.data;

import io.github.winterbear.wintercore.wonderhaul.dropper.Chance;
import io.github.winterbear.wintercore.wonderhaul.equipment.generators.Generators;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WinterBear on 14/12/2020.
 */
public class BlockPools {

    public static List<BlockPool> getPools(){
        List<BlockPool> defaultPools = new ArrayList<>();

        defaultPools.add(PoolBuilder.create("Crate I")
        .setGenerator(Generators.CRATE_I)
        .setEnabledBiomes(BiomeSets.ALL_BIOMES)
        .setGlobalChance(new Chance(100.0))
        .createBlockPool()
        .blockChance(Material.DIAMOND_ORE, 0.025)
        .blockChance(Material.EMERALD_ORE, 0.125)
        .blockChance(Material.NETHER_QUARTZ_ORE, 0.005)
        .blockChance(Material.IRON_ORE, 0.01)
        .blockChance(Material.GOLD_ORE, 0.0015)
        .blockChance(Material.LAPIS_ORE, 0.05)
        .blockChance(Material.CRYING_OBSIDIAN, 0.06)
        .blockChance(Material.ANCIENT_DEBRIS, 0.08)
        .blockChance(Material.SPRUCE_LOG, 0.01)
        .blockChance(Material.OAK_LOG, 0.01)
        .blockChance(Material.BIRCH_LOG, 0.01)
        .blockChance(Material.JUNGLE_LOG, 0.01)
        .blockChance(Material.ACACIA_LOG, 0.01)
        .blockChance(Material.DARK_OAK_LOG, 0.01)
        .blockChance(Material.WARPED_STEM, 0.01)
        .blockChance(Material.CRIMSON_STEM, 0.01)
        .blockChance(Material.WHEAT, 0.01)
        .blockChance(Material.MELON, 0.02)
        .blockChance(Material.BEETROOTS, 0.02)
        .blockChance(Material.CARROT, 0.02)
        .blockChance(Material.SEA_LANTERN, 0.02));

        defaultPools.add(PoolBuilder.create("Crate II")
        .setGenerator(Generators.CRATE_II)
        .setEnabledBiomes(BiomeSets.ALL_BIOMES)
        .setGlobalChance(new Chance(20.0))
        .createBlockPool()
        .blockChance(Material.DIAMOND_ORE, 0.025)
        .blockChance(Material.EMERALD_ORE, 0.125)
        .blockChance(Material.NETHER_QUARTZ_ORE, 0.005)
        .blockChance(Material.IRON_ORE, 0.01)
        .blockChance(Material.GOLD_ORE, 0.0015)
        .blockChance(Material.LAPIS_ORE, 0.05)
        .blockChance(Material.CRYING_OBSIDIAN, 0.06)
        .blockChance(Material.ANCIENT_DEBRIS, 0.08)
        .blockChance(Material.SPRUCE_LOG, 0.01)
        .blockChance(Material.OAK_LOG, 0.01)
        .blockChance(Material.BIRCH_LOG, 0.01)
        .blockChance(Material.JUNGLE_LOG, 0.01)
        .blockChance(Material.ACACIA_LOG, 0.01)
        .blockChance(Material.DARK_OAK_LOG, 0.01)
        .blockChance(Material.WARPED_STEM, 0.01)
        .blockChance(Material.CRIMSON_STEM, 0.01)
        .blockChance(Material.WHEAT, 0.01)
        .blockChance(Material.MELON, 0.02)
        .blockChance(Material.BEETROOTS, 0.02)
        .blockChance(Material.CARROT, 0.02)
        .blockChance(Material.SEA_LANTERN, 0.02));

        return defaultPools;
    }

}
