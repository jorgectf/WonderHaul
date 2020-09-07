package io.github.winterbear.wintercore.wonderhaul.crafting;

import org.bukkit.inventory.ItemStack;

/**
 * Created by WinterBear on 02/09/2017.
 */
public class InvalidItemStackException extends Exception {

    private ItemStack invalidStack;

    public InvalidItemStackException(String e, ItemStack stack){
        super(e);
        this.invalidStack = stack;
    }

    public ItemStack getInvalidStack(){
        return invalidStack;
    }

}
