package com.hordeofgreg.hogilabs.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.jetbrains.annotations.NotNull;

import com.hordeofgreg.hogilabs.common.blocks.block.*;
import com.hordeofgreg.hogilabs.common.blocks.block.BlockLargeMultiblockCasing;

import gregtech.common.blocks.MetaBlocks;

public final class LabsMetaBlocks {

    private LabsMetaBlocks() {}

    public static BlockLargeMultiblockCasing LARGE_MULTIBLOCK_CASING;
    public static BlockLargeActiveMultiblockCasing LARGE_ACTIVE_MULTIBLOCK_CASING;
    public static BlockWireCoil WIRE_COIL;

    public static void init() {
        LARGE_MULTIBLOCK_CASING = new BlockLargeMultiblockCasing();
        LARGE_MULTIBLOCK_CASING.setRegistryName("large_multiblock_casing");

        LARGE_ACTIVE_MULTIBLOCK_CASING = new BlockLargeActiveMultiblockCasing();
        LARGE_ACTIVE_MULTIBLOCK_CASING.setRegistryName("large_active_multiblock_casing");

        WIRE_COIL = new BlockWireCoil();
        WIRE_COIL.setRegistryName("wire_coil");
    }

    @SideOnly(Side.CLIENT)
    public static void registerItemModels() {
        // Active models
        LARGE_ACTIVE_MULTIBLOCK_CASING.onModelRegister();
        WIRE_COIL.onModelRegister();

        // Static models
        registerItemModel(LARGE_MULTIBLOCK_CASING);
    }

    @SideOnly(Side.CLIENT)
    private static void registerItemModel(@NotNull Block block) {
        for (IBlockState state : block.getBlockState().getValidStates()) {
            // noinspection ConstantConditions
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block),
                    block.getMetaFromState(state),
                    new ModelResourceLocation(block.getRegistryName(),
                            MetaBlocks.statePropertiesToString(state.getProperties())));
        }
    }

    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> @NotNull String getPropertyName(@NotNull IProperty<T> property,
                                                                             Comparable<?> value) {
        return property.getName((T) value);
    }
}
