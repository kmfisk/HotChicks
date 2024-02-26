package com.github.kmfisk.hotchicks.entity.goal;

import com.github.kmfisk.hotchicks.block.entity.NestTileEntity;
import com.github.kmfisk.hotchicks.entity.HotChickenEntity;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

public class LayEggsGoal extends Goal {
    private final HotChickenEntity chicken;

    public LayEggsGoal(HotChickenEntity chicken) {
        this.chicken = chicken;
    }

    @Override
    public boolean canUse() {
        if (chicken.hasNest() && chicken.wantsToLayEggs() && chicken.getNestPos().closerToCenterThan(chicken.position(), 1.0D)) {
            if (chicken.doesNestHaveSpace(chicken.getNestPos())) return true;
            chicken.setNestPos(null);
        }

        return false;
    }

    @Override
    public boolean canContinueToUse() {
        return false;
    }

    @Override
    public void start() {
        BlockEntity tileEntity = chicken.level.getBlockEntity(chicken.getNestPos());
        if (tileEntity instanceof NestTileEntity) {
            NestTileEntity nestTileEntity = (NestTileEntity) tileEntity;

            for (int i = 0; i < nestTileEntity.getContainerSize(); i++) {
                if (nestTileEntity.getItem(i) == ItemStack.EMPTY) {
                    ItemStack stack = chicken.addChildrenDataToItem(chicken.getBreedFromVariant().getEggColor().getDefaultInstance());
                    nestTileEntity.setItem(i, stack);
                    if (!chicken.level.isClientSide) {
                        for (int j = 0; j < 20; ++j) {
                            double d3 = chicken.getRandom().nextGaussian() * 0.02D;
                            double d1 = chicken.getRandom().nextGaussian() * 0.02D;
                            double d2 = chicken.getRandom().nextGaussian() * 0.02D;
                            ((ServerLevel) chicken.level).sendParticles(ParticleTypes.HEART, (double) chicken.getNestPos().getX() + 0.5D, chicken.getNestPos().getY(), (double) chicken.getNestPos().getZ() + 0.5D, 1, d3, d1, d2, 0.15F);
                        }
                    }
                    break;
                }
            }
        }
    }
}
