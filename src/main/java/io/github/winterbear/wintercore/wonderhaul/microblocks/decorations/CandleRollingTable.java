package io.github.winterbear.wintercore.wonderhaul.microblocks.decorations;

import io.github.winterbear.wintercore.wonderhaul.crafting.MaterialIngredient;
import io.github.winterbear.wintercore.wonderhaul.crafting.SpecialRecipe;
import io.github.winterbear.wintercore.wonderhaul.microblocks.specialcrafter.SpecialCrafter;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by WinterBear on 17/04/2021.
 */
public class CandleRollingTable extends SpecialCrafter {

    public CandleRollingTable(JavaPlugin plugin) {
        super(plugin);
    }

    @Override
    public String getDisplayName() {
        return "Candle Rolling Table";
    }

    @Override
    public Map<Integer, SpecialRecipe> getRecipes() {
        Map<Integer, SpecialRecipe> display = new HashMap<>();
        int slot = 0;

        for(CandleGenerator.Candles candle : CandleGenerator.Candles.values()){

            SpecialRecipe recipe = new SpecialRecipe(candle.getDisplayName(),
                    Arrays.asList(
                            new MaterialIngredient(candle.getDye(), 64),
                            new MaterialIngredient(Material.HONEYCOMB, 1)),
                    CandleGenerator.generate(candle));

            display.put(slot++, recipe);
        }

        return display;
    }

    @Override
    public String getReference() {
        return "Candle Rolling Table";
    }
}
