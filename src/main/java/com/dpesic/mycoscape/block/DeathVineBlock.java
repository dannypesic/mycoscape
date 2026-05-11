package com.dpesic.mycoscape.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class DeathVineBlock extends Block {
    public static final MapCodec<DeathVineBlock> CODEC = simpleCodec(DeathVineBlock::new);

    public DeathVineBlock(Properties properties) {
        super(properties);
    }

    @Override
    public MapCodec<DeathVineBlock> codec() {
        return CODEC;
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier effectApplier, boolean isPrecise) {
        entity.makeStuckInBlock(state, new Vec3(0.25, 0.05000000074505806, 0.25));
        if (entity instanceof LivingEntity && level instanceof ServerLevel serverLevel) {
            Vec3 movement = entity.isClientAuthoritative()
                    ? entity.getKnownMovement()
                    : entity.oldPosition().subtract(entity.position());
            if (movement.horizontalDistanceSqr() > 0.0) {
                double xs = Math.abs(movement.x());
                double zs = Math.abs(movement.z());
                if (xs >= 0.003 || zs >= 0.003) {
                    entity.hurtServer(serverLevel, level.damageSources().sweetBerryBush(), 2.0F);
                }
            }
        }
    }
}
