package io.github.winterbear.wintercore.wonderhaul.sockets.abilitytypes;

import io.github.winterbear.wintercore.wonderhaul.sockets.Ability;
import io.github.winterbear.wintercore.wonderhaul.sockets.SocketUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;

/**
 * Created by WinterBear on 30/08/2020.
 */
public abstract class BlockBreakAbility extends Ability {

    public abstract void onBlockBreak(Player player, BlockBreakEvent event);

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        Player player = event.getPlayer();
        int level = SocketUtils.getSocketLevel(player, socketable);
        if(level > 0){
            this.onBlockBreak(player, event);
        }
    }
}
