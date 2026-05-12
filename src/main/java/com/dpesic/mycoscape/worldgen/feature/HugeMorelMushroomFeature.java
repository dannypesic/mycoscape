package com.dpesic.mycoscape.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.levelgen.feature.AbstractHugeMushroomFeature;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;

/**
 * Morel shape, built from the ground up exactly as specified:
 *
 *   dy = 0..1 (or 0..2)      bare stem (2–3 blocks, from treeHeight override)
 *   dy = treeHeight-5         PLUS layer: stem centre + 4 cardinal cap blocks (N/S/E/W only)
 *   dy = treeHeight-4..-2     BODY: stem centre + full 3×3 ring of cap (8 surrounding blocks)
 *   dy = treeHeight-1         PLUS layer: stem centre + 4 cardinal cap blocks
 *   dy = treeHeight           single cap block on the very top
 *
 * makeCap runs BEFORE placeTrunk, so cap blocks placed at centre-column heights will prevent
 * the trunk from placing a stem block there — but we deliberately skip the centre (dx=0,dz=0)
 * in the plus and ring layers so the trunk fills those naturally, leaving the centre as stem.
 * Only the topmost block (treeHeight) is a lone cap block above the trunk.
 *
 * Tree height is overridden to 7 or 8 so the bare stem below the head is 2–3 blocks tall.
 */
public class HugeMorelMushroomFeature extends AbstractHugeMushroomFeature {

    public HugeMorelMushroomFeature(Codec<HugeMushroomFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    protected int getTreeHeight(RandomSource random) {
        // 7 → 2 bare stem blocks visible; 8 → 3 bare stem blocks visible
        return random.nextBoolean() ? 7 : 8;
    }

    @Override
    protected void makeCap(LevelAccessor level, RandomSource random, BlockPos origin,
                           int treeHeight, BlockPos.MutableBlockPos blockPos,
                           HugeMushroomFeatureConfiguration config) {

        // ── Plus layers (cardinal 4, no centre, no corners) ───────────────────────────
        int[] plusHeights = { treeHeight - 5, treeHeight - 1 };
        for (int dy : plusHeights) {
            placePlus(level, random, origin, dy, blockPos, config);
        }

        // ── Body: 3-layer full 3×3 ring around the stem centre ────────────────────────
        for (int dy = treeHeight - 4; dy <= treeHeight - 2; dy++) {
            placeRing(level, random, origin, dy, blockPos, config);
        }

        // ── Single cap block on top ───────────────────────────────────────────────────
        blockPos.setWithOffset(origin, 0, treeHeight, 0);
        this.placeMushroomBlock(level, blockPos, config.capProvider.getState(random, origin));
    }

    /** 4 cap blocks at N/S/E/W — no centre, no corners. */
    private void placePlus(LevelAccessor level, RandomSource random, BlockPos origin,
                           int dy, BlockPos.MutableBlockPos blockPos,
                           HugeMushroomFeatureConfiguration config) {
        int[][] offsets = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
        for (int[] o : offsets) {
            blockPos.setWithOffset(origin, o[0], dy, o[1]);
            this.placeMushroomBlock(level, blockPos, config.capProvider.getState(random, origin));
        }
    }

    /** 8 cap blocks forming the full 3×3 ring around (0,0) — centre skipped, trunk fills it. */
    private void placeRing(LevelAccessor level, RandomSource random, BlockPos origin,
                           int dy, BlockPos.MutableBlockPos blockPos,
                           HugeMushroomFeatureConfiguration config) {
        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                if (dx == 0 && dz == 0) continue; // leave centre for trunk
                blockPos.setWithOffset(origin, dx, dy, dz);
                this.placeMushroomBlock(level, blockPos, config.capProvider.getState(random, origin));
            }
        }
    }

    @Override
    protected int getTreeRadiusForHeight(int trunkHeight, int treeHeight, int leafRadius, int yo) {
        // Cap never extends beyond radius 1 from the stem centre.
        return yo <= 1 ? 0 : 1;
    }
}
