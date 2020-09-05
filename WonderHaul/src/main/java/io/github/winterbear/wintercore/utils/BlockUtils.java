package io.github.winterbear.wintercore.utils;


import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import java.util.EnumSet;
import java.util.Set;

/**
 * Created by WinterBear on 01/08/2020.
 */
public class BlockUtils {

    private static Set<Material> WATER_BLOCKS = EnumSet.of(Material.WATER);

    public static boolean isUnderwater(Location location){
        World world = location.getWorld();
        return WATER_BLOCKS.contains(world.getBlockAt(location).getType());
    }

    public static Location getBlockCentre(Location block){

        return new Location(block.getWorld(), block.getX() + 0.5, block.getY() + 0.5, block.getZ() + 0.5);


    }

    public static Location getBlockCentre(Location block, double offsetX, double offsetY, double offsetZ){

        return new Location(block.getWorld(), block.getX() + 0.5 + offsetX, block.getY() + 0.5 + offsetY, block.getZ() + 0.5 + offsetZ);


    }


}
