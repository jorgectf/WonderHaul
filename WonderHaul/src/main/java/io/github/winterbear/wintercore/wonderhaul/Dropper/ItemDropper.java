package io.github.winterbear.wintercore.wonderhaul.Dropper;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import io.github.winterbear.WinterCoreUtils.CommandRegistry;
import io.github.winterbear.WinterCoreUtils.CommandWrapper;
import io.github.winterbear.wintercore.Annotations.Command;
import io.github.winterbear.wintercore.wonderhaul.Equipment.Generators.Generator;
import io.github.winterbear.wintercore.wonderhaul.Equipment.Generators.Generators;
import io.github.winterbear.wintercore.wonderhaul.data.Pools;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by WinterBear on 07/07/2019.
 */
public class ItemDropper {


    public static List<ItemStack> generate(World world, Biome biome, EntityType victimType, double luckModifier){
        return Pools.getPools()
                .stream()
                .filter(pool -> pool.getGlobalChance().roll()) //Global pool chance
                .filter(pool -> pool.getEnabledBiomes().contains(biome)) //Biome is valid
                .filter(pool -> pool.getMobChances().containsKey(victimType)) //Mob is valid
                .filter(pool -> pool.getMobChances().get(victimType).roll(luckModifier)) //Roll mob chance
                .map(p -> p.getGenerator().create())
                .collect(Collectors.toList());
    }

    @Command(permission = "wonderhaul.itemgenerator.test")
    public static CommandWrapper generateItem(){
        return CommandRegistry.createPlayerCommand("generateitem", (player, command, label, args) -> {

            if(args.length == 0){
                ChatUtils.error(player, "No generator specified!");
                return;
            }

            Generator g = Generators.fromName(args[0]);
            if(g == null){
                ChatUtils.error(player, "No Generator by the name of " + args[0]);
                return;
            }

            player.getInventory().addItem(g.create());
            ChatUtils.send(player, "Generated item using generator " + g.getName());

        });
    }




}
