package com.dpesic.mycoscape.worldgen;

import com.dpesic.mycoscape.core.Mycoscape;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

public class ModBiomes {
    // Set to true in the @Mod constructor, which runs after Bootstrap.validate() but before world loading.
    // Keeps the mixin from firing during Bootstrap.validate() where our biomes aren't registered.
    public static volatile boolean INJECTION_ENABLED = false;

    public static final ResourceKey<Biome> MYCOSCAPE_SURFACE = ResourceKey.create(
            Registries.BIOME,
            Identifier.fromNamespaceAndPath(Mycoscape.MODID, "mycoscape_surface")
    );

    public static final ResourceKey<Biome> MYCOSCAPE_CAVES = ResourceKey.create(
            Registries.BIOME,
            Identifier.fromNamespaceAndPath(Mycoscape.MODID, "mycoscape_caves")
    );

    private ModBiomes() {}
}
