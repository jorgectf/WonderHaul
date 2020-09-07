package io.github.winterbear.wintercore.wonderhaul.equipment.microblocks.specialcrafter;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import io.github.winterbear.wintercore.utils.InventoryUtils;
import io.github.winterbear.wintercore.utils.ItemUtils;
import io.github.winterbear.wintercore.utils.WHInventoryType;
import io.github.winterbear.wintercore.wonderhaul.blockstorage.BlockMetadata;
import io.github.winterbear.wintercore.wonderhaul.crafting.SpecialRecipe;
import io.github.winterbear.wintercore.wonderhaul.equipment.microblocks.InventoryAwareMicroblock;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Optional;

/**
 * Created by WinterBear on 03/08/2020.
 */
public abstract class SpecialCrafter extends InventoryAwareMicroblock {

    private WHInventoryType type;

    public SpecialCrafter(JavaPlugin plugin, WHInventoryType type) {
        super(plugin);
        this.type = type;
    }

    public abstract String getDisplayName();

    public abstract List<SpecialRecipe> getRecipes();

    @Override
    public boolean interact(PlayerInteractEvent event, BlockMetadata metadata) {

        Player player = event.getPlayer();
        int rows = Math.min(getRecipes().size()/9, 6);
        InventoryUtils.openInventory(player, rows, getDisplayName(), type);
        int slot = 0;
        for(SpecialRecipe recipe : getRecipes()){
            player.getOpenInventory().getTopInventory().setItem(slot++, recipe.getResult());
        }
        return true;
    }

    public void handleClick(InventoryClickEvent event){
        event.setCancelled(true);
        Optional<SpecialRecipe> recipe = getRecipes().stream()
                .filter(r -> r.getResult().equals(event.getCurrentItem()))
                .findFirst();
        if(recipe.isPresent()){
            if(recipe.get().canCraft(event.getWhoClicked())){
                InventoryUtils.removeItems(event.getWhoClicked(), recipe.get().getIngredients());
                ItemUtils.safelyGiveItem((Player)event.getWhoClicked(), recipe.get().getResult());
                ChatUtils.send((Player) event.getWhoClicked(), getDisplayName() + "&8: &7Crafted " + recipe.get().getDisplayName());
            } else {
                ChatUtils.send((Player)event.getWhoClicked(), getDisplayName() + "&8: &7Not enough ingredients.");
            }
        }
    }
}
