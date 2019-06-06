package io.github.winterbear.wintercore.servercore;

import io.github.winterbear.wintercore.CommandRegistry;
import io.github.winterbear.wintercore.CommandWrapper;
import io.github.winterbear.wintercore.utils.ChatUtils;
import io.github.winterbear.wintercore.utils.CommandSenderUtils;
import io.github.winterbear.wintercore.utils.LoreUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

/**
 * Created by WinterBear on 20/05/2019.
 */
public class DevTools {

    public static CommandWrapper setHealth(){
        return CommandRegistry.createCommand("sethealth", (sender, command, alias, args) -> {
            int health = Integer.valueOf(args[0]);
            if(args.length > 1){
                Player player = sender.getServer().getPlayer(args[1]);
                if(player != null){
                    player.setHealth(health);
                    ChatUtils.send(sender, "&9Notice&8: &7Set " + args[1] + "'s health to " + health);
                    return;
                } else {
                    ChatUtils.send(sender, "&cError&8: &7No player " + args[1] + "found");
                    return;
                }
            }
            Optional<Player> player = CommandSenderUtils.getPlayer(sender);
            if(player.isPresent()){
                player.get().setHealth(health);
                ChatUtils.send(sender, "&9Notice&8: &7Set your health to " + health);
            } else {
                ChatUtils.send(sender, "cError&8: &7You are not a player and no target player was specified.");
            }
        });
    }

    public static CommandWrapper setHunger(){
        return CommandRegistry.createCommand("sethunger", (sender, command, alias, args) -> {
            int hunger = Integer.valueOf(args[0]);
            if(args.length > 1){
                sender.getServer().getPlayer(args[1]).setFoodLevel(hunger);
                ChatUtils.send(sender, "&9Notice&8: &7Set " + args[1] + "'s hunger to " + hunger);
            }

            sender.getServer().getPlayer(sender.getName()).setFoodLevel(hunger);
            ChatUtils.send(sender, "&9Notice&8: &7Set your hunger to " + hunger);
        });
    }

    public static CommandWrapper addLore(){
        return CommandRegistry.createPlayerCommand("addlore", (player, command, label, args) -> {
            if(args.length > 0) {
                ItemStack mainHandItem = player.getInventory().getItemInMainHand();
                if(mainHandItem.getType().equals(Material.AIR)){
                    ChatUtils.send(player, "&cError&8: &7You must be holding an item to do that.");
                    return;
                }
                LoreUtils.addLoreLine(mainHandItem, args[0]);
                ChatUtils.send(player, "Added line " + args[0] + " to your tool.");
                return;
            }
        });
    }

    public static CommandWrapper clearLore(){
        return CommandRegistry.createPlayerCommand("clearlore", (player, command, label, args) -> {
            if(args.length > 0) {
                ItemStack mainHandItem = player.getInventory().getItemInMainHand();
                if(mainHandItem.getType().equals(Material.AIR)){
                    ChatUtils.send(player, "&cError&8: &7You must be holding an item to do that.");
                    return;
                }
                LoreUtils.clearlore(mainHandItem);
                ChatUtils.send(player, "Cleared the lore of your tool.");
                return;
            }
        });
    }

    public static CommandWrapper removeLore(){
        return CommandRegistry.createPlayerCommand("removelore", (player, command, label, args) -> {
            if(args.length > 1) {
                ItemStack mainHandItem = player.getInventory().getItemInMainHand();
                if(mainHandItem.getType().equals(Material.AIR)){
                    ChatUtils.send(player, "&cError&8: &7You must be holding an item to do that.");
                    return;
                }
                LoreUtils.removeLoreLine(mainHandItem, Integer.valueOf(args[0]));
                ChatUtils.send(player, "Cleared the lore of your tool.");
                return;
            }

        });
    }


}
