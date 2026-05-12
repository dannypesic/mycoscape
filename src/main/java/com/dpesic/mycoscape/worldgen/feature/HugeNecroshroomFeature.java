package com.dpesic.mycoscape.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.AbstractHugeMushroomFeature;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;

/**
 * 7×7×3 hollow ring cylinder topped with a 5×5 disc and a 3×3 disc knob.
 *
 * Layer layout (bottom → top, foliageRadius=3):
 *   treeHeight-4  7×7 ring (radius 3, XOR-edge, no corners)
 *   treeHeight-3  7×7 ring
 *   treeHeight-2  7×7 ring  ← top of cylinder; outer blocks connect to 5×5 disc above
 *   treeHeight-1  5×5 solid disc (radius 2)
 *   treeHeight    3×3 solid disc (radius 1)
 */
public class HugeNecroshroomFeature extends AbstractHugeMushroomFeature {

    public HugeNecroshroomFeature(Codec<HugeMushroomFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    protected int getTreeHeight(RandomSource random) {
        int h = random.nextInt(3) + 4 + random.nextInt(2) + 1; // 1–2 taller than vanilla (5–8)
        if (random.nextInt(12) == 0) h *= 2;
        return h;
    }

    @Override
    protected void makeCap(LevelAccessor level, RandomSource random, BlockPos origin,
                           int treeHeight, BlockPos.MutableBlockPos blockPos,
                           HugeMushroomFeatureConfiguration config) {
        int sideRadius = config.foliageRadius;       // 3 → 7×7 ring
        int center     = config.foliageRadius - 2;   // face-flag threshold (=1 with r=3)

        // ── 3-layer 7×7 hollow ring cylinder ──────────────────────────────────────────
        for (int dy = treeHeight - 4; dy <= treeHeight - 2; dy++) {
            for (int dx = -sideRadius; dx <= sideRadius; dx++) {
                for (int dz = -sideRadius; dz <= sideRadius; dz++) {
                    boolean xEdge = Math.abs(dx) == sideRadius;
                    boolean zEdge = Math.abs(dz) == sideRadius;
                    if (xEdge == zEdge) continue; // ring only (XOR), skip corners & interior

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
                    this.placeMushroomBlock(level, blockPos, state);
                }
            }
        }

        // ── 5×5 solid disc (radius 2) directly above the cylinder ─────────────────────
        for (int dx = -2; dx <= 2; dx++) {
            for (int dz = -2; dz <= 2; dz++) {
                blockPos.setWithOffset(origin, dx, treeHeight - 1, dz);
                this.placeMushroomBlock(level, blockPos, config.capProvider.getState(random, origin));
            }
        }

        // ── 3×3 solid disc (radius 1) on top of the 5×5 ──────────────────────────────
        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                blockPos.setWithOffset(origin, dx, treeHeight, dz);
                this.placeMushroomBlock(level, blockPos, config.capProvider.getState(random, origin));
            }
        }
    }

    @Override
    protected int getTreeRadiusForHeight(int trunkHeight, int treeHeight, int leafRadius, int yo) {
        if (yo == 0) return 0;
        return leafRadius; // foliageRadius=3 gives adequate clearance for the 7×7 cylinder
    }
}
