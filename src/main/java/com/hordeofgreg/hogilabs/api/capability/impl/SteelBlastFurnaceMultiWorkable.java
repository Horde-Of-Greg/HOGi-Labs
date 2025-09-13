package com.hordeofgreg.hogilabs.api.capability.impl;

import gregtech.api.capability.impl.PrimitiveRecipeLogic;
import gregtech.api.metatileentity.multiblock.RecipeMapPrimitiveMultiblockController;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.util.GTUtility;

public class SteelBlastFurnaceMultiWorkable extends PrimitiveRecipeLogic {
    ;

    public SteelBlastFurnaceMultiWorkable(RecipeMapPrimitiveMultiblockController tileEntity, RecipeMap<?> recipeMap) {
        super(tileEntity, recipeMap);
    }

    @Override
    public void setupRecipe(Recipe recipe) {
        this.progressTime = 1;
        this.maxProgressTime = 10 * 20;
        this.recipeEUt = overclockResults[0];
        int recipeTier = GTUtility.getTierByVoltage(recipe.getEUt());
        int machineTier = getOverclockForTier(getMaximumOverclockVoltage());
        this.fluidOutputs = GTUtility
                .copyFluidList(recipe.getResultFluidOutputs(recipeTier, machineTier, getRecipeMap()));
        this.itemOutputs = GTUtility
                .copyStackList(recipe.getResultItemOutputs(recipeTier, machineTier, getRecipeMap()));

        if (this.wasActiveAndNeedsUpdate) {
            this.wasActiveAndNeedsUpdate = false;
        } else {
            this.setActive(true);
        }
    }
}
