package io.github.winterbear.wintercore.wonderhaul.sockets.infusions;

import io.github.winterbear.wintercore.wonderhaul.sockets.SocketUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by WinterBear on 30/08/2020.
 */
public abstract class BlockBreakInfusion extends Infusion {


    public BlockBreakInfusion(JavaPlugin plugin) {
        super(plugin);
    }

    public abstract void onBlockBreak(Player player, BlockBreakEvent event);

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        Player player = event.getPlayer();
        int level = SocketUtils.getSocketLevel(player, this);
        if(level > 0){
            this.onBlockBreak(player, event);
        }
    }
}
