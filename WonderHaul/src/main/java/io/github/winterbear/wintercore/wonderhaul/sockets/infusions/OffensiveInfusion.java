package io.github.winterbear.wintercore.wonderhaul.sockets.infusions;

import io.github.winterbear.wintercore.wonderhaul.sockets.SocketUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by WinterBear on 30/08/2020.
 */
public abstract class OffensiveInfusion extends Infusion {

    public OffensiveInfusion(JavaPlugin plugin) {
        super(plugin);
    }

    public abstract void onAttack(Player attacker, EntityDamageByEntityEvent event, int socketLevel);

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event){
        if(event.getDamager() instanceof Player){
            Player attacker = (Player) event.getDamager();
            int level = SocketUtils.getSocketLevel(attacker, this);
            if(level > 0){
                this.onAttack(attacker, event, level);
            }
        }
    }

}
