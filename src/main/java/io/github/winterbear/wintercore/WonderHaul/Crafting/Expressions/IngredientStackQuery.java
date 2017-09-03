package io.github.winterbear.wintercore.WonderHaul.Crafting.Expressions;

import io.github.winterbear.wintercore.WonderHaul.Crafting.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Created by WinterBear on 02/09/2017.
 */
public class IngredientStackQuery implements IngredientQuery{

    ItemStack stack;

    Integer required;

    public IngredientStackQuery(ItemStack stack, Integer amount){
        this.stack = stack;
        this.required = amount;
    }

    @Override
    public boolean searchPlayer(Player player) {
        List<Ingredient> ingredients = IngredientUtils.getIngredients(player);
        Integer has = required;
        for(Ingredient ingredient : ingredients){
            if (ingredient.getName().equals(this.stack.getItemMeta().getDisplayName())){
                has += ingredient.getItemStack().getAmount();
            }
        }
        if (has >= required){
            return true;
        } else {
            return false;
        }
    }

    public void removeFromPlayer(Player player) {
        List<Ingredient> ingredients = IngredientUtils.getIngredients(player);
        Integer removed = 0;
        for(Ingredient ingredient : ingredients){
            if(removed < required ){
                boolean match = false;
                if(ingredient.getType().equals(IngredientType.METADATA)){
                    if(ingredient.equals(this.stack.getData())){
                        match = true;
                    }
                } else if (ingredient.getType().equals(IngredientType.NAME)){
                    if (ingredient.getName().equals(IngredientUtils.getIngredient(stack).getName())){
                        match = true;
                    }
                }

                if (match){
                    int amount = ingredient.getItemStack().getAmount();
                    int remaining = required - removed;
                    if(remaining > amount){
                        removed += amount;
                        player.getInventory().remove(ingredient.getItemStack());
                    } else {
                        ItemStack remove = ingredient.getItemStack().clone();
                        remove.setAmount(remaining);
                        player.getInventory().remove(remove);
                        removed = required;
                    }

                }
            }

        }
    }




}
