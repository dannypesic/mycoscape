package com.dpesic.mycoscape.mixin;

import com.dpesic.mycoscape.core.ModBlocks;
import com.dpesic.mycoscape.worldgen.ModBiomes;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.neoforged.neoforge.data.loading.DatagenModLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NoiseGeneratorSettings.class)
public class NoiseGeneratorSettingsMixin {

    @Inject(method = "surfaceRule", at = @At("RETURN"), cancellable = true)
    private void addMycoscaveSurfaceRule(CallbackInfoReturnable<SurfaceRules.RuleSource> cir) {
        if (DatagenModLoader.isRunningDataGen()) return;
        if (!ModBiomes.INJECTION_ENABLED) return;

        SurfaceRules.RuleSource mycoscaveRule = SurfaceRules.ifTrue(
            SurfaceRules.isBiome(ModBiomes.MYCOSCAPE_CAVES),
            SurfaceRules.state(ModBlocks.MYCOSLATE.get().defaultBlockState())
        );

        cir.setReturnValue(SurfaceRules.sequence(mycoscaveRule, cir.getReturnValue()));
    }
}
