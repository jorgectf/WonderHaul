package io.github.winterbear.wintercore.WonderHaul.Crafting.Expressions;

import javafx.util.Pair;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by WinterBear on 02/09/2017.
 */
public class PlayerHasIngredientExpression {

    public boolean playerHasIngredient(Player player,ItemStack stack, int amount){
        IngredientStackQuery query = new IngredientStackQuery(stack, amount);
        return query.searchPlayer(player);
    }

    public boolean playerHasIngredient(Player player,IngredientQuery query){
        return query.searchPlayer(player);
    }

    public boolean playerHasAllIngredients(Player player, IngredientQuery... queries){
        int has = 0;
        for(IngredientQuery query : queries){
            if(query.searchPlayer(player)){
                has++;
            }
        }
        if(has == queries.length){
            return true;
        } else {
            return false;
        }
    }

}
