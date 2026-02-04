package com.dpesic.chronoscape.core;

import com.dpesic.chronoscape.item.BlewitItem;
import com.dpesic.chronoscape.item.JackOLanternMushroomItem;
import com.dpesic.chronoscape.item.MorelItem;
import com.dpesic.chronoscape.item.NecroshroomItem;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

public class ModItems {
    private ModItems() {}


    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(Chronoscape.MODID);



    public static final DeferredItem<Item> IMBUED_SHARD = ITEMS.registerSimpleItem(
            "imbued_shard"
    );

    public static final DeferredItem<BlewitItem> BLEWIT = ITEMS.registerItem(
            "blewit",
            BlewitItem::new,
            new Item.Properties()
    );

    public static final DeferredItem<MorelItem> MOREL = ITEMS.registerItem(
            "morel",
            MorelItem::new,
            new Item.Properties()
    );

    public static final DeferredItem<JackOLanternMushroomItem> JACK_O_LANTERN_MUSHROOM = ITEMS.registerItem(
            "jack_o_lantern_mushroom",
            JackOLanternMushroomItem::new,
            new Item.Properties()
    );
    public static final DeferredItem<NecroshroomItem> NECROSHROOM = ITEMS.registerItem(
            "necroshroom",
            NecroshroomItem::new,
            new Item.Properties()
    );
}
