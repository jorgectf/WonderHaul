package io.github.winterbear.wintercore.WonderHaul.Crafting.Expressions;

import io.github.winterbear.wintercore.WonderHaul.Crafting.Ingredient;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by WinterBear on 02/09/2017.
 */
public class RemoveIngredientsFromPlayerExpression {

    public void removeIngredientFromPlayer(Player player, ItemStack stack, int amount){
        IngredientStackQuery ingredientNameQuery = new IngredientStackQuery(stack, amount);
        ingredientNameQuery.removeFromPlayer(player);
    }

    public void removeIngredientFromPlayer(Player player, IngredientQuery query){
        query.removeFromPlayer(player);
    }

}
