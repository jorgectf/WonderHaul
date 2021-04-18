package io.github.winterbear.wintercore.wonderhaul.crafting;

import io.github.winterbear.wintercore.utils.EnumUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Created by WinterBear on 17/04/2021.
 */
public class MaterialIngredient implements Ingredient{

    private Material material;

    private int amount;

    public MaterialIngredient(Material material, int amount) {
        this.material = material;
        this.amount = amount;
    }

    @Override
    public String getName() {
        return EnumUtils.getDisplayText(material);
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(material, amount);
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public boolean isValidIngredient(ItemStack item) {
        return item != null && material.equals(item.getType());
    }
}
