package com.github.kmfisk.hotchicks.entity.goal;

import com.github.kmfisk.hotchicks.entity.HotChickenEntity;
import com.github.kmfisk.hotchicks.entity.LivestockEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;

public class UpdateNestGoal extends Goal {
    private final HotChickenEntity chicken;

    public UpdateNestGoal(HotChickenEntity chicken) {
        this.chicken = chicken;
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
        chicken.remainingCooldownBeforeLocatingNewNest = 200;

        int i = 4;
        int j = 2;
        BlockPos chickenPos = chicken.blockPosition();
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
        for (int k = 0; k <= j; k = k > 0 ? -k : 1 - k) {
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
