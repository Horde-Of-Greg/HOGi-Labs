package com.hordeofgreg.hogilabs.common.metatileentities;

import static com.hordeofgreg.hogilabs.api.utils.LabsUtil.labsId;
import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntity;

import com.hordeofgreg.hogilabs.api.metatileentity.LabsMultiblockAbility;
import com.hordeofgreg.hogilabs.common.metatileentities.multi.boostrap.MTEBootstrappedMixer;
import com.hordeofgreg.hogilabs.common.metatileentities.multi.generator.fuel.MTECatGenerator;
import com.hordeofgreg.hogilabs.common.metatileentities.multi.standard.electric.MTEMicroverseAssemblerT1;
import com.hordeofgreg.hogilabs.common.metatileentities.multi.standard.primitive.MTEPanner;
import com.hordeofgreg.hogilabs.common.metatileentities.multi.standard.primitive.MTESteelBlastFurnace;
import com.hordeofgreg.hogilabs.common.metatileentities.steam.SteamMixer;
import com.hordeofgreg.hogilabs.config.LabsConfig;
import com.zorbatron.zbgt.common.metatileentities.multi.multiblockpart.MTEFilteredHatch;

import gregtech.api.GTValues;
import gregtech.api.unification.material.Materials;

public class LabsMetaTileEntities {

    public static MTECatGenerator CAT_GENERATOR;
    public static MTEMicroverseAssemblerT1 MICRO_ASSEMBLER_T1;
    public static MTESteelBlastFurnace STEEL_BLAST_FURNACE;
    public static MTEPanner PANNER;
    public static MTEBootstrappedMixer BOOTSTRAPPED_MIXER;

    public static MTEFilteredHatch LIQUID_FUEL_BLAST_HATCH;

    public static SteamMixer STEAM_MIXER_BRONZE;
    public static SteamMixer STEAM_MIXER_STEEL;

    public static void init() {
        // Starts at 3100 because 31, if you know, you know.
        MICRO_ASSEMBLER_T1 = registerMetaTileEntity(3101,
                new MTEMicroverseAssemblerT1(labsId("microverse_assembler_t1")));
        CAT_GENERATOR = registerMetaTileEntity(3102,
                new MTECatGenerator(labsId("cat_generator"), GTValues.LuV));
        STEEL_BLAST_FURNACE = registerMetaTileEntity(3103,
                new MTESteelBlastFurnace(labsId("steel_blast_furnace")));
        PANNER = registerMetaTileEntity(3104,
                new MTEPanner(labsId("panner")));
        BOOTSTRAPPED_MIXER = registerMetaTileEntity(3105,
                new MTEBootstrappedMixer(labsId("bootstrapped_mixer")));

        LIQUID_FUEL_BLAST_HATCH = registerMetaTileEntity(3201,
                new MTEFilteredHatch(labsId("liquid_blast_hatch"), GTValues.ULV,
                        LabsMultiblockAbility.LIQUID_BLAST_HATCH, () -> Materials.Lava.getFluid(1), 4_000));

        STEAM_MIXER_BRONZE = registerMetaTileEntity(3301,
                new SteamMixer(labsId("steam_mixer_bronze"), false));
        STEAM_MIXER_STEEL = registerMetaTileEntity(3302,
                new SteamMixer(labsId("steam_mixer_steel"), true));

        if (LabsConfig.advanced.activateVerboseLogging) {
            System.out.println("HOGiLabs MetaTileEntities initialized.");
        }
    }
}
