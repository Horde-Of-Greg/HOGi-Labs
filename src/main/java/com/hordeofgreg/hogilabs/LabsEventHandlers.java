package com.hordeofgreg.hogilabs;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import org.jetbrains.annotations.ApiStatus;

import com.hordeofgreg.hogilabs.api.material.LabsMaterials;
import com.hordeofgreg.hogilabs.api.material.LabsPropertyChange;

import gregtech.api.unification.material.event.MaterialEvent;
import gregtech.api.unification.material.event.PostMaterialEvent;

@ApiStatus.Internal
@Mod.EventBusSubscriber(modid = Tags.MODID)
public class LabsEventHandlers {

    private LabsEventHandlers() {}

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void registerMaterials(MaterialEvent event) {
        LabsMaterials.init();
    }

    @SubscribeEvent
    public static void registerMaterialsPost(PostMaterialEvent event) {
        LabsPropertyChange.initLate();
    }
}
