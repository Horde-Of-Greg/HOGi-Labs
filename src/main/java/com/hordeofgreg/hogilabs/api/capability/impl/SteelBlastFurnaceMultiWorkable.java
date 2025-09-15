package com.hordeofgreg.hogilabs.api.capability.impl;

import gregtech.api.capability.impl.PrimitiveRecipeLogic;
import gregtech.api.metatileentity.multiblock.RecipeMapPrimitiveMultiblockController;
import gregtech.api.recipes.RecipeMap;

public class SteelBlastFurnaceMultiWorkable extends PrimitiveRecipeLogic {

    public SteelBlastFurnaceMultiWorkable(RecipeMapPrimitiveMultiblockController tileEntity, RecipeMap<?> recipeMap) {
        super(tileEntity, recipeMap);
    }

    @Override
    protected long getMaxParallelVoltage() {
        return Integer.MAX_VALUE;
    }
}
