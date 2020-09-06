package io.github.winterbear.wintercore.wonderhaul.sockets.infusions;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import io.github.winterbear.wintercore.utils.LoreUtils;
import io.github.winterbear.wintercore.wonderhaul.equipment.Tier;
import io.github.winterbear.wintercore.wonderhaul.sockets.infusions.infusiontypes.*;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by WinterBear on 28/08/2020.
 */
public class Infusions {

    private static final String SYMBOL = "✵";

    public static String PREFIX = Tier.ASCENDED.getColor() + SYMBOL + ChatUtils.format(" &7Infusion&8: &7");

    private static Set<Infusion> INFUSIONS = new HashSet<>();

    public static ItemStack setInfusionLore(ItemStack item, ChatColor color, String name){
        LoreUtils.addLoreLine(item, PREFIX + color + name);
        return item;
    }

    public static void registerAll(JavaPlugin plugin){
        INFUSIONS.add(new CriticalStrike(plugin));
        INFUSIONS.add(new AquaticAttunement(plugin));
        INFUSIONS.add(new Warding(plugin));
        INFUSIONS.add(new FirstStrike(plugin));
        INFUSIONS.add(new Frostbitten(plugin));
        INFUSIONS.add(new Carnivorous(plugin));
        INFUSIONS.add(new Adrenaline(plugin));
        INFUSIONS.add(new Vicious(plugin));
        INFUSIONS.add(new Distortion(plugin));
    }

}
