package com.hordeofgreg.hogilabs.api.material;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;

import gregtech.api.fluids.FluidBuilder;
import gregtech.api.fluids.store.FluidStorageKeys;
import gregtech.api.unification.material.properties.*;

public class LabsPropertyChange {

    public static void init() {
        // Ingots
        Rhenium.setProperty(PropertyKey.INGOT, new IngotProperty());

        // Fluids
        Rhenium.setProperty(PropertyKey.FLUID, new FluidProperty(FluidStorageKeys.LIQUID, new FluidBuilder()));

        // EBF
        Rhenium.setProperty(PropertyKey.BLAST, new BlastProperty.Builder()
                .temp(3460, BlastProperty.GasTier.HIGH)
                .blastStats(VA[IV], 50 * 20)
                .vacuumStats(VA[EV])
                .build());

        // Color
        Rhenium.setMaterialRGB(0xbd4863);

        // Fluid from gas
        Fluorine.getProperty(PropertyKey.FLUID).enqueueRegistration(FluidStorageKeys.LIQUID,
                new FluidBuilder()
                        .temperature(55)
                        .name("liquid_fluorine")
                        .translation("gregtech.fluid.liquid_generic"));

        // Formulas
        Lubricant.setFormula("(C₁₆H₃₂O₂)Si");
    }

    public static void initLate() {}
}
