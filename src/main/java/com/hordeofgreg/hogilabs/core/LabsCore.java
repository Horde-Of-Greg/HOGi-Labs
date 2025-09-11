package com.hordeofgreg.hogilabs.core;

import com.google.common.collect.ImmutableList;
import com.hordeofgreg.hogilabs.Tags;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import zone.rong.mixinbooter.IEarlyMixinLoader;

import java.util.List;
import java.util.Map;

@IFMLLoadingPlugin.Name("HogilabsCore")
@IFMLLoadingPlugin.MCVersion("1.12.2")
@IFMLLoadingPlugin.TransformerExclusions({ "com.hordeofgreg.hogilabs.core" })
public class LabsCore implements IFMLLoadingPlugin, IEarlyMixinLoader {

    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {}

    @Override
    public String getAccessTransformerClass() {
        return null;
    }

    @Override
    public List<String> getMixinConfigs() {
        return ImmutableList.of(
                "mixins." + Tags.MODID + ".json");
    }
}