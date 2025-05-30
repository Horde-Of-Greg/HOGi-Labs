package com.hordeofgreg.hogilabs.api.material;

import static com.hordeofgreg.hogilabs.api.material.LabsMaterials.*;
import static com.hordeofgreg.hogilabs.api.utils.LabsUtil.labsId;
import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;
import gregtech.api.unification.material.properties.BlastProperty;

public class LabsFirstDegreeMaterials {

    private LabsFirstDegreeMaterials() {}

    public static void init() {
        Tungsten25Rhenium = new Material.Builder(9000, labsId("tungsten_25_rhenium"))
                .ingot().fluid()
                .color(0x190530).iconSet(MaterialIconSet.METALLIC)
                .cableProperties(VA[LuV], 2, 8)
                .flags(GENERATE_FOIL, GENERATE_PLATE)
                .components(Tungsten, 3, Rhenium, 1)
                .blast(builder -> builder
                        .temp(5200, BlastProperty.GasTier.HIGH)
                        .blastStats(VA[LuV], 35 * 20))
                .build();
    }
}
