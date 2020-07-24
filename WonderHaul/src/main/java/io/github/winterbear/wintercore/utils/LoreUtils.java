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

    public static void addLore(ItemStack item, List<String> lore, Player player){
        lore.forEach(l -> addLoreLine(item, l));
        player.updateInventory();
    }

    public static void addLoreLine(ItemStack item, String lore){
        List<String> itemLore = getLore(item);
        itemLore.add(ChatUtils.format(lore));
        setLore(item, itemLore);
    }

    public static void addMultiLineLore(ItemStack item, String lore){
        List<String> itemLore = getLore(item);
        itemLore.addAll(getMultiline(lore, 26, 0));
        setLore(item, itemLore);
    }

    public static List<String> getMultiline(String string, int lineLength, int indentCount){
        List<String> result = new ArrayList<>();
        String[] characters = string.split("");
        String color = "&7";
        String format = "";
        String indent = "";
        StringBuilder line = new StringBuilder();
        line.append(color);
        int maxLineLength = 0;
        for(int i = 0; i < indentCount; i++){
            indent = indent + " ";
        }
        for(int i = 0; i < characters.length; i++){
            String c = characters[i];
            if(c.equals("&") && i + 1 < characters.length){
                String modifier = characters[i + 1];
                if(ChatUtils.COLOR_CODES.contains(modifier)){
                    color = "&" + modifier;
                } else if (ChatUtils.FORMAT_CODES.contains(modifier)){
                    format = "&" + modifier;
                } else if (ChatUtils.RESET.equals(modifier)){
                    color = "&7";
                    format = "";
                }
            }
            line.append(c);
            if(line.length() > lineLength && c.equals(" ")){
                //Start new line
                String newLine = ChatUtils.format(line.toString());
                int currentLineLength = newLine.length();
                int remainingChars = characters.length - (i + 1);
                if((currentLineLength + remainingChars) > maxLineLength){
                    result.add(indent + newLine);
                    line = new StringBuilder();
                    line.append(color);
                    line.append(format);
                }
                if(currentLineLength > maxLineLength){
                    maxLineLength = currentLineLength;
                }
            }
        }
        result.add(indent + ChatUtils.format(line.toString()));

        return result;
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
