package com.github.kmfisk.hotchicks.client.gui;

import com.github.kmfisk.hotchicks.HotChicks;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.github.kmfisk.hotchicks.inventory.TroughContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TroughScreen extends ContainerScreen<TroughContainer> {
    /**
     * The ResourceLocation containing the gui texture for the hopper
     */
    private static final ResourceLocation SINGLE_TEXTURE = new ResourceLocation(HotChicks.MOD_ID, "textures/gui/trough.png");
    private static final ResourceLocation DOUBLE_TEXTURE = new ResourceLocation(HotChicks.MOD_ID, "textures/gui/trough_large.png");

    TroughContainer container;

    public TroughScreen(TroughContainer container, PlayerInventory playerInventory, ITextComponent title) {
        super(container, playerInventory, title);
        this.container = container;
        this.passEvents = false;
        this.imageHeight = container.slot == 12 ? 133 + 17 : 133;
        this.inventoryLabelY = this.imageHeight - 94;
    }

    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    protected void renderBg(MatrixStack matrixStack, float partialTicks, int x, int y) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        int mod = 0;
        if (container.slot == 3) {
            this.minecraft.getTextureManager().bind(SINGLE_TEXTURE);
        } else if (container.slot == 6) {
            this.minecraft.getTextureManager().bind(DOUBLE_TEXTURE);
        } else if (container.slot == 12) {
            mod = 0;
            this.minecraft.getTextureManager().bind(new ResourceLocation(HotChicks.MOD_ID, "textures/gui/trough_large_metal.png"));
        } else {
            this.minecraft.getTextureManager().bind(DOUBLE_TEXTURE);
        }
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - imageHeight + mod) / 2;
        this.blit(matrixStack, i, j, 0, 0, this.imageWidth, this.imageHeight);
    }
}

