package com.ryanhcode.hotchicks.block;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.ryanhcode.hotchicks.HotChickens;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TroughScreen extends ContainerScreen<TroughContainer> {
    /** The ResourceLocation containing the gui texture for the hopper */
    private static final ResourceLocation SINGLE_TEXTURE = new ResourceLocation(HotChickens.MODID, "textures/gui/trough.png");
    private static final ResourceLocation DOUBLE_TEXTURE = new ResourceLocation(HotChickens.MODID, "textures/gui/trough_large.png");

    TroughContainer container;
    public TroughScreen(TroughContainer container, PlayerInventory playerInventory, ITextComponent title) {
        super(container, playerInventory, title);
        this.container = container;
        this.passEvents = false;
        this.ySize = 133;
        this.playerInventoryTitleY = this.ySize - 94;
    }

    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }

    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        if(container.slots == 3){
            this.minecraft.getTextureManager().bindTexture(SINGLE_TEXTURE);
        }else {
            this.minecraft.getTextureManager().bindTexture(DOUBLE_TEXTURE);
        }
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.blit(matrixStack, i, j, 0, 0, this.xSize, this.ySize);
    }
}

