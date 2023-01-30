package com.github.kmfisk.hotchicks.item;

import com.github.kmfisk.hotchicks.entity.HotChickenEntity;
import com.github.kmfisk.hotchicks.entity.base.ChickenBreeds;
import com.github.kmfisk.hotchicks.HotChicks;
import com.github.kmfisk.hotchicks.entity.HotEntities;
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

public class ChickenSpawnEggItem extends Item {
    private final ChickenBreeds breed;

    public ChickenSpawnEggItem(ChickenBreeds breed) {
        super(new Item.Properties().tab(HotChicks.HOT_CHICKS_GROUP));
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

            EntityType<?> entitytype = HotEntities.CHICKEN.get();
            HotChickenEntity e = (HotChickenEntity) entitytype.spawn((ServerWorld) world, itemstack, context.getPlayer(), blockpos1, SpawnReason.SPAWN_EGG, true, !Objects.equals(blockpos, blockpos1) && direction == Direction.UP);

            if (e != null) {
                setStats(e);

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

                EntityType<?> entitytype = HotEntities.CHICKEN.get();

                HotChickenEntity e = (HotChickenEntity) entitytype.spawn((ServerWorld) worldIn, itemstack, playerIn, blockpos, SpawnReason.SPAWN_EGG, false, false);
                if (e == null) {
                    return ActionResult.pass(itemstack);

                } else {
                    setStats(e);

                    if (!playerIn.abilities.instabuild)
                        itemstack.shrink(1);

                    playerIn.awardStat(Stats.ITEM_USED.get(this));
                    return ActionResult.consume(itemstack);
                }
            } else {
                return ActionResult.fail(itemstack);
            }
        }
    }

    private void setStats(HotChickenEntity chicken) {
        switch (breed) {
            default:
            case JUNGLEFOWL:
                chicken.setVariant(0);
                break;
            case AMERAUCANA:
                chicken.setVariant(random.nextInt(7) + 1);
                break;
            case BARRED_ROCK:
                chicken.setVariant(8);
                break;
            case LEGHORN:
                chicken.setVariant(9);
                break;
            case MARANS:
                chicken.setVariant(random.nextInt(4) + 10);
                break;
            case OLIVE_EGGER:
                chicken.setVariant(random.nextInt(7) + 14);
                break;
            case ORPINGTON:
                chicken.setVariant(random.nextInt(4) + 21);
                break;
            case RHODE_ISLAND_RED:
                chicken.setVariant(random.nextInt(3) + 25);
                break;
            case SILKIE:
                chicken.setVariant(random.nextInt(5) + 28);
                break;
        }

        chicken.setStats(breed.getStats());
    }
}
