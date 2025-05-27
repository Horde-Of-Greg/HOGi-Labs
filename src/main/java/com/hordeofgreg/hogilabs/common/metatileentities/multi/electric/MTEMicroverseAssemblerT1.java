package com.hordeofgreg.hogilabs.common.metatileentities.multi.electric;

import net.minecraft.util.ResourceLocation;

import org.jetbrains.annotations.NotNull;

import com.hordeofgreg.hogilabs.api.recipes.LabsRecipeMaps;
import com.nomiceu.nomilabs.LabsTextures;
import com.nomiceu.nomilabs.block.registry.LabsBlocks;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.client.renderer.ICubeRenderer;

public class MTEMicroverseAssemblerT1 extends RecipeMapMultiblockController {

    public MTEMicroverseAssemblerT1(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, LabsRecipeMaps.MICRO_ASSEMBLER_T1_RECIPES);
    }

    protected TraceabilityPredicate getCasingState() {
        return states(LabsBlocks.MICROVERSE_CASING.getDefaultState());
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXX", "XXX", "XXX")
                .aisle("XXX", "X#X", "XXX")
                .aisle("XXX", "XSX", "XXX")
                .where('S', selfPredicate())
                .where('X',
                        getCasingState().setMinGlobalLimited(15)
                                .or(autoAbilities(true, true, true, true, true, false, false)))
                .where('#', air())
                .build();
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return LabsTextures.MICROVERSE_CASING;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity metaTileEntityHolder) {
        return new MTEMicroverseAssemblerT1(this.metaTileEntityId);
    }
}
