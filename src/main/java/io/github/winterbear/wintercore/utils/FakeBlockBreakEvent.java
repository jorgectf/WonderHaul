package io.github.winterbear.wintercore.utils;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;

/**
 * Created by WinterBear on 13/09/2020.
 */
public class FakeBlockBreakEvent extends BlockBreakEvent {
    public FakeBlockBreakEvent(Block theBlock, Player player) {
        super(theBlock, player);
    }
}
