package com.dpesic.mycoscape.core;

public final class BlockProps {
    private BlockProps() {}

    public enum ToolTier {
        WOOD(2f), STONE(4f), IRON(6f), DIAMOND(8f), NETHERITE(9f), GOLD(12f);
        public final float speed;
        ToolTier(float speed) { this.speed = speed; }
    }

    /** destroyTime value that results in ~ticks break time with the given tool tier */
    public static float ticks(int ticks, ToolTier tier) {
        return ticks * tier.speed / 30f;
    }
}
