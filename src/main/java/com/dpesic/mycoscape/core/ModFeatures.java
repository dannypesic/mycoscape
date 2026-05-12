package com.dpesic.mycoscape.core;

import com.dpesic.mycoscape.worldgen.feature.HugeBlewitMushroomFeature;
import com.dpesic.mycoscape.worldgen.feature.HugeJackOLanternMushroomFeature;
import com.dpesic.mycoscape.worldgen.feature.HugeMorelMushroomFeature;
import com.dpesic.mycoscape.worldgen.feature.HugeNecroshroomFeature;
import com.dpesic.mycoscape.worldgen.feature.WallVeinFeature;
import com.dpesic.mycoscape.worldgen.feature.WallVeinFeatureConfiguration;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES =
            DeferredRegister.create(Registries.FEATURE, Mycoscape.MODID);

    public static final DeferredHolder<Feature<?>, WallVeinFeature> WALL_VEIN = FEATURES.register(
            "wall_vein",
            () -> new WallVeinFeature(WallVeinFeatureConfiguration.CODEC)
    );

    public static final DeferredHolder<Feature<?>, HugeMorelMushroomFeature> HUGE_MOREL_MUSHROOM = FEATURES.register(
            "huge_morel_mushroom",
            () -> new HugeMorelMushroomFeature(HugeMushroomFeatureConfiguration.CODEC)
    );

    public static final DeferredHolder<Feature<?>, HugeBlewitMushroomFeature> HUGE_BLEWIT_MUSHROOM = FEATURES.register(
            "huge_blewit_mushroom",
            () -> new HugeBlewitMushroomFeature(HugeMushroomFeatureConfiguration.CODEC)
    );

    public static final DeferredHolder<Feature<?>, HugeNecroshroomFeature> HUGE_NECROSHROOM = FEATURES.register(
            "huge_necroshroom",
            () -> new HugeNecroshroomFeature(HugeMushroomFeatureConfiguration.CODEC)
    );

    public static final DeferredHolder<Feature<?>, HugeJackOLanternMushroomFeature> HUGE_JACK_O_LANTERN_MUSHROOM = FEATURES.register(
            "huge_jack_o_lantern_mushroom",
            () -> new HugeJackOLanternMushroomFeature(HugeMushroomFeatureConfiguration.CODEC)
    );
}
