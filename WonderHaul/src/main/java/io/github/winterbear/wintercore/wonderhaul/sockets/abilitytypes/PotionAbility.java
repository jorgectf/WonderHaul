package io.github.winterbear.wintercore.wonderhaul.sockets.abilitytypes;

import io.github.winterbear.wintercore.wonderhaul.sockets.Ability;
import io.github.winterbear.wintercore.wonderhaul.sockets.SocketUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityPotionEffectEvent;

/**
 * Created by WinterBear on 30/08/2020.
 */
public abstract class PotionAbility extends Ability {

    public abstract void onPotionEffect(Player victim, EntityPotionEffectEvent event);

    @EventHandler
    public void onPotionEffectEvent(EntityPotionEffectEvent event){
        if(event.getEntity() instanceof Player){
            Player victim = (Player) event.getEntity();
            int level = SocketUtils.getSocketLevel(victim, socketable);
            if(level > 0){
                this.onPotionEffect(victim, event);
            }
        }
    }
}
