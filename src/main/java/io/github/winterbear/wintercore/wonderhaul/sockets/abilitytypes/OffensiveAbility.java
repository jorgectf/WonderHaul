package io.github.winterbear.wintercore.wonderhaul.sockets.abilitytypes;

import io.github.winterbear.wintercore.wonderhaul.sockets.Ability;
import io.github.winterbear.wintercore.wonderhaul.sockets.SocketUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * Created by WinterBear on 30/08/2020.
 */
public abstract class OffensiveAbility extends Ability{

    public abstract void onAttack(Player attacker, EntityDamageByEntityEvent event, int socketLevel);

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event){
        if(event.getDamager() instanceof Player){
            Player attacker = (Player) event.getDamager();
            event.getCause().equals(EntityDamageEvent.DamageCause.PROJECTILE);
            int level = SocketUtils.getSocketLevel(attacker, socketable);
            if(level > 0){
                this.onAttack(attacker, event, level);
            }
        }
    }
}
