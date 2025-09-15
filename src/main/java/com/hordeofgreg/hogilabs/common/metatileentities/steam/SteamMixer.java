package com.hordeofgreg.hogilabs.common.metatileentities.steam;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandlerModifiable;

import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.capability.impl.NotifiableFluidTank;
import gregtech.api.capability.impl.NotifiableItemStackHandler;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.ProgressWidget.MoveType;
import gregtech.api.gui.widgets.TankWidget;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.SteamMetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.recipes.RecipeMaps;
import gregtech.client.particle.VanillaParticleEffects;
import gregtech.client.renderer.texture.Textures;

public class SteamMixer extends SteamMetaTileEntity {

    private NotifiableFluidTank inputFluidTank;
    private NotifiableFluidTank outputFluidTank;

    private void ensureTanks() {
        if (inputFluidTank == null) inputFluidTank = new NotifiableFluidTank(8000, this, false);
        if (outputFluidTank == null) outputFluidTank = new NotifiableFluidTank(8000, this, true);
    }

    public SteamMixer(ResourceLocation metaTileEntityId, boolean isHighPressure) {
        super(metaTileEntityId, RecipeMaps.MIXER_RECIPES, Textures.MIXER_OVERLAY, isHighPressure);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new SteamMixer(metaTileEntityId, isHighPressure);
    }

    @Override
    protected IItemHandlerModifiable createImportItemHandler() {
        return new NotifiableItemStackHandler(this, 3, this, false);
    }

    @Override
    protected IItemHandlerModifiable createExportItemHandler() {
        return new NotifiableItemStackHandler(this, 1, this, true);
    }

    @Override
    public FluidTankList createImportFluidHandler() {
        ensureTanks();
        FluidTankList base = super.createImportFluidHandler(); // steam is guaranteed here
        List<IFluidTank> tanks = new ArrayList<>(base.getFluidTanks());
        tanks.add(inputFluidTank);
        return new FluidTankList(false, tanks);
    }

    @Override
    public FluidTankList createExportFluidHandler() {
        ensureTanks();
        return new FluidTankList(false, outputFluidTank);
    }

    @Override
    public ModularUI createUI(EntityPlayer player) {
        ensureTanks();
        return createUITemplate(player)
                // Item slots
                .slot(this.importItems, 0, 53, 25, GuiTextures.SLOT_STEAM.get(isHighPressure),
                        GuiTextures.DUST_OVERLAY_STEAM.get(isHighPressure))
                .slot(this.importItems, 1, 35, 25, GuiTextures.SLOT_STEAM.get(isHighPressure),
                        GuiTextures.DUST_OVERLAY_STEAM.get(isHighPressure))
                .slot(this.importItems, 2, 17, 25, GuiTextures.SLOT_STEAM.get(isHighPressure),
                        GuiTextures.DUST_OVERLAY_STEAM.get(isHighPressure))

                .slot(this.exportItems, 0, 107, 25, true, false, GuiTextures.SLOT_STEAM.get(isHighPressure))

                // tank visuals
                .widget(new TankWidget(inputFluidTank, 17, 43, 18, 18).setBackgroundTexture(GuiTextures.FLUID_SLOT)
                        .setDrawHoveringText(false).setContainerClicking(true, true))
                .widget(new TankWidget(outputFluidTank, 125, 25, 18, 18).setBackgroundTexture(GuiTextures.FLUID_SLOT)
                        .setDrawHoveringText(false).setContainerClicking(true, true))
                .progressBar(workableHandler::getProgressPercent, 79, 26, 20, 20,
                        GuiTextures.PROGRESS_BAR_MIXER, MoveType.HORIZONTAL,
                        workableHandler.getRecipeMap())
                .build(getHolder(), player);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick() {
        if (isActive()) {
            VanillaParticleEffects.defaultFrontEffect(this, EnumParticleTypes.CLOUD);
        }
    }
}
