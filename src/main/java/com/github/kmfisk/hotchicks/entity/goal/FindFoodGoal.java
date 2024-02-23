package com.github.kmfisk.hotchicks.entity.goal;

import com.github.kmfisk.hotchicks.block.HotBlocks;
import com.github.kmfisk.hotchicks.block.entity.FoodCrockTileEntity;
import com.github.kmfisk.hotchicks.block.entity.TroughTileEntity;
import com.github.kmfisk.hotchicks.entity.LivestockEntity;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;

public class FindFoodGoal extends MoveToBlockGoal {
    private final LivestockEntity entity;

    public FindFoodGoal(LivestockEntity entity, int searchRange, int verticalSearchRange) {
        super(entity, 1.0D, searchRange, verticalSearchRange);
        this.entity = entity;
    }

    @Override
    public boolean canUse() {
        if (!(entity.getHunger().getValue() < entity.getHunger().getMax())) return false;
        if (entity.getHunger().isLow()) return super.canUse();
        else return entity.getRandom().nextInt(entity.isBaby() ? 50 : 1000) == 0 && super.canUse();
    }

    @Override
    public boolean canContinueToUse() {
        return entity.getHunger().getValue() < entity.getHunger().getMax() && super.canContinueToUse();
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
                BlockEntity tileEntity = entity.level.getBlockEntity(blockPos);
                if (tileEntity instanceof Container) {
                    Container inventory = (Container) tileEntity;
                    for (int i = 0; i < inventory.getContainerSize(); i++) {
                        ItemStack stack = inventory.getItem(i);
                        if (!stack.isEmpty() && entity.isEdibleFood(stack)) {
                            stack.shrink(1);
                            tileEntity.setChanged();
                            entity.getHunger().increment(1);
                            break;
                        }
                    }
                }
            }
        }
    }

    @Override
    protected boolean isValidTarget(LevelReader level, BlockPos pos) {
        if (level.getBlockState(pos).is(HotBlocks.WOODEN_TROUGH.get()) || level.getBlockState(pos).is(HotBlocks.METAL_TROUGH.get())) {
            BlockEntity tileEntity = level.getBlockEntity(pos);
            if (tileEntity instanceof TroughTileEntity)
                return ((TroughTileEntity) tileEntity).getItems().stream().anyMatch(entity::isEdibleFood);
        }
        if (entity.canUseSmallDishes() && level.getBlockState(pos).is(HotBlocks.FOOD_CROCK.get())) {
            BlockEntity tileEntity = level.getBlockEntity(pos);
            if (tileEntity instanceof FoodCrockTileEntity)
                return ((FoodCrockTileEntity) tileEntity).getItems().stream().anyMatch(entity::isEdibleFood);
        }

        return false;
    }
}
