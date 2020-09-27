package io.github.winterbear.wintercore.utils;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

/**
 * Created by WinterBear on 27/09/2020.
 */
public class EconomyUtils {

    public static void payPlayer(Player player, Double amount){

        RegisteredServiceProvider<Economy> provider =
                Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
        if(provider != null){
            provider.getProvider().depositPlayer(player, amount);
        }

    }


}
