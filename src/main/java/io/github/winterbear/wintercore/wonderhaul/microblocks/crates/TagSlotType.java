package io.github.winterbear.wintercore.wonderhaul.microblocks.crates;

import io.github.winterbear.wintercore.utils.ItemUtils;
import io.github.winterbear.wintercore.wonderhaul.equipment.generators.Generators;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by WinterBear on 19/09/2020.
 */
public class TagSlotType implements SlotType {


    @Override
    public ItemStack getNewItem() {
        return Generators.TAG.create();
    }

    @Override
    public void reward(Player player, ItemStack item) {
        ItemUtils.safelyGiveItem(player, item);
    }
}
