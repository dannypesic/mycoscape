package com.dpesic.mycoscape.mixin;

import com.dpesic.mycoscape.worldgen.ModBiomes;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MultiNoiseBiomeSourceParameterList;
import net.neoforged.neoforge.data.loading.DatagenModLoader;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Mixin(MultiNoiseBiomeSourceParameterList.class)
public class MultiNoiseBiomeSourceParamListMixin {

    @Shadow @Final @Mutable
    private Climate.ParameterList<Holder<Biome>> parameters;

    @Inject(
        method = "<init>(Lnet/minecraft/world/level/biome/MultiNoiseBiomeSourceParameterList$Preset;Lnet/minecraft/core/HolderGetter;)V",
        at = @At("TAIL")
    )
    private void addMycoscapeBiomes(MultiNoiseBiomeSourceParameterList.Preset preset,
                                     HolderGetter<Biome> biomes,
                                     CallbackInfo ci) {
        // Skip during datagen (VanillaRegistries.createLookup() uses UniversalLookup
        // which always creates holders and would flag ours as "Unreferenced key").
        if (DatagenModLoader.isRunningDataGen()) return;
        // Skip during Bootstrap.validate() — the mod constructor hasn't run yet
        // so we haven't set INJECTION_ENABLED. In world loading, the constructor
        // runs before the first world is opened, so the flag is true.
        if (!ModBiomes.INJECTION_ENABLED) return;

        if (!preset.id().equals(Identifier.withDefaultNamespace("overworld"))) return;

        // Use get() (returns Optional) so we skip gracefully if the biomes somehow
        // aren't in the registry (shouldn't happen in normal world loading).
        Optional<Holder.Reference<Biome>> surface = biomes.get(ModBiomes.MYCOSCAPE_SURFACE);
        Optional<Holder.Reference<Biome>> caves   = biomes.get(ModBiomes.MYCOSCAPE_CAVES);
        if (surface.isEmpty() || caves.isEmpty()) return;

        List<Pair<Climate.ParameterPoint, Holder<Biome>>> list = new ArrayList<>(this.parameters.values());

        list.add(Pair.of(
            Climate.parameters(
                Climate.Parameter.span(-0.05f, 0.4f),   // temperature: slightly expanded
                Climate.Parameter.span(0.05f, 0.35f),   // humidity: slightly expanded
                Climate.Parameter.span(0.0f, 0.35f),    // continentalness: slightly expanded
                Climate.Parameter.span(-0.375f, 0.05f), // erosion: low-moderate
                Climate.Parameter.point(0.0f),           // depth: surface
                Climate.Parameter.span(0.1f, 0.9f),     // weirdness: expanded for ~1.5x more common
                0L
            ),
            surface.get()
        ));

        list.add(Pair.of(
            Climate.parameters(
                Climate.Parameter.span(0.0f, 0.35f),    // temperature: mild-warm
                Climate.Parameter.span(0.1f, 0.3f),     // humidity: moderately wet
                Climate.Parameter.span(-0.11f, 0.55f),  // continentalness: inland range
                Climate.Parameter.span(-0.375f, 0.05f), // erosion: low-moderate
                Climate.Parameter.span(0.65f, 0.9f),    // depth: narrowed for ~4x rarer
                Climate.Parameter.span(0.8f, 0.9f),     // weirdness: narrowed for ~4x rarer
                0L
            ),
            caves.get()
        ));

        this.parameters = new Climate.ParameterList<>(list);
    }
}
