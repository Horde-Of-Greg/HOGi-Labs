package com.hordeofgreg.hogilabs.api.worldgen;

import static com.hordeofgreg.hogilabs.HOGiLabs.LOGGER;

import java.io.IOException;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hordeofgreg.hogilabs.config.LabsConfig;

import gregtech.api.worldgen.config.OreDepositDefinition;
import gregtech.api.worldgen.config.WorldGenRegistry;

public class CustomOreVeins {

    public static void init() throws IOException {
        if (LabsConfig.advanced.activateVerboseLogging) {
            LOGGER.info("Registering ore veins...");
        }

        JsonParser parser = new JsonParser();

        JsonObject crocoiteDefinition = parser.parse("""
                {
                  "name": "hogilabs.vein.crocoite",
                  "weight": 25,
                  "density": 0.05,
                  "min_height": 50,
                  "max_height": 80,
                  "dimension_filter": [
                    "dimension_id:111",
                    "dimension_id:0"
                  ],
                  "generator": {
                    "type": "layered",
                    "radius": [
                      12,
                      14
                    ]
                  },
                  "filler": {
                    "type": "layered",
                    "values": [
                      {
                        "primary": "ore:gregtech:galena"
                      },
                      {
                        "secondary": "ore:hogilabs:crocoite"
                      },
                      {
                        "between": "ore:gregtech:quartzite"
                      },
                      {
                        "sporadic": "ore:gregtech:chromite"
                      }
                    ]
                  }
                }
                """).getAsJsonObject();
        OreDepositDefinition crocoiteDeposit = new OreDepositDefinition("crocoite");
        crocoiteDeposit.initializeFromConfig(crocoiteDefinition);

        WorldGenRegistry worldGenRegistry = WorldGenRegistry.INSTANCE;
        worldGenRegistry.addVeinDefinitions(crocoiteDeposit);

        worldGenRegistry.reinitializeRegisteredVeins();
    }
}
