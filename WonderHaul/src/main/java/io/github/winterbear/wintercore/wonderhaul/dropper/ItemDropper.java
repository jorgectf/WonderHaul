package io.github.winterbear.wintercore.wonderhaul.dropper;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import io.github.winterbear.WinterCoreUtils.CommandRegistry;
import io.github.winterbear.WinterCoreUtils.CommandWrapper;
import io.github.winterbear.wintercore.Annotations.Command;
import io.github.winterbear.wintercore.wonderhaul.equipment.generators.Generator;
import io.github.winterbear.wintercore.wonderhaul.equipment.generators.Generators;
import io.github.winterbear.wintercore.wonderhaul.data.Pools;
import org.bukkit.Chunk;
import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by WinterBear on 07/07/2019.
 */
public class ItemDropper {


    public static List<ItemStack> generate(Chunk chunk, Biome biome, EntityType victimType, double luckModifier){
        if(!chunkIsValid(chunk)){
            return Collections.EMPTY_LIST;
        }

        return Pools.getPools()
                .stream()
                .filter(pool -> pool.getGlobalChance().roll()) //Global pool chance
                .filter(pool -> pool.getEnabledBiomes().contains(biome)) //Biome is valid
                .filter(pool -> pool.getMobChances().containsKey(victimType)) //Mob is valid
                .filter(pool -> pool.getMobChances().get(victimType).rollModified(luckModifier)) //Roll mob chance
                .map(p -> p.getGenerator().create())
                .collect(Collectors.toList());
    }


    private static boolean chunkIsValid(Chunk chunk){
        long time = chunk.getInhabitedTime();

        if(time > 216000L){

            long excess = time - 216000L;
            if (excess < 72000L) {
                return new Chance(50).roll();
            } else if (excess < 144000L) {
                return new Chance(25).roll();
            } else if (excess < 216000L) {
                return new Chance(5).roll();
            } else {
                return false;
            }

        }

        return true;
    }

    @Command
    public static CommandWrapper chunkChance(){
        return CommandRegistry.createPlayerCommand("chunkchance", (player, command, label, args) -> {

            Chunk chunk = player.getLocation().getChunk();
            long time = chunk.getInhabitedTime();

            if(time > 216000L){
                long excess = time - 216000L;
                if (excess < 72000L) {
                    ChatUtils.send(player, ChatUtils.format("&7There is an &eok &7chance of getting loot drops in this chunk."));
                } else if (excess < 144000L) {
                    ChatUtils.send(player, ChatUtils.format("&7There is a &clow &7chance of getting loot drops in this chunk."));
                } else if (excess < 216000L) {
                    ChatUtils.send(player, ChatUtils.format("&7There is an &4extremely low &7chance of getting loot drops in this chunk."));
                } else {
                    ChatUtils.send(player, ChatUtils.format("&7There is &fno &7chance of getting loot drops in this chunk."));
                }

            } else {
                ChatUtils.send(player, "&7There is a &agood &7chance of getting loot drops in this chunk.");
            }


        });

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
