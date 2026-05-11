package com.dpesic.mycoscape.item;

import com.dpesic.mycoscape.core.ModBlocks;
import com.dpesic.mycoscape.tags.MycoscapeBlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BlewitItem extends AbstractMushroomItem{


    public BlewitItem(Properties props) {
        super(props);
    }
    @Override
    protected BlockState placeBlockstate() {
        return ModBlocks.BLEWIT_FUNGUS.get().defaultBlockState();
    }

    @Override
    protected TagKey<Block> groundStateTag() {return MycoscapeBlockTags.FUNGI_GROUND;}
}