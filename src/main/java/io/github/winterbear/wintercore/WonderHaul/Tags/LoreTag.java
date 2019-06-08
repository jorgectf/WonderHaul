package io.github.winterbear.wintercore.WonderHaul.Tags;

import io.github.winterbear.wintercore.ConfigLoader;
import io.github.winterbear.wintercore.utils.CollectionUtils;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

import static io.github.winterbear.wintercore.ConfigLoader.getConfig;

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
