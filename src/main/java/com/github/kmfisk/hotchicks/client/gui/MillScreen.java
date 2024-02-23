package com.github.kmfisk.hotchicks.client.gui;

import com.github.kmfisk.hotchicks.HotChicks;
import com.github.kmfisk.hotchicks.inventory.MillContainer;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MillScreen extends AbstractContainerScreen<MillContainer> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(HotChicks.MOD_ID, "textures/gui/mill.png");

    public MillScreen(MillContainer menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        passEvents = false;
        this.imageHeight = 166;
    }

    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void renderBg(PoseStack matrixStack, float partialTicks, int x, int y) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        minecraft.textureManager.bind(TEXTURE);
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        this.blit(matrixStack, i, j, 0, 0, this.imageWidth, this.imageHeight);

        int progress = this.menu.getChurnProgress();
        this.blit(matrixStack, i + 99, j + 35, 176, 14, progress + 1, 16);
    }
}
