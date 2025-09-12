package com.hordeofgreg.hogilabs.api.material;

import static com.hordeofgreg.hogilabs.api.material.LabsMaterials.*;
import static com.hordeofgreg.hogilabs.api.utils.LabsUtil.labsId;
import static com.nomiceu.nomilabs.gregtech.material.registry.LabsMaterials.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

public class LabsThirdDegreeMaterials {

    private LabsThirdDegreeMaterials() {}

    public static void init() {
        IndustrialLubricant = new Material.Builder(11000, labsId("industrial_lubricant"))
                .fluid()
                .color(0x4a4532)
                .flags(DISABLE_DECOMPOSITION)
                .iconSet(MaterialIconSet.FLUID)
                .components(Graphene, 4, HighEfficiencyLubricant, 1)
                .build();

        CrocoiteSolution = new Material.Builder(11001, labsId("crocoite_solution"))
                .fluid()
                .color(0x915147).iconSet(MaterialIconSet.FLUID)
                .components(ChromicAcid, 1, LeadChloride, 1)
                .build();
    }
}
