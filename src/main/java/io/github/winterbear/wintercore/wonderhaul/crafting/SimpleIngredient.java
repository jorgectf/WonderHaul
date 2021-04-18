package io.github.winterbear.wintercore.wonderhaul.crafting;

import io.github.winterbear.wintercore.utils.ItemUtils;
import org.bukkit.inventory.ItemStack;

/**
 * Created by WinterBear on 02/09/2017.
 */
public class SimpleIngredient implements Ingredient {

    private ItemStack stack;

    int amount;

    public SimpleIngredient(ItemStack itemStack, int amount) {
        this.stack = ItemUtils.oneOf(itemStack);
        this.amount = amount;
    }

    //TODO this wont work
    @Override
    public String getName() {
        return stack.getItemMeta().getDisplayName();
    }

    @Override
    public ItemStack getItemStack() {
        return stack;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public boolean isValidIngredient(ItemStack item) {
        return this.stack.getItemMeta().equals(item.getItemMeta());
    }

}
