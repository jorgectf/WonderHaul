package io.github.winterbear.wintercore.wonderhaul.microblocks.crates;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by WinterBear on 19/09/2020.
 */
public interface SlotType {

    ItemStack getNewItem();

    void reward(Player player, ItemStack item);

}
