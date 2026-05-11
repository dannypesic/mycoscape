package com.dpesic.mycoscape.core;

import com.dpesic.mycoscape.item.*;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    private ModItems() {}

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(Registries.ITEM, Mycoscape.MODID);

    public static <T extends Block> DeferredHolder<Item, BlockItem> registerBlockItem(String name, DeferredHolder<Block, T> block) {
        return ITEMS.register(
                name,
                rn -> new BlockItem(
                        block.get(),
                        new Item.Properties()
                                .setId(ResourceKey.create(Registries.ITEM, rn))
                )
        );
    }

    // ---- Mushroom food items -------------------------------------------------

    public static final DeferredHolder<Item, BlewitItem> BLEWIT = ITEMS.register(
            "blewit",
            rn -> new BlewitItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, rn))
            )
    );

    public static final DeferredHolder<Item, MorelItem> MOREL = ITEMS.register(
            "morel",
            rn -> new MorelItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, rn))
            )
    );

    public static final DeferredHolder<Item, JackOLanternMushroomItem> JACK_O_LANTERN_MUSHROOM = ITEMS.register(
            "jack_o_lantern_mushroom",
            rn -> new JackOLanternMushroomItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, rn))
            )
    );

    public static final DeferredHolder<Item, NecroshroomItem> NECROSHROOM = ITEMS.register(
            "necroshroom",
            rn -> new NecroshroomItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, rn))
            )
    );

    // ---- Block Items: Fungus plants ------------------------------------------

    // No block items for the small fungus plants (they're not placeable as items)

    // ---- Block Items: Big mushroom caps --------------------------------------

    public static final DeferredHolder<Item, BlockItem> BLEWIT_MUSHROOM_CAP_ITEM =
            registerBlockItem("blewit_mushroom_cap", ModBlocks.BLEWIT_MUSHROOM_CAP);

    public static final DeferredHolder<Item, BlockItem> MOREL_MUSHROOM_CAP_ITEM =
            registerBlockItem("morel_mushroom_cap", ModBlocks.MOREL_MUSHROOM_CAP);

    public static final DeferredHolder<Item, BlockItem> JACK_O_LANTERN_MUSHROOM_CAP_ITEM =
            registerBlockItem("jack_o_lantern_mushroom_cap", ModBlocks.JACK_O_LANTERN_MUSHROOM_CAP);

    public static final DeferredHolder<Item, BlockItem> NECROSHROOM_CAP_ITEM =
            registerBlockItem("necroshroom_cap", ModBlocks.NECROSHROOM_CAP);

    public static final DeferredHolder<Item, BlockItem> MUSHROOM_STEM_ITEM =
            registerBlockItem("mushroom_stem", ModBlocks.MUSHROOM_STEM);


    // ---- Block Items: Rotwood wood set ----------------------------------------

    public static final DeferredHolder<Item, BlockItem> ROTWOOD_ITEM =
            registerBlockItem("rotwood", ModBlocks.ROTWOOD);

    public static final DeferredHolder<Item, BlockItem> STRIPPED_ROTWOOD_LOG_ITEM =
            registerBlockItem("stripped_rotwood_log", ModBlocks.STRIPPED_ROTWOOD_LOG);

    public static final DeferredHolder<Item, BlockItem> ROTWOOD_WOOD_ITEM =
            registerBlockItem("rotwood_wood", ModBlocks.ROTWOOD_WOOD);

    public static final DeferredHolder<Item, BlockItem> STRIPPED_ROTWOOD_WOOD_ITEM =
            registerBlockItem("stripped_rotwood_wood", ModBlocks.STRIPPED_ROTWOOD_WOOD);

    public static final DeferredHolder<Item, BlockItem> ROTWOOD_PLANKS_ITEM =
            registerBlockItem("rotwood_planks", ModBlocks.ROTWOOD_PLANKS);

    public static final DeferredHolder<Item, BlockItem> ROTWOOD_STAIRS_ITEM =
            registerBlockItem("rotwood_stairs", ModBlocks.ROTWOOD_STAIRS);

    public static final DeferredHolder<Item, BlockItem> ROTWOOD_SLAB_ITEM =
            registerBlockItem("rotwood_slab", ModBlocks.ROTWOOD_SLAB);

    public static final DeferredHolder<Item, BlockItem> ROTWOOD_FENCE_ITEM =
            registerBlockItem("rotwood_fence", ModBlocks.ROTWOOD_FENCE);

    public static final DeferredHolder<Item, BlockItem> ROTWOOD_FENCE_GATE_ITEM =
            registerBlockItem("rotwood_fence_gate", ModBlocks.ROTWOOD_FENCE_GATE);

    public static final DeferredHolder<Item, BlockItem> ROTWOOD_DOOR_ITEM =
            registerBlockItem("rotwood_door", ModBlocks.ROTWOOD_DOOR);

    public static final DeferredHolder<Item, BlockItem> ROTWOOD_TRAPDOOR_ITEM =
            registerBlockItem("rotwood_trapdoor", ModBlocks.ROTWOOD_TRAPDOOR);

    public static final DeferredHolder<Item, BlockItem> ROTWOOD_BUTTON_ITEM =
            registerBlockItem("rotwood_button", ModBlocks.ROTWOOD_BUTTON);

    public static final DeferredHolder<Item, BlockItem> ROTWOOD_PRESSURE_PLATE_ITEM =
            registerBlockItem("rotwood_pressure_plate", ModBlocks.ROTWOOD_PRESSURE_PLATE);

    // ---- Block Items: Terrain & decoration -----------------------------------

    public static final DeferredHolder<Item, BlockItem> FUNGAL_SUBSTRATE_ITEM =
            registerBlockItem("fungal_substrate", ModBlocks.FUNGAL_SUBSTRATE);

    public static final DeferredHolder<Item, BlockItem> MYCOSLATE_ITEM =
            registerBlockItem("mycoslate", ModBlocks.MYCOSLATE);

    public static final DeferredHolder<Item, BlockItem> NECROSHROOM_HYPHAE_ITEM =
            registerBlockItem("necroshroom_hyphae", ModBlocks.NECROSHROOM_HYPHAE);

    public static final DeferredHolder<Item, BlockItem> JACK_O_LANTERN_VEIN_ITEM =
            registerBlockItem("jack_o_lantern_vein", ModBlocks.JACK_O_LANTERN_VEIN);

    public static final DeferredHolder<Item, BlockItem> FUNGAL_CONDUIT_ITEM =
            registerBlockItem("fungal_conduit", ModBlocks.FUNGAL_CONDUIT);
}
