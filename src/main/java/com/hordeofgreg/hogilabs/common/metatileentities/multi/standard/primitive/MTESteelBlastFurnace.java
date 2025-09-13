package com.hordeofgreg.hogilabs.common.metatileentities.multi.standard.primitive;

import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.jetbrains.annotations.NotNull;

import com.hordeofgreg.hogilabs.api.capability.impl.SteelBlastFurnaceMultiWorkable;
import com.hordeofgreg.hogilabs.api.metatileentity.LabsMultiblockAbility;
import com.hordeofgreg.hogilabs.api.recipes.LabsRecipeMaps;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.capability.impl.ItemHandlerList;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockDisplayText;
import gregtech.api.metatileentity.multiblock.RecipeMapPrimitiveMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockFireboxCasing;
import gregtech.common.blocks.BlockGlassCasing;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;

public class MTESteelBlastFurnace extends RecipeMapPrimitiveMultiblockController {

    private static final int PARALLEL_LIMIT = 1024;

    private static final int LAVA_DRAIN_AMOUNT = 50;

    private IFluidTank fuelTank;

    public MTESteelBlastFurnace(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, LabsRecipeMaps.STEEL_BLAST_FURNACE_RECIPES);
        recipeMapWorkable = new SteelBlastFurnaceMultiWorkable(this, LabsRecipeMaps.STEEL_BLAST_FURNACE_RECIPES) {

            @Override
            protected boolean shouldSearchForRecipes() {
                return hasFuel() && super.shouldSearchForRecipes();
            }

            @Override
            protected boolean canProgressRecipe() {
                return hasFuel() && super.canProgressRecipe();
            }
        };
        this.recipeMapWorkable.setParallelLimit(PARALLEL_LIMIT);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MTESteelBlastFurnace(metaTileEntityId);
    }

    @Override
    protected void updateFormedValid() {
        super.updateFormedValid();

        if (isActive() && getOffsetTimer() % 20 == 0) {
            fuelTank.drain(LAVA_DRAIN_AMOUNT, true);
        }
    }

    @Override
    protected void initializeAbilities() {
        this.importItems = new ItemHandlerList(getAbilities(MultiblockAbility.IMPORT_ITEMS));
        this.exportItems = new ItemHandlerList(getAbilities(MultiblockAbility.EXPORT_ITEMS));

        this.importFluids = new FluidTankList(true, getAbilities(MultiblockAbility.IMPORT_FLUIDS));
        this.exportFluids = new FluidTankList(false, getAbilities(MultiblockAbility.EXPORT_FLUIDS));
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);

        initializeAbilities();

        fuelTank = getAbilities(LabsMultiblockAbility.LIQUID_BLAST_HATCH).get(0);
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        MultiblockDisplayText.builder(textList, isStructureFormed())
                .setWorkingStatus(recipeMapWorkable.isWorkingEnabled(), recipeMapWorkable.isActive())
                .addParallelsLine(recipeMapWorkable.getParallelLimit())
                .addWorkingStatusLine()
                .addProgressLine(recipeMapWorkable.getProgressPercent());
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("FFF", "XOX", "XXX")
                .aisle("FFF", "G#G", "XMX")
                .aisle("FFF", "G#G", "XMX")
                .aisle("FFF", "G#G", "XMX")
                .aisle("FEF", "XSX", "XXX")
                .where('S', selfPredicate())
                .where('X', states(getCasingState()))
                .where('F', states(getFireboxCasingState()).setMinGlobalLimited(10)
                        .or(abilities(MultiblockAbility.IMPORT_ITEMS)
                                .or(abilities(MultiblockAbility.IMPORT_FLUIDS).setMaxGlobalLimited(1))))
                .where('G', states(getGlassCasingState(), getCasingState()))
                .where('O', abilities(MultiblockAbility.EXPORT_ITEMS))
                .where('M', abilities(MultiblockAbility.MUFFLER_HATCH))
                .where('E', abilities(LabsMultiblockAbility.LIQUID_BLAST_HATCH))
                .where('#', air())
                .build();
    }

    private static IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(
                BlockMetalCasing.MetalCasingType.STEEL_SOLID);
    }

    private static IBlockState getFireboxCasingState() {
        return MetaBlocks.BOILER_FIREBOX_CASING.getState(
                BlockFireboxCasing.FireboxCasingType.STEEL_FIREBOX);
    }

    private static IBlockState getGlassCasingState() {
        return MetaBlocks.TRANSPARENT_CASING.getState(
                BlockGlassCasing.CasingType.TEMPERED_GLASS);
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
        return Textures.PRIMITIVE_BLAST_FURNACE_OVERLAY;
    }

    @Override
    public boolean hasMaintenanceMechanics() {
        return false;
    }

    @Override
    public boolean hasMufflerMechanics() {
        return true;
    }

    private boolean hasFuel() {
        return fuelTank.getFluidAmount() >= LAVA_DRAIN_AMOUNT;
    }
}
