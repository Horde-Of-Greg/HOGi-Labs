package com.hordeofgreg.hogilabs.api.utils;

import net.minecraft.util.ResourceLocation;

import org.jetbrains.annotations.NotNull;

import com.hordeofgreg.hogilabs.Tags;

public class LabsUtil {

    @NotNull
    public static ResourceLocation labsId(@NotNull String path) {
        return new ResourceLocation(Tags.MODID, path);
    }
}
