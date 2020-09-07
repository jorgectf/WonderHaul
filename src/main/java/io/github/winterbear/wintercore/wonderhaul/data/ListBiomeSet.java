package io.github.winterbear.wintercore.wonderhaul.data;

import org.bukkit.block.Biome;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by WinterBear on 07/07/2019.
 */
public class ListBiomeSet implements BiomeSet {

    private List<Biome> biomes;

    public ListBiomeSet(List<Biome> biomes){
        this.biomes = biomes;
    }


    @Override
    public boolean contains(Biome biome) {
        return this.biomes.contains(biome);
    }

    @Override
    public List<String> toSerializable() {
        return this.biomes.stream().map(Biome::name).collect(Collectors.toList());
    }

    @Override
    public List<Biome> contents() {
        return biomes;
    }
}
