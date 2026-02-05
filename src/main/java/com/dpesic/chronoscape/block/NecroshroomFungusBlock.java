package com.dpesic.chronoscape.block;

import com.dpesic.chronoscape.core.ModBlocks;
import com.dpesic.chronoscape.core.ModItems;
import com.dpesic.chronoscape.tags.ChronoscapeBlockTags;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public class NecroshroomFungusBlock extends AbstractFungusBlock {

    public NecroshroomFungusBlock(Properties props) {
        super(props);
    }

    @Override
    protected ItemStack dropItemstack() {
        int dropCount = 1;
        return new ItemStack(ModItems.NECROSHROOM.get(), dropCount);
    }

    @Override
    protected VoxelShape shapeMycelium() {
        return Block.column(14.0D, 0.0D, 3.0D);
    }

    @Override
    protected VoxelShape shapeMushroom() {
        return Block.column(14.0D, 0.0D, 14.0D);
    }

    @Override
    protected boolean mayPlaceOn(BlockState below, BlockGetter level, BlockPos pos) {
        return below.is(ModBlocks.ROTWOOD);
    }
}
