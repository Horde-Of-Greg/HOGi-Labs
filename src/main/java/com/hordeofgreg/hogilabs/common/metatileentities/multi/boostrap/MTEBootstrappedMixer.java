package com.hordeofgreg.hogilabs.common.metatileentities.multi.boostrap;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

import org.jetbrains.annotations.NotNull;

import com.hordeofgreg.hogilabs.api.capability.impl.BootstrapMultiWorkable;
import com.hordeofgreg.hogilabs.api.render.LabsTextures;
import com.hordeofgreg.hogilabs.common.blocks.LabsMetaBlocks;
import com.hordeofgreg.hogilabs.common.blocks.block.BlockLargeMultiblockCasing;

import gregtech.api.capability.IMufflerHatch;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapSteamMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.recipes.RecipeMaps;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockGlassCasing;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.BlockTurbineCasing;
import gregtech.common.blocks.MetaBlocks;

public class MTEBootstrappedMixer extends RecipeMapSteamMultiblockController {

    private static final int PARALLEL_LIMIT = 16;

    public MTEBootstrappedMixer(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, RecipeMaps.MIXER_RECIPES, CONVERSION_RATE);
        this.recipeMapWorkable = new BootstrapMultiWorkable(this, CONVERSION_RATE);
        this.recipeMapWorkable.setParallelLimit(PARALLEL_LIMIT);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MTEBootstrappedMixer(metaTileEntityId);
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("#######", "#XXXXX#", "#XCMCX#", "#XCMCX#", "#XCMCX#", "#XCMCX#", "#XCMCX#", "#XXXXX#",
                        "#######")
                .aisle("#XXXXX#", "XAAAAAX", "XAAAAAX", "XAAAAAX", "XAAAAAX", "XAAAAAX", "XAAAAAX", "XAAAAAX",
                        "#XXXXX#")
                .aisle("#XCCCX#", "XAAAAAX", "GABAAAG", "GAAAAAG", "GAABBAG", "GAAAAAG", "GABAAAG", "XAAAAAX",
                        "#XCCCX#")
                .aisle("#XCCCX#", "XAARAAX", "GABRBAG", "GAARAAG", "GAARAAG", "GAARAAG", "GABRBAG", "XAARAAX",
                        "#XCRCX#")
                .aisle("#XCCCX#", "XAAAAAX", "GAAABAG", "GAAAAAG", "GABBAAG", "GAAAAAG", "GAAABAG", "XAAAAAX",
                        "#XCCCX#")
                .aisle("#XXXXX#", "XAAAAAX", "XAAAAAX", "XAAAAAX", "XAAAAAX", "XAAAAAX", "XAAAAAX", "XAAAAAX",
                        "#XXXXX#")
                .aisle("#######", "#XXXXX#", "#XCSCX#", "#XCCCX#", "#XCCCX#", "#XCCCX#", "#XCCCX#", "#XXXXX#",
                        "#######")
                .where('S', selfPredicate())
                .where('X',
                        states(getCasingState()).setMinGlobalLimited(105)
                                .or(autoAbilities(true, true, true, true, false)))
                .where('C', states(getStainlessCasingState()).setMinGlobalLimited(35))
                .where('G', states(getGlassCasingState(), getStainlessCasingState()))
                .where('R', states(getRotationCasingState()))
                .where('M', abilities(MultiblockAbility.MUFFLER_HATCH))
                .where('B', states(getCasingState()))
                .where('A', air())
                .where('#', any())
                .build();
    }

    public IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(
                BlockMetalCasing.MetalCasingType.STEEL_SOLID);
    }

    public IBlockState getStainlessCasingState() {
        return LabsMetaBlocks.LARGE_MULTIBLOCK_CASING.getState(
                BlockLargeMultiblockCasing.CasingType.CRUDE_STAINLESS_STEEL_CASING);
    }

    public IBlockState getGlassCasingState() {
        return MetaBlocks.TRANSPARENT_CASING.getState(
                BlockGlassCasing.CasingType.TEMPERED_GLASS);
    }

    public IBlockState getRotationCasingState() {
        return MetaBlocks.TURBINE_CASING.getState(
                BlockTurbineCasing.TurbineCasingType.STEEL_GEARBOX);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        if (sourcePart == null || (sourcePart instanceof IMufflerHatch)) {
            // If the block is either the controller or a muffler hatch
            return LabsTextures.CRUDE_STAINLESS_STEEL_CASING;
        } else {
            return Textures.SOLID_STEEL_CASING;
        }
    }

    @Override
    protected @NotNull ICubeRenderer getFrontOverlay() {
        return Textures.MIXER_OVERLAY;
    }

    @Override
    public boolean hasMufflerMechanics() {
        return true;
    }
}
