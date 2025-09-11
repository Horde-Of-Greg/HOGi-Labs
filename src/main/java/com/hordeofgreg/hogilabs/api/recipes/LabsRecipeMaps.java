package com.hordeofgreg.hogilabs.api.recipes;

import static gregtech.api.recipes.RecipeMaps.*;

import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.PrimitiveRecipeBuilder;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;

public class LabsRecipeMaps {

    public static final RecipeMap<SimpleRecipeBuilder> MICRO_ASSEMBLER_T1_RECIPES = new RecipeMap<>(
            "microminer_assembler_t1", 12, 1, 3, 0, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<PrimitiveRecipeBuilder> STEEL_BLAST_FURNACE_RECIPES = new RecipeMap<>(
            "steel_blast_furnace", 9, 1, 1, 0, new PrimitiveRecipeBuilder(), false);

    public static void modifyMaps() {
        COKE_OVEN_RECIPES.setMaxFluidInputs(1);
    }
}
