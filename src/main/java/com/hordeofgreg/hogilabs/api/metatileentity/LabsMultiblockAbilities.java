package com.hordeofgreg.hogilabs.api.metatileentity;

import net.minecraftforge.fluids.IFluidTank;

import gregtech.api.metatileentity.multiblock.MultiblockAbility;

@SuppressWarnings("InstantiationOfUtilityClass")
public class LabsMultiblockAbilities {

    public static final MultiblockAbility<IFluidTank> LIQUID_BLAST_HATCH = new MultiblockAbility<>(
            "liquid_blast_hatch");
}
