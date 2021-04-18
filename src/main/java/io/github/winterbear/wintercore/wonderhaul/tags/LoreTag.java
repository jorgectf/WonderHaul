package io.github.winterbear.wintercore.wonderhaul.tags;

import de.tr7zw.nbtapi.NBTItem;
import io.github.winterbear.wintercore.ConfigLoader;
import io.github.winterbear.wintercore.utils.LoreUtils;
import io.github.winterbear.wintercore.utils.RandomUtils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class LoreTag implements Tag{



    @Override
    public boolean apply(ItemStack item, TagApplication application) {
        NBTItem nbt = new NBTItem(application.getTagItem());

        if(!item.getItemMeta().getLore().contains("")){
            LoreUtils.addBlankLine(item);
        }
        LoreUtils.addMultiLineLore(item, ChatColor.GRAY + "&o" + nbt.getString("Lore Tag"));
        sendMessage(application.getPlayer(), "Lore was added successfully!");
        return true;

    }

    private String pickLore(ItemStack item){
        List<String> lore = ConfigLoader.getConfig("lore").getStringList("lore.generic");
        return RandomUtils.getRandomElementOf(lore);
    }

    @Override
    public String getDisplayName() {
        return "Lore Tag";
    }

    @Override
    public String getInstructions() {
        return "Right click with a piece of equipment to add lore to it!";
    }

    @Override
    public String getDescription() {
        return "A scrap of cloth covered with a mysterious script. Holding it reminds you of times long ago.";
    }

    @Override
    public ChatColor getColor() {
        return ChatColor.of("#9350b5");
    }

    @Override
    public ItemStack modify(ItemStack item){
        NBTItem nbt = new NBTItem(item);
        String lore = pickLore(item);
        nbt.setString("Lore Tag", lore);
        ItemStack newItem = nbt.getItem();
        LoreUtils.addBlankLine(newItem);
        LoreUtils.addMultiLineLore(newItem, ChatColor.GRAY + "&o" + lore);
        return newItem;
    }

    private String getColor(ItemStack item){
        if(item.getItemMeta().getDisplayName().contains("§")){
            if(item.getItemMeta().getDisplayName().charAt(2) == '§'){
                return item.getItemMeta().getDisplayName().substring(0, 14);
            } else {
                return item.getItemMeta().getDisplayName().substring(0, 2);
            }
        }
        return "§7";
    }
}
