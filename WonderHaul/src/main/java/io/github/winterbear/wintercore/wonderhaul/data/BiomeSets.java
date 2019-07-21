package io.github.winterbear.wintercore.wonderhaul.data;

import org.bukkit.block.Biome;

import java.util.Arrays;

/**
 * Created by WinterBear on 20/07/2019.
 */
public class BiomeSets {

    public static final BiomeSet ALL_BIOMES = new AllBiomes();

    public static final BiomeSet RARE_BIOMES = new ListBiomeSet(Arrays.asList(
            Biome.MODIFIED_GRAVELLY_MOUNTAINS,
            Biome.WOODED_BADLANDS_PLATEAU,
            Biome.DARK_FOREST_HILLS,
            Biome.TAIGA_MOUNTAINS,
            Biome.BADLANDS_PLATEAU,
            Biome.DESERT_LAKES,
            Biome.SHATTERED_SAVANNA,
            Biome.SNOWY_TAIGA_MOUNTAINS,
            Biome.MUSHROOM_FIELDS,
            Biome.MUSHROOM_FIELD_SHORE,
            Biome.ICE_SPIKES,
            Biome.MODIFIED_JUNGLE,
            Biome.GIANT_SPRUCE_TAIGA_HILLS,
            Biome.ERODED_BADLANDS,
            Biome.FROZEN_RIVER,
            Biome.BADLANDS,
            Biome.DEEP_FROZEN_OCEAN,
            Biome.FLOWER_FOREST
    ));

}
