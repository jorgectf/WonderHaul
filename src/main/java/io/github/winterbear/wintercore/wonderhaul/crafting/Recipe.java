package io.github.winterbear.wintercore.wonderhaul.crafting;

import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Recipe {

    private String displayName;

    private String reference;

    private int craftingTime;

    private List<Ingredient> ingredientList;

    private List<ItemStack> result;

    //Given a list of items, calculate what items would be left after crafting this recipe
    //Note - make sure to handle buckets and bottles
    public List<ItemStack> craft(List<ItemStack> items){
        //TODO implement

        return Collections.emptyList();
    }

    //Can this recipe succeed with the provided items as input
    public boolean canCraft(List<ItemStack> items){
        Map<Ingredient, Integer> ingredients = ingredientList.stream()
                .collect(Collectors.toMap(e -> e, Ingredient::getAmount));

        for(ItemStack item : items){
            for(Ingredient ingredient : ingredients.keySet()){
                if(ingredient.isValidIngredient(item)){
                    int newAmount = ingredients.get(ingredient) - item.getAmount();
                    newAmount = Math.max(newAmount, 0);
                    ingredients.put(ingredient, newAmount);
                }
            }
        }

        return ingredients.values().stream().noneMatch(v -> v > 0);
    }


}
