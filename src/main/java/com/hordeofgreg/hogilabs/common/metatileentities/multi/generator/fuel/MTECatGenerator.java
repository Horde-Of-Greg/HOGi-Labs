package com.hordeofgreg.hogilabs.common.metatileentities.multi.generator.fuel;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import org.jetbrains.annotations.NotNull;

import com.hordeofgreg.hogilabs.api.render.LabsTextures;
import com.hordeofgreg.hogilabs.common.blocks.LabsMetaBlocks;
import com.hordeofgreg.hogilabs.common.blocks.block.BlockLargeActiveMultiblockCasing;
import com.hordeofgreg.hogilabs.common.blocks.block.BlockLargeMultiblockCasing;

import gregtech.api.GTValues;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.MultiblockFuelRecipeLogic;
import gregtech.api.fluids.store.FluidStorageKeys;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.FuelMultiblockController;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Materials;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;

public class MTECatGenerator extends FuelMultiblockController {

    private final static boolean boostAllowed = true;

    private final int tier;

    public MTECatGenerator(ResourceLocation metaTileEntityId, int tier) {
        super(metaTileEntityId, RecipeMaps.COMBUSTION_GENERATOR_FUELS, tier);
        this.recipeMapWorkable = new IndustrialCombustionEngineWorkableHandler(this);
        this.recipeMapWorkable.setMaximumOverclockVoltage(GTValues.V[tier]);
        this.tier = tier;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MTECatGenerator(this.metaTileEntityId, tier);
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

    @Override
    protected boolean shouldShowVoidingModeButton() {
        return false;
    }

    public boolean isBoostAllowed() {
        return boostAllowed;
    }

    private static class IndustrialCombustionEngineWorkableHandler extends MultiblockFuelRecipeLogic {

        private boolean isFluorineBoosted = false;
        private boolean isOxygenBoosted = false;

        private final MTECatGenerator catEngine;
        private final int tier;

        private static final FluidStack LUBRICANT_STACK = Materials.Lubricant.getFluid(100);
        private static final FluidStack BOOSTED_LUBRICANT_STACK = Materials.Lubricant.getFluid(1000);
        private static final FluidStack LIQUID_FLUORINE_STACK = Materials.Fluorine.getFluid(200);
        private static final FluidStack LIQUID_OXYGEN_STACK = Materials.Oxygen.getFluid(FluidStorageKeys.LIQUID, 400);

        public IndustrialCombustionEngineWorkableHandler(RecipeMapMultiblockController tileEntity) {
            super(tileEntity);
            this.catEngine = (MTECatGenerator) tileEntity;
            this.tier = GTValues.LuV;
        }

        @Override
        protected void updateRecipeProgress() {
            if (canRecipeProgress && drawEnergy(recipeEUt, true)) {
                drainLubricant();
                drainFluorine();
                drainOxygen();
                drawEnergy(recipeEUt, false);

                if (++progressTime > maxProgressTime) {
                    completeRecipe();
                }
            }
        }

        protected void checkFluorine() {
            if (catEngine.isBoostAllowed()) {
                IMultipleTankHandler inputTank = catEngine.getInputFluidInventory();
                isFluorineBoosted = LIQUID_FLUORINE_STACK
                        .isFluidStackIdentical(inputTank.drain(LIQUID_FLUORINE_STACK, false));
            }
        }

        protected void drainFluorine() {
            if (isFluorineBoosted && totalContinuousRunningTime % 20 == 0) {
                catEngine.getInputFluidInventory().drain(LIQUID_FLUORINE_STACK, true);
            }
        }

        protected void checkOxygen() {
            if (catEngine.isBoostAllowed()) {
                IMultipleTankHandler inputTank = catEngine.getInputFluidInventory();
                isOxygenBoosted = LIQUID_OXYGEN_STACK
                        .isFluidStackIdentical(inputTank.drain(LIQUID_OXYGEN_STACK, false));
            }
        }

        protected void drainOxygen() {
            if (totalContinuousRunningTime % 20 == 0) {
                catEngine.getInputFluidInventory().drain(LIQUID_OXYGEN_STACK, true);
            }
        }

        protected boolean checkLubricant() {
            IMultipleTankHandler inputTank = catEngine.getInputFluidInventory();
            FluidStack lubricantStack = isFluorineBoosted ? BOOSTED_LUBRICANT_STACK : LUBRICANT_STACK;
            if (lubricantStack.isFluidStackIdentical(inputTank.drain(lubricantStack, false))) {
                return true;
            } else {
                invalidate();
                return false;
            }
        }

        protected void drainLubricant() {
            FluidStack lubricantStack = isFluorineBoosted ? BOOSTED_LUBRICANT_STACK : LUBRICANT_STACK;
            if (totalContinuousRunningTime == 1 || totalContinuousRunningTime % 72 == 0) {
                IMultipleTankHandler inputTank = catEngine.getInputFluidInventory();
                inputTank.drain(lubricantStack, true);
            }
        }

        @Override
        protected boolean shouldSearchForRecipes() {
            checkFluorine();
            checkOxygen();
            return super.shouldSearchForRecipes() && checkLubricant();
        }

        @Override
        protected boolean canProgressRecipe() {
            return super.canProgressRecipe() && checkLubricant();
        }

        @Override
        public long getMaxVoltage() {
            if (isFluorineBoosted)
                return GTValues.V[tier] * 4; // 4x boost
            if (isOxygenBoosted)
                return GTValues.V[tier] * 2; // 2x boost
            else
                return GTValues.LuV;
        }

        @Override
        protected long boostProduction(long production) {
            if (isFluorineBoosted)
                return production * 3; // 3x boost in efficiency
            // Result : 12x Total Boost (4x comsumption, 3x production)
            // 393 216 EU/t = 3A ZPM
            if (isOxygenBoosted)
                return production * 2; // 2x boost in efficiency
            // Result : 4x Total Boost (2x comsumption, 2x production)
            // 131 072 EU/t = 1A ZPM
            return production;
        }

        @Override
        public void invalidate() {
            super.invalidate();
            isFluorineBoosted = false;
            isOxygenBoosted = false;
        }
    }
}
