package com.dpesic.mycoscape.core;

import com.dpesic.mycoscape.datagen.DataGenerators;
import com.dpesic.mycoscape.worldgen.ModBiomes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Mycoscape.MODID)
public class Mycoscape {
    public static final String MODID = "mycoscape";

    public Mycoscape(IEventBus modBus) {
        ModBiomes.INJECTION_ENABLED = true;
        ModItems.ITEMS.register(modBus);
        ModBlocks.BLOCKS.register(modBus);
        ModFeatures.FEATURES.register(modBus);
        ModCreativeTabs.CREATIVE_MODE_TABS.register(modBus);
        modBus.addListener(DataGenerators::gatherData);
    }
}
