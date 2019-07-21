package io.github.winterbear.wintercore.wonderhaul.Tags;

import io.github.winterbear.wintercore.ConfigLoader;
import io.github.winterbear.wintercore.utils.LoreUtils;
import io.github.winterbear.wintercore.utils.RandomUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class LoreTag implements Tag{



    @Override
    public boolean apply(ItemStack item, TagApplication application) {
        return false;
    }

    private String pickLore(ItemStack item){
        List<String> lore = ConfigLoader.getConfig("lore").getStringList("lore.generic");
        return RandomUtils.getRandomElementOf(lore);
    }

    @Override
    public String getDisplayName() {
        return null;
    }

    @Override
    public String getInstructions() {
        return null;
    }

    @Override
    public ItemStack modify(ItemStack item){
        item.getItemMeta().getLore().add(pickLore(item));
        return item;
    }
}
