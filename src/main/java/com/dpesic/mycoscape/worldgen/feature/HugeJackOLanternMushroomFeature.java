package com.dpesic.mycoscape.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.levelgen.feature.AbstractHugeMushroomFeature;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;


public class HugeJackOLanternMushroomFeature extends AbstractHugeMushroomFeature {

    public HugeJackOLanternMushroomFeature(Codec<HugeMushroomFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    protected void makeCap(LevelAccessor level, RandomSource random, BlockPos origin,
                           int treeHeight, BlockPos.MutableBlockPos blockPos,
                           HugeMushroomFeatureConfiguration config) {
        int r = config.foliageRadius;



        for (int dx = -r; dx <= r; dx++) {
            for (int dz = -r; dz <= r; dz++) {
                boolean xEdge = Math.abs(dx) == r;
                boolean zEdge = Math.abs(dz) == r;
                if (xEdge == zEdge) continue;
                blockPos.setWithOffset(origin, dx, treeHeight, dz);
                this.placeMushroomBlock(level, blockPos, config.capProvider.getState(random, origin));
            }
        }




        int inner = r - 1;
        for (int dx = -inner; dx <= inner; dx++) {
            for (int dz = -inner; dz <= inner; dz++) {
                blockPos.setWithOffset(origin, dx, treeHeight - 1, dz);
                this.placeMushroomBlock(level, blockPos, config.capProvider.getState(random, origin));
            }
        }




        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                blockPos.setWithOffset(origin, dx, treeHeight - 2, dz);
                this.placeMushroomBlock(level, blockPos, config.capProvider.getState(random, origin));
            }
        }
    }

    @Override
    protected int getTreeRadiusForHeight(int trunkHeight, int treeHeight, int leafRadius, int yo) {
        return yo == 0 ? 0 : leafRadius;
    }
}
