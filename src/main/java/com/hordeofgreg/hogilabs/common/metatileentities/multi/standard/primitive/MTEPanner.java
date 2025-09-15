package com.hordeofgreg.hogilabs.common.metatileentities.multi.standard.primitive;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.jetbrains.annotations.NotNull;

import com.hordeofgreg.hogilabs.api.recipes.LabsRecipeMaps;
import com.hordeofgreg.hogilabs.api.render.LabsTextures;
import com.hordeofgreg.hogilabs.common.blocks.LabsMetaBlocks;
import com.hordeofgreg.hogilabs.common.blocks.block.BlockLargeMultiblockCasing;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.capability.impl.ItemHandlerList;
import gregtech.api.capability.impl.PrimitiveRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapPrimitiveMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.unification.material.Materials;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.*;

public class MTEPanner extends RecipeMapPrimitiveMultiblockController {

    public MTEPanner(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, LabsRecipeMaps.PANNER_RECIPES);
        recipeMapWorkable = new PrimitiveRecipeLogic(this, LabsRecipeMaps.PANNER_RECIPES) {

            @Override
            protected boolean shouldSearchForRecipes() {
                return super.shouldSearchForRecipes();
            }

            @Override
            protected boolean canProgressRecipe() {
                return super.canProgressRecipe();
            }
        };
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MTEPanner(metaTileEntityId);
    }

    @Override
    protected void updateFormedValid() {
        super.updateFormedValid();
    }

    @Override
    protected void initializeAbilities() {
        this.exportItems = new ItemHandlerList(getAbilities(MultiblockAbility.EXPORT_ITEMS));

        this.importFluids = new FluidTankList(true, getAbilities(MultiblockAbility.IMPORT_FLUIDS));
        this.exportFluids = new FluidTankList(false, getAbilities(MultiblockAbility.EXPORT_FLUIDS));
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);

        initializeAbilities();
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("FHHHF", "FHHHF", "F###F", "F###F", "FFFFF", "FHHHF")
                .aisle("HHHHH", "HAAAH", "#AAA#", "#AAA#", "FGGGF", "HAAAH")
                .aisle("HHHHH", "HAAAH", "#AAA#", "#AAA#", "FGGGF", "HAAAH")
                .aisle("HHHHH", "HAAAH", "#AAA#", "#AAA#", "FGGGF", "HAAAH")
                .aisle("FHHHF", "FOSOF", "F###F", "F###F", "FFFFF", "FHIHF")
                .where('S', selfPredicate())
                .where('H', states(getCasingState()))
                .where('O', states(getCasingState())
                        .or(abilities(MultiblockAbility.EXPORT_ITEMS)).setMaxGlobalLimited(1)
                        .or(abilities(MultiblockAbility.EXPORT_FLUIDS)).setMaxGlobalLimited(1))
                .where('I', abilities(MultiblockAbility.IMPORT_FLUIDS))
                .where('B', states(getCasingState()))
                .where('F', frames(Materials.Steel).or(frames(Materials.Bronze)))
                .where('G', states(getGrateCasingState()))
                .where('A', air())
                .where('#', any())
                .build();
    }

    private static IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID);
    }

    private static IBlockState getGrateCasingState() {
        return LabsMetaBlocks.LARGE_MULTIBLOCK_CASING
                .getState(BlockLargeMultiblockCasing.CasingType.BRONZE_GRATE_CASING);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return Textures.SOLID_STEEL_CASING;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        getFrontOverlay().renderOrientedState(renderState, translation, pipeline, getFrontFacing(),
                recipeMapWorkable.isActive(), recipeMapWorkable.isWorkingEnabled());
    }

    @SideOnly(Side.CLIENT)
    @Override
    protected @NotNull ICubeRenderer getFrontOverlay() {
        return LabsTextures.PANNER_OVERLAY;
    }

    @Override
    public boolean hasMaintenanceMechanics() {
        return false;
    }
}
