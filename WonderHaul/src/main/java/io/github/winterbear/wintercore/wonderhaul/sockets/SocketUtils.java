package io.github.winterbear.wintercore.wonderhaul.sockets;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import io.github.winterbear.wintercore.utils.LoreUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by WinterBear on 30/08/2020.
 */
public class SocketUtils {

    public static int getSocketLevel(Player player, Socketable socket){
        switch (socket.getTriggerType()){
            case MAINHAND:
                return mainhandSocketLevel(player, socket);
            case OFFHAND:
                return offhandSocketLevel(player, socket);
            case ARMOR:
                return armorSocketLevel(player, socket);
            default:
                ChatUtils.warn("Invalid socket trigger type:" + socket.getTriggerType());
                return 0;
        }
    }

    private static int mainhandSocketLevel(Player player, Socketable socket){
        ItemStack weapon = player.getInventory().getItemInMainHand();
        List<String> abilities = LoreUtils.getTag(weapon, socket.getSocketType().getName());
        return getSocketLevel(abilities, socket.getAbilityName());
    }

    private static int offhandSocketLevel(Player player, Socketable socket){
        ItemStack weapon = player.getInventory().getItemInOffHand();
        List<String> abilities = LoreUtils.getTag(weapon, socket.getSocketType().getName());
        return getSocketLevel(abilities, socket.getAbilityName());
    }

    private static int armorSocketLevel(Player player,Socketable socket){
        List<ItemStack> armor = Arrays.asList(player.getInventory().getArmorContents());
        List<String> abilities = armor.stream()
                .map(a -> LoreUtils.getTag(a, socket.getSocketType().getName()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        return getSocketLevel(abilities, socket.getAbilityName());
    }

    private static int getSocketLevel(List<String> abilities, String name){
        return (int) abilities.stream()
                .filter(name::equals)
                .count();
    }
}
