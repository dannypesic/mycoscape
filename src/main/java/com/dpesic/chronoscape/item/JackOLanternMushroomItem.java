package com.dpesic.chronoscape.item;

import com.dpesic.chronoscape.core.ModBlocks;
import com.dpesic.chronoscape.tags.ChronoscapeBlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class JackOLanternMushroomItem extends AbstractMushroomItem{

    public JackOLanternMushroomItem(Properties props) {
        super(props);
    }
    @Override
    protected BlockState placeBlockstate() {
        return ModBlocks.JACK_O_LANTERN_FUNGUS.get().defaultBlockState();
    }
    @Override
    protected TagKey<Block> groundStateTag() {return ChronoscapeBlockTags.FUNGI_GROUND;}
}
