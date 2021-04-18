package io.github.winterbear.wintercore.wonderhaul.packs;

import io.github.winterbear.wintercore.utils.EconomyUtils;
import io.github.winterbear.wintercore.utils.RandomUtils;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WinterBear on 28/09/2020.
 */
public class GoldHaulListener extends PackListener {

    private static final Map<String, Integer> GOLD_HAULS = getPacks();

    private static Map<String, Integer> getPacks(){

        Map<String, Integer> packs = new HashMap<>();

        packs.put("Small Gold Haul", 500);
        packs.put("Medium Gold Haul", 1000);
        packs.put("Large Gold Haul", 2000);
        packs.put("Colossal Gold Haul", 8000);

        return packs;
    }

    public GoldHaulListener(JavaPlugin plugin){
        super(plugin);
    }



    @Override
    public boolean handle(String packName, PlayerInteractEvent event) {

        if(GOLD_HAULS.containsKey(packName)){

            int max = GOLD_HAULS.get(packName);

            EconomyUtils.ECONOMY.depositPlayer(event.getPlayer(), RandomUtils.getDoubleBetween(max - (max/20.0), max));

            return true;
        }

        return false;
    }
}
