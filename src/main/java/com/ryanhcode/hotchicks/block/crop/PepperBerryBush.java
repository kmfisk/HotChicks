package com.ryanhcode.hotchicks.block.crop;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.RegistryObject;

import java.util.Random;

public class PepperBerryBush extends BerryBush implements IGrowable {
    public static final EnumProperty<PepperType> VARIANT = EnumProperty.create("variant", PepperType.class);

    RegistryObject<Item> item;

    public PepperBerryBush(Properties properties, RegistryObject<Item> item) {
        super(properties, item);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0).setValue(VARIANT, PepperType.getRandom()));
    }

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        int i = state.getValue(AGE);
        boolean flag = i == 3;
        if (!flag && player.getItemInHand(handIn).getItem() == Items.BONE_MEAL)
            return ActionResultType.PASS;
        else if (i > 2) {
            int j = 1 + worldIn.random.nextInt(2);
            popResource(worldIn, pos, new ItemStack(item.get(), j + (flag ? 1 : 0)));
            worldIn.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS, 1.0F, 0.8F + worldIn.random.nextFloat() * 0.4F);
            worldIn.setBlock(pos, state.setValue(AGE, 2).setValue(VARIANT, PepperType.getRandom()), 2);
            return ActionResultType.sidedSuccess(worldIn.isClientSide);
        } else
            return super.use(state, worldIn, pos, player, handIn, hit);
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE);
        builder.add(VARIANT);
    }

    @Override
    public void performBonemeal(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
        int i = Math.min(3, state.getValue(AGE) + 1);
        worldIn.setBlock(pos, state.setValue(AGE, i).setValue(VARIANT, PepperType.getRandom()), 2);
    }
}
