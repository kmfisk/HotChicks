package com.ryanhcode.hotchicks.block.crop;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.RegistryObject;

import javax.annotation.Nullable;
import java.util.Random;


public class CornBlock extends BushBlock implements IGrowable {
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 5);
    public static final IntegerProperty TYPE = IntegerProperty.create("type", 0, 2);


    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockState state = context.getWorld().getBlockState(context.getPos().down());
        if(context.getPlayer() != null && !(state.isIn(Blocks.FARMLAND))){
            return null;
        }
        return super.getStateForPlacement(context);
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        //return super.isValidGround(state, worldIn, pos);
        return state.isIn(Blocks.GRASS_BLOCK) || state.isIn(Blocks.DIRT) || state.isIn(Blocks.COARSE_DIRT) || state.isIn(Blocks.PODZOL) || state.isIn(Blocks.FARMLAND) || state.isIn(this);
    }

    RegistryObject<Item> item;
    public CornBlock(Properties properties, RegistryObject<Item> item) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(AGE, Integer.valueOf(0)).with(TYPE, 0));
        this.item = item;
    }

    public void setAge(ServerWorld world, BlockPos pos, int age){
        BlockState state = world.getBlockState(pos);
        world.setBlockState(pos, state.with(AGE, age), 2);
    }

    private void growTo(BlockState state, ServerWorld world, BlockPos pos, boolean boned) {
        if(!boned) {
            if (getType(state) != 0) return;
        }
        int newAge = state.get(AGE) + 1;
        if(newAge == 2) {
            setAge(world, pos, newAge);
            addPlant(world, pos, pos.up(), 1);
        }else if(newAge > 2) {
            setAge(world, pos, newAge);
            addPlant(world, pos, pos.up(), 1);
            addPlant(world, pos, pos.up().up(), 2);
        }else{
            setAge(world, pos, newAge);
            //setAge(world, pos.up(), newAge);
            //setAge(world, pos.up().up(), newAge);
        }
    }


    public void setType(ServerWorld world, BlockPos pos, int type){
        world.setBlockState(pos, world.getBlockState(pos).with(TYPE,type));

    }
    private void addPlant(ServerWorld w, BlockPos thisPos, BlockPos newPos, int type) {
        w.setBlockState(newPos, w.getBlockState(thisPos).with(TYPE,type));
    }

    /**
     * Returns whether or not this block is of a type that needs random ticking. Called for ref-counting purposes by
     * ExtendedBlockStorage in order to broadly cull a chunk from the random chunk update list for efficiency's sake.
     */
    public boolean ticksRandomly(BlockState state) {
        return state.get(AGE) < 5;
    }

    private int getType(BlockState state) {
        return state.get(TYPE);
    }

    /**
     * Whether this IGrowable can grow
     */
    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return ticksRandomly(state);
    }

    /**
     * Performs a random tick on a block.
     */
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        int i = state.get(AGE);
        if (i < 5 && worldIn.getLightSubtracted(pos.up(), 0) >= 9 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state,random.nextInt(5) == 0)) {
            growTo(state, worldIn, pos, false);
            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
        }

    }

    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        if (entityIn instanceof LivingEntity && entityIn.getType() != EntityType.FOX && entityIn.getType() != EntityType.BEE) {
            entityIn.setMotionMultiplier(state, new Vector3d((double)0.8F, 0.75D, (double)0.8F));
            if (!worldIn.isRemote && state.get(AGE) > 0 && (entityIn.lastTickPosX != entityIn.getPosX() || entityIn.lastTickPosZ != entityIn.getPosZ())) {
                double d0 = Math.abs(entityIn.getPosX() - entityIn.lastTickPosX);
                double d1 = Math.abs(entityIn.getPosZ() - entityIn.lastTickPosZ);
            }

        }
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE);
        builder.add(TYPE);
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return true;
        //return isValidToGrow(worldIn.getBlockState(pos.up())) && isValidToGrow(worldIn.getBlockState(pos.up().up()));
    }

    private boolean isValidToGrow(BlockState blockState) {
        return blockState.getBlock() instanceof CornBlock || blockState.isAir();
    }

    public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {

        int type = getType(state);
        int age = state.get(AGE);
        if(type == 0) {
            boolean canGrow = (isValidToGrow(worldIn.getBlockState(pos.up())) && isValidToGrow(worldIn.getBlockState(pos.up().up())));
            if (canGrow && age < 5) {
                growTo(state, worldIn, pos, true);
            }
        }else if(type==1){
            boolean canGrow = (isValidToGrow(worldIn.getBlockState(pos.up())));
            if (canGrow && age < 5) {
                growTo(state, worldIn, pos.down(), true);
            }
        }else if(type==2) {
            if (age < 5) {
                growTo(state, worldIn, pos.down().down(), true);
            }
        }
    }

    @Override
    public void onReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) {

        if(newState.getBlock() instanceof CornBlock){
            super.onReplaced(state, world, pos, newState, isMoving);
            return;
        }
        int type = getType(state);
        int age = state.get(AGE);
        if(type == 1){
            if(age > 2){
                world.destroyBlock(pos.up(), false);
            }
            world.destroyBlock(pos.down(), false);
        }
        if(type == 2){
            world.destroyBlock(pos.down(), false);
            world.destroyBlock(pos.down().down(), false);
        }

        super.onReplaced(state, world, pos, newState, isMoving);
    }


}
