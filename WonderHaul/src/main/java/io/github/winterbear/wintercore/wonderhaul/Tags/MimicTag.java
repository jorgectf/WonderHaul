package io.github.winterbear.wintercore.wonderhaul.Tags;

import io.github.winterbear.wintercore.utils.MaterialGroups;
import io.github.winterbear.wintercore.utils.ItemUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by WinterBear on 06/06/2019.
 */
public class MimicTag  implements Tag {



    @Override
    public boolean apply(ItemStack item, Player player) {
        if(MaterialGroups.EQUIPMENT.contains(item.getType())) {
            ItemUtils.safelyGiveItem(player, item);
            sendMessage(player, "Item was duplicated successfully!");
            return true;
        }
        return false;
    }

    @Override
    public String getDisplayName() {
        return "&bMimic Tag";
    }

    @Override
    public String getInstructions() {
        return "&7Right click with a piece of equipment to duplicate it!";
    }
}
