package io.github.winterbear.wintercore.wonderhaul.Crafting;

import org.bukkit.inventory.ItemStack;

/**
 * Created by WinterBear on 02/09/2017.
 */
public interface Ingredient {

    IngredientType getType();

    String getName();

    ItemStack getItemStack();





}
