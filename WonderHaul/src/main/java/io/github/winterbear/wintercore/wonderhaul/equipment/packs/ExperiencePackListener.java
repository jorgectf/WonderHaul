package io.github.winterbear.wintercore.wonderhaul.equipment.packs;

import io.github.winterbear.wintercore.utils.InteractEventUtils;
import io.github.winterbear.wintercore.utils.ItemUtils;
import org.bukkit.Location;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by WinterBear on 27/05/2020.
 */
public class ExperiencePackListener extends PackListener {

    private static final Map<Player, Long> clickCooldown = new ConcurrentHashMap<>();

    public ExperiencePackListener(JavaPlugin plugin){
        super(plugin);
    }

    @EventHandler
    public void onInteractEvent(PlayerInteractEvent event) {
        if(InteractEventUtils.eventIsRightClickEvent(event, clickCooldown)){
            if(begin(event)){
                event.setCancelled(true);
            }
        }
    }

    @Override
    public boolean handle(String packName, PlayerInteractEvent event) {

        if(packName.equalsIgnoreCase("Experience Haul")){

            Player player = event.getPlayer();

            player.getInventory().removeItem(ItemUtils.oneOf(player.getInventory().getItemInMainHand()));

            spawnOrbs(player.getLocation(), 20);

            return true;
        }

        return false;
    }

    private void spawnOrbs(Location centre, int amount){

        centre.getWorld().spawn(centre, ExperienceOrb.class).setExperience(amount * 200);

    }
}
