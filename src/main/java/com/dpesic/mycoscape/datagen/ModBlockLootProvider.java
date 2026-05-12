package com.dpesic.mycoscape.datagen;

import com.dpesic.mycoscape.core.ModBlocks;
import com.dpesic.mycoscape.core.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.Set;
import java.util.function.BiConsumer;

public class ModBlockLootProvider extends net.minecraft.data.loot.BlockLootSubProvider {

    protected ModBlockLootProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        
        dropSelf(ModBlocks.OVERGROWN_GRASS.get());
        dropSelf(ModBlocks.MYCOSLATE.get());
        dropSelf(ModBlocks.JACK_O_LANTERN_VEIN.get());


        add(ModBlocks.NECROSHROOM_HYPHAE.get(), createShearsOrSilkTouchOnlyDrop(ModBlocks.NECROSHROOM_HYPHAE.get()));

        
        add(ModBlocks.DEATH_VINE.get(), createShearsOrSilkTouchOnlyDrop(ModBlocks.DEATH_VINE.get()));

        
        add(ModBlocks.ROTWOOD_LEAVES.get(), createShearsOrSilkTouchOnlyDrop(ModBlocks.ROTWOOD_LEAVES.get()));
        dropSelf(ModBlocks.ROTWOOD_SAPLING.get());

        
        dropSelf(ModBlocks.ROTWOOD.get());
        dropSelf(ModBlocks.STRIPPED_ROTWOOD_LOG.get());
        dropSelf(ModBlocks.ROTWOOD_WOOD.get());
        dropSelf(ModBlocks.STRIPPED_ROTWOOD_WOOD.get());

        
        dropSelf(ModBlocks.ROTWOOD_PLANKS.get());
        dropSelf(ModBlocks.ROTWOOD_STAIRS.get());
        add(ModBlocks.ROTWOOD_SLAB.get(), createSlabItemTable(ModBlocks.ROTWOOD_SLAB.get()));
        dropSelf(ModBlocks.ROTWOOD_FENCE.get());
        dropSelf(ModBlocks.ROTWOOD_FENCE_GATE.get());
        add(ModBlocks.ROTWOOD_DOOR.get(), createDoorTable(ModBlocks.ROTWOOD_DOOR.get()));
        dropSelf(ModBlocks.ROTWOOD_TRAPDOOR.get());
        dropSelf(ModBlocks.ROTWOOD_BUTTON.get());
        dropSelf(ModBlocks.ROTWOOD_PRESSURE_PLATE.get());

        
        add(ModBlocks.BLEWIT_MUSHROOM_CAP.get(), createMushroomBlockDrop(ModBlocks.BLEWIT_MUSHROOM_CAP.get(), ModItems.BLEWIT.get()));
        add(ModBlocks.MOREL_MUSHROOM_CAP.get(), createMushroomBlockDrop(ModBlocks.MOREL_MUSHROOM_CAP.get(), ModItems.MOREL.get()));
        add(ModBlocks.JACK_O_LANTERN_MUSHROOM_CAP.get(), createMushroomBlockDrop(ModBlocks.JACK_O_LANTERN_MUSHROOM_CAP.get(), ModItems.JACK_O_LANTERN_MUSHROOM.get()));
        dropSelf(ModBlocks.NECROSHROOM_CAP.get());
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
        generate();
        map.forEach(consumer);
    }
}
