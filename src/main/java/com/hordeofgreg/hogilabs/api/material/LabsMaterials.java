package com.hordeofgreg.hogilabs.api.material;

import gregtech.api.unification.material.Material;

public class LabsMaterials {

    // First degree materials 9000-9999
    public static Material Tungsten25Rhenium;
    public static Material MolybdenumDisulfide;
    public static Material RecrystallizedRhodiumSulfate;

    // Second degree materials 10000-10999
    public static Material LumiumPalladium;
    public static Material HighEfficiencyLubricant;

    // Third degree materials 11000-11999
    public static Material IndustrialLubricant;

    private LabsMaterials() {}

    public static void init() {
        LabsPropertyChange.init();
        LabsMaterialFlagAddition.init();
        LabsFirstDegreeMaterials.init();
        LabsSecondDegreeMaterials.init();
        LabsThirdDegreeMaterials.init();
    }
}
