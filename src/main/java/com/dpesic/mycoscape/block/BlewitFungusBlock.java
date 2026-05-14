package com.dpesic.mycoscape.block;

import com.dpesic.mycoscape.core.ModItems;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlewitFungusBlock extends AbstractFungusBlock {

    public BlewitFungusBlock(BlockBehaviour.Properties props) {
        super(props);
    }

    @Override
    public MapCodec<BlewitFungusBlock> codec() {
        return simpleCodec(BlewitFungusBlock::new);
    }

    @Override
    public ItemStack dropItemstack() {
        return new ItemStack(ModItems.BLEWIT.get(), 1);
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
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath("mycoscape", "blewit_mushroom"));
    }
}
