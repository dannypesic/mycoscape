package com.dpesic.mycoscape.worldgen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.biome.AmbientAdditionsSettings;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import net.minecraft.world.level.biome.AmbientParticleSettings;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModWorldgenBootstrap {

    public static void bootstrapBiomes(BootstrapContext<Biome> ctx) {
        HolderGetter<PlacedFeature> placedFeatures = ctx.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> carvers = ctx.lookup(Registries.CONFIGURED_CARVER);

        ctx.register(ModBiomes.MYCOSCAPE_SURFACE, mycoscapeSurface(placedFeatures, carvers));
        ctx.register(ModBiomes.MYCOSCAPE_CAVES, mycoscapeCaves(placedFeatures, carvers));
    }

    private static Biome mycoscapeSurface(HolderGetter<PlacedFeature> pf, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        return new Biome.BiomeBuilder()
            .hasPrecipitation(true)
            .temperature(0.6f)
            .downfall(0.8f)
            .specialEffects(new BiomeSpecialEffects.Builder()
                .fogColor(0x2a3d32)
                .skyColor(0x4a5a4f)
                .waterColor(0x2f3b3e)
                .waterFogColor(0x1f2a2d)
                .foliageColorOverride(0x3a5e3a)
                .grassColorOverride(0x2e4d2e)
                .ambientLoopSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_LOOP)
                .ambientMoodSound(new AmbientMoodSettings(SoundEvents.AMBIENT_CAVE, 6000, 8, 2.0))
                .ambientAdditionsSound(new AmbientAdditionsSettings(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, 0.006))
                .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_DEEP_DARK))
                .ambientParticle(new AmbientParticleSettings(ParticleTypes.MYCELIUM, 0.001f))
                .build())
            .mobSpawnSettings(new MobSpawnSettings.Builder().build())
            .generationSettings(new BiomeGenerationSettings.Builder(pf, carvers).build())
            .build();
    }

    private static Biome mycoscapeCaves(HolderGetter<PlacedFeature> pf, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        return new Biome.BiomeBuilder()
            .hasPrecipitation(false)
            .temperature(0.45f)
            .downfall(0.0f)
            .specialEffects(new BiomeSpecialEffects.Builder()
                .fogColor(0x3a4d42)
                .skyColor(0x6d7479)
                .waterColor(0x2f3b3e)
                .waterFogColor(0x1f2a2d)
                .ambientLoopSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_LOOP)
                .ambientMoodSound(new AmbientMoodSettings(SoundEvents.AMBIENT_CAVE, 4000, 8, 2.0))
                .ambientAdditionsSound(new AmbientAdditionsSettings(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, 0.008))
                .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_DRIPSTONE_CAVES))
                .ambientParticle(new AmbientParticleSettings(ParticleTypes.ASH, 0.003f))
                .build())
            .mobSpawnSettings(new MobSpawnSettings.Builder().build())
            .generationSettings(new BiomeGenerationSettings.Builder(pf, carvers).build())
            .build();
    }
}
