package com.hordeofgreg.hogilabs.api.render;

import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;

public final class LabsTextures {

    // Casings
    public static OrientedOverlayRenderer CATERPILLAR_CASING;

    private LabsTextures() {}

    public static void preInit() {
        // Casings
        CATERPILLAR_CASING = new OrientedOverlayRenderer("caterpillar_casing");
    }
}
