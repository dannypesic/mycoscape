package com.dpesic.chronoscape.tags;

import com.dpesic.chronoscape.core.Chronoscape;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.resources.Identifier;

public class ChronoscapeBlockTags {

    public static final TagKey<Block> FUNGI_GROUND = chronoscapeTag("fungi_ground");

    private ChronoscapeBlockTags() {
    }
    static Identifier id(String id) {
        return Identifier.fromNamespaceAndPath(Chronoscape.MODID, id);
    }

    public static TagKey<Block> chronoscapeTag(String path) {
        return TagKey.create(Registries.BLOCK, id(path));
    }
}
