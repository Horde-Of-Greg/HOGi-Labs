package com.hordeofgreg.hogilabs.api.recipes.builders;

import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.recipeproperties.PrimitiveProperty;
import gregtech.api.util.ValidationResult;

public class SteelBlastFurnaceRecipeBuilder extends RecipeBuilder<SteelBlastFurnaceRecipeBuilder> {

    public SteelBlastFurnaceRecipeBuilder() {}

    public SteelBlastFurnaceRecipeBuilder(Recipe recipe, RecipeMap<SteelBlastFurnaceRecipeBuilder> recipeMap) {
        super(recipe, recipeMap);
    }

    public SteelBlastFurnaceRecipeBuilder(RecipeBuilder<SteelBlastFurnaceRecipeBuilder> recipeBuilder) {
        super(recipeBuilder);
    }

    @Override
    public SteelBlastFurnaceRecipeBuilder copy() {
        return new SteelBlastFurnaceRecipeBuilder(this);
    }

    @Override
    public ValidationResult<Recipe> build() {
        this.EUt(1); // secretly force to 1 to allow recipe matching to work properly
        this.duration(1000 * 20); // I'm cheating too
        applyProperty(PrimitiveProperty.getInstance(), true);
        return super.build();
    }
}
