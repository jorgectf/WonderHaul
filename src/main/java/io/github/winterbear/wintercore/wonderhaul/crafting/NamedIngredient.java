package io.github.winterbear.wintercore.wonderhaul.crafting;

/**
 * Created by WinterBear on 02/09/2017.
 */
public abstract class NamedIngredient implements Ingredient{

    public boolean equals(String string){
        return this.getName().equals(string);
    }

}
