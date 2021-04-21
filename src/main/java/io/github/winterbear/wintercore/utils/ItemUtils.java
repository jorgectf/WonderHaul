package io.github.winterbear.wintercore.utils;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

/**
 * Created by WinterBear on 06/06/2019.
 */
public class ItemUtils {

    public static void safelyGiveItem(final Player player, final ItemStack item){
        if(player.getInventory().firstEmpty() == -1){
            player.getWorld().dropItemNaturally(player.getLocation(), item.clone());
        } else {
            player.getInventory().addItem(item.clone());
        }

    }

    public static void dropNaturally(Location location, ItemStack item){

        location.getWorld().dropItemNaturally(location, item.clone());

    }

    public static void dropNaturally(Location location, Collection<ItemStack> items){
        items.forEach(i -> dropNaturally(location, i));
    }

    public static ItemStack oneOf(ItemStack item){
        ItemStack clone = item.clone();
        clone.setAmount(1);
        return clone;
    }

}
