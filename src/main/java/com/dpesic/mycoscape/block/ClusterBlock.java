package com.dpesic.mycoscape.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Map;

public class ClusterBlock extends AmethystClusterBlock{
    public static final BooleanProperty WATERLOGGED;
    public static final EnumProperty<Direction> FACING;
    private final Map<Direction, VoxelShape> shapes;

    public ClusterBlock(final BlockBehaviour.Properties props) {
        super(7.0f, 10.0f, props);
        this.registerDefaultState((this.defaultBlockState().setValue(WATERLOGGED, false)).setValue(FACING, Direction.UP));
        this.shapes = Shapes.rotateAll(Block.boxZ(10.0f, 9.0f, 16.0F)); // width, 16-height, 16
    }

    protected VoxelShape getShape(final BlockState state, final BlockGetter level, final BlockPos pos, final CollisionContext context) {
        return this.shapes.get(state.getValue(FACING));
    }

    static {
        WATERLOGGED = BlockStateProperties.WATERLOGGED;
        FACING = BlockStateProperties.FACING;
    }
}
