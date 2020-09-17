package io.github.winterbear.wintercore.utils;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by WinterBear on 13/09/2020.
 */
public class FakeBlockDamageEvent extends BlockDamageEvent {

    public FakeBlockDamageEvent(Player player, Block block, ItemStack itemInHand, boolean instaBreak) {
        super(player, block, itemInHand, instaBreak);
    }
}
