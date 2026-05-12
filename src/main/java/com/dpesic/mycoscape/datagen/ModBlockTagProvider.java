package com.dpesic.mycoscape.datagen;

import com.dpesic.mycoscape.core.ModBlocks;
import com.dpesic.mycoscape.core.Mycoscape;
import com.dpesic.mycoscape.tags.MycoscapeBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {

    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup) {
        super(output, lookup, Mycoscape.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {

        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.ROTWOOD.get())
                .add(ModBlocks.STRIPPED_ROTWOOD_LOG.get())
                .add(ModBlocks.ROTWOOD_WOOD.get())
                .add(ModBlocks.STRIPPED_ROTWOOD_WOOD.get())
                .add(ModBlocks.ROTWOOD_PLANKS.get())
                .add(ModBlocks.ROTWOOD_STAIRS.get())
                .add(ModBlocks.ROTWOOD_SLAB.get())
                .add(ModBlocks.ROTWOOD_FENCE.get())
                .add(ModBlocks.ROTWOOD_FENCE_GATE.get())
                .add(ModBlocks.ROTWOOD_DOOR.get())
                .add(ModBlocks.ROTWOOD_TRAPDOOR.get())
                .add(ModBlocks.ROTWOOD_BUTTON.get())
                .add(ModBlocks.ROTWOOD_PRESSURE_PLATE.get())
                .add(ModBlocks.BLEWIT_MUSHROOM_CAP.get())
                .add(ModBlocks.MOREL_MUSHROOM_CAP.get())
                .add(ModBlocks.JACK_O_LANTERN_MUSHROOM_CAP.get())
                .add(ModBlocks.NECROSHROOM_CAP.get());


        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.MYCOSLATE.get());

        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.MYCOSLATE.get());


        tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(ModBlocks.OVERGROWN_GRASS.get());

        tag(BlockTags.DIRT)
                .add(ModBlocks.OVERGROWN_GRASS.get());


        tag(BlockTags.MINEABLE_WITH_HOE)
                .add(ModBlocks.NECROSHROOM_HYPHAE.get())
                .add(ModBlocks.ROTWOOD_LEAVES.get())
                .add(ModBlocks.DEATH_VINE.get());


        tag(BlockTags.LEAVES)
                .add(ModBlocks.ROTWOOD_LEAVES.get());


        tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.ROTWOOD.get())
                .add(ModBlocks.STRIPPED_ROTWOOD_LOG.get())
                .add(ModBlocks.ROTWOOD_WOOD.get())
                .add(ModBlocks.STRIPPED_ROTWOOD_WOOD.get());

        tag(BlockTags.PLANKS)
                .add(ModBlocks.ROTWOOD_PLANKS.get());

        tag(BlockTags.WOODEN_STAIRS)
                .add(ModBlocks.ROTWOOD_STAIRS.get());

        tag(BlockTags.WOODEN_SLABS)
                .add(ModBlocks.ROTWOOD_SLAB.get());

        tag(BlockTags.WOODEN_FENCES)
                .add(ModBlocks.ROTWOOD_FENCE.get());

        tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.ROTWOOD_FENCE_GATE.get());

        tag(BlockTags.WOODEN_DOORS)
                .add(ModBlocks.ROTWOOD_DOOR.get());

        tag(BlockTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.ROTWOOD_TRAPDOOR.get());

        tag(BlockTags.WOODEN_BUTTONS)
                .add(ModBlocks.ROTWOOD_BUTTON.get());

        tag(BlockTags.WOODEN_PRESSURE_PLATES)
                .add(ModBlocks.ROTWOOD_PRESSURE_PLATE.get());


        tag(MycoscapeBlockTags.FUNGI_GROUND)
                .addTag(BlockTags.DIRT)
                .addTag(MycoscapeBlockTags.ROTWOOD)
                .add(ModBlocks.OVERGROWN_GRASS.get())
                .add(ModBlocks.MYCOSLATE.get())
                .add(ModBlocks.JACK_O_LANTERN_VEIN.get());

        tag(MycoscapeBlockTags.MYCOSLATE)
                .add(ModBlocks.MYCOSLATE.get());


        tag(BlockTags.STONE_ORE_REPLACEABLES)
                .add(ModBlocks.MYCOSLATE.get());
        tag(BlockTags.DEEPSLATE_ORE_REPLACEABLES)
                .add(ModBlocks.MYCOSLATE.get());

        tag(MycoscapeBlockTags.ROTWOOD)
                .add(ModBlocks.ROTWOOD.get())
                .add(ModBlocks.STRIPPED_ROTWOOD_LOG.get())
                .add(ModBlocks.ROTWOOD_WOOD.get())
                .add(ModBlocks.STRIPPED_ROTWOOD_WOOD.get());

        tag(BlockTags.MUSHROOM_GROW_BLOCK)
                .add(ModBlocks.MYCOSLATE.get())
                .add(ModBlocks.OVERGROWN_GRASS.get())
                .addTag(MycoscapeBlockTags.ROTWOOD);
    }
}
