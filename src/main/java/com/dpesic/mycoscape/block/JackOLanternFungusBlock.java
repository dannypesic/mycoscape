package com.dpesic.mycoscape.block;

import com.dpesic.mycoscape.core.ModItems;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.phys.shapes.VoxelShape;

public class JackOLanternFungusBlock extends AbstractFungusBlock {

    public JackOLanternFungusBlock(Properties props) {
        super(props);
    }

    @Override
    public MapCodec<JackOLanternFungusBlock> codec() {
        return simpleCodec(JackOLanternFungusBlock::new);
    }

    @Override
    public ItemStack dropItemstack() {
        return new ItemStack(ModItems.JACK_O_LANTERN_MUSHROOM.get(), 1);
    }

    @Override
    protected VoxelShape shapeMycelium() {
        return Block.box(5, 0, 5, 11, 14, 11);
    }

    @Override
    protected VoxelShape shapeMushroom() {
        return Block.box(0, 0, 0, 16, 14, 16);
    }

    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getHugeMushroomFeature() {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath("mycoscape", "jack_o_lantern_mushroom"));
    }
}
