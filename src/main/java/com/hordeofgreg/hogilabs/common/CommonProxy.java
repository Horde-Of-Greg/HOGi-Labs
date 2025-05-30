package com.hordeofgreg.hogilabs.common;

import java.util.Objects;
import java.util.function.Function;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hordeofgreg.hogilabs.Tags;
import com.hordeofgreg.hogilabs.common.blocks.LabsMetaBlocks;
import com.hordeofgreg.hogilabs.config.LabsConfig;

import gregtech.api.block.VariantItemBlock;

@Mod.EventBusSubscriber(modid = Tags.MODID)
public class CommonProxy {

    public static final Logger LOGGER = LogManager.getLogger(Tags.MODID);

    public void preLoad() {}

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        if (LabsConfig.advanced.activateVerboseLogging) {
            LOGGER.info("Registering blocks...");
        }
        IForgeRegistry<Block> registry = event.getRegistry();

        registry.register(LabsMetaBlocks.LARGE_MULTIBLOCK_CASING);
        registry.register(LabsMetaBlocks.LARGE_ACTIVE_MULTIBLOCK_CASING);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        if (LabsConfig.advanced.activateVerboseLogging) {
            LOGGER.info("Registering items...");
        }
        IForgeRegistry<Item> registry = event.getRegistry();

        registry.register(createItemBlock(LabsMetaBlocks.LARGE_MULTIBLOCK_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(LabsMetaBlocks.LARGE_ACTIVE_MULTIBLOCK_CASING, VariantItemBlock::new));
    }

    private static <T extends Block> ItemBlock createItemBlock(T block, Function<T, ItemBlock> producer) {
        ItemBlock itemBlock = producer.apply(block);
        itemBlock.setRegistryName(Objects.requireNonNull(block.getRegistryName()));
        return itemBlock;
    }
}
