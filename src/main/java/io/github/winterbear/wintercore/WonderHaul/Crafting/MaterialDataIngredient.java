package io.github.winterbear.wintercore.WonderHaul.Crafting;

import org.bukkit.material.MaterialData;

/**
 * Created by WinterBear on 02/09/2017.
 */
public abstract class MaterialDataIngredient implements Ingredient{

    @Override
    public IngredientType getType() {
        return IngredientType.METADATA;
    }

    public boolean equals(MaterialData data){
        return this.getItemStack().getData().equals(data);
    }

}
