package io.github.winterbear.wintercore.wonderhaul.equipment.packs;

import io.github.winterbear.wintercore.utils.ItemUtils;
import org.bukkit.Location;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by WinterBear on 27/05/2020.
 */
public class ExperiencePackListener extends PackListener {



    public ExperiencePackListener(JavaPlugin plugin){
        super(plugin);
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
