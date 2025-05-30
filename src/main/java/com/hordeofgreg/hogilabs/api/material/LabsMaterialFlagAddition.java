package com.hordeofgreg.hogilabs.api.material;

import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.info.MaterialFlags;

public class LabsMaterialFlagAddition {

    public static void init() {
        Materials.Rhenium.addFlags(
                MaterialFlags.GENERATE_ROD,
                MaterialFlags.GENERATE_PLATE,
                MaterialFlags.GENERATE_FOIL,
                MaterialFlags.GENERATE_FRAME);
    }
}
