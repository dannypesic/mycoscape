package com.dpesic.mycoscape.worldgen.feature;

import com.dpesic.mycoscape.core.ModBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.AbstractHugeMushroomFeature;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;


public class HugeNecroshroomFeature extends AbstractHugeMushroomFeature {

    public HugeNecroshroomFeature(Codec<HugeMushroomFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    protected int getTreeHeight(RandomSource random) {
        int h = random.nextInt(3) + 4 + random.nextInt(2) + 1;
        if (random.nextInt(12) == 0) h *= 2;
        return h;
    }

    @Override
    protected void makeCap(LevelAccessor level, RandomSource random, BlockPos origin,
                           int treeHeight, BlockPos.MutableBlockPos blockPos,
                           HugeMushroomFeatureConfiguration config) {
        int sideRadius = config.foliageRadius;
        int center     = config.foliageRadius - 2;


        for (int dy = treeHeight - 4; dy <= treeHeight - 2; dy++) {
            for (int dx = -sideRadius; dx <= sideRadius; dx++) {
                for (int dz = -sideRadius; dz <= sideRadius; dz++) {
                    boolean xEdge = Math.abs(dx) == sideRadius;
                    boolean zEdge = Math.abs(dz) == sideRadius;
                    if (xEdge == zEdge) continue;

                    blockPos.setWithOffset(origin, dx, dy, dz);
                    BlockState state = config.capProvider.getState(random, origin);
                    if (state.hasProperty(HugeMushroomBlock.WEST)
                            && state.hasProperty(HugeMushroomBlock.EAST)
                            && state.hasProperty(HugeMushroomBlock.NORTH)
                            && state.hasProperty(HugeMushroomBlock.SOUTH)
                            && state.hasProperty(HugeMushroomBlock.UP)) {
                        state = state
                                .setValue(HugeMushroomBlock.UP,    dy == treeHeight - 2)
                                .setValue(HugeMushroomBlock.WEST,  dx < -center)
                                .setValue(HugeMushroomBlock.EAST,  dx > center)
                                .setValue(HugeMushroomBlock.NORTH, dz < -center)
                                .setValue(HugeMushroomBlock.SOUTH, dz > center);
                    }
                    level.setBlock(blockPos, state, 2);
                }
            }
        }


        for (int dx = -2; dx <= 2; dx++) {
            for (int dz = -2; dz <= 2; dz++) {
                blockPos.setWithOffset(origin, dx, treeHeight - 1, dz);
                level.setBlock(blockPos, config.capProvider.getState(random, origin), 2);
            }
        }


        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                blockPos.setWithOffset(origin, dx, treeHeight, dz);
                level.setBlock(blockPos, config.capProvider.getState(random, origin), 2);
            }
        }
    }

    @Override
    protected boolean isValidPosition(LevelAccessor level, BlockPos pos, int maxHeight,
                                      BlockPos.MutableBlockPos mutablePos,
                                      HugeMushroomFeatureConfiguration config) {
        int y = pos.getY();
        if (y < level.getMinBuildHeight() + 1 || y + maxHeight + 1 > level.getMaxBuildHeight()) return false;

        BlockState below = level.getBlockState(pos.below());
        if (!isDirt(below)
                && !below.is(BlockTags.MUSHROOM_GROW_BLOCK)
                && !below.is(BlockTags.STONE_ORE_REPLACEABLES)
                && !below.is(BlockTags.DEEPSLATE_ORE_REPLACEABLES)) {
            return false;
        }

        for (int dy = 0; dy <= maxHeight; dy++) {
            int r = this.getTreeRadiusForHeight(-1, -1, config.foliageRadius, dy);
            for (int dx = -r; dx <= r; dx++) {
                for (int dz = -r; dz <= r; dz++) {
                    BlockState s = level.getBlockState(mutablePos.setWithOffset(pos, dx, dy, dz));
                    if (!s.isAir() && !s.is(BlockTags.LEAVES)
                            && !s.is(BlockTags.STONE_ORE_REPLACEABLES)
                            && !s.is(BlockTags.DEEPSLATE_ORE_REPLACEABLES)
                            && !s.is(ModBlocks.MYCOSLATE.get())) return false;
                }
            }
        }
        return true;
    }

    @Override
    protected int getTreeRadiusForHeight(int trunkHeight, int treeHeight, int leafRadius, int yo) {
        if (yo == 0) return 0;
        return leafRadius;
    }
}
