package com.github.kmfisk.hotchicks.item;

import com.github.kmfisk.hotchicks.HotChicks;
import com.github.kmfisk.hotchicks.entity.LivestockEntity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.Tags;

import javax.annotation.Nullable;
import java.util.List;

import net.minecraft.world.item.Item.Properties;

public class LivestockCrateItem extends Item {
    public static final Tags.IOptionalNamedTag<EntityType<?>> CRATEABLE_ENTITIES = EntityTypeTags.createOptional(new ResourceLocation(HotChicks.MOD_ID, "crateables"));

    public LivestockCrateItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        if (stack.hasTag() && stack.getTag().contains("id")) {
            player.displayClientMessage(new TranslatableComponent("chat.hotchicks.livestock_crate.full"), true);
            return InteractionResult.PASS;
        }

        if (target instanceof TamableAnimal && ((TamableAnimal) target).isTame() && ((TamableAnimal) target).getOwner() != player)
            return InteractionResult.PASS;

        else if (target.getType().is(CRATEABLE_ENTITIES)) {
            if (player.level.isClientSide) return InteractionResult.SUCCESS;
            ItemStack capturedEntityItem = caughtEntityItem(target, player);
            player.setItemInHand(hand, capturedEntityItem);
            return InteractionResult.CONSUME;

        } else
            player.displayClientMessage(new TranslatableComponent("chat.hotchicks.livestock_crate.fail"), true);

        return super.interactLivingEntity(stack, player, target, hand);
    }

    private ItemStack caughtEntityItem(LivingEntity target, Player player) {
        target.stopRiding();
        target.ejectPassengers();
        target.revive();

        CompoundTag tags = new CompoundTag();
        target.save(tags);

        ResourceLocation key = EntityType.getKey(target.getType());
        tags.putString("id", key.toString());
        if (target.hasCustomName()) tags.putString("DisplayName", target.getDisplayName().getString());
        if (target instanceof LivestockEntity)
            tags.putString("LivestockBreed", ((LivestockEntity) target).getReadableBreed());

        target.discard();
        player.displayClientMessage(new TranslatableComponent("chat.hotchicks.livestock_crate.capture", target.getDisplayName()), true);

        ItemStack newStack = new ItemStack(this);
        newStack.setTag(tags);
        return newStack;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        if (player != null) {
            Level level = context.getLevel();
            ItemStack stack = context.getItemInHand();
            if (!stack.hasTag() || (stack.hasTag() && !stack.getTag().contains("id"))) {
                player.displayClientMessage(new TranslatableComponent("chat.hotchicks.livestock_crate.empty"), true);
                return InteractionResult.PASS;
            }

            if (!level.isClientSide) {
                BlockPos pos = new BlockPos(context.getClickedPos()).relative(context.getClickedFace());

                CompoundTag tags = stack.getTag();
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

                    player.displayClientMessage(new TranslatableComponent("chat.hotchicks.livestock_crate.release", entity.getDisplayName()), true);
                }
            }

            return InteractionResult.sidedSuccess(level.isClientSide);
        }

        return super.useOn(context);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        CompoundTag nbt = stack.getTag();
        if (nbt != null && nbt.contains("id")) {
            if (nbt.contains("DisplayName"))
                tooltip.add(new TextComponent("\"" + nbt.getString("DisplayName") + "\"").withStyle(ChatFormatting.BLUE));

            String livestockBreed = nbt.contains("LivestockBreed") ? nbt.getString("LivestockBreed") + " " : "";
            Component entityId = new TranslatableComponent(Util.makeDescriptionId("entity", new ResourceLocation(nbt.getString("id"))));
            tooltip.add(new TextComponent(livestockBreed).append(entityId).withStyle(ChatFormatting.AQUA));

        } else
            tooltip.add(new TranslatableComponent("tooltip.hotchicks.livestock_crate.empty").withStyle(ChatFormatting.AQUA));
    }
}
