package com.dpesic.mycoscape.worldgen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.attribute.AmbientAdditionsSettings;
import net.minecraft.world.attribute.AmbientMoodSettings;
import net.minecraft.world.attribute.AmbientParticle;
import net.minecraft.world.attribute.AmbientSounds;
import net.minecraft.world.attribute.BackgroundMusic;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;
import java.util.Optional;

public class ModWorldgenBootstrap {

    public static void bootstrapBiomes(BootstrapContext<Biome> ctx) {
        HolderGetter<PlacedFeature> placedFeatures = ctx.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> carvers = ctx.lookup(Registries.CONFIGURED_CARVER);

        ctx.register(ModBiomes.MYCOSCAPE_SURFACE, mycoscapeSurface(placedFeatures, carvers));
        ctx.register(ModBiomes.MYCOSCAPE_CAVES, mycoscapeCaves(placedFeatures, carvers));
    }

    private static Biome mycoscapeSurface(HolderGetter<PlacedFeature> pf, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        AmbientSounds sounds = new AmbientSounds(
            Optional.of(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_LOOP),
            Optional.of(new AmbientMoodSettings(SoundEvents.AMBIENT_CAVE, 6000, 8, 2.0)),
            List.of(new AmbientAdditionsSettings(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, 0.006))
        );

        return new Biome.BiomeBuilder()
            .hasPrecipitation(true)
            .temperature(0.6f)
            .downfall(0.8f)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, 0x2a3d32)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, 0x4a5a4f)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0x1f2a2d)
            .setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS, sounds)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_DEEP_DARK))
            .setAttribute(EnvironmentAttributes.AMBIENT_PARTICLES, AmbientParticle.of(ParticleTypes.MYCELIUM, 0.001f))
            .specialEffects(new BiomeSpecialEffects.Builder()
                .waterColor(0x2f3b3e)
                .foliageColorOverride(0x3a5e3a)
                .grassColorOverride(0x2e4d2e)
                .build())
            .mobSpawnSettings(new MobSpawnSettings.Builder().build())
            .generationSettings(new BiomeGenerationSettings.Builder(pf, carvers).build())
            .build();
    }

    private static Biome mycoscapeCaves(HolderGetter<PlacedFeature> pf, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        AmbientSounds sounds = new AmbientSounds(
            Optional.of(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_LOOP),
            Optional.of(new AmbientMoodSettings(SoundEvents.AMBIENT_CAVE, 4000, 8, 2.0)),
            List.of(new AmbientAdditionsSettings(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, 0.008))
        );

        return new Biome.BiomeBuilder()
            .hasPrecipitation(false)
            .temperature(0.45f)
            .downfall(0.0f)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, 0x3a4d42)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, 0x6d7479)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0x1f2a2d)
            .setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS, sounds)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_DRIPSTONE_CAVES))
            .setAttribute(EnvironmentAttributes.AMBIENT_PARTICLES,
                List.of(new AmbientParticle(ParticleTypes.ASH, 0.003f),
                        new AmbientParticle(ParticleTypes.WHITE_ASH, 0.001f)))
            .specialEffects(new BiomeSpecialEffects.Builder()
                .waterColor(0x2f3b3e)
                .build())
            .mobSpawnSettings(new MobSpawnSettings.Builder().build())
            .generationSettings(new BiomeGenerationSettings.Builder(pf, carvers).build())
            .build();
    }
}
