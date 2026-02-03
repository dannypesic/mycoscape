package com.dpesic.chronoscape.item;

import com.dpesic.chronoscape.core.ModBlocks;
import net.minecraft.world.level.block.state.BlockState;

public class BlewitItem extends AbstractMushroomItem{

    public BlewitItem(Properties props) {
        super(props);
    }
    @Override
    protected BlockState placeBlockstate() {
        return ModBlocks.BLEWIT_FUNGUS.get().defaultBlockState();
    }
}