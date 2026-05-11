package com.dpesic.mycoscape.block;

import com.dpesic.mycoscape.core.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;

public class JackOLanternFungusBlock extends AbstractFungusBlock {

    public JackOLanternFungusBlock(Properties props) {
        super(props);
    }

    @Override
    public ItemStack dropItemstack() {
        int dropCount = 1;
        return new ItemStack(ModItems.JACK_O_LANTERN_MUSHROOM.get(), dropCount);
    }

    @Override
    public InteractionResult useItemOn(ItemStack held, BlockState state, Level level, BlockPos pos,
                                       Player player, InteractionHand hand, BlockHitResult hit) {
        return InteractionResult.PASS;
    }

    @Override
    protected VoxelShape shapeMycelium() {
        return Block.column(14.0D, 0.0D, 3.0D);
    }

    @Override
    protected VoxelShape shapeMushroom() {
        return Block.column(14.0D, 0.0D, 13.0D);
    }

}
