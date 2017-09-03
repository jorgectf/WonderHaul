package io.github.winterbear.wintercore.WonderHaul.Crafting;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Optional;

/**
 * Created by WinterBear on 02/09/2017.
 */
public class WonderHaulFoodIngredient extends NamedIngredient{

    String name;

    ItemStack itemStack;

    IngredientType type = IngredientType.NAME;

    public static boolean is(ItemStack itemStack){
        ItemMeta meta = itemStack.getItemMeta();
        List<String> lore = meta.getLore();
        for(String line : lore){
            if(line.contains("Food") && line.contains("❈")){
                return true;
            }
        }
        return false;
    }

    public static Ingredient create(ItemStack itemStack) throws InvalidItemStackException {
        if (is(itemStack)) {
            Optional<String> ingredientName = parseIngredientName(itemStack);
            if (!ingredientName.isPresent()) {
                throw new InvalidItemStackException("Parsing of Food Name Failed", itemStack);
            }
            Ingredient ingredient = new WonderhaulIngredient(parseIngredientName(itemStack).get(), itemStack);
            return ingredient;
        } else {
            throw new InvalidItemStackException("Not a WonderHaul Food Ingredient.", itemStack);
        }
    }


    public WonderHaulFoodIngredient(String name, ItemStack itemStack){
        this.name = name;
        this.itemStack = itemStack;
    };

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public ItemStack getItemStack() {
        return this.itemStack;
    }

    private static Optional<String> parseIngredientName(ItemStack itemStack){
        ItemMeta meta = itemStack.getItemMeta();
        List<String> lore = meta.getLore();
        for(String line : lore){
            if(line.contains("Food") && line.contains("❈")){
                String[] split = ChatColor.stripColor(line).split(": ");
                String name = split[split.length - 1];
                return Optional.of(name);
            }
        }
        return Optional.empty();
    }
}
