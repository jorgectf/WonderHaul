package io.github.winterbear.wintercore.wonderhaul.crafting;

import io.github.winterbear.wintercore.utils.ItemUtils;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by WinterBear on 03/08/2020.
 */
public class SpecialRecipe {

    private String displayName;

    private List<Ingredient> ingredients;

    private ItemStack result;

    public SpecialRecipe(String displayName, List<Ingredient> ingredients, ItemStack result) {
        this.displayName = displayName;
        this.ingredients = ingredients;
        this.result = result;
    }

    public String getDisplayName() {
        return displayName;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public ItemStack getResult() {
        return ItemUtils.oneOf(result);
    }

    public boolean canCraft(InventoryHolder player){
        Map<Ingredient, Integer> amount = new HashMap<>();
        ingredients.forEach(i -> amount.put(i, 0));
        for(ItemStack playerItem : player.getInventory()){
            Optional<Ingredient> validIngredient = ingredients.stream()
                    .filter(g -> g.isValidIngredient(playerItem))
                    .findAny();
            validIngredient.ifPresent(ingredient -> amount.put(ingredient, amount.get(ingredient) + playerItem.getAmount()));
        }
        return amount.entrySet().stream()
                .allMatch(e -> e.getValue() >= e.getKey().getAmount());


    }
}
