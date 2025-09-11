package com.hordeofgreg.hogilabs.mixin.gregtech;

import static com.hordeofgreg.hogilabs.common.CommonProxy.LOGGER;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import com.hordeofgreg.hogilabs.config.LabsConfig;

@Mixin(value = gregtech.api.recipes.RecipeMaps.class, remap = false)
public class RecipeMapsMixin {

    @ModifyArgs(
                method = "<clinit>",
                at = @At(
                         value = "INVOKE",
                         target = "Lgregtech/api/recipes/machines/RecipeMapCokeOven;<init>(Ljava/lang/String;IZIZIZIZLgregtech/api/recipes/RecipeBuilder;Z)V"))
    private static void hog$allowCokeOvenFluidInputs(Args args) {
        if (LabsConfig.advanced.activateVerboseLogging) {
            LOGGER.info("Changing Recipe map args through mixins");
        }

        // indices: 0 name,1 iIn,2 modIIn,3 iOut,4 modIOut,5 fIn,6 modFIn,7 fOut,8 modFOut,9 builder,10 hidden
        args.set(6, true);   // allow changing maxFluidInputs
    }
}
