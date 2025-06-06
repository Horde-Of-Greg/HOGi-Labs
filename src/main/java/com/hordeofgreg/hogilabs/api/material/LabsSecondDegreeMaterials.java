package com.hordeofgreg.hogilabs.api.material;

import static com.hordeofgreg.hogilabs.api.material.LabsMaterials.*;
import static com.hordeofgreg.hogilabs.api.utils.LabsUtil.labsId;
import static com.nomiceu.nomilabs.gregtech.material.registry.LabsMaterials.*;
import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;
import gregtech.api.unification.material.properties.BlastProperty;

public class LabsSecondDegreeMaterials {

    private LabsSecondDegreeMaterials() {}

    public static void init() {
        LumiumPalladium = new Material.Builder(10000, labsId("lumium_palladium"))
                .ingot().liquid()
                .color(0xc4d11d).iconSet(MaterialIconSet.METALLIC)
                .flags(
                        GENERATE_PLATE,
                        GENERATE_SMALL_GEAR,
                        GENERATE_GEAR,
                        GENERATE_ROD,
                        GENERATE_LONG_ROD,
                        GENERATE_RING,
                        GENERATE_ROTOR,
                        GENERATE_BOLT_SCREW)
                .components(Lumium, 2, Palladium, 3)
                .blast(builder -> builder
                        .temp(5200, BlastProperty.GasTier.HIGH)
                        .blastStats(VA[LuV], 70 * 20))
                .build();

        RecrystallizedRhodiumSulfate = new Material.Builder(10001, labsId("recrystallized_rhodium_sulfate"))
                .dust()
                .color(0xEEAA55).iconSet(MaterialIconSet.FINE)
                .flags(DISABLE_DECOMPOSITION)
                .components(RhodiumSulfate, 1)
                .build();

        HighEfficiencyLubricant = new Material.Builder(10002, labsId("high_efficiency_lubricant"))
                .fluid()
                .color(0x8f8561).iconSet(MaterialIconSet.FLUID)
                .components(MolybdenumDisulfide, 2, Lubricant, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build();

        NaquadahSolutionHeavy = new Material.Builder(10003, labsId("heavy_naquadah_solution"))
                .fluid()
                .color(0x62bd3e).iconSet(MaterialIconSet.FLUID)
                .build();

        NaquadahSolutionSuperheavy = new Material.Builder(10004, labsId("superheavy_naquadah_solution"))
                .fluid()
                .color(0x59ab39).iconSet(MaterialIconSet.FLUID)
                .build();

        NaquadahSolution = new Material.Builder(10005, labsId("naquadah_solution"))
                .fluid()
                .color(0x69db3d).iconSet(MaterialIconSet.FLUID)
                .build();

        NaquadahSolutionLight = new Material.Builder(10006, labsId("light_naquadah_solution"))
                .fluid()
                .color(0x8bff5e).iconSet(MaterialIconSet.FLUID)
                .build();

        HydrogenFluoride = new Material.Builder(10007, labsId("hydrogen_fluoride"))
                .fluid()
                .color(0xbec5c5).iconSet(MaterialIconSet.FLUID)
                .build();
    }
}
