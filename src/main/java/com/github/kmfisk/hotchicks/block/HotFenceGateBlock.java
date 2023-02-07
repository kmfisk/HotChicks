package com.github.kmfisk.hotchicks.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class HotFenceGateBlock extends FenceGateBlock {
    public HotFenceGateBlock(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType use(BlockState state, World level, BlockPos pos, PlayerEntity entity, Hand hand, BlockRayTraceResult hit) {
        /*if (state.getValue(OPEN)) state = state.setValue(OPEN, Boolean.FALSE);
        else state = state.setValue(OPEN, Boolean.TRUE);*/
        level.setBlock(pos, state.cycle(OPEN), 10);

        level.levelEvent(entity, state.getValue(OPEN) ? 1008 : 1014, pos, 0);
        return ActionResultType.sidedSuccess(level.isClientSide);
    }
}
