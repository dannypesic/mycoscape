package com.dpesic.mycoscape.datagen;

import com.dpesic.mycoscape.core.ModItems;
import com.dpesic.mycoscape.core.Mycoscape;
import com.dpesic.mycoscape.tags.MycoscapeItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {

    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup) {
        super(output, lookup, Mycoscape.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        
        tag(MycoscapeItemTags.CUSTOM_MUSHROOMS)
                .add(ModItems.BLEWIT.get())
                .add(ModItems.MOREL.get())
                .add(ModItems.JACK_O_LANTERN_MUSHROOM.get())
                .add(ModItems.NECROSHROOM.get());

        tag(ItemTags.LOGS_THAT_BURN)
                .add(ModItems.ROTWOOD_ITEM.get())
                .add(ModItems.STRIPPED_ROTWOOD_LOG_ITEM.get())
                .add(ModItems.ROTWOOD_WOOD_ITEM.get())
                .add(ModItems.STRIPPED_ROTWOOD_WOOD_ITEM.get());

        tag(ItemTags.PLANKS)
                .add(ModItems.ROTWOOD_PLANKS_ITEM.get());

        tag(ItemTags.WOODEN_FENCES)
                .add(ModItems.ROTWOOD_FENCE_ITEM.get());

        tag(ItemTags.FENCE_GATES)
                .add(ModItems.ROTWOOD_FENCE_GATE_ITEM.get());

        tag(ItemTags.WOODEN_DOORS)
                .add(ModItems.ROTWOOD_DOOR_ITEM.get());

        tag(ItemTags.WOODEN_TRAPDOORS)
                .add(ModItems.ROTWOOD_TRAPDOOR_ITEM.get());

        tag(ItemTags.WOODEN_BUTTONS)
                .add(ModItems.ROTWOOD_BUTTON_ITEM.get());

        tag(ItemTags.WOODEN_PRESSURE_PLATES)
                .add(ModItems.ROTWOOD_PRESSURE_PLATE_ITEM.get());

        tag(ItemTags.WOODEN_SLABS)
                .add(ModItems.ROTWOOD_SLAB_ITEM.get());

        tag(ItemTags.WOODEN_STAIRS)
                .add(ModItems.ROTWOOD_STAIRS_ITEM.get());
    }
}
