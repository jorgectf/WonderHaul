package io.github.winterbear.wintercore.wonderhaul.Tags;

import io.github.winterbear.wintercore.ConfigLoader;
import io.github.winterbear.wintercore.utils.CollectionUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class LoreTag implements Tag{



    @Override
    public boolean apply(ItemStack item, Player player) {
        return false;
    }

    private String pickLore(ItemStack item){
        List<String> lore = ConfigLoader.getConfig("lore").getStringList("lore.generic");
        return CollectionUtils.getRandomElementOf(lore);
    }

    @Override
    public String getDisplayName() {
        return null;
    }

    @Override
    public String getInstructions() {
        return null;
    }
}
