package com.dpesic.mycoscape.tags;

import com.dpesic.mycoscape.core.Mycoscape;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class MycoscapeItemTags {
    private MycoscapeItemTags() {}

    static ResourceLocation id(String id) {
        return ResourceLocation.fromNamespaceAndPath(Mycoscape.MODID, id);
    }

    public static TagKey<Item> mycoscapeTag(String path) {
        return TagKey.create(Registries.ITEM, id(path));
    }

    public static final TagKey<Item> CUSTOM_MUSHROOMS = mycoscapeTag("custom_mushrooms");
}
