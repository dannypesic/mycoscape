package com.dpesic.mycoscape.worldgen;

import com.dpesic.mycoscape.core.Mycoscape;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

public class ModBiomes {


    public static volatile boolean INJECTION_ENABLED = false;

    public static final ResourceKey<Biome> MYCOSCAPE_SURFACE = ResourceKey.create(
            Registries.BIOME,
            ResourceLocation.fromNamespaceAndPath(Mycoscape.MODID, "fungal_forest")
    );

    public static final ResourceKey<Biome> MYCOSCAPE_CAVES = ResourceKey.create(
            Registries.BIOME,
            ResourceLocation.fromNamespaceAndPath(Mycoscape.MODID, "necrotic_caverns")
    );

    private ModBiomes() {}
}
