package com.dpesic.mycoscape.core;

import com.dpesic.mycoscape.item.*;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
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
                () -> new BlockItem(
                        block.get(),
                        new Item.Properties()
                )
        );
    }



    public static final DeferredHolder<Item, BlewitItem> BLEWIT = ITEMS.register(
            "blewit",
            () -> new BlewitItem(new Item.Properties()
            )
    );

    public static final DeferredHolder<Item, MorelItem> MOREL = ITEMS.register(
            "morel",
            () -> new MorelItem(new Item.Properties()
            )
    );

    public static final DeferredHolder<Item, JackOLanternMushroomItem> JACK_O_LANTERN_MUSHROOM = ITEMS.register(
            "jack_o_lantern_mushroom",
            () -> new JackOLanternMushroomItem(new Item.Properties()
            )
    );

    public static final DeferredHolder<Item, NecroshroomItem> NECROSHROOM = ITEMS.register(
            "necroshroom",
            () -> new NecroshroomItem(new Item.Properties()
            )
    );



    public static final DeferredHolder<Item, Item> ROASTED_BLEWIT = ITEMS.register(
            "roasted_blewit",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationModifier(0.4f)
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 2), 1.0f)
                            .build())
            )
    );

    public static final DeferredHolder<Item, Item> ROASTED_MOREL = ITEMS.register(
            "roasted_morel",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationModifier(0.4f)
                            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 120, 0), 1.0f)
                            .build())
            )
    );

    public static final DeferredHolder<Item, Item> ROASTED_JACK_O_LANTERN_MUSHROOM = ITEMS.register(
            "roasted_jack_o_lantern_mushroom",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationModifier(0.4f)
                            .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 600, 0), 1.0f)
                            .build())
            )
    );

    public static final DeferredHolder<Item, Item> ROASTED_NECROSHROOM = ITEMS.register(
            "roasted_necroshroom",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationModifier(0.4f)
                            .effect(() -> new MobEffectInstance(MobEffects.BLINDNESS, 100, 0), 1.0f)
                            .build())
            )
    );



    public static final DeferredHolder<Item, Item> MYCELIUM_FABRIC = ITEMS.register(
            "mycelium_fabric",
            () -> new Item(new Item.Properties()
            )
    );



    public static final DeferredHolder<Item, BlockItem> BLEWIT_MUSHROOM_CAP_ITEM =
            registerBlockItem("blewit_mushroom_cap", ModBlocks.BLEWIT_MUSHROOM_CAP);

    public static final DeferredHolder<Item, BlockItem> MOREL_MUSHROOM_CAP_ITEM =
            registerBlockItem("morel_mushroom_cap", ModBlocks.MOREL_MUSHROOM_CAP);

    public static final DeferredHolder<Item, BlockItem> JACK_O_LANTERN_MUSHROOM_CAP_ITEM =
            registerBlockItem("jack_o_lantern_mushroom_cap", ModBlocks.JACK_O_LANTERN_MUSHROOM_CAP);

    public static final DeferredHolder<Item, BlockItem> NECROSHROOM_CAP_ITEM =
            registerBlockItem("necroshroom_cap", ModBlocks.NECROSHROOM_CAP);



    public static final DeferredHolder<Item, BlockItem> ROTWOOD_SAPLING_ITEM =
            registerBlockItem("rotwood_sapling", ModBlocks.ROTWOOD_SAPLING);

    public static final DeferredHolder<Item, BlockItem> ROTWOOD_LEAVES_ITEM =
            registerBlockItem("rotwood_leaves", ModBlocks.ROTWOOD_LEAVES);

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



    public static final DeferredHolder<Item, BlockItem> OVERGROWN_GRASS_ITEM =
            registerBlockItem("overgrown_grass", ModBlocks.OVERGROWN_GRASS);

    public static final DeferredHolder<Item, BlockItem> MYCOSLATE_ITEM =
            registerBlockItem("mycoslate", ModBlocks.MYCOSLATE);

    public static final DeferredHolder<Item, BlockItem> NECROSHROOM_HYPHAE_ITEM =
            registerBlockItem("necroshroom_hyphae", ModBlocks.NECROSHROOM_HYPHAE);

    public static final DeferredHolder<Item, BlockItem> JACK_O_LANTERN_VEIN_ITEM =
            registerBlockItem("jack_o_lantern_vein", ModBlocks.JACK_O_LANTERN_VEIN);

    public static final DeferredHolder<Item, BlockItem> DEATH_VINE_ITEM =
            registerBlockItem("death_vine", ModBlocks.DEATH_VINE);

    public static final DeferredHolder<Item, BlockItem> ROTWOOD_LEAF_LITTER_ITEM =
            registerBlockItem("rotwood_leaf_litter", ModBlocks.ROTWOOD_LEAF_LITTER);
}
