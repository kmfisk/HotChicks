package com.github.kmfisk.hotchicks.item;

import com.github.kmfisk.hotchicks.entity.LivestockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.stats.Stats;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BaseSpawner;
import net.minecraftforge.common.ForgeSpawnEggItem;

import java.util.Objects;
import java.util.function.Supplier;

import net.minecraft.world.item.Item.Properties;

public class HotSpawnEggItem extends ForgeSpawnEggItem {
    private final String breed;

    public HotSpawnEggItem(String breed, Supplier<? extends EntityType<?>> type, Properties props) {
        super(type, 0xFFFFFF, 0xFFFFFF, props);
        this.breed = breed;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level world = context.getLevel();
        if (!(world instanceof ServerLevel)) return InteractionResult.SUCCESS;
        else {
            ItemStack itemstack = context.getItemInHand();
            BlockPos blockpos = context.getClickedPos();
            Direction direction = context.getClickedFace();
            BlockState blockstate = world.getBlockState(blockpos);
            if (blockstate.is(Blocks.SPAWNER)) {
                BlockEntity tileentity = world.getBlockEntity(blockpos);
                if (tileentity instanceof SpawnerBlockEntity) {
                    BaseSpawner abstractspawner = ((SpawnerBlockEntity) tileentity).getSpawner();
                    EntityType<?> entitytype1 = this.getType(itemstack.getTag());
                    abstractspawner.setEntityId(entitytype1);
                    tileentity.setChanged();
                    world.sendBlockUpdated(blockpos, blockstate, blockstate, 3);
                    itemstack.shrink(1);
                    return InteractionResult.CONSUME;
                }
            }

            BlockPos blockpos1;
            if (blockstate.getCollisionShape(world, blockpos).isEmpty()) blockpos1 = blockpos;
            else blockpos1 = blockpos.relative(direction);

            EntityType<?> entitytype = this.getType(itemstack.getTag());
            Entity entity = entitytype.spawn((ServerLevel) world, itemstack, context.getPlayer(), blockpos1, MobSpawnType.SPAWN_EGG, true, !Objects.equals(blockpos, blockpos1) && direction == Direction.UP);
            if (entity != null) {
                if (entity instanceof LivestockEntity) ((LivestockEntity) entity).initByBreed(breed);
                itemstack.shrink(1);
            }

            return InteractionResult.CONSUME;
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        HitResult raytraceresult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);
        if (raytraceresult.getType() != HitResult.Type.BLOCK) return InteractionResultHolder.pass(itemstack);
        else if (!(level instanceof ServerLevel)) return InteractionResultHolder.success(itemstack);
        else {
            BlockHitResult blockraytraceresult = (BlockHitResult) raytraceresult;
            BlockPos blockpos = blockraytraceresult.getBlockPos();
            if (!(level.getBlockState(blockpos).getBlock() instanceof LiquidBlock)) {
                return InteractionResultHolder.pass(itemstack);
            } else if (level.mayInteract(player, blockpos) && player.mayUseItemAt(blockpos, blockraytraceresult.getDirection(), itemstack)) {
                EntityType<?> entitytype = this.getType(itemstack.getTag());
                Entity entity = entitytype.spawn((ServerLevel) level, itemstack, player, blockpos, MobSpawnType.SPAWN_EGG, false, false);
                if (entity == null) return InteractionResultHolder.pass(itemstack);
                else {
                    if (entity instanceof LivestockEntity) ((LivestockEntity) entity).initByBreed(breed);
                    if (!player.abilities.instabuild) itemstack.shrink(1);

                    player.awardStat(Stats.ITEM_USED.get(this));
                    return InteractionResultHolder.consume(itemstack);
                }

            } else return InteractionResultHolder.fail(itemstack);
        }
    }
}
