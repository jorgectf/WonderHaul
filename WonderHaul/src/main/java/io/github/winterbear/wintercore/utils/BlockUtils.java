package io.github.winterbear.wintercore.utils;


import org.bukkit.Location;

/**
 * Created by WinterBear on 01/08/2020.
 */
public class BlockUtils {


    public static Location getBlockCentre(Location block){

        return new Location(block.getWorld(), block.getX() + 0.5, block.getY() + 0.5, block.getZ() + 0.5);


    }

    public static Location getBlockCentre(Location block, double offsetX, double offsetY, double offsetZ){

        return new Location(block.getWorld(), block.getX() + 0.5 + offsetX, block.getY() + 0.5 + offsetY, block.getZ() + 0.5 + offsetZ);


    }


}
