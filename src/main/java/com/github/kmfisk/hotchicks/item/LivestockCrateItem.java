package com.github.kmfisk.hotchicks.item;

import com.github.kmfisk.hotchicks.entity.LivestockEntity;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class LivestockCrateItem extends Item {
    public LivestockCrateItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType interactLivingEntity(ItemStack stack, PlayerEntity player, LivingEntity target, Hand hand) {
        if (stack.hasTag() && stack.getTag().contains("id")) {
            player.displayClientMessage(new TranslationTextComponent("chat.hotchicks.livestock_crate.full"), true);
            return ActionResultType.PASS;
        }

        boolean tameable = target instanceof TameableEntity && (!((TameableEntity) target).isTame() || ((TameableEntity) target).isOwnedBy(player));
        boolean vanilla = target instanceof ChickenEntity || target instanceof CowEntity || target instanceof PigEntity || target instanceof RabbitEntity || target instanceof SheepEntity;
        if (target instanceof LivestockEntity || tameable || vanilla) {
            if (player.level.isClientSide) return ActionResultType.SUCCESS;
            ItemStack capturedEntityItem = caughtEntityItem(target, player);
            player.setItemInHand(hand, capturedEntityItem);
            return ActionResultType.CONSUME;

        } else
            player.displayClientMessage(new TranslationTextComponent("chat.hotchicks.livestock_crate.fail"), true);

        return super.interactLivingEntity(stack, player, target, hand);
    }

    private ItemStack caughtEntityItem(LivingEntity target, PlayerEntity player) {
        target.stopRiding();
        target.ejectPassengers();
        target.revive();

        CompoundNBT tags = new CompoundNBT();
        target.save(tags);

        ResourceLocation key = EntityType.getKey(target.getType());
        tags.putString("id", key.toString());
        if (target.hasCustomName()) tags.putString("DisplayName", target.getDisplayName().getString());
        if (target instanceof LivestockEntity) tags.putString("LivestockBreed", ((LivestockEntity) target).getReadableBreed());

        target.remove();
        player.displayClientMessage(new TranslationTextComponent("chat.hotchicks.livestock_crate.capture", target.getDisplayName()), true);

        ItemStack newStack = new ItemStack(this);
        newStack.setTag(tags);
        return newStack;
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        PlayerEntity player = context.getPlayer();
        if (player != null) {
            World level = context.getLevel();
            ItemStack stack = context.getItemInHand();
            if (!stack.hasTag() || (stack.hasTag() && !stack.getTag().contains("id"))) {
                player.displayClientMessage(new TranslationTextComponent("chat.hotchicks.livestock_crate.empty"), true);
                return ActionResultType.PASS;
            }

            if (!level.isClientSide) {
                BlockPos pos = new BlockPos(context.getClickedPos()).relative(context.getClickedFace());

                CompoundNBT tags = stack.getTag();
                tags.remove("Passengers");
                tags.remove("Leash");
                tags.remove("OwnerName");
                tags.remove("DisplayName");

                LivingEntity entity = (LivingEntity) EntityType.loadEntityRecursive(tags, level, entity1 -> entity1);
                if (entity != null) {
                    entity.absMoveTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, context.getRotation(), 0);
                    entity.setUUID(tags.getUUID("UUID"));
                    level.addFreshEntity(entity);

                    stack.shrink(1);
                    player.setItemInHand(context.getHand(), new ItemStack(this));

                    ITextComponent displayName = tags.contains("DisplayName") ? new StringTextComponent(tags.getString("DisplayName")) : new TranslationTextComponent(Util.makeDescriptionId("entity", new ResourceLocation(tags.getString("id"))));
                    player.displayClientMessage(new TranslationTextComponent("chat.hotchicks.livestock_crate.release", displayName), true);
                }
            }

            return ActionResultType.sidedSuccess(level.isClientSide);
        }

        return super.useOn(context);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World level, List<ITextComponent> tooltip, ITooltipFlag flag) {
        CompoundNBT nbt = stack.getTag();
        if (nbt != null && nbt.contains("id")) {
            if (nbt.contains("DisplayName"))
                tooltip.add(new StringTextComponent("\"" + nbt.getString("DisplayName") + "\"").withStyle(TextFormatting.BLUE));

            String livestockBreed = nbt.contains("LivestockBreed") ? nbt.getString("LivestockBreed") + " " : "";
            ITextComponent entityId = new TranslationTextComponent(Util.makeDescriptionId("entity", new ResourceLocation(nbt.getString("id"))));
            tooltip.add(new StringTextComponent(livestockBreed).append(entityId).withStyle(TextFormatting.AQUA));

        } else
            tooltip.add(new TranslationTextComponent("tooltip.hotchicks.livestock_crate.empty").withStyle(TextFormatting.AQUA));
    }
}
