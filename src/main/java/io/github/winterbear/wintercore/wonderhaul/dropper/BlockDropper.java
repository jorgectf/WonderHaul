package io.github.winterbear.wintercore.wonderhaul.dropper;

import io.github.winterbear.wintercore.wonderhaul.data.BlockPools;
import org.bukkit.Chunk;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by WinterBear on 07/07/2019.
 */
public class BlockDropper {

    public static List<ItemStack> generate(Chunk chunk, Biome biome, Block block, double luckModifier){
        if(!blockIsValid(block)){
            return Collections.EMPTY_LIST;
        }

        return BlockPools.getPools()
                .stream()
                .filter(pool -> pool.getGlobalChance().roll()) //Global pool chance
                .filter(pool -> pool.getEnabledBiomes().contains(biome)) //Biome is valid
                .filter(pool -> pool.getBlockChances().containsKey(block.getType())) //Mob is valid
                .filter(pool -> pool.getBlockChances().get(block.getType()).rollModified(luckModifier)) //Roll mob chance
                .map(p -> p.getGenerator().create())
                .collect(Collectors.toList());
    }

    private static boolean blockIsValid(Block block){
        //TODO
        return false;
    }
}
