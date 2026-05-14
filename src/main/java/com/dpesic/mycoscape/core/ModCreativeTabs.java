package com.dpesic.mycoscape.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeTabs {
    private ModCreativeTabs() {}

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Mycoscape.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MYCOSCAPE_TAB =
            CREATIVE_MODE_TABS.register("mycoscape", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.mycoscape.mycoscape"))
                    .icon(() -> new ItemStack(ModItems.BLEWIT.get()))
                    .displayItems((params, output) -> {
                        output.accept(ModItems.BLEWIT.get());
                        output.accept(ModItems.MOREL.get());
                        output.accept(ModItems.JACK_O_LANTERN_MUSHROOM.get());
                        output.accept(ModItems.NECROSHROOM.get());

                        output.accept(ModItems.ROASTED_BLEWIT.get());
                        output.accept(ModItems.ROASTED_MOREL.get());
                        output.accept(ModItems.ROASTED_JACK_O_LANTERN_MUSHROOM.get());
                        output.accept(ModItems.ROASTED_NECROSHROOM.get());

                        output.accept(ModItems.MYCELIUM_FABRIC.get());

                        output.accept(ModItems.BLEWIT_MUSHROOM_CAP_ITEM.get());
                        output.accept(ModItems.MOREL_MUSHROOM_CAP_ITEM.get());
                        output.accept(ModItems.JACK_O_LANTERN_MUSHROOM_CAP_ITEM.get());
                        output.accept(ModItems.NECROSHROOM_CAP_ITEM.get());
                        output.accept(ModItems.ROTWOOD_SAPLING_ITEM.get());
                        output.accept(ModItems.ROTWOOD_LEAVES_ITEM.get());
                        output.accept(ModItems.ROTWOOD_ITEM.get());
                        output.accept(ModItems.STRIPPED_ROTWOOD_LOG_ITEM.get());
                        output.accept(ModItems.ROTWOOD_WOOD_ITEM.get());
                        output.accept(ModItems.STRIPPED_ROTWOOD_WOOD_ITEM.get());
                        output.accept(ModItems.ROTWOOD_PLANKS_ITEM.get());
                        output.accept(ModItems.ROTWOOD_STAIRS_ITEM.get());
                        output.accept(ModItems.ROTWOOD_SLAB_ITEM.get());
                        output.accept(ModItems.ROTWOOD_FENCE_ITEM.get());
                        output.accept(ModItems.ROTWOOD_FENCE_GATE_ITEM.get());
                        output.accept(ModItems.ROTWOOD_DOOR_ITEM.get());
                        output.accept(ModItems.ROTWOOD_TRAPDOOR_ITEM.get());
                        output.accept(ModItems.ROTWOOD_BUTTON_ITEM.get());
                        output.accept(ModItems.ROTWOOD_PRESSURE_PLATE_ITEM.get());

                        output.accept(ModItems.OVERGROWN_GRASS_ITEM.get());
                        output.accept(ModItems.MYCOSLATE_ITEM.get());
                        output.accept(ModItems.NECROSHROOM_HYPHAE_ITEM.get());
                        output.accept(ModItems.JACK_O_LANTERN_VEIN_ITEM.get());
output.accept(ModItems.DEATH_VINE_ITEM.get());
                        output.accept(ModItems.ROTWOOD_LEAF_LITTER_ITEM.get());
                    })
                    .build());
}
