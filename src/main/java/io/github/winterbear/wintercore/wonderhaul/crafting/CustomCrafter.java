package io.github.winterbear.wintercore.wonderhaul.crafting;

import java.util.List;

public class CustomCrafter {

    private String displayName;

    private String reference;

    private List<Recipe> recipes;

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}
