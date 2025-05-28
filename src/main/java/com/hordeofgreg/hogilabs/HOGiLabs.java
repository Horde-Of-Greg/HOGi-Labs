package com.hordeofgreg.hogilabs;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hordeofgreg.hogilabs.common.CommonProxy;
import com.hordeofgreg.hogilabs.common.blocks.LabsMetaBlocks;
import com.hordeofgreg.hogilabs.common.metatileentities.LabsMetaTileEntities;
import com.hordeofgreg.hogilabs.config.LabsConfig;

@Mod(modid = Tags.MODID,
     version = Tags.VERSION,
     name = Tags.MODNAME,
     acceptedMinecraftVersions = "[1.12.2]",
     dependencies = "required:forge@[14.23.5.2847,);" + "required-after:gregtech@[2.8,);" + "required-after:gcym;" +
             "required-after:nomilabs;")
public class HOGiLabs {

    public static final String MODID = Tags.MODID;
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    @SidedProxy(modId = MODID,
                clientSide = "com.hordeofgreg.hogilabs.common.ClientProxy",
                serverSide = "com.hordeofgreg.hogilabs.common.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        if (LabsConfig.advanced.activateVerboseLogging) {
            LOGGER.info("I am " + Tags.MODNAME + " + at version " + Tags.VERSION);
        }
        LabsMetaBlocks.init();
        LabsMetaTileEntities.init();

        proxy.preLoad();
    }

    @SubscribeEvent
    // Register recipes here (Remove if not needed)
    public void registerRecipes(RegistryEvent.Register<IRecipe> event) {}

    @SubscribeEvent
    // Register items here (Remove if not needed)
    public void registerItems(RegistryEvent.Register<Item> event) {}

    @SubscribeEvent
    // Register blocks here (Remove if not needed)
    public void registerBlocks(RegistryEvent.Register<Block> event) {}

    @EventHandler
    // load "Do your mod setup. Build whatever data structures you care about." (Remove if not needed)
    public void init(FMLInitializationEvent event) {}

    @EventHandler
    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) {}

    @EventHandler
    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {}
}
