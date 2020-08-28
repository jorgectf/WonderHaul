package io.github.winterbear.wintercore.wonderhaul.tags;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import io.github.winterbear.wintercore.utils.ItemBuilder;
import io.github.winterbear.wintercore.utils.LoreUtils;
import io.github.winterbear.wintercore.utils.RandomUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.inventory.ItemStack;


/**
 * Created by WinterBear on 02/08/2020.
 */
public class ColorTag implements Tag{

    @Override
    public boolean apply(ItemStack item, TagApplication application) {
        String color = application.getTagItem().getItemMeta().getDisplayName().substring(0, 2);
        ItemBuilder.setDisplayName(item, color + ChatUtils.uncolored(getDisplayName(item)));
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
        String name = RandomUtils.getRandomElementOf(ChatUtils.COLOR_NAMES.keySet());
        return new String[]{ name , ChatUtils.COLOR_NAMES.get(name) };
    }


}
