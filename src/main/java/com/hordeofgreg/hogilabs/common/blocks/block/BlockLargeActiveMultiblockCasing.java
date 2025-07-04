package com.hordeofgreg.hogilabs.common.blocks.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import org.jetbrains.annotations.NotNull;

import gregtech.api.block.VariantActiveBlock;

public class BlockLargeActiveMultiblockCasing extends
                                              VariantActiveBlock<BlockLargeActiveMultiblockCasing.ActiveCasingType> {

    public BlockLargeActiveMultiblockCasing() {
        super(Material.IRON);
        setTranslationKey("large_active_multiblock_casing");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.METAL);
        setHarvestLevel("wrench", 2);
        setDefaultState(getState(ActiveCasingType.CATERPILLAR_ENGINE_INTAKE));
    }

    @Override
    public boolean canCreatureSpawn(@NotNull IBlockState state, @NotNull IBlockAccess world, @NotNull BlockPos pos,
                                    @NotNull EntityLiving.SpawnPlacementType type) {
        return false;
    }

    public enum ActiveCasingType implements IStringSerializable {

        // Regular casings
        CATERPILLAR_ENGINE_INTAKE("caterpillar_engine_intake");

        private final String name;

        ActiveCasingType(String name) {
            this.name = name;
        }

        @Override
        public @NotNull String getName() {
            return this.name;
        }
    }
}
