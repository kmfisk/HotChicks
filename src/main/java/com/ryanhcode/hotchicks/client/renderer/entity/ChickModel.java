package com.ryanhcode.hotchicks.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

/**
 * SimplyLivestockChick - fisk
 * Created using Tabula 7.1.0
 */
public class ChickModel<T extends LivingEntity> extends EntityModel {
    public ModelRenderer Body;
    public ModelRenderer Tail;
    public ModelRenderer Neck;
    public ModelRenderer LegLeft;
    public ModelRenderer LegRight;
    public ModelRenderer Head;
    public ModelRenderer Beak;

    public ChickModel() {
        this.texWidth = 32;
        this.texHeight = 32;
        this.Tail = new ModelRenderer(this, 0, 13);
        this.Tail.setPos(0.0F, -1.7F, 1.5F);
        this.Tail.addBox(-1.5F, 0.0F, 0.0F, 3, 3, 2, 0.0F);
        this.setRotateAngle(Tail, -0.6373942428283291F, 0.0F, 0.0F);
        this.Neck = new ModelRenderer(this, 14, 0);
        this.Neck.setPos(0.0F, 0.0F, -1.5F);
        this.Neck.addBox(-1.0F, -2.0F, -0.6F, 2, 3, 2, 0.0F);
        this.setRotateAngle(Neck, 0.6373942428283291F, 0.0F, 0.0F);
        this.Head = new ModelRenderer(this, 0, 7);
        this.Head.setPos(0.0F, -1.8F, 0.6F);
        this.Head.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3, 0.0F);
        this.setRotateAngle(Head, -0.4553564018453205F, 0.0F, 0.0F);
        this.LegRight = new ModelRenderer(this, 13, 7);
        this.LegRight.setPos(-0.9F, 2.0F, 1.0F);
        this.LegRight.addBox(-0.5F, -0.8F, -1.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(LegRight, 0.091106186954104F, 0.0F, 0.0F);
        this.Body = new ModelRenderer(this, 0, 0);
        this.Body.setPos(0.0F, 20.7F, 0.0F);
        this.Body.addBox(-2.0F, -2.0F, -1.5F, 4, 4, 3, 0.0F);
        this.setRotateAngle(Body, -0.091106186954104F, 0.0F, 0.0F);
        this.Beak = new ModelRenderer(this, 14, 5);
        this.Beak.setPos(0.0F, 0.0F, -0.9F);
        this.Beak.addBox(-0.5F, 0.0F, -1.0F, 1, 1, 1, 0.0F);
        this.LegLeft = new ModelRenderer(this, 9, 7);
        this.LegLeft.setPos(0.9F, 2.0F, 1.0F);
        this.LegLeft.addBox(-0.5F, -0.8F, -1.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(LegLeft, 0.091106186954104F, 0.0F, 0.0F);
        this.Body.addChild(this.Tail);
        this.Body.addChild(this.Neck);
        this.Neck.addChild(this.Head);
        this.Body.addChild(this.LegRight);
        this.Head.addChild(this.Beak);
        this.Body.addChild(this.LegLeft);
    }

    @Override
    public void setupAnim(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        this.Body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
    }
}