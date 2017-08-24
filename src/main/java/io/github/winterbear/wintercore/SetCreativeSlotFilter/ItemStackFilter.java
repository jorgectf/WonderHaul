package io.github.winterbear.wintercore.SetCreativeSlotFilter;

import org.bukkit.inventory.ItemStack;

/**
 * Created by WinterBear on 08/05/2017.
 */
public class ItemStackFilter {

    public ItemStack filterItemStack(ItemStack stack){
        ItemStack filteredStack = new ItemStack(stack.getType());
        filteredStack.setDurability(stack.getDurability());
        filteredStack.addEnchantments(stack.getEnchantments());
        filteredStack.setAmount(stack.getAmount());
        filteredStack.setData(stack.getData());
        filteredStack.setItemMeta(stack.getItemMeta());
        return filteredStack;
    }
}
