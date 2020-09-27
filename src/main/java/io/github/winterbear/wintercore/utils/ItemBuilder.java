package io.github.winterbear.wintercore.utils;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import io.github.winterbear.wintercore.wonderhaul.ItemCategory;
import net.md_5.bungee.api.ChatColor;
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

    private TexturedHead texture;
    private String displayName;
    private ItemCategory itemCategory;
    private String itemType;
    private ChatColor color;
    private ColorLoreMode colorLoreMode;
    private String description;
    private String usage;



    public static ItemBuilder newMicroblock(String itemType, ItemCategory itemCategory, ChatColor color, ColorLoreMode colorLoreMode, TexturedHead texturedHead){
        ItemBuilder builder = new ItemBuilder();
        builder.texture = texturedHead;
        builder.itemType = itemType;
        builder.itemCategory = itemCategory;
        builder.color = color;
        builder.colorLoreMode = colorLoreMode;
        return builder;
    }

    public ItemBuilder withDisplayName(String displayName){
        this.displayName = displayName;
        return this;
    }

    public ItemBuilder withDescription(String description){
        this.description = description;
        return this;
    }

    public ItemBuilder withUsage(String usage){
        this.usage = usage;
        return this;
    }

    public ItemStack build(){
        ItemStack item = texture.get();
        String lore = ChatUtils.format(itemCategory.getSymbol() + " &7" + itemCategory.getDisplayName() + "&8: " + color + itemType);
        ChatColor loreColor = getLoreColor(color, colorLoreMode);

        setDisplayName(item, displayName != null ? color + displayName : color + itemType);
        LoreUtils.addLoreLine(item, lore);
        if(description != null) {
            LoreUtils.addBlankLine(item);
            LoreUtils.addMultiLineLore(item, description);
        }
        if(usage != null) {
            LoreUtils.addBlankLine(item);
            LoreUtils.addMultiLineLore(item, loreColor, usage);
        }

        return item;
    }

    private ChatColor getLoreColor(ChatColor color, ColorLoreMode loreMode){
        switch (loreMode) {
            case DARKER:
                return ChatColor.of(color.getColor().darker());
            case BRIGHTER:
                return ChatColor.of(color.getColor().brighter());
            case DOUBLEDARK:
                return ChatColor.of(color.getColor().darker().darker());
            case DOUBLEBRIGHT:
                return ChatColor.of(color.getColor().brighter().brighter());
            default:
                return color;
        }
    }







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

    public static ItemStack setDisplayName(ItemStack stack, String displayName){
        ItemMeta iMeta = stack.getItemMeta();
        iMeta.setDisplayName(ChatUtils.format(displayName));
        stack.setItemMeta(iMeta);
        return stack;
    }

    public static ItemStack addLore(ItemStack stack, String lore){
        ItemMeta iMeta = stack.getItemMeta();
        if(lore != null){
            iMeta.setLore(buildLore(lore));
        }
        stack.setItemMeta(iMeta);
        return stack;
    }

    private static List<String> buildLore(String toParse){
        return Arrays.stream(toParse.split("\\|\\|"))
                .map(s -> ChatColor.translateAlternateColorCodes('&' , s))
                .collect(Collectors.toList());
    }

}
