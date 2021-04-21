package io.github.winterbear.wintercore.wonderhaul.data;

import org.bukkit.block.Biome;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by WinterBear on 07/07/2019.
 */
public class AllBiomes implements BiomeSet {


    @Override
    public boolean contains(Biome biome) {
        return true;
    }

    @Override
    public List<String> toSerializable() {
        return Arrays.asList("All");
    }

    @Override
    public List<Biome> contents() {
        return new ArrayList<>(Arrays.asList(Biome.values()));
    }
}
