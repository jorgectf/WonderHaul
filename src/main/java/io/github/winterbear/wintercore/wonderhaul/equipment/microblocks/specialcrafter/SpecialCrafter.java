package io.github.winterbear.wintercore.wonderhaul.equipment.microblocks.specialcrafter;

import io.github.winterbear.WinterCoreUtils.ChatUtils;
import io.github.winterbear.wintercore.utils.InventoryUtils;
import io.github.winterbear.wintercore.utils.ItemUtils;
import io.github.winterbear.wintercore.utils.LoreUtils;
import io.github.winterbear.wintercore.wonderhaul.blockstorage.BlockMetadata;
import io.github.winterbear.wintercore.wonderhaul.crafting.SpecialRecipe;
import io.github.winterbear.wintercore.wonderhaul.equipment.microblocks.InventoryAwareMicroblock;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by WinterBear on 03/08/2020.
 */
public abstract class SpecialCrafter extends InventoryAwareMicroblock {

    private static final String INGREDIENTS = "&bIngredients&8: ";

    public SpecialCrafter(JavaPlugin plugin) {
        super(plugin);
    }

    public abstract String getDisplayName();

    public abstract Map<Integer, SpecialRecipe> getRecipes();

    @Override
    public boolean interact(PlayerInteractEvent event, BlockMetadata metadata) {

        Player player = event.getPlayer();
        int rows = Math.min(getRecipes().size()/9, 6);
        if(getRecipes().size() % 9 > 0){
            rows++;
        }
        InventoryUtils.openInventory(player, rows, getDisplayName(), getReference());
        int slot = 0;
        for(SpecialRecipe recipe : getRecipes().values()){
            ItemStack displayItem = recipe.getResult().clone();
            List<String> ingredientsLore = recipe.getIngredients().stream()
                    .map(i -> " &8- &6" + i.getAmount() + " &3" + i.getName())
                    .collect(Collectors.toList());
            LoreUtils.addBlankLine(displayItem);
            LoreUtils.addLoreLine(displayItem, INGREDIENTS);
            LoreUtils.addLore(displayItem, ingredientsLore, null);
            LoreUtils.addBlankLine(displayItem);
            player.getOpenInventory().getTopInventory().setItem(slot++, displayItem);
        }
        return true;
    }

    public void handleClick(InventoryClickEvent event){
        event.setCancelled(true);
        Optional<SpecialRecipe> recipe = getRecipes().entrySet().stream()
                .filter(r -> r.getKey().equals(event.getRawSlot()))
                .map(Map.Entry::getValue)
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
