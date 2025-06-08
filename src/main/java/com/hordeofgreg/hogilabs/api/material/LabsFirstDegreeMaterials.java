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
                .color(0x470918).iconSet(MaterialIconSet.METALLIC)
                .cableProperties(VA[LuV], 2, 8)
                .flags(GENERATE_FOIL, GENERATE_PLATE)
                .components(Tungsten, 3, Rhenium, 1)
                .blast(builder -> builder
                        .temp(4300, BlastProperty.GasTier.MID)
                        .blastStats(VA[LuV], 35 * 20))
                .build();

        MolybdenumDisulfide = new Material.Builder(9001, labsId("molybdenum_disulfide"))
                .dust()
                .color(0x4b485e).iconSet(MaterialIconSet.FINE)
                .components(Molybdenum, 1, Sulfur, 2)
                .build();

        NaquadahRaw = new Material.Builder(9002, labsId("naquadah_raw"))
                .dust()
                .color(0x6a6c70).iconSet(MaterialIconSet.METALLIC)
                .build();

        NaquadahLight = new Material.Builder(9003, labsId("naquadah_light"))
                .ingot().fluid().dust()
                .color(0x3b3c3d).iconSet(MaterialIconSet.METALLIC)
                .flags(GENERATE_PLATE)
                .blast(builder -> builder
                        .temp(5300, BlastProperty.GasTier.HIGH)
                        .blastStats(VA[LuV], 45 * 20))
                .build();

        NaquadahHeavy = new Material.Builder(9004, labsId("naquadah_heavy"))
                .ingot().fluid().dust()
                .color(0x1d1e20).iconSet(MaterialIconSet.METALLIC)
                .flags(GENERATE_PLATE)
                .blast(builder -> builder
                        .temp(6300, BlastProperty.GasTier.HIGH)
                        .blastStats(VA[UV], 50 * 20))
                .build();

        NaquadahSuperheavy = new Material.Builder(9005, labsId("naquadah_superheavy"))
                .ingot().fluid().dust()
                .color(0x050506).iconSet(MaterialIconSet.METALLIC)
                .flags(GENERATE_PLATE)
                .blast(builder -> builder
                        .temp(6900, BlastProperty.GasTier.HIGHER)
                        .blastStats(VA[UEV], 60 * 20))
                .build();
    }
}
