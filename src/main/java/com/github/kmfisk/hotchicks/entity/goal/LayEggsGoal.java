package com.github.kmfisk.hotchicks.entity.goal;

import com.github.kmfisk.hotchicks.block.entity.NestTileEntity;
import com.github.kmfisk.hotchicks.entity.HotChickenEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.server.ServerWorld;

public class LayEggsGoal extends Goal {
    private final HotChickenEntity chicken;

    public LayEggsGoal(HotChickenEntity chicken) {
        this.chicken = chicken;
    }

    @Override
    public boolean canUse() {
        if (chicken.hasNest() && chicken.wantsToLayEggs() && chicken.getNestPos().closerThan(chicken.position(), 1.0D)) {
            TileEntity tileEntity = chicken.level.getBlockEntity(chicken.getNestPos());
            if (tileEntity instanceof NestTileEntity) {
                NestTileEntity nestTileEntity = (NestTileEntity) tileEntity;
                if (nestTileEntity.getItems().contains(ItemStack.EMPTY)) return true;
                chicken.setNestPos(null);
            }
        }

        return false;
    }

    @Override
    public boolean canContinueToUse() {
        return false;
    }

    @Override
    public void start() {
        TileEntity tileEntity = chicken.level.getBlockEntity(chicken.getNestPos());
        if (tileEntity instanceof NestTileEntity) {
            NestTileEntity nestTileEntity = (NestTileEntity) tileEntity;
            chicken.setEggTimer(0);
            ItemStack stack = chicken.getMainHandItem();

            for (int i = 0; i < nestTileEntity.getContainerSize(); i++) {
                if (nestTileEntity.getItem(i) == ItemStack.EMPTY) {
                    nestTileEntity.setItem(i, stack);
                    chicken.setItemInHand(Hand.MAIN_HAND, ItemStack.EMPTY);
                    if (!chicken.level.isClientSide) {
                        for (int j = 0; j < 20; ++j) {
                            double d3 = chicken.getRandom().nextGaussian() * 0.02D;
                            double d1 = chicken.getRandom().nextGaussian() * 0.02D;
                            double d2 = chicken.getRandom().nextGaussian() * 0.02D;
                            ((ServerWorld) chicken.level).sendParticles(ParticleTypes.HEART, (double) chicken.getNestPos().getX() + 0.5D, chicken.getNestPos().getY(), (double) chicken.getNestPos().getZ() + 0.5D, 1, d3, d1, d2, 0.15F);
                        }
                    }
                    break;
                }
            }
        }
    }
}
