package com.hordeofgreg.hogilabs.common.blocks.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import org.jetbrains.annotations.NotNull;

import gregtech.api.block.VariantBlock;

public class BlockLargeMultiblockCasing extends VariantBlock<BlockLargeMultiblockCasing.CasingType> {

    public BlockLargeMultiblockCasing() {
        super(Material.IRON);
        setTranslationKey("large_multiblock_casing");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.METAL);
        setHarvestLevel("wrench", 2);
        setDefaultState(getState(CasingType.CATERPILLAR_CASING));
    }

    @Override
    public boolean canCreatureSpawn(@NotNull IBlockState state, @NotNull IBlockAccess world, @NotNull BlockPos pos,
                                    @NotNull EntityLiving.SpawnPlacementType type) {
        return false;
    }

    public enum CasingType implements IStringSerializable {

        // Solid casings
        CATERPILLAR_CASING("caterpillar_casing"),
        CRUDE_STAINLESS_STEEL_CASING("crude_stainless_steel_casing"),

        // Pipe casings
        CATERPILLAR_PIPE_CASING("caterpillar_pipe_casing");

        private final String name;

        CasingType(String name) {
            this.name = name;
        }

        @Override
        public @NotNull String getName() {
            return this.name;
        }
    }
}
