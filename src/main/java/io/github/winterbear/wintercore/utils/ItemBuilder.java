package io.github.winterbear.wintercore.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by WinterBear on 30/09/2018.
 */
public class ItemBuilder {

    public static ItemStack createItem(String name, Material mat){
        return createItem(name, null, mat);
    }

    public static ItemStack createItem(String name, String lore, Material mat) {
        ItemStack i = new ItemStack(mat, 1);
        ItemMeta iMeta = i.getItemMeta();
        iMeta.setDisplayName(name);
        if(lore != null){
            iMeta.setLore(buildLore(lore));
        }
        i.setItemMeta(iMeta);
        return i;
    }

    private static List<String> buildLore(String toParse){
        return Arrays.stream(toParse.split("\\|\\|"))
                .map(s -> ChatColor.translateAlternateColorCodes('&' , s))
                .collect(Collectors.toList());
    }

}
