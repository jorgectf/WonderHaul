package io.github.winterbear.wintercore.wonderhaul.equipment.microblocks;

import io.github.winterbear.wintercore.utils.InventoryUtils;
import io.github.winterbear.wintercore.utils.WHInventoryType;
import io.github.winterbear.wintercore.wonderhaul.equipment.Microblock;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;

/**
 * Created by WinterBear on 03/08/2020.
 */
public abstract class InventoryAwareMicroblock extends Microblock {

    public InventoryAwareMicroblock(JavaPlugin plugin) {
        super(plugin);
    }

    @EventHandler
    public void onInventoryClickItem(InventoryClickEvent event) {
        Optional<WHInventoryType> inventoryReference = InventoryUtils.getCurrentInventory((Player) event.getWhoClicked());
        if(inventoryReference.isPresent() && inventoryReference.get().equals(getInventoryType())) {
            if (event.getClickedInventory().getType() != InventoryType.CHEST) {
                handleClickOutsideChest(event);
                return;
            } else if (event.getCurrentItem().getType() == Material.AIR) {
                handleAirClick(event);
                return;
            } else {
                handleClick(event);
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event){
        if(InventoryUtils.getCurrentInventory((Player) event.getPlayer()).equals(getInventoryType())) {
            InventoryUtils.closeInventory((Player) event.getPlayer());
        }
    }

    public abstract WHInventoryType getInventoryType();

    public abstract void handleClick(InventoryClickEvent event);


    protected void handleClickOutsideChest(InventoryClickEvent event){
        event.setCancelled(true);
    }

    protected void handleAirClick(InventoryClickEvent event){
        event.setCancelled(true);
    }


}
