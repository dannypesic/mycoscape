package com.dpesic.mycoscape.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.Vec3;

public class RotwoodLeavesBlock extends LeavesBlock {
    private static final Vec3 LEAF_COLOR = Vec3.fromRGB24(0x70553D);

    public RotwoodLeavesBlock(float leafParticleChance, BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public void animateTick(net.minecraft.world.level.block.state.BlockState state, Level level, BlockPos pos, RandomSource random) {
        super.animateTick(state, level, pos, random);
        if (random.nextInt(10) == 0) {
            level.addParticle(
                new DustParticleOptions(new org.joml.Vector3f((float) LEAF_COLOR.x, (float) LEAF_COLOR.y, (float) LEAF_COLOR.z), 1.0f),
                pos.getX() + random.nextDouble(),
                pos.getY() - 0.05,
                pos.getZ() + random.nextDouble(),
                0, -0.05, 0
            );
        }
    }
}
