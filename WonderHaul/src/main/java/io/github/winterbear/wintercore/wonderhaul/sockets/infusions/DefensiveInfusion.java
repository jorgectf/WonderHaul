package io.github.winterbear.wintercore.wonderhaul.sockets.infusions;

import io.github.winterbear.wintercore.wonderhaul.sockets.SocketUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by WinterBear on 31/08/2020.
 */
public abstract class DefensiveInfusion extends Infusion{

    public DefensiveInfusion(JavaPlugin plugin) {
        super(plugin);
    }

    public abstract void onDefend(Player victim, EntityDamageByEntityEvent event, int socketLevel);

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event){
        if(event.getEntity() instanceof Player){
            Player victim = (Player) event.getEntity();
            int level = SocketUtils.getSocketLevel(victim, this);
            if(level > 0){
                this.onDefend(victim, event, level);
            }
        }
    }

}
