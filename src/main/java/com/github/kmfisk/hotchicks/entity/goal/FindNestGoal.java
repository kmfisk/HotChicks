package com.github.kmfisk.hotchicks.entity.goal;

import com.github.kmfisk.hotchicks.entity.HotChickenEntity;
import com.google.common.collect.Lists;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;

public class FindNestGoal extends Goal {
    private final HotChickenEntity chicken;
    private final int tooFarDist;
    private int travellingTicks;
    private final List<BlockPos> blacklistedTargets = Lists.newArrayList();
    @Nullable
    private Path lastPath = null;
    private int ticksStuck;

    public FindNestGoal(HotChickenEntity chicken, int tooFarDist) {
        this.setFlags(EnumSet.of(Flag.MOVE));
        this.chicken = chicken;
        this.tooFarDist = tooFarDist;
        this.travellingTicks = chicken.level.random.nextInt(10);
    }

    @Override
    public boolean canUse() {
        return chicken.getNestPos() != null && !chicken.hasRestriction() && chicken.wantsToLayEggs() && !hasReachedTarget(chicken.getNestPos()) && chicken.isValidNestBlock(chicken.getNestPos());
    }

    @Override
    public void start() {
        travellingTicks = 0;
        ticksStuck = 0;
        super.start();
    }

    @Override
    public void stop() {
        travellingTicks = 0;
        ticksStuck = 0;
        chicken.getNavigation().stop();
    }

    @Override
    public void tick() {
        if (chicken.getNestPos() != null) {
            ++travellingTicks;
            if (travellingTicks > 600) dropAndBlacklistNest();
            else if (!chicken.getNavigation().isInProgress()) {
                if (!(chicken.getNestPos().closerThan(chicken.blockPosition(), tooFarDist))) dropNest();
                else {
                    chicken.getNavigation().moveTo(chicken.getNestPos().getX(), chicken.getNestPos().getY(), chicken.getNestPos().getZ(), 1.0D);
                    if (chicken.getNavigation().getPath() == null && !chicken.getNavigation().getPath().canReach())
                        dropAndBlacklistNest();
                    else if (lastPath != null && chicken.getNavigation().getPath().sameAs(lastPath)) {
                        ++ticksStuck;
                        if (ticksStuck > 60) {
                            dropNest();
                            ticksStuck = 0;
                        }
                    } else lastPath = chicken.getNavigation().getPath();
                }
            }
        }
    }

    boolean isTargetBlacklisted(BlockPos pPos) {
        return blacklistedTargets.contains(pPos);
    }

    private void blacklistTarget(BlockPos pPos) {
        blacklistedTargets.add(pPos);

        while (blacklistedTargets.size() > 3) blacklistedTargets.remove(0);
    }

    void clearBlacklist() {
        blacklistedTargets.clear();
    }

    private void dropAndBlacklistNest() {
        if (chicken.getNestPos() != null) blacklistTarget(chicken.getNestPos());
        dropNest();
    }

    private void dropNest() {
        chicken.setNestPos(null);
        chicken.remainingCooldownBeforeLocatingNewNest = 200;
    }

    private boolean hasReachedTarget(BlockPos pPos) {
        if (pPos.closerThan(chicken.blockPosition(), 1)) return true;
        else {
            Path path = chicken.getNavigation().getPath();
            return path != null && path.getTarget().equals(pPos) && path.canReach() && path.isDone();
        }
    }
}
