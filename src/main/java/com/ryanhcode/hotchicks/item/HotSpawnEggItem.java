package com.ryanhcode.hotchicks.item;

import com.ryanhcode.hotchicks.HotChickens;
import com.ryanhcode.hotchicks.entity.HotChickenEntity;
import com.ryanhcode.hotchicks.entity.HotEntities;
import com.ryanhcode.hotchicks.entity.base.ChickenBreed;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Objects;

public class HotSpawnEggItem extends Item {
    private final ChickenBreed breed;

    public HotSpawnEggItem(ChickenBreed breed) {
        super(new Item.Properties().tab(HotChickens.HOT_CHICKS_GROUP));
        this.breed = breed;
    }

    public ActionResultType useOn(ItemUseContext context) {
        World world = context.getLevel();
        if (!(world instanceof ServerWorld)) {
            return ActionResultType.SUCCESS;
        } else {
            ItemStack itemstack = context.getItemInHand();
            BlockPos blockpos = context.getClickedPos();
            Direction direction = context.getClickedFace();
            BlockState blockstate = world.getBlockState(blockpos);
            BlockPos blockpos1;
            if (blockstate.getCollisionShape(world, blockpos).isEmpty()) {
                blockpos1 = blockpos;
            } else {
                blockpos1 = blockpos.relative(direction);
            }

            EntityType<?> entitytype = HotEntities.HOT_CHICKEN.get();
            HotChickenEntity e = (HotChickenEntity) entitytype.spawn((ServerWorld) world, itemstack, context.getPlayer(), blockpos1, SpawnReason.SPAWN_EGG, true, !Objects.equals(blockpos, blockpos1) && direction == Direction.UP);

            if (e != null) {
                e.setBreed(breed);
                itemstack.shrink(1);
            }

            return ActionResultType.CONSUME;
        }
    }

    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        RayTraceResult raytraceresult = getPlayerPOVHitResult(worldIn, playerIn, RayTraceContext.FluidMode.SOURCE_ONLY);
        if (raytraceresult.getType() != RayTraceResult.Type.BLOCK) {
            return ActionResult.pass(itemstack);
        } else if (!(worldIn instanceof ServerWorld)) {
            return ActionResult.success(itemstack);
        } else {
            BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult) raytraceresult;
            BlockPos blockpos = blockraytraceresult.getBlockPos();
            if (!(worldIn.getBlockState(blockpos).getBlock() instanceof FlowingFluidBlock)) {
                return ActionResult.pass(itemstack);
            } else if (worldIn.mayInteract(playerIn, blockpos) && playerIn.mayUseItemAt(blockpos, blockraytraceresult.getDirection(), itemstack)) {

                EntityType<?> entitytype = HotEntities.HOT_CHICKEN.get();

                HotChickenEntity e = (HotChickenEntity) entitytype.spawn((ServerWorld) worldIn, itemstack, playerIn, blockpos, SpawnReason.SPAWN_EGG, false, false);
                if (e == null) {
                    return ActionResult.pass(itemstack);
                } else {

                    e.setBreed(breed);
                    if (breed == ChickenBreed.JUNGLEFOWL) {
                        e.setTameness(50);
                    } else {
                        e.setTameness(100);
                    }


                    if (!playerIn.abilities.instabuild) {
                        itemstack.shrink(1);
                    }

                    playerIn.awardStat(Stats.ITEM_USED.get(this));
                    return ActionResult.consume(itemstack);
                }
            } else {
                return ActionResult.fail(itemstack);
            }
        }
    }
}
