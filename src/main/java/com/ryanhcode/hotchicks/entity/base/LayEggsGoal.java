package com.ryanhcode.hotchicks.entity.base;

import com.ryanhcode.hotchicks.block.NestTileEntity;
import com.ryanhcode.hotchicks.entity.chicken.HotChickenEntity;
import com.ryanhcode.hotchicks.registry.BlockRegistry;
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
    private final Block block;
    private final HotChickenEntity entity;
    private int breakingTime;

    public LayEggsGoal(HotChickenEntity creature) {

        super(creature, 1.3, 24, 3);
        this.block = BlockRegistry.NEST_BOX.get();
        this.entity = creature;
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean canUse() {
        if (entity.getEggTimer() < entity.getMaxEggTimer() || entity.getMainHandItem().isEmpty()) {
            return false;
        } else if (this.nextStartTick > 0) {
            --this.nextStartTick;
            return false;
        } else if (this.tryFindBlock()) {
            this.nextStartTick = 20;
            return true;
        } else {
            this.nextStartTick = this.nextStartTick(this.mob);
            return false;
        }
    }

    private boolean tryFindBlock() {
        return this.blockPos != null && this.isValidTarget(this.mob.level, this.blockPos) ? true : this.findNearestBlock();
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void stop() {
        super.stop();
        this.entity.fallDistance = 1.0F;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void start() {
        super.start();
        this.breakingTime = 0;
        System.out.println("lay egg woo");
    }

    @Override
    public double acceptedDistance() {
        return 1.0;
    }


    public boolean above = false;

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {


        BlockPos blockpos5 = this.blockPos;
        if (!blockpos5.closerThan(this.mob.position(), this.acceptedDistance())) {
            this.above = false;
            ++this.tryTicks;
            if (this.shouldRecalculatePath()) {
                this.mob.getNavigation().moveTo((double) ((float) blockpos5.getX()) + 0.5D, (double) blockpos5.getY(), (double) ((float) blockpos5.getZ()) + 0.5D, this.speedModifier);
            }
        } else {
            this.above = true;
            --this.tryTicks;
        }


        World world = this.entity.level;
        BlockPos blockpos = this.entity.blockPosition();
        BlockPos blockpos1 = this.findTarget(blockpos, world);
        Random random = this.entity.getRandom();
        if (above && blockpos1 != null) {
            if (this.breakingTime > 0) {
                Vector3d vector3d = this.entity.getDeltaMovement();
                this.entity.setDeltaMovement(vector3d.x, 0.3D, vector3d.z);
                if (!world.isClientSide) {
                    double d0 = 0.08D;
                    ((ServerWorld) world).sendParticles(new ItemParticleData(ParticleTypes.ITEM, new ItemStack(Items.EGG)), (double) blockpos1.getX() + 0.5D, (double) blockpos1.getY() + 0.7D, (double) blockpos1.getZ() + 0.5D, 3, ((double) random.nextFloat() - 0.5D) * 0.08D, ((double) random.nextFloat() - 0.5D) * 0.08D, ((double) random.nextFloat() - 0.5D) * 0.08D, (double) 0.15F);
                }
            }

            if (this.breakingTime % 2 == 0) {
                Vector3d vector3d1 = this.entity.getDeltaMovement();
                this.entity.setDeltaMovement(vector3d1.x, -0.3D, vector3d1.z);
            }

            if (true) {
                TileEntity te = world.getBlockEntity(blockpos1);


                if (te instanceof NestTileEntity) {
                    entity.setEggTimer(0);
                    stop();
                    System.out.println("lay egg now");
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
                        //this.playBrokenSound(world, blockpos1);
                    }
                }
            }

            this.breakingTime += 1;
        }

    }

    @Override
    public boolean canContinueToUse() {
        return !entity.getMainHandItem().isEmpty();
    }

    @Nullable
    private BlockPos findTarget(BlockPos pos, IBlockReader worldIn) {
        if (worldIn.getBlockState(pos).is(this.block) || worldIn.getBlockState(pos).is(BlockRegistry.NEST.get())) {
            return pos;
        } else {
            BlockPos[] ablockpos = new BlockPos[]{pos.below(), pos.west(), pos.east(), pos.north(), pos.south(), pos.below().below()};

            for (BlockPos blockpos : ablockpos) {
                if (worldIn.getBlockState(blockpos).is(this.block) || worldIn.getBlockState(blockpos).is(BlockRegistry.NEST.get())) {
                    return blockpos;
                }
            }

            return null;
        }
    }

    /**
     * Return true to set given position as destination
     */
    protected boolean isValidTarget(IWorldReader worldIn, BlockPos pos) {
        IChunk ichunk = worldIn.getChunk(pos.getX() >> 4, pos.getZ() >> 4, ChunkStatus.FULL, false);
        if (ichunk == null) {
            return false;
        } else {
            return (ichunk.getBlockState(pos).is(this.block) || ichunk.getBlockState(pos).is(BlockRegistry.NEST.get())) && ichunk.getBlockState(pos.above()).isAir() && ichunk.getBlockState(pos.above(2)).isAir();
        }
    }
}
