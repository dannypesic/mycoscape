package com.dpesic.mycoscape.datagen;

import com.dpesic.mycoscape.core.Mycoscape;
import com.dpesic.mycoscape.worldgen.ModWorldgenBootstrap;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public class DataGenerators {

    public static void gatherData(GatherDataEvent.Client event) {

        event.createDatapackRegistryObjects(new RegistrySetBuilder()
            .add(Registries.BIOME, ModWorldgenBootstrap::bootstrapBiomes));


        event.createProvider(ModModelProvider::new);
        event.createProvider(output -> new ModLanguageProvider(output, Mycoscape.MODID, "en_us"));


        event.createProvider((output, lookup) -> new ModRecipeProvider.Runner(output, lookup));
        event.createProvider((output, lookup) -> new ModLootTableProvider(output, lookup));
        event.createProvider((output, lookup) -> new ModBlockTagProvider(output, lookup));
        event.createProvider((output, lookup) -> new ModItemTagProvider(output, lookup));
    }
}
