package io.github.winterbear.wintercore.wonderhaul.data;

import org.bukkit.block.Biome;

import java.util.List;

/**
 * Created by WinterBear on 07/07/2019.
 */
public interface BiomeSet {

    public boolean contains(Biome biome);

    public List<String> toSerializable();



}
