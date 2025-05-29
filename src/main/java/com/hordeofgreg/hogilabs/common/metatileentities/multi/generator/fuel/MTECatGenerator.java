package com.hordeofgreg.hogilabs.common.metatileentities.multi.generator.fuel;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

import org.jetbrains.annotations.NotNull;

import com.hordeofgreg.hogilabs.api.render.LabsTextures;
import com.hordeofgreg.hogilabs.common.blocks.LabsMetaBlocks;
import com.hordeofgreg.hogilabs.common.blocks.block.BlockLargeActiveMultiblockCasing;
import com.hordeofgreg.hogilabs.common.blocks.block.BlockLargeMultiblockCasing;

import gregtech.api.GTValues;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.FuelMultiblockController;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.recipes.RecipeMaps;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;

public class MTECatGenerator extends FuelMultiblockController {

    public MTECatGenerator(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, RecipeMaps.COMBUSTION_GENERATOR_FUELS, GTValues.LuV);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MTECatGenerator(this.metaTileEntityId);
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXXPPPPPPPPC", "XXX##IIIIIIC", "XXX########C")
                .aisle("XPPPPPPPPPPC", "XPPPPPPPPPPM", "XXX##IIIIIIC")
                .aisle("XXXPPPPPPPPC", "XSX##IIIIIIC", "XXX########C")
                .where('S', selfPredicate())
                .where('X', states(getCasingState()).setMinGlobalLimited(15)
                        .or(maintenancePredicate())
                        .or(abilities(MultiblockAbility.OUTPUT_ENERGY).setMaxGlobalLimited(1).setMinGlobalLimited(1))
                        .or(abilities(MultiblockAbility.IMPORT_FLUIDS).setMaxGlobalLimited(3)))
                .where('P', states(getPipeCasingState()))
                .where('I', states(getIntakeCasingState()))
                .where('C', states(getCasingState()))
                .where('M', abilities(MultiblockAbility.MUFFLER_HATCH))
                .where('#', any())
                .build();
    }

    private static IBlockState getCasingState() {
        return LabsMetaBlocks.LARGE_MULTIBLOCK_CASING.getState(
                BlockLargeMultiblockCasing.CasingType.CATERPILLAR_CASING);
    }

    private static IBlockState getPipeCasingState() {
        return LabsMetaBlocks.LARGE_MULTIBLOCK_CASING.getState(
                BlockLargeMultiblockCasing.CasingType.CATERPILLAR_PIPE_CASING);
    }

    private static IBlockState getIntakeCasingState() {
        return LabsMetaBlocks.LARGE_ACTIVE_MULTIBLOCK_CASING.getState(
                BlockLargeActiveMultiblockCasing.ActiveCasingType.CATERPILLAR_ENGINE_INTAKE);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return LabsTextures.CATERPILLAR_CASING;
    }

    @Override
    protected @NotNull ICubeRenderer getFrontOverlay() {
        return Textures.EXTREME_COMBUSTION_ENGINE_OVERLAY;
    }

    @Override
    public boolean hasMufflerMechanics() {
        return true;
    }
}
