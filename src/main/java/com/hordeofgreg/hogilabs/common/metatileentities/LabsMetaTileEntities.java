package com.hordeofgreg.hogilabs.common.metatileentities;

import static com.hordeofgreg.hogilabs.api.utils.LabsUtil.labsId;
import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntity;

import com.hordeofgreg.hogilabs.api.metatileentity.LabsMultiblockAbilities;
import com.hordeofgreg.hogilabs.common.metatileentities.multi.boostrap.MTEBootstrappedMixer;
import com.hordeofgreg.hogilabs.common.metatileentities.multi.generator.fuel.MTECatGenerator;
import com.hordeofgreg.hogilabs.common.metatileentities.multi.standard.electric.MTEMicroverseAssemblerT1;
import com.hordeofgreg.hogilabs.common.metatileentities.multi.standard.primitive.MTESteelBlastFurnace;
import com.hordeofgreg.hogilabs.config.LabsConfig;
import com.zorbatron.zbgt.common.metatileentities.multi.multiblockpart.MTEFilteredHatch;

import gregtech.api.GTValues;
import gregtech.api.unification.material.Materials;

public class LabsMetaTileEntities {

    public static MTECatGenerator CAT_GENERATOR;
    public static MTEMicroverseAssemblerT1 MICRO_ASSEMBLER_T1;
    public static MTESteelBlastFurnace STEEL_BLAST_FURNACE;
    public static MTEBootstrappedMixer BOOTSTRAPPED_MIXER;

    public static MTEFilteredHatch LIQUID_FUEL_BLAST_HATCH;

    public static void init() {
        // Starts at 3100 because 31, if you know, you know.
        MICRO_ASSEMBLER_T1 = registerMetaTileEntity(3101,
                new MTEMicroverseAssemblerT1(labsId("microverse_assembler_t1")));
        CAT_GENERATOR = registerMetaTileEntity(3102,
                new MTECatGenerator(labsId("cat_generator"), GTValues.LuV));
        STEEL_BLAST_FURNACE = registerMetaTileEntity(3103,
                new MTESteelBlastFurnace(labsId("steel_blast_furnace")));
        BOOTSTRAPPED_MIXER = registerMetaTileEntity(3104,
                new MTEBootstrappedMixer(labsId("bootstrapped_mixer")));

        LIQUID_FUEL_BLAST_HATCH = registerMetaTileEntity(3201,
                new MTEFilteredHatch(labsId("liquid_blast_hatch"), GTValues.ULV,
                        LabsMultiblockAbilities.LIQUID_BLAST_HATCH, () -> Materials.Lava.getFluid(1), 4_000));

        if (LabsConfig.advanced.activateVerboseLogging) {
            System.out.println("HOGiLabs MetaTileEntities initialized.");
        }
    }
}
