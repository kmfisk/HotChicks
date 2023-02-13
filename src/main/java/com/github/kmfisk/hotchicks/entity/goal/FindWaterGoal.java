package com.github.kmfisk.hotchicks.entity.goal;

import com.github.kmfisk.hotchicks.block.HotBlocks;
import com.github.kmfisk.hotchicks.block.TroughBlock;
import com.github.kmfisk.hotchicks.block.TroughFillType;
import com.github.kmfisk.hotchicks.block.WaterBottleBlock;
import com.github.kmfisk.hotchicks.entity.LivestockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

public class FindWaterGoal extends MoveToBlockGoal {
    private final LivestockEntity entity;

    public FindWaterGoal(LivestockEntity entity, int searchRange, int verticalSearchRange) {
        super(entity, 1.0D, searchRange, verticalSearchRange);
        this.entity = entity;
    }

    @Override
    public boolean canUse() {
        if (!(entity.getThirst().getValue() < entity.getThirst().getMax())) return false;
        if (entity.getThirst().isLow()) return super.canUse();
        else return entity.getRandom().nextInt(entity.isBaby() ? 50 : 1000) == 0 && super.canUse();
    }

    @Override
    public boolean canContinueToUse() {
        return entity.getThirst().getValue() < entity.getThirst().getMax() && super.canContinueToUse();
    }

    @Override
    protected BlockPos getMoveToTarget() {
        return blockPos.relative(entity.getDirection().getOpposite()); // todo check
    }

    @Override
    public void tick() {
        super.tick();
        if (isReachedTarget()) {
            if (entity.tickCount % 20 == 0) {
                BlockState blockState = entity.level.getBlockState(blockPos);
                boolean flag1 = blockState.getBlock() instanceof TroughBlock && blockState.getValue(TroughBlock.CONTAINS) == TroughFillType.WATER;
                if (flag1 || blockState.getBlock() instanceof WaterBottleBlock)
                    if (entity.getThirst().getValue() < entity.getThirst().getMax()) entity.getThirst().increment(1);
            }
        }
    }

    @Override
    protected boolean isValidTarget(IWorldReader level, BlockPos pos) {
        if (level.getBlockState(pos).is(HotBlocks.WOODEN_TROUGH.get()) || level.getBlockState(pos).is(HotBlocks.METAL_TROUGH.get())) {
            BlockState blockState = level.getBlockState(pos);
            return blockState.getValue(TroughBlock.CONTAINS) == TroughFillType.WATER;
        }

        return level.getBlockState(pos).is(HotBlocks.WATER_BOTTLE.get());
    }
}
