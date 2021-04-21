package io.github.winterbear.wintercore.wonderhaul.sockets.abilitytypes;

import io.github.winterbear.wintercore.wonderhaul.sockets.Ability;
import io.github.winterbear.wintercore.wonderhaul.sockets.SocketUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerExpChangeEvent;

/**
 * Created by WinterBear on 13/09/2020.
 */
public abstract class XPAbility extends Ability {

    public abstract void onXPChange(Player player, PlayerExpChangeEvent event, int socketLevel);

    @EventHandler
    public void onPlayerExpChange(PlayerExpChangeEvent event){
        Player player = event.getPlayer();
        int level = SocketUtils.getSocketLevel(player, socketable);
        if(level > 0){
            this.onXPChange(player, event, level);
        }
    }
}
