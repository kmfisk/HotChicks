package com.ryanhcode.hotchicks.entity.goal;

import com.ryanhcode.hotchicks.block.HotBlocks;
import com.ryanhcode.hotchicks.block.NestTileEntity;
import com.ryanhcode.hotchicks.entity.HotChickenEntity;
import com.ryanhcode.hotchicks.entity.base.Sex;
import net.minecraft.block.Block;
import net.minecraft.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

public class LayEggsGoal extends MoveToBlockGoal {
    public boolean reachedTarget;
    private final Block block;
    private final HotChickenEntity entity;
    private int breakingTime;

    public LayEggsGoal(HotChickenEntity creature) {
        super(creature, 1.3, 24, 3);
        this.block = HotBlocks.NEST_BOX.get();
        this.entity = creature;
    }

    @Override
    public boolean canUse() {
        if (entity.getSex() != Sex.FEMALE || entity.getEggTimer() < entity.getMaxEggTimer() || entity.getMainHandItem().isEmpty())
            return false;
        else if (this.nextStartTick > 0) {
            --this.nextStartTick;
            return false;
        } else {
            this.nextStartTick = this.nextStartTick(this.mob);
            return this.tryFindBlock();
        }
    }

    @Override
    public boolean canContinueToUse() {
        return !entity.getMainHandItem().isEmpty() && super.canContinueToUse();
    }

    private boolean tryFindBlock() {
        return this.blockPos != null && this.isValidTarget(this.mob.level, this.blockPos) || this.findNearestBlock();
    }

    @Override
    public void start() {
        super.start();
        this.breakingTime = 0;
    }

    @Override
    public void tick() {
        BlockPos blockpos5 = this.blockPos;
        if (!blockpos5.closerThan(this.mob.position(), this.acceptedDistance())) {
            this.reachedTarget = false;
            ++this.tryTicks;
            if (this.shouldRecalculatePath())
                this.mob.getNavigation().moveTo((double) ((float) blockpos5.getX()) + 0.5D, blockpos5.getY(), (double) ((float) blockpos5.getZ()) + 0.5D, this.speedModifier);
        } else {
            this.reachedTarget = true;
            --this.tryTicks;
        }

        World world = this.entity.level;
        BlockPos blockpos = this.entity.blockPosition();
        BlockPos blockpos1 = this.findTarget(blockpos, world);
        Random random = this.entity.getRandom();
        if (reachedTarget && blockpos1 != null) {
            if (this.breakingTime > 0) {
                Vector3d vector3d = this.entity.getDeltaMovement();
                this.entity.setDeltaMovement(vector3d.x, 0.3D, vector3d.z);
                if (!world.isClientSide) {
                    ((ServerWorld) world).sendParticles(new ItemParticleData(ParticleTypes.ITEM, new ItemStack(Items.EGG)), (double) blockpos1.getX() + 0.5D, (double) blockpos1.getY() + 0.7D, (double) blockpos1.getZ() + 0.5D, 3, ((double) random.nextFloat() - 0.5D) * 0.08D, ((double) random.nextFloat() - 0.5D) * 0.08D, ((double) random.nextFloat() - 0.5D) * 0.08D, (double) 0.15F);
                }
            }

            if (this.breakingTime % 2 == 0) {
                Vector3d vector3d1 = this.entity.getDeltaMovement();
                this.entity.setDeltaMovement(vector3d1.x, -0.3D, vector3d1.z);
            }

            TileEntity te = world.getBlockEntity(blockpos1);


            if (te instanceof NestTileEntity) {
                entity.setEggTimer(0);
                stop();
                ItemStack stack = entity.getMainHandItem();

                NestTileEntity nest = (NestTileEntity) te;

                nest.barrelContents.set(0, stack); // TODO: Make proper
                entity.setItemInHand(Hand.MAIN_HAND, ItemStack.EMPTY);

                if (!world.isClientSide) {
                    for (int i = 0; i < 20; ++i) {
                        double d3 = random.nextGaussian() * 0.02D;
                        double d1 = random.nextGaussian() * 0.02D;
                        double d2 = random.nextGaussian() * 0.02D;
                        ((ServerWorld) world).sendParticles(ParticleTypes.HEART, (double) blockpos1.getX() + 0.5D, (double) blockpos1.getY(), (double) blockpos1.getZ() + 0.5D, 1, d3, d1, d2, (double) 0.15F);
                    }
                }
            }

            this.breakingTime += 1;
        }
    }

    @Override
    public boolean isReachedTarget() {
        return this.reachedTarget;
    }

    @Override
    protected boolean isValidTarget(IWorldReader worldIn, BlockPos pos) {
        IChunk ichunk = worldIn.getChunk(pos.getX() >> 4, pos.getZ() >> 4, ChunkStatus.FULL, false);
        if (ichunk == null)
            return false;
        else
            return (ichunk.getBlockState(pos).is(this.block) || ichunk.getBlockState(pos).is(HotBlocks.NEST.get())) && ichunk.getBlockState(pos.above()).isAir() && ichunk.getBlockState(pos.above(2)).isAir();
    }

    @Override
    public void stop() {
        super.stop();
        this.entity.fallDistance = 1.0F;
    }

    @Nullable
    private BlockPos findTarget(BlockPos pos, IBlockReader worldIn) {
        if (worldIn.getBlockState(pos).is(this.block) || worldIn.getBlockState(pos).is(HotBlocks.NEST.get())) {
            return pos;
        } else {
            BlockPos[] ablockpos = new BlockPos[]{pos.below(), pos.west(), pos.east(), pos.north(), pos.south(), pos.below().below()};

            for (BlockPos blockpos : ablockpos) {
                if (worldIn.getBlockState(blockpos).is(this.block) || worldIn.getBlockState(blockpos).is(HotBlocks.NEST.get())) {
                    return blockpos;
                }
            }

            return null;
        }
    }
}
