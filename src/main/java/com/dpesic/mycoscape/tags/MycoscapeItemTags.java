package com.dpesic.mycoscape.tags;

import com.dpesic.mycoscape.core.Mycoscape;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class MycoscapeItemTags {
    private MycoscapeItemTags() {}

    static Identifier id(String id) {
        return Identifier.fromNamespaceAndPath(Mycoscape.MODID, id);
    }

    public static TagKey<Item> mycoscapeTag(String path) {
        return TagKey.create(Registries.ITEM, id(path));
    }
}
