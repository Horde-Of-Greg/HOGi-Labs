package com.hordeofgreg.hogilabs.api.capability.impl;

import gregtech.api.GTValues;
import gregtech.api.capability.impl.SteamMultiblockRecipeLogic;
import gregtech.api.metatileentity.multiblock.RecipeMapSteamMultiblockController;

public class BootstrapMultiWorkable extends SteamMultiblockRecipeLogic {

    public BootstrapMultiWorkable(RecipeMapSteamMultiblockController tileEntity, double conversionRate) {
        super(tileEntity, tileEntity.recipeMap, tileEntity.getSteamFluidTank(), conversionRate * 1.5);

        // SLOWS down by 2x, doesn't make it faster. Idk why GT did it this way but that's how it is
        setSpeedBonus(2);
    }

    @Override
    public long getMaxVoltage() {
        return GTValues.V[GTValues.HV];
    }
}
