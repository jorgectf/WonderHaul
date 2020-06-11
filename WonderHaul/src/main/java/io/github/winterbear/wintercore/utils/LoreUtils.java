package io.github.winterbear.wintercore.utils;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WinterBear on 20/05/2019.
 */
public class LoreUtils {

    public static void clearLore(ItemStack item, Player player){
        clearLore(item);
        player.updateInventory();
    }

    public static void clearLore(ItemStack item){
        setLore(item, null);
    }

    public static void addLoreLine(ItemStack item, String lore, Player player){
        addLoreLine(item, lore);
        player.updateInventory();
    }

    public static void addLoreLine(ItemStack item, String lore){
        List<String> itemLore = getLore(item);
        itemLore.add(ChatUtils.format(lore));
        setLore(item, itemLore);
    }


    public static void removeLoreLine(ItemStack item, int line, Player player){
        removeLoreLine(item, line);
        player.updateInventory();
    }

    public static void removeLoreLine(ItemStack item, int line){
        List<String> itemLore = getLore(item);
        itemLore.remove(line);
        setLore(item, itemLore);
    }

    private static void setLore(ItemStack item, List<String> lore){
        ItemMeta meta = initialiseMeta(item);
        meta.setLore(lore);
        item.setItemMeta(meta);
    }

    private static ItemMeta initialiseMeta(ItemStack item){
        ItemMeta meta = item.getItemMeta();
        if (meta == null)
            meta = Bukkit.getItemFactory().getItemMeta(item.getType());
        return meta;
    }

    public static List<String> getLore(ItemStack item){
        ItemMeta meta = initialiseMeta(item);
        List<String> itemLore = meta.hasLore() ? new ArrayList<>(meta.getLore()) : new ArrayList<>();
        return itemLore;
    }



    public static void truncateLoreLine(ItemStack item, int line, Player player){
        truncateLoreLine(item, line);
        player.updateInventory();
    }

    public static void truncateLoreLine(ItemStack item, int line){
        List<String> itemLore = getLore(item);
        while(itemLore.size() >= line){
            itemLore.remove(line);
        }
        setLore(item, itemLore);
    }


}
