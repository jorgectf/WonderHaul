package io.github.winterbear.wintercore.utils;

import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

/**
 * Created by WinterBear on 20/05/2019.
 */
public class LoreUtils {

    public static void clearlore(ItemStack item){
        item.getItemMeta().getLore().clear();
    }

    public static void addLoreLine(ItemStack item, String lore){
        item.getItemMeta().getLore().add(lore);
    }

    public static void removeLoreLine(ItemStack item, int line){
        item.getItemMeta().getLore().remove(line);
    }

    public static void truncateLoreLine(ItemStack item, int line){
        while(item.getItemMeta().getLore().size() >= line){
            item.getItemMeta().getLore().remove(line);
        }
    }

}
