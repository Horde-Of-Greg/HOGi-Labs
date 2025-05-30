package com.hordeofgreg.hogilabs.api.material;

import gregtech.api.unification.material.Material;

public class LabsMaterials {

    // First degree materials 9000-9999
    public static Material Tungsten25Rhenium;

    private LabsMaterials() {}

    public static void init() {
        LabsPropertyChange.init();
        LabsMaterialFlagAddition.init();
        LabsFirstDegreeMaterials.init();
    }
}
