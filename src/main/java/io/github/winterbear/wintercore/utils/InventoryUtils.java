package io.github.winterbear.wintercore.utils;

import io.github.winterbear.wintercore.wonderhaul.crafting.Ingredient;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.*;

/**
 * Created by WinterBear on 03/08/2020.
 */
public class InventoryUtils {

    private static final Map<UUID, String> OPEN_INVENTORIES = new HashMap<>();

    public static Inventory openInventory(Player player, int rows, String displayName, String reference){
        Inventory inventory = Bukkit.createInventory(player, rows*9, displayName);
        player.openInventory(inventory);
        OPEN_INVENTORIES.put(player.getUniqueId(), reference);
        return inventory;
    }

    public static void closeInventory(Player player){
        OPEN_INVENTORIES.remove(player.getUniqueId());
    }

    public static Optional<String> getCurrentInventory(Player player){
        if(OPEN_INVENTORIES.containsKey(player.getUniqueId())){
            return Optional.of(OPEN_INVENTORIES.get(player.getUniqueId()));
        }
        return Optional.empty();
    }

    public static void removeItems(InventoryHolder inventoryHolder, List<Ingredient> items){
        for(Ingredient i : items){
            ItemStack item = ItemUtils.oneOf(i.getItemStack());
            item.setAmount(i.getAmount());
            inventoryHolder.getInventory().remove(item);
        }
    }



}
