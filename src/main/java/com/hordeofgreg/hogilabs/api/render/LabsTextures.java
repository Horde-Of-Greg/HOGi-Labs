package com.hordeofgreg.hogilabs.api.render;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;

import com.hordeofgreg.hogilabs.Tags;

import gregtech.client.renderer.texture.cube.SimpleOverlayRenderer;

@Mod.EventBusSubscriber(modid = Tags.MODID, value = Side.CLIENT)
public final class LabsTextures {

    // Casings
    public static SimpleOverlayRenderer CATERPILLAR_CASING;
    public static SimpleOverlayRenderer CATERPILLAR_PIPE_CASING;
    public static SimpleOverlayRenderer CRUDE_STAINLESS_STEEL_CASING;

    private LabsTextures() {}

    public static void preInit() {
        // Casings
        CATERPILLAR_CASING = new SimpleOverlayRenderer("casings/large_multiblock_casing/solid/caterpillar_casing");
        CATERPILLAR_PIPE_CASING = new SimpleOverlayRenderer(
                "casings/large_multiblock_casing/pipe/caterpillar_pipe_casing");
        CRUDE_STAINLESS_STEEL_CASING = new SimpleOverlayRenderer(
                "casings/large_multiblock_casing/solid/crude_stainless_steel_casing");
    }
}
