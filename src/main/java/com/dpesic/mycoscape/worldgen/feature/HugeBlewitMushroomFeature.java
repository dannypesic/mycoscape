package com.dpesic.mycoscape.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.levelgen.feature.AbstractHugeMushroomFeature;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;

/**
 * Drooping umbrella/parasol shape: flat at the center but slopes down toward the edges.
 * One cap block per (dx, dz) column placed at Y = treeHeight - Chebyshev_distance.
 * The center column (d=0) is shifted down by 1 to replace the top stem block,
 * so the cap appears to rest on top of the stem rather than floating above it.
 * Corners are cut like vanilla brown. With foliageRadius=3 the outer edge droops 3 blocks.
 */
public class HugeBlewitMushroomFeature extends AbstractHugeMushroomFeature {

    public HugeBlewitMushroomFeature(Codec<HugeMushroomFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    protected int getTreeHeight(RandomSource random) {
        int h = random.nextInt(3) + 5; // vanilla default +1 (5–7 instead of 4–6)
        if (random.nextInt(12) == 0) h *= 2;
        return h;
    }

    @Override
    protected void makeCap(LevelAccessor level, RandomSource random, BlockPos origin,
                           int treeHeight, BlockPos.MutableBlockPos blockPos,
                           HugeMushroomFeatureConfiguration config) {
        int radius = config.foliageRadius;

        for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {
                if (Math.abs(dx) == radius && Math.abs(dz) == radius) continue;

                int d = Math.max(Math.abs(dx), Math.abs(dz));
                // Shift the center block down by 1 — makeCap runs before placeTrunk,
                // so the cap block lands in air and then blocks the stem from being placed there.
                int dy = (d == 0) ? treeHeight - 1 : treeHeight - d;

                blockPos.setWithOffset(origin, dx, dy, dz);
                this.placeMushroomBlock(level, blockPos, config.capProvider.getState(random, origin));
            }
        }
    }

    @Override
    protected int getTreeRadiusForHeight(int trunkHeight, int treeHeight, int leafRadius, int yo) {
        return yo <= 1 ? 0 : leafRadius;
    }
}
