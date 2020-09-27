package io.github.winterbear.wintercore.wonderhaul.equipment.gobblers;

import io.github.winterbear.wintercore.utils.TexturedHeads;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by WinterBear on 26/09/2020.
 */
public class Gobblers {

    public static final List<Gobbler> GOBBLERS = new ArrayList<>();

    public static void registerAll(JavaPlugin plugin){

        GOBBLERS.add(new Gobbler(plugin,"Rock Gobbler",
                Arrays.asList(Material.STONE, Material.COBBLESTONE, Material.DIORITE, Material.ANDESITE, Material.GRANITE),
                ChatColor.of("#db9532"),
                Arrays.asList(new GobblerReward(0.8f, Material.IRON_NUGGET),
                        new GobblerReward(0.8f, Material.GOLD_NUGGET),
                        new GobblerReward(0.5f, Material.IRON_INGOT),
                        new GobblerReward(0.25f, Material.GOLD_INGOT),
                        new GobblerReward(0.05f, Material.DIAMOND),
                        new GobblerReward(0.01f, Material.EMERALD)),
                TexturedHeads.OPEN_GOBBLER_A,
                TexturedHeads.CLOSED_GOBBLER_A,
                "Occasionally produces pure mineral deposits"));


    }


}
