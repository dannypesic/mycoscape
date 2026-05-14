package com.dpesic.mycoscape.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.levelgen.feature.AbstractHugeMushroomFeature;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;


public class HugeBlewitMushroomFeature extends AbstractHugeMushroomFeature {

    public HugeBlewitMushroomFeature(Codec<HugeMushroomFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    protected int getTreeHeight(RandomSource random) {
        int h = random.nextInt(3) + 5;
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


                int dy = (d == 0) ? treeHeight - 1 : treeHeight - d;

                blockPos.setWithOffset(origin, dx, dy, dz);
                level.setBlock(blockPos, config.capProvider.getState(random, origin), 2);
            }
        }
    }

    @Override
    protected int getTreeRadiusForHeight(int trunkHeight, int treeHeight, int leafRadius, int yo) {
        return yo <= 1 ? 0 : leafRadius;
    }
}
