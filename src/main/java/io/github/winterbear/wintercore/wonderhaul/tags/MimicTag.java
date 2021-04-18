package io.github.winterbear.wintercore.wonderhaul.tags;

import io.github.winterbear.wintercore.utils.ItemUtils;
import io.github.winterbear.wintercore.wonderhaul.equipment.MaterialGroup;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.inventory.ItemStack;

/**
 * Created by WinterBear on 06/06/2019.
 */
public class MimicTag  implements Tag {



    @Override
    public boolean apply(ItemStack item, TagApplication application) {
        if(MaterialGroup.ALL_EQUIPMENT.contains(item.getType())) {
            ItemUtils.safelyGiveItem(application.getPlayer(), item);
            sendMessage(application.getPlayer(), "Item was duplicated successfully!");
            return true;
        }
        return false;
    }

    @Override
    public String getDisplayName() {
        return "Mimic Tag";
    }

    @Override
    public String getInstructions() {
        return "Right click with a piece of equipment to duplicate it!";
    }

    @Override
    public String getDescription() {
        return "A rare piece of shimmering cloth with a symbol of a dazzling sun woven into the fabric. It doesn't look completely corporeal.";
    }

    @Override
    public ChatColor getColor() {
        return ChatColor.of("#c2fdff");
    }
}
