package com.dpesic.mycoscape.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.levelgen.feature.AbstractHugeMushroomFeature;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;


public class HugeMorelMushroomFeature extends AbstractHugeMushroomFeature {

    public HugeMorelMushroomFeature(Codec<HugeMushroomFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    protected int getTreeHeight(RandomSource random) {

        return random.nextBoolean() ? 7 : 8;
    }

    @Override
    protected void makeCap(LevelAccessor level, RandomSource random, BlockPos origin,
                           int treeHeight, BlockPos.MutableBlockPos blockPos,
                           HugeMushroomFeatureConfiguration config) {


        int[] plusHeights = { treeHeight - 5, treeHeight - 1 };
        for (int dy : plusHeights) {
            placePlus(level, random, origin, dy, blockPos, config);
        }


        for (int dy = treeHeight - 4; dy <= treeHeight - 2; dy++) {
            placeRing(level, random, origin, dy, blockPos, config);
        }


        blockPos.setWithOffset(origin, 0, treeHeight, 0);
        level.setBlock(blockPos, config.capProvider.getState(random, origin), 2);
    }


    private void placePlus(LevelAccessor level, RandomSource random, BlockPos origin,
                           int dy, BlockPos.MutableBlockPos blockPos,
                           HugeMushroomFeatureConfiguration config) {
        int[][] offsets = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
        for (int[] o : offsets) {
            blockPos.setWithOffset(origin, o[0], dy, o[1]);
            level.setBlock(blockPos, config.capProvider.getState(random, origin), 2);
        }
    }


    private void placeRing(LevelAccessor level, RandomSource random, BlockPos origin,
                           int dy, BlockPos.MutableBlockPos blockPos,
                           HugeMushroomFeatureConfiguration config) {
        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                if (dx == 0 && dz == 0) continue;
                blockPos.setWithOffset(origin, dx, dy, dz);
                level.setBlock(blockPos, config.capProvider.getState(random, origin), 2);
            }
        }
    }

    @Override
    protected int getTreeRadiusForHeight(int trunkHeight, int treeHeight, int leafRadius, int yo) {

        return yo <= 1 ? 0 : 1;
    }
}
