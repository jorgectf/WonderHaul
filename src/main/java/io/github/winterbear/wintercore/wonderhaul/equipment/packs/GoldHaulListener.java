package io.github.winterbear.wintercore.wonderhaul.equipment.packs;

import io.github.winterbear.wintercore.utils.EconomyUtils;
import io.github.winterbear.wintercore.utils.RandomUtils;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by WinterBear on 28/09/2020.
 */
public class GoldHaulListener extends PackListener {

    public GoldHaulListener(JavaPlugin plugin){
        super(plugin);
    }



    @Override
    public boolean handle(String packName, PlayerInteractEvent event) {

        if(packName.equalsIgnoreCase("Gold Haul")){

            EconomyUtils.payPlayer(event.getPlayer(), RandomUtils.getDoubleBetween(400, 500));

            return true;
        }

        return false;
    }
}
