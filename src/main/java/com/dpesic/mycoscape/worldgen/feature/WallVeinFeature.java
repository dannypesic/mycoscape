package com.dpesic.mycoscape.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

import java.util.HashSet;
import java.util.Set;

public class WallVeinFeature extends Feature<WallVeinFeatureConfiguration> {

    public WallVeinFeature(Codec<WallVeinFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<WallVeinFeatureConfiguration> ctx) {
        WorldGenLevel level = ctx.level();
        RandomSource random = ctx.random();
        BlockPos origin = ctx.origin();
        WallVeinFeatureConfiguration config = ctx.config();

        BlockPos wallPos = findWallPos(level, origin, config);
        if (wallPos == null) return false;

        int length = config.minLength() + random.nextInt(config.maxLength() - config.minLength() + 1);

        Direction primary = randomDirection(random);
        Direction[] driftAxes = getPerpendicularDirections(primary);

        Set<BlockPos> placed = new HashSet<>();
        walk(level, random, wallPos, primary, driftAxes, length, config, 0, placed);
        return !placed.isEmpty();
    }

    

    private void walk(WorldGenLevel level, RandomSource random, BlockPos start,
                      Direction primary, Direction[] driftAxes, int maxSteps,
                      WallVeinFeatureConfiguration config, int depth, Set<BlockPos> placed) {
        BlockPos pos = start;
        int failStreak = 0;

        for (int i = 0; i < maxSteps && failStreak < 6; i++) {
            BlockPos next;

            if (random.nextFloat() < config.curveChance()) {
                
                
                Direction drift = driftAxes[random.nextInt(driftAxes.length)];
                next = pos.relative(drift);
            } else {
                
                next = pos.relative(primary);
            }

            if (tryPlace(level, random, next, config, placed)) {
                pos = next;
                failStreak = 0;

                
                if (depth < 1 && random.nextFloat() < config.branchChance()) {
                    Direction branchDrift = driftAxes[random.nextInt(driftAxes.length)];
                    BlockPos branchStart = pos.relative(branchDrift);
                    int branchLen = 3 + random.nextInt(Math.max(1, (maxSteps - i) / 3));
                    walk(level, random, branchStart, primary, driftAxes, branchLen, config, depth + 1, placed);
                }
                continue;
            }

            
            boolean recovered = false;
            for (Direction altDrift : driftAxes) {
                BlockPos alt = pos.relative(altDrift);
                if (tryPlace(level, random, alt, config, placed)) {
                    pos = alt;
                    failStreak = 0;
                    recovered = true;
                    break;
                }
            }
            if (!recovered) {
                
                pos = pos.relative(primary);
                failStreak++;
            }
        }
    }

    

    private boolean tryPlace(WorldGenLevel level, RandomSource random, BlockPos pos,
                             WallVeinFeatureConfiguration config, Set<BlockPos> placed) {
        if (!isReplaceable(level, pos, config)) return false;
        if (!hasAirNeighbor(level, pos)) return false;
        if (countAdjacentVein(pos, placed) > 1) return false;
        level.setBlock(pos, config.state().getState(random, pos), 2);
        placed.add(pos.immutable());
        config.vegetation().ifPresent(veg -> {
            BlockPos above = pos.above();
            if (level.getBlockState(above).isAir()) {
                level.setBlock(above, veg.getState(random, above), 2);
            }
        });
        return true;
    }

    

    private int countAdjacentVein(BlockPos pos, Set<BlockPos> placed) {
        int count = 0;
        for (Direction dir : Direction.values()) {
            if (placed.contains(pos.relative(dir))) {
                count++;
            }
        }
        return count;
    }

    private BlockPos findWallPos(WorldGenLevel level, BlockPos origin, WallVeinFeatureConfiguration config) {
        if (isReplaceable(level, origin, config) && hasAirNeighbor(level, origin)) {
            return origin;
        }
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        for (int radius = 1; radius <= 8; radius++) {
            for (int dx = -radius; dx <= radius; dx++) {
                for (int dy = -radius; dy <= radius; dy++) {
                    for (int dz = -radius; dz <= radius; dz++) {
                        if (Math.abs(dx) != radius && Math.abs(dy) != radius && Math.abs(dz) != radius) continue;
                        mutable.set(origin.getX() + dx, origin.getY() + dy, origin.getZ() + dz);
                        if (isReplaceable(level, mutable, config) && hasAirNeighbor(level, mutable)) {
                            return mutable.immutable();
                        }
                    }
                }
            }
        }
        return null;
    }

    private boolean isReplaceable(WorldGenLevel level, BlockPos pos, WallVeinFeatureConfiguration config) {
        BlockState state = level.getBlockState(pos);
        return !state.isAir() && state.is(config.replaceable());
    }

    private boolean hasAirNeighbor(WorldGenLevel level, BlockPos pos) {
        for (Direction dir : Direction.values()) {
            if (level.getBlockState(pos.relative(dir)).isAir()) {
                return true;
            }
        }
        return false;
    }

    private Direction randomDirection(RandomSource random) {
        return Direction.values()[random.nextInt(6)];
    }

    private Direction[] getPerpendicularDirections(Direction dir) {
        return switch (dir.getAxis()) {
            case X -> new Direction[]{ Direction.NORTH, Direction.SOUTH, Direction.UP, Direction.DOWN };
            case Z -> new Direction[]{ Direction.EAST, Direction.WEST, Direction.UP, Direction.DOWN };
            case Y -> new Direction[]{ Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST };
        };
    }
}
