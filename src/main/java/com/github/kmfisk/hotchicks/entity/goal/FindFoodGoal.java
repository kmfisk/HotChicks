package com.github.kmfisk.hotchicks.entity.goal;

import com.github.kmfisk.hotchicks.block.HotBlocks;
import com.github.kmfisk.hotchicks.block.entity.FoodCrockTileEntity;
import com.github.kmfisk.hotchicks.block.entity.TroughTileEntity;
import com.github.kmfisk.hotchicks.entity.LivestockEntity;
import net.minecraft.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

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
                TileEntity tileEntity = entity.level.getBlockEntity(blockPos);
                if (tileEntity instanceof TroughTileEntity) {
                    IInventory inventory = (IInventory) tileEntity;
                    for (int i = 0; i < inventory.getContainerSize(); i++) {
                        ItemStack stack = inventory.getItem(i);
                        if (!stack.isEmpty() && entity.isEdibleFood(stack)) {
                            stack.shrink(1);
                            entity.getHunger().increment(1);
                            break;
                        }
                    }
                }
            }
        }
    }

    @Override
    protected boolean isValidTarget(IWorldReader level, BlockPos pos) {
        if (level.getBlockState(pos).is(HotBlocks.TROUGH_BLOCK.get()) || level.getBlockState(pos).is(HotBlocks.METAL_TROUGH_BLOCK.get())) {
            TileEntity tileEntity = level.getBlockEntity(pos);
            if (tileEntity instanceof TroughTileEntity)
                return ((TroughTileEntity) tileEntity).getItems().stream().anyMatch(entity::isEdibleFood);
        }
        if (level.getBlockState(pos).is(HotBlocks.FOOD_CROCK.get())) {
            TileEntity tileEntity = level.getBlockEntity(pos);
            if (tileEntity instanceof FoodCrockTileEntity)
                return ((FoodCrockTileEntity) tileEntity).getItems().stream().anyMatch(entity::isEdibleFood);
        }

        return false;
    }
}
