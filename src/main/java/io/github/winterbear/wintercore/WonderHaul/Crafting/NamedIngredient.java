package io.github.winterbear.wintercore.WonderHaul.Crafting;

import org.bukkit.material.MaterialData;

/**
 * Created by WinterBear on 02/09/2017.
 */
public abstract class NamedIngredient implements Ingredient{

    @Override
    public IngredientType getType() {
        return IngredientType.NAME;
    }

    public boolean equals(String string){
        return this.getName().equals(string);
    }

}
