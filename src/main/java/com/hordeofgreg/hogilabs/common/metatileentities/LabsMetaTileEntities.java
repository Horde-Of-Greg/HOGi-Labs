package com.hordeofgreg.hogilabs.common.metatileentities;

import static com.hordeofgreg.hogilabs.api.utils.LabsUtil.labsId;

import com.hordeofgreg.hogilabs.api.LabsAPI;
import com.hordeofgreg.hogilabs.common.metatileentities.multi.electric.MTEMicroverseAssemblerT1;
import com.hordeofgreg.hogilabs.config.LabsConfig;

import gregtech.api.block.machines.MachineItemBlock;

public class LabsMetaTileEntities {

    public static MTEMicroverseAssemblerT1 MICRO_ASSEMBLER_T1;

    public static void init() {
        MachineItemBlock.addCreativeTab(LabsAPI.TAG_HOGI_LABS);
        MICRO_ASSEMBLER_T1 = new MTEMicroverseAssemblerT1(labsId("microverse_assembler_t1"));

        if (LabsConfig.advanced.activateVerboseLogging) {
            System.out.println("HOGiLabs MetaTileEntities initialized.");
        }
    }
}
