package com.github.kmfisk.hotchicks.block;

import com.github.kmfisk.hotchicks.block.entity.HotTileEntities;
import com.github.kmfisk.hotchicks.block.entity.TroughTileEntity;
import com.github.kmfisk.hotchicks.inventory.TroughContainer;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.DoubleSidedInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMerger;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.BiPredicate;

public class MetalTroughBlock extends TroughBlock {
    private static final TileEntityMerger.ICallback<TroughTileEntity, Optional<INamedContainerProvider>> METAL_CONTAINER_MERGER = new TileEntityMerger.ICallback<TroughTileEntity, Optional<INamedContainerProvider>>() {
        @Override
        public Optional<INamedContainerProvider> acceptDouble(final TroughTileEntity tileEntity, final TroughTileEntity tileEntity1) {
            final IInventory iinventory = new DoubleSidedInventory(tileEntity, tileEntity1);
            return Optional.of(new INamedContainerProvider() {
                @Nullable
                @Override
                public Container createMenu(int id, PlayerInventory playerInventory, PlayerEntity player) {
                    if (tileEntity.canOpen(player) && tileEntity1.canOpen(player))
                        return TroughContainer.createGenericDoubleMetal(id, playerInventory, iinventory);
                    else return null;
                }

                @Override
                public ITextComponent getDisplayName() {
                    if (tileEntity.hasCustomName()) return tileEntity.getDisplayName();
                    else
                        return new TranslationTextComponent(Util.makeDescriptionId("container_double", ForgeRegistries.BLOCKS.getKey(tileEntity.getBlockState().getBlock())));
                }
            });
        }

        @Override
        public Optional<INamedContainerProvider> acceptSingle(TroughTileEntity tileEntity) {
            return Optional.of(tileEntity);
        }

        @Override
        public Optional<INamedContainerProvider> acceptNone() {
            return Optional.empty();
        }
    };

    public MetalTroughBlock(Properties properties) {
        super(properties);
    }

    @Override
    public TileEntityMerger.ICallbackWrapper<? extends TroughTileEntity> combine(BlockState state, World level, BlockPos pos, boolean override) {
        BiPredicate<IWorld, BlockPos> bipredicate;
        if (override) bipredicate = (level1, pos1) -> false;
        else bipredicate = ChestBlock::isChestBlockedAt;

        return TileEntityMerger.combineWithNeigbour(HotTileEntities.METAL_TROUGH.get(), MetalTroughBlock::getChestMergerType, MetalTroughBlock::getDirectionToAttached, FACING, state, level, pos, bipredicate);
    }

    @Nullable
    @Override
    public INamedContainerProvider getMenuProvider(BlockState state, World level, BlockPos pos) {
        return this.combine(state, level, pos, false).apply(METAL_CONTAINER_MERGER).orElse(null);
    }

    @Nullable
    @Override
    public TileEntity newBlockEntity(IBlockReader level) {
        return HotTileEntities.METAL_TROUGH.get().create();
    }
}
