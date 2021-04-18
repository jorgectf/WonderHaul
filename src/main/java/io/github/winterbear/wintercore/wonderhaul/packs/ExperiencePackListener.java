package io.github.winterbear.wintercore.wonderhaul.packs;

import io.github.winterbear.wintercore.utils.ItemUtils;
import org.bukkit.Location;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WinterBear on 27/05/2020.
 */
public class ExperiencePackListener extends PackListener {

    private static final Map<String, Integer> EXPERIENCE_PACKS = getPacks();

    private static Map<String, Integer> getPacks(){

        Map<String, Integer> packs = new HashMap<>();

        packs.put("Small Experience Haul", 5);
        packs.put("Medium Experience Haul", 10);
        packs.put("Large Experience Haul", 20);
        packs.put("Colossal Experience Haul", 30);

        return packs;
    }



    public ExperiencePackListener(JavaPlugin plugin){
        super(plugin);
    }



    @Override
    public boolean handle(String packName, PlayerInteractEvent event) {

        if(EXPERIENCE_PACKS.containsKey(packName)){
            int amount = EXPERIENCE_PACKS.get(packName);

            Player player = event.getPlayer();
            player.getInventory().removeItem(ItemUtils.oneOf(player.getInventory().getItemInMainHand()));
            spawnOrbs(player.getLocation(), amount);

            return true;
        }


        return false;
    }

    private void spawnOrbs(Location centre, int amount){

        centre.getWorld().spawn(centre, ExperienceOrb.class).setExperience(amount * 200);

    }
}
