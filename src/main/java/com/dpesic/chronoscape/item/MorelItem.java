package com.dpesic.chronoscape.item;

import com.dpesic.chronoscape.core.ModBlocks;
import net.minecraft.world.level.block.state.BlockState;

public class MorelItem extends AbstractMushroomItem{

    public MorelItem(Properties props) {
        super(props);
    }
    @Override
    protected BlockState placeBlockstate() {
        return ModBlocks.MOREL_FUNGUS.get().defaultBlockState();
    }
}
