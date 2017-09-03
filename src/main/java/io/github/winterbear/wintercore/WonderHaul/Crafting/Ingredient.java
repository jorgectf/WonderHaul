package io.github.winterbear.wintercore.WonderHaul.Crafting;

import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

/**
 * Created by WinterBear on 02/09/2017.
 */
public interface Ingredient {

    IngredientType getType();

    String getName();

    ItemStack getItemStack();





}
