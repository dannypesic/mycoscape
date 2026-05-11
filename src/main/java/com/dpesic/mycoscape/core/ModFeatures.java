package com.dpesic.mycoscape.core;

import com.dpesic.mycoscape.worldgen.feature.WallVeinFeature;
import com.dpesic.mycoscape.worldgen.feature.WallVeinFeatureConfiguration;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES =
            DeferredRegister.create(Registries.FEATURE, Mycoscape.MODID);

    public static final DeferredHolder<Feature<?>, WallVeinFeature> WALL_VEIN = FEATURES.register(
            "wall_vein",
            () -> new WallVeinFeature(WallVeinFeatureConfiguration.CODEC)
    );
}
