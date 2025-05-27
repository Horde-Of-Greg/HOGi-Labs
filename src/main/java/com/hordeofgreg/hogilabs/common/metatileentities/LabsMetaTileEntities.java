package com.hordeofgreg.hogilabs.common.metatileentities;

import static com.hordeofgreg.hogilabs.api.utils.LabsUtil.labsId;

import com.hordeofgreg.hogilabs.common.metatileentities.multi.electric.MTEMicroverseAssemblerT1;

public class LabsMetaTileEntities {

    public static MTEMicroverseAssemblerT1 MICRO_ASSEMBLER_T1;

    public static void init() {
        MICRO_ASSEMBLER_T1 = new MTEMicroverseAssemblerT1(labsId("microverse_assembler_t1"));
    }
}
