package com.dpesic.mycoscape.item;

import com.dpesic.mycoscape.core.ModBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public abstract class AbstractMushroomItem extends Item {
    public AbstractMushroomItem(Properties props) {
        super(props);
    }

    protected abstract BlockState placeBlockstate(); // return ModBlocks.MY_ITEM.get().defaultBlockState();

    protected abstract TagKey<Block> groundStateTag();

    @Override
    public InteractionResult useOn(UseOnContext ctx) {
        Level level = ctx.getLevel();
        BlockPos groundPos = ctx.getClickedPos();

        // On mycoscape:#fungi_ground
        BlockState groundState = level.getBlockState(groundPos);
        if (!groundState.is(groundStateTag())) {
            return InteractionResult.PASS;
        }

        BlockPos placePos = groundPos.above();

        BlockPlaceContext placeCtx = new BlockPlaceContext(ctx);
        BlockState targetState = level.getBlockState(placePos);
        if (!targetState.canBeReplaced(placeCtx)) {
            return InteractionResult.PASS;
        }

        if (!level.mayInteract(ctx.getPlayer(), groundPos)) {
            return InteractionResult.PASS;
        }

        if (!level.isClientSide()) {
            BlockState plantState = placeBlockstate();
            if (!plantState.canSurvive(level, placePos)) {
                return InteractionResult.PASS;
            }

            if (groundState.is(ModBlocks.ROTWOOD)) {
                level.setBlock(placePos, ModBlocks.NECROSHROOM_FUNGUS.get().defaultBlockState(), 3);
            }
            else {
                level.setBlock(placePos, plantState, 3);
            }

            level.playSound(null, placePos, SoundEvents.ROOTS_PLACE, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
            ItemStack stack = ctx.getItemInHand();
            if (ctx.getPlayer() == null || !ctx.getPlayer().getAbilities().instabuild) {
                stack.shrink(1);
            }
        }

        return InteractionResult.SUCCESS;
    }
}
