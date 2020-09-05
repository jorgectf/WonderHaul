package io.github.winterbear.wintercore.wonderhaul.sockets.infusions;

import io.github.winterbear.wintercore.wonderhaul.sockets.SocketUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by WinterBear on 30/08/2020.
 */
public abstract class PotionInfusion extends Infusion {

    public PotionInfusion(JavaPlugin plugin) {
        super(plugin);
    }

    public abstract void onPotionEffect(Player victim, EntityPotionEffectEvent event);

    @EventHandler
    public void onPotionEffectEvent(EntityPotionEffectEvent event){
        if(event.getEntity() instanceof Player){
            Player victim = (Player) event.getEntity();
            int level = SocketUtils.getSocketLevel(victim, this);
            if(level > 0){
                this.onPotionEffect(victim, event);
            }
        }
    }
}
