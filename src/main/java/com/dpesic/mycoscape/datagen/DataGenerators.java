package com.dpesic.mycoscape.datagen;

import com.dpesic.mycoscape.core.Mycoscape;
import com.dpesic.mycoscape.worldgen.ModWorldgenBootstrap;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public class DataGenerators {

    public static void gatherData(GatherDataEvent.Client event) {
        // Datapack registry objects (biomes, etc.)
        event.createDatapackRegistryObjects(new RegistrySetBuilder()
            .add(Registries.BIOME, ModWorldgenBootstrap::bootstrapBiomes));

        // Client-side data
        event.createProvider(ModModelProvider::new);
        event.createProvider(output -> new ModLanguageProvider(output, Mycoscape.MODID, "en_us"));

        // Server-side data – also registered here since the run config uses clientData()
        event.createProvider((output, lookup) -> new ModRecipeProvider.Runner(output, lookup));
        event.createProvider((output, lookup) -> new ModLootTableProvider(output, lookup));
        event.createProvider((output, lookup) -> new ModBlockTagProvider(output, lookup));
        event.createProvider((output, lookup) -> new ModItemTagProvider(output, lookup));
    }
}
