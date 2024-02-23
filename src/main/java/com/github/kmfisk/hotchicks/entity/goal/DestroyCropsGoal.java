package com.github.kmfisk.hotchicks.entity.goal;

import com.github.kmfisk.hotchicks.block.BerryBushBlock;
import com.github.kmfisk.hotchicks.block.TrellisBlock;
import com.github.kmfisk.hotchicks.block.TripleCropBlock;
import com.github.kmfisk.hotchicks.entity.LivestockEntity;
import net.minecraft.block.*;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.tags.BlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.ForgeEventFactory;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;

public class DestroyCropsGoal extends MoveToBlockGoal {
    private final LivestockEntity livestock;
    private boolean wantsToRaid;
    private boolean canRaid;

    public DestroyCropsGoal(LivestockEntity livestock, double speedModifier, int searchRange) {
        super(livestock, speedModifier, searchRange);
        this.livestock = livestock;
    }

    @Override
    public boolean canUse() {
        if (nextStartTick <= 0) {
            if (!ForgeEventFactory.getMobGriefingEvent(livestock.level, livestock)) return false;

            canRaid = false;
            wantsToRaid = true;
        }

        return super.canUse();
    }

    @Override
    public boolean canContinueToUse() {
        return canRaid && super.canContinueToUse();
    }

    @Override
    public void tick() {
        super.tick();
        livestock.getLookControl().setLookAt((double) blockPos.getX() + 0.5D, blockPos.getY() + 1, (double) blockPos.getZ() + 0.5D, 10.0F, (float) livestock.getMaxHeadXRot());
        if (isReachedTarget()) {
            Level level = livestock.level;
            BlockPos cropPos = blockPos.above();
            BlockState state = level.getBlockState(cropPos);
            Block block = state.getBlock();
            boolean isCrop = block.is(BlockTags.CROPS) || block instanceof SweetBerryBushBlock;
            boolean excludedCropTypes = block instanceof TripleCropBlock || block instanceof TrellisBlock;
            if (canRaid && isCrop && !excludedCropTypes) {
                if (level.getBlockState(blockPos).getBlock() instanceof GrassBlock)
                    level.setBlock(blockPos, Blocks.COARSE_DIRT.defaultBlockState(), 2);

                if (block instanceof BerryBushBlock || block instanceof SweetBerryBushBlock) {
                    level.levelEvent(2001, cropPos, Block.getId(state));
                    level.setBlock(cropPos, Blocks.DEAD_BUSH.defaultBlockState(), 2);
                } else level.destroyBlock(cropPos, false, livestock);

                livestock.ate();
            }

            canRaid = false;
            nextStartTick = 400 + livestock.getRandom().nextInt(200);
        }
    }

    @Override
    protected boolean isValidTarget(LevelReader level, BlockPos pos) {
        Block block = level.getBlockState(pos.above()).getBlock();
        boolean isCrop = block.is(BlockTags.CROPS) || block instanceof SweetBerryBushBlock;
        boolean excludedCropTypes = block instanceof TripleCropBlock || block instanceof TrellisBlock;
        if (isCrop && !excludedCropTypes && this.wantsToRaid && !this.canRaid) {
            this.canRaid = true;
            return true;
        }

        return false;
    }
}
