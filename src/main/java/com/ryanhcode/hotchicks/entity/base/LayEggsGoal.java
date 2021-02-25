package com.ryanhcode.hotchicks.entity.base;

import com.ryanhcode.hotchicks.block.NestTileEntity;
import com.ryanhcode.hotchicks.entity.chicken.HotChickenEntity;
import com.ryanhcode.hotchicks.registry.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.BreakBlockGoal;
import net.minecraft.entity.ai.goal.Goal;
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
import net.minecraft.world.IWorld;
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
    public boolean shouldExecute() {
        if (entity.getEggTimer() < entity.getMaxEggTimer() || entity.getHeldItemMainhand().isEmpty()) {
            return false;
        } else if (this.runDelay > 0) {
            --this.runDelay;
            return false;
        } else if (this.func_220729_m()) {
            this.runDelay = 20;
            return true;
        } else {
            this.runDelay = this.getRunDelay(this.creature);
            return false;
        }
    }

    private boolean func_220729_m() {
        return this.destinationBlock != null && this.shouldMoveTo(this.creature.world, this.destinationBlock) ? true : this.searchForDestination();
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask() {
        super.resetTask();
        this.entity.fallDistance = 1.0F;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        super.startExecuting();
        this.breakingTime = 0;
        System.out.println("lay egg woo");
    }

    @Override
    public double getTargetDistanceSq() {
        return 1.0;
    }


    public boolean above = false;
    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {



            BlockPos blockpos5 = this.destinationBlock;
            if (!blockpos5.withinDistance(this.creature.getPositionVec(), this.getTargetDistanceSq())) {
                this.above = false;
                ++this.timeoutCounter;
                if (this.shouldMove()) {
                    this.creature.getNavigator().tryMoveToXYZ((double)((float)blockpos5.getX()) + 0.5D, (double)blockpos5.getY(), (double)((float)blockpos5.getZ()) + 0.5D, this.movementSpeed);
                }
            } else {
                this.above = true;
                --this.timeoutCounter;
            }






        World world = this.entity.world;
        BlockPos blockpos = this.entity.getPosition();
        BlockPos blockpos1 = this.findTarget(blockpos, world);
        Random random = this.entity.getRNG();
        if (above && blockpos1 != null) {
            if (this.breakingTime > 0) {
                Vector3d vector3d = this.entity.getMotion();
                this.entity.setMotion(vector3d.x, 0.3D, vector3d.z);
                if (!world.isRemote) {
                    double d0 = 0.08D;
                    ((ServerWorld)world).spawnParticle(new ItemParticleData(ParticleTypes.ITEM, new ItemStack(Items.EGG)), (double)blockpos1.getX() + 0.5D, (double)blockpos1.getY() + 0.7D, (double)blockpos1.getZ() + 0.5D, 3, ((double)random.nextFloat() - 0.5D) * 0.08D, ((double)random.nextFloat() - 0.5D) * 0.08D, ((double)random.nextFloat() - 0.5D) * 0.08D, (double)0.15F);
                }
            }

            if (this.breakingTime % 2 == 0) {
                Vector3d vector3d1 = this.entity.getMotion();
                this.entity.setMotion(vector3d1.x, -0.3D, vector3d1.z);
            }

            if (true) {
                TileEntity te = world.getTileEntity(blockpos1);


                if(te instanceof NestTileEntity){
                    entity.setEggTimer(0);
                    resetTask();
                    System.out.println("lay egg now");
                    ItemStack stack = entity.getHeldItemMainhand();

                    NestTileEntity nest = (NestTileEntity) te;

                    nest.barrelContents.set(0, stack); // TODO: Make proper
                    entity.setHeldItem(Hand.MAIN_HAND, ItemStack.EMPTY);

                    if (!world.isRemote) {
                        for(int i = 0; i < 20; ++i) {
                            double d3 = random.nextGaussian() * 0.02D;
                            double d1 = random.nextGaussian() * 0.02D;
                            double d2 = random.nextGaussian() * 0.02D;
                            ((ServerWorld)world).spawnParticle(ParticleTypes.HEART, (double)blockpos1.getX() + 0.5D, (double)blockpos1.getY(), (double)blockpos1.getZ() + 0.5D, 1, d3, d1, d2, (double)0.15F);
                        }
                        //this.playBrokenSound(world, blockpos1);
                    }
                }
            }

            this.breakingTime+=1;
        }

    }

    @Override
    public boolean shouldContinueExecuting() {
        return !entity.getHeldItemMainhand().isEmpty();
    }

    @Nullable
    private BlockPos findTarget(BlockPos pos, IBlockReader worldIn) {
        if (worldIn.getBlockState(pos).isIn(this.block) || worldIn.getBlockState(pos).isIn(BlockRegistry.NEST.get())) {
            return pos;
        } else {
            BlockPos[] ablockpos = new BlockPos[]{pos.down(), pos.west(), pos.east(), pos.north(), pos.south(), pos.down().down()};

            for(BlockPos blockpos : ablockpos) {
                if (worldIn.getBlockState(blockpos).isIn(this.block) || worldIn.getBlockState(blockpos).isIn(BlockRegistry.NEST.get())) {
                    return blockpos;
                }
            }

            return null;
        }
    }

    /**
     * Return true to set given position as destination
     */
    protected boolean shouldMoveTo(IWorldReader worldIn, BlockPos pos) {
        IChunk ichunk = worldIn.getChunk(pos.getX() >> 4, pos.getZ() >> 4, ChunkStatus.FULL, false);
        if (ichunk == null) {
            return false;
        } else {
            return (ichunk.getBlockState(pos).isIn(this.block) || ichunk.getBlockState(pos).isIn(BlockRegistry.NEST.get())) && ichunk.getBlockState(pos.up()).isAir() && ichunk.getBlockState(pos.up(2)).isAir();
        }
    }
}
