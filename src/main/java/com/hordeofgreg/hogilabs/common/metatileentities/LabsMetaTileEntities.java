package com.hordeofgreg.hogilabs.common.metatileentities;

import static com.hordeofgreg.hogilabs.api.utils.LabsUtil.labsId;
import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntity;

import com.hordeofgreg.hogilabs.common.metatileentities.multi.generator.fuel.MTECatGenerator;
import com.hordeofgreg.hogilabs.common.metatileentities.multi.standard.electric.MTEMicroverseAssemblerT1;
import com.hordeofgreg.hogilabs.config.LabsConfig;

public class LabsMetaTileEntities {

    public static MTECatGenerator CAT_GENERATOR;
    public static MTEMicroverseAssemblerT1 MICRO_ASSEMBLER_T1;

    public static void init() {
        // Starts at 3100 because 31, if you know, you know.
        MICRO_ASSEMBLER_T1 = registerMetaTileEntity(3101,
                new MTEMicroverseAssemblerT1(labsId("microverse_assembler_t1")));
        CAT_GENERATOR = registerMetaTileEntity(3102,
                new MTECatGenerator(labsId("cat_generator")));

        if (LabsConfig.advanced.activateVerboseLogging) {
            System.out.println("HOGiLabs MetaTileEntities initialized.");
        }
    }
}
