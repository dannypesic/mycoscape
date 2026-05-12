package com.dpesic.mycoscape.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.levelgen.feature.AbstractHugeMushroomFeature;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;

/**
 * Concave stepped-bowl cap resembling a mature jack-o-lantern mushroom (Omphalotus).
 *
 * Viewed from the side (with foliageRadius=3):
 *
 *   [  . . . . . . .  ]   treeHeight,   ring  r=3   (outer rim, highest — concave edge)
 *        [. . . . .]       treeHeight-1, disc  r-1=2 (5×5 inner bowl floor)
 *           [. . .]        treeHeight-2, disc  r=1   (3×3 base under the bowl, covers stem)
 *
 * Viewed from above: outer ring is the highest visible surface; the 5×5 inner disc sits
 * 1 block lower in the concave depression; the 3×3 disc sits 1 block lower still, hugging
 * the stem. The centre of the 5×5 and 3×3 discs land in air (makeCap runs before placeTrunk),
 * blocking the trunk — so the stem is fully covered from above.
 */
public class HugeJackOLanternMushroomFeature extends AbstractHugeMushroomFeature {

    public HugeJackOLanternMushroomFeature(Codec<HugeMushroomFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    protected void makeCap(LevelAccessor level, RandomSource random, BlockPos origin,
                           int treeHeight, BlockPos.MutableBlockPos blockPos,
                           HugeMushroomFeatureConfiguration config) {
        int r = config.foliageRadius; // e.g. 3

        // ── Outer rim: ring at treeHeight ──────────────────────────────────────────────
        // XOR-edge ring (no corners, no interior) — the highest part of the concave cap.
        for (int dx = -r; dx <= r; dx++) {
            for (int dz = -r; dz <= r; dz++) {
                boolean xEdge = Math.abs(dx) == r;
                boolean zEdge = Math.abs(dz) == r;
                if (xEdge == zEdge) continue;
                blockPos.setWithOffset(origin, dx, treeHeight, dz);
                this.placeMushroomBlock(level, blockPos, config.capProvider.getState(random, origin));
            }
        }

        // ── Inner bowl floor: 5×5 disc at treeHeight-1 ────────────────────────────────
        // Solid disc, radius r-1. Centre block lands in air (trunk not placed yet),
        // preventing the stem from growing into this layer.
        int inner = r - 1;
        for (int dx = -inner; dx <= inner; dx++) {
            for (int dz = -inner; dz <= inner; dz++) {
                blockPos.setWithOffset(origin, dx, treeHeight - 1, dz);
                this.placeMushroomBlock(level, blockPos, config.capProvider.getState(random, origin));
            }
        }

        // ── Stem base: 3×3 disc at treeHeight-2 ───────────────────────────────────────
        // Sits directly below the 5×5 bowl floor, hugging the stem.
        // Centre block again lands in air, blocking trunk placement one layer lower.
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
