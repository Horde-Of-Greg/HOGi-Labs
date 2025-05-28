package com.hordeofgreg.hogilabs.config;

import net.minecraftforge.common.config.Config;

import com.hordeofgreg.hogilabs.Tags;

@Config(modid = Tags.MODID)
public class LabsConfig {

    @Config.Comment("Advanced configs")
    @Config.Name("advanced")
    public static Advanced advanced = new Advanced();

    public static class Advanced {

        @Config.Comment({
                "Activate verbose logging.",
                "This will enable verbose logging for the mod.",
                "This is useful for debugging and development.",
                "[Default: true]"
        })
        @Config.Name("activateVerboseLogging")
        @Config.RequiresMcRestart
        public boolean activateVerboseLogging = true;
    }
}
