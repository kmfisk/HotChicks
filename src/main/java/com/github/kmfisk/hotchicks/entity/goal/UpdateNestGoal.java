package com.github.kmfisk.hotchicks.entity.goal;

import com.github.kmfisk.hotchicks.entity.HotChickenEntity;
import com.github.kmfisk.hotchicks.entity.LivestockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;

public class UpdateNestGoal extends Goal {
    private final HotChickenEntity chicken;
    private final int searchRange;
    private final int verticalSearchRange;
    protected int verticalSearchStart;

    public UpdateNestGoal(HotChickenEntity chicken, int searchRange, int verticalSearchRange) {
        this.chicken = chicken;
        this.searchRange = searchRange;
        this.verticalSearchStart = 0;
        this.verticalSearchRange = verticalSearchRange;
    }

    @Override
    public boolean canUse() {
        if (chicken.getSex() == LivestockEntity.Sex.MALE) return false;
        return chicken.remainingCooldownBeforeLocatingNewNest == 0 && !chicken.hasNest() && chicken.wantsToLayEggs();
    }

    @Override
    public boolean canContinueToUse() {
        return false;
    }

    @Override
    public void start() {
        int i = this.searchRange;
        int j = this.verticalSearchRange;
        chicken.remainingCooldownBeforeLocatingNewNest = 200;
        BlockPos chickenPos = chicken.blockPosition();
        BlockPos.MutableBlockPos blockpos$mutable = new BlockPos.MutableBlockPos();
        for (int k = verticalSearchStart; k <= j; k = k > 0 ? -k : 1 - k) {
            for (int l = 0; l < i; ++l) {
                for (int i1 = 0; i1 <= l; i1 = i1 > 0 ? -i1 : 1 - i1) {
                    for (int j1 = i1 < l && i1 > -l ? l : 0; j1 <= l; j1 = j1 > 0 ? -j1 : 1 - j1) {
                        blockpos$mutable.setWithOffset(chickenPos, i1, k - 1, j1);
                        if (chicken.isWithinRestriction(blockpos$mutable) && chicken.isValidNestBlock(blockpos$mutable)) {
                            if (!chicken.goToNestGoal.isTargetBlacklisted(blockpos$mutable)) {
                                chicken.setNestPos(blockpos$mutable);
                                return;
                            }
                            chicken.goToNestGoal.clearBlacklist();
                        }
                    }
                }
            }
        }
    }
}
