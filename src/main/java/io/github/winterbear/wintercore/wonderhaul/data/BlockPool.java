package io.github.winterbear.wintercore.wonderhaul.data;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import io.github.winterbear.wintercore.wonderhaul.dropper.Chance;
import io.github.winterbear.wintercore.wonderhaul.equipment.generators.Generator;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WinterBear on 14/12/2020.
 */
public class BlockPool extends BasicPool<Block> {


    private Map<Material, Chance> blockChances = new HashMap<>();

    public BlockPool(String name, Chance globalChance, List<String> enabledWorlds, BiomeSet enabledBiomes, Generator generator) {
        this.globalChance = globalChance;
        this.enabledWorlds = enabledWorlds;
        this.enabledBiomes = enabledBiomes;
        this.generator = generator;
        this.name = name;
    }

    public BlockPool blockChance(Material block, double chance){
        this.blockChances.put(block, new Chance(chance));
        return this;
    }

    public Map<Material, Chance> getBlockChances() {
        return blockChances;
    }

    @Override
    public Chance getGlobalChance() {
        return globalChance;
    }

    @Override
    public BiomeSet getEnabledBiomes() {
        return enabledBiomes;
    }

    @Override
    public boolean roll(Block block, long luckModifier){
        if(blockChances.containsKey(block.getType())){
            ChatUtils.info(this.name + " rolling chance " + blockChances.get(block.getType()));
            return blockChances.get(block.getType()).rollModified(luckModifier);
        }
        return false;
    }

    @Override
    public Generator getGenerator() {
        return generator;
    }


}
