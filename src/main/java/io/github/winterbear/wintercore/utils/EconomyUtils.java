package io.github.winterbear.wintercore.utils;

import io.github.winterbear.wintercore.Annotations.Dependency;
import io.github.winterbear.wintercore.Annotations.DependencyType;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

/**
 * Created by WinterBear on 27/09/2020.
 */
@Dependency(name = "Vault", type = DependencyType.HARD)
public class EconomyUtils {

    public static Economy ECONOMY;

    public static void setupEconomy() {
        if (Bukkit.getServer().getPluginManager().getPlugin("Vault") == null) {
            return;
        }
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return;
        }
        ECONOMY = rsp.getProvider();
    }



}
