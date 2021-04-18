package io.github.winterbear.wintercore.wonderhaul.equipment.microblocks.decorations;

import io.github.winterbear.wintercore.utils.WHInventoryType;
import io.github.winterbear.wintercore.wonderhaul.crafting.MaterialIngredient;
import io.github.winterbear.wintercore.wonderhaul.crafting.SpecialRecipe;
import io.github.winterbear.wintercore.wonderhaul.equipment.microblocks.specialcrafter.SpecialCrafter;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by WinterBear on 17/04/2021.
 */
public class Stonecutter extends SpecialCrafter {


    public Stonecutter(JavaPlugin plugin) {
        super(plugin);
    }

    @Override
    public String getDisplayName() {
        return "Carving Station";
    }

    @Override
    public Map<Integer, SpecialRecipe> getRecipes() {
        Map<Integer, SpecialRecipe> display = new HashMap<>();
        int slot = 0;

        for(CarvedStone carving : CarvedStone.values()){

            SpecialRecipe recipe = new SpecialRecipe(carving.getDisplayName(),
                    Collections.singletonList(
                            new MaterialIngredient(Material.STONE, 64)),
                    carving.generate());

            display.put(slot++, recipe);
        }

        return display;
    }

    @Override
    public WHInventoryType getInventoryType() {
        return WHInventoryType.SPECIAL_CRAFTER;
    }

    @Override
    public String getReference() {
        return "Carving Station";
    }
}
