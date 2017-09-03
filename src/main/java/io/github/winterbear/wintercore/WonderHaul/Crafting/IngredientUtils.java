package io.github.winterbear.wintercore.WonderHaul.Crafting;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by WinterBear on 02/09/2017.
 */
public class IngredientUtils {

    public static List<Ingredient> getIngredients(Player player){
        return IngredientUtils.getIngredients(Arrays.asList(player.getInventory().getContents()));
    }

    public static Ingredient getIngredient(ItemStack item){
        return getIngredients(Arrays.asList(item)).get(0);
    }

    public static List<Ingredient> getIngredients(List<ItemStack> items){
        List<Ingredient> ingredients = new ArrayList<>();
        for(ItemStack item : items){
            Ingredient ingredient = new SimpleIngredient(item);
            if(WonderhaulIngredient.is(item)){
                try {
                    ingredient = WonderhaulIngredient.create(item);
                } catch (InvalidItemStackException e) {
                    e.printStackTrace();
                }
            } else if (WonderHaulFoodIngredient.is(item)){
                try {
                    ingredient = WonderHaulFoodIngredient.create(item);
                } catch (InvalidItemStackException e) {
                    e.printStackTrace();
                }
            }
            ingredients.add(ingredient);
        }
        return ingredients;
    }
}
