package com.github.kmfisk.hotchicks.block;

import com.github.kmfisk.hotchicks.block.entity.HotTileEntities;
import com.github.kmfisk.hotchicks.block.entity.TroughTileEntity;
import com.github.kmfisk.hotchicks.inventory.TroughContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.CompoundContainer;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.BiPredicate;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class MetalTroughBlock extends TroughBlock {
    private static final DoubleBlockCombiner.Combiner<TroughTileEntity, Optional<MenuProvider>> METAL_CONTAINER_MERGER = new DoubleBlockCombiner.Combiner<TroughTileEntity, Optional<MenuProvider>>() {
        @Override
        public Optional<MenuProvider> acceptDouble(final TroughTileEntity tileEntity, final TroughTileEntity tileEntity1) {
            final Container iinventory = new CompoundContainer(tileEntity, tileEntity1);
            return Optional.of(new MenuProvider() {
                @Nullable
                @Override
                public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {
                    if (tileEntity.canOpen(player) && tileEntity1.canOpen(player))
                        return TroughContainer.createGenericDoubleMetal(id, playerInventory, iinventory);
                    else return null;
                }

                @Override
                public Component getDisplayName() {
                    if (tileEntity.hasCustomName()) return tileEntity.getDisplayName();
                    else
                        return new TranslatableComponent(Util.makeDescriptionId("container_double", ForgeRegistries.BLOCKS.getKey(tileEntity.getBlockState().getBlock())));
                }
            });
        }

        @Override
        public Optional<MenuProvider> acceptSingle(TroughTileEntity tileEntity) {
            return Optional.of(tileEntity);
        }

        @Override
        public Optional<MenuProvider> acceptNone() {
            return Optional.empty();
        }
    };

    public MetalTroughBlock(Properties properties) {
        super(properties);
    }

    @Override
    public DoubleBlockCombiner.NeighborCombineResult<? extends TroughTileEntity> combine(BlockState state, Level level, BlockPos pos, boolean override) {
        BiPredicate<LevelAccessor, BlockPos> bipredicate;
        if (override) bipredicate = (level1, pos1) -> false;
        else bipredicate = ChestBlock::isChestBlockedAt;

        return DoubleBlockCombiner.combineWithNeigbour(HotTileEntities.METAL_TROUGH.get(), MetalTroughBlock::getChestMergerType, MetalTroughBlock::getDirectionToAttached, FACING, state, level, pos, bipredicate);
    }

    @Nullable
    @Override
    public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        return this.combine(state, level, pos, false).apply(METAL_CONTAINER_MERGER).orElse(null);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockGetter level) {
        return HotTileEntities.METAL_TROUGH.get().create();
    }
}
