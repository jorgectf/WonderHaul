package io.github.winterbear.wintercore.WonderHaul.Crafting;

import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

/**
 * Created by WinterBear on 02/09/2017.
 */
public class SimpleIngredient extends MaterialDataIngredient {

    private ItemStack stack;

    private IngredientType type = IngredientType.METADATA;

    public SimpleIngredient(ItemStack itemStack) {
        this.stack = itemStack;
    }

    @Override
    public IngredientType getType() {
        return type;
    }

    @Override
    public String getName() {
        return stack.getItemMeta().getDisplayName();
    }

    @Override
    public ItemStack getItemStack() {
        return stack;
    }

}
