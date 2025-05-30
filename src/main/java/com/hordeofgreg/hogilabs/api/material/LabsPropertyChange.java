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
        Rhenium.setMaterialRGB(0x4b0f94);
    }

    public static void initLate() {}
}
