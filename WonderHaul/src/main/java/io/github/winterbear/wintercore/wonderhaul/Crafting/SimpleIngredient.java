package io.github.winterbear.wintercore.wonderhaul.Crafting;

import org.bukkit.inventory.ItemStack;

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
