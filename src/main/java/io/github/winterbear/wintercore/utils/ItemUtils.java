package io.github.winterbear.wintercore.utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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

}
