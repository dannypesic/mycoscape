package com.dpesic.mycoscape.tags;

import com.dpesic.mycoscape.core.Mycoscape;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.resources.ResourceLocation;

public class MycoscapeBlockTags {

    public static final TagKey<Block> FUNGI_GROUND = mycoscapeTag("fungi_ground");
    public static final TagKey<Block> ROTWOOD = mycoscapeTag("rotwood");
    public static final TagKey<Block> MYCOSLATE = mycoscapeTag("mycoslate");


    private MycoscapeBlockTags() {
    }
    static ResourceLocation id(String id) {
        return ResourceLocation.fromNamespaceAndPath(Mycoscape.MODID, id);
    }

    public static TagKey<Block> mycoscapeTag(String path) {
        return TagKey.create(Registries.BLOCK, id(path));
    }
}
