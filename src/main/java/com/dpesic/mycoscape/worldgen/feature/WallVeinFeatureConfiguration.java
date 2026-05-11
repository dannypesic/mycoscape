package com.dpesic.mycoscape.worldgen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import java.util.Optional;

public record WallVeinFeatureConfiguration(
        BlockStateProvider state,
        int minLength,
        int maxLength,
        float branchChance,
        float curveChance,
        TagKey<Block> replaceable,
        Optional<BlockStateProvider> vegetation
) implements FeatureConfiguration {

    public static final Codec<WallVeinFeatureConfiguration> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    BlockStateProvider.CODEC.fieldOf("state").forGetter(WallVeinFeatureConfiguration::state),
                    Codec.intRange(1, 64).fieldOf("min_length").forGetter(WallVeinFeatureConfiguration::minLength),
                    Codec.intRange(1, 64).fieldOf("max_length").forGetter(WallVeinFeatureConfiguration::maxLength),
                    Codec.floatRange(0.0f, 1.0f).fieldOf("branch_chance").forGetter(WallVeinFeatureConfiguration::branchChance),
                    Codec.floatRange(0.0f, 1.0f).fieldOf("curve_chance").forGetter(WallVeinFeatureConfiguration::curveChance),
                    TagKey.codec(Registries.BLOCK).fieldOf("replaceable").forGetter(WallVeinFeatureConfiguration::replaceable),
                    BlockStateProvider.CODEC.optionalFieldOf("vegetation").forGetter(WallVeinFeatureConfiguration::vegetation)
            ).apply(instance, WallVeinFeatureConfiguration::new)
    );
}
