package io.github.winterbear.wintercore.wonderhaul.sockets.abilitytypes;

import io.github.winterbear.wintercore.wonderhaul.sockets.Ability;
import io.github.winterbear.wintercore.wonderhaul.sockets.SocketUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Created by WinterBear on 13/09/2020.
 */
public abstract class PlayerInteractAbility extends Ability {

    public abstract void onPlayerInteract(Player player, PlayerInteractEvent event, int socketLevel);

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        int level = SocketUtils.getSocketLevel(player, socketable);
        if(level > 0){
            this.onPlayerInteract(player, event, level);
        }
    }
}
