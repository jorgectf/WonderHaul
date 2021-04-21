package io.github.winterbear.wintercore.wonderhaul.crafting;

import org.bukkit.inventory.ItemStack;

/**
 * Created by WinterBear on 02/09/2017.
 */
public interface Ingredient {

    String getName();

    ItemStack getItemStack();

    int getAmount();

    boolean isValidIngredient(ItemStack item);

}
