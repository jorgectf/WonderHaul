package io.github.winterbear.wintercore.wonderhaul.tags;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import io.github.winterbear.wintercore.utils.ColorUtils;
import io.github.winterbear.wintercore.utils.ItemBuilder;
import io.github.winterbear.wintercore.utils.LoreUtils;
import net.md_5.bungee.api.ChatColor;
import org.apache.commons.lang.StringUtils;
import org.bukkit.inventory.ItemStack;

import java.awt.*;
import java.util.List;
import java.util.Random;


/**
 * Created by WinterBear on 02/08/2020.
 */
public class ColorTag implements Tag{

    @Override
    public boolean apply(ItemStack item, TagApplication application) {
        String color = application.getTagItem().getItemMeta().getDisplayName().substring(0, 14);
        ItemBuilder.setDisplayName(item, color + ChatUtils.uncolored(getDisplayName(item)));
        List<String> lore = item.getItemMeta().getLore();
        if(lore != null) {
            lore.stream()
                    .filter(l -> !l.contains(":"))
                    .forEach(l -> LoreUtils.setLoreLine(item, lore.indexOf(l), color + ChatUtils.uncolored(l)));
        }
        return true;
    }

    private String getDisplayName(ItemStack item){
        String name = LoreUtils.convertToTitleCase(item.getType().toString().replaceAll("_", " "));
        if(item.getItemMeta() != null && !StringUtils.isEmpty(item.getItemMeta().getDisplayName())){
            name = item.getItemMeta().getDisplayName();
        }
        return name;
    }

    @Override
    public String getDisplayName() {
        return "&dColor Tag";
    }

    @Override
    public String getInstructions() {
        return "&7Right click with a piece of equipment to change the color of it's name!";
    }

    @Override
    public ItemStack modify(ItemStack item){
        String[] color = pickColor();
        ItemBuilder.setDisplayName(item, ChatUtils.format(color[1] +
                ChatUtils.uncolored(getDisplayName(item))));
        LoreUtils.addLoreLine(item, ChatUtils.format(color[1] + color[0]));
        return item;
    }

    private String[] pickColor(){
        int r = (new Random().nextInt(256));
        int g = (new Random().nextInt(256));
        int b = (new Random().nextInt(256));
        ChatColor color = ChatColor.of(new Color(r, g, b));
        return new String[]{ColorUtils.getColorNameFromRgb(r, g, b), color + "" };
    }


}
