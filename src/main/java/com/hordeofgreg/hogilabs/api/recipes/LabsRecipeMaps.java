package com.hordeofgreg.hogilabs.api.recipes;

import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;

public class LabsRecipeMaps {

    public static final RecipeMap<SimpleRecipeBuilder> MICRO_ASSEMBLER_T1_RECIPES = new RecipeMap<>(
            "microminer_assembler_t1", 12, 1, 3, 0, new SimpleRecipeBuilder(), false);
}
