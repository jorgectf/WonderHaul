package io.github.winterbear.wintercore.WonderHaul.Inventories;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Created by WinterBear on 30/09/2018.
 */
public class InventoryGUIListener implements Listener {

    private final Inventory inventory;

    public InventoryGUIListener(Player player, String title){
        inventory = Bukkit.createInventory(player, 9, title);
        player.openInventory(inventory);
    }

    public InventoryGUIListener setItem(ItemStack item, int position){
        inventory.setItem(position, item);
        return this;
    }

}
