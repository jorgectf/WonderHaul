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
                ChatColor.of("#c9c9c9"),
                Arrays.asList(new GobblerReward(0.8f, Material.IRON_NUGGET),
                        new GobblerReward(0.8f, Material.GOLD_NUGGET),
                        new GobblerReward(0.5f, Material.IRON_INGOT),
                        new GobblerReward(0.25f, Material.GOLD_INGOT),
                        new GobblerReward(0.05f, Material.DIAMOND),
                        new GobblerReward(0.01f, Material.EMERALD)),
                TexturedHeads.OPEN_ROCK_GOBBLER,
                TexturedHeads.CLOSED_ROCK_GOBBLER,
                "Occasionally produces metal nuggets"));

        GOBBLERS.add(new Gobbler(plugin,"Desert Gobbler",
                Arrays.asList(Material.SAND, Material.DIRT, Material.GRAVEL),
                ChatColor.of("#dec23a"),
                Arrays.asList(new GobblerReward(0.8f, Material.REDSTONE),
                        new GobblerReward(0.8f, Material.GLOWSTONE_DUST),
                        new GobblerReward(0.5f, Material.GUNPOWDER),
                        new GobblerReward(0.25f, Material.EXPERIENCE_BOTTLE)),
                TexturedHeads.OPEN_DESERT_GOBBLER,
                TexturedHeads.CLOSED_DESERT_GOBBLER,
                "Occasionally produces redstone dust"));

        GOBBLERS.add(new Gobbler(plugin,"Infernal Gobbler",
                Arrays.asList(Material.NETHERRACK),
                ChatColor.of("#911616"),
                Arrays.asList(new GobblerReward(0.8f, Material.BLAZE_POWDER),
                        new GobblerReward(0.8f, Material.BLAZE_ROD),
                        new GobblerReward(0.5f, Material.GHAST_TEAR),
                        new GobblerReward(0.25f, Material.MAGMA_CREAM)),
                TexturedHeads.OPEN_NETHER_GOBBLER,
                TexturedHeads.CLOSED_NETHER_GOBBLER,
                "Occasionally produces blaze powder"));

    }


}
