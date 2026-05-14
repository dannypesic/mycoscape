package com.dpesic.mycoscape.core;

import net.minecraft.client.renderer.BiomeColors;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@Mod(value = Mycoscape.MODID, dist = Dist.CLIENT)
public class MycoscapeClient {
    public MycoscapeClient(ModContainer container, IEventBus modEventBus) {
        modEventBus.addListener(MycoscapeClient::registerBlockColors);
    }

    private static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        event.register(
            (state, level, pos, tintIndex) -> level != null && pos != null
                ? BiomeColors.getAverageGrassColor(level, pos)
                : 0x79C05A,
            ModBlocks.OVERGROWN_GRASS.get()
        );
    }
}
