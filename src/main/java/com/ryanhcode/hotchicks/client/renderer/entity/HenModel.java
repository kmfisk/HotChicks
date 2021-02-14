package com.ryanhcode.hotchicks.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;

/**
 * SimplyLivestockChick - fisk
 * Created using Tabula 7.1.0
 */
public class HenModel<T extends LivingEntity> extends EntityModel {
    public ModelRenderer Body;
    public ModelRenderer Chest;
    public ModelRenderer Tail;
    public ModelRenderer Belly;
    public ModelRenderer ThighLeft;
    public ModelRenderer ThighRight;
    public ModelRenderer WingLeft;
    public ModelRenderer WingRight;
    public ModelRenderer Neck;
    public ModelRenderer Head;
    public ModelRenderer BeakBase;
    public ModelRenderer Comb;
    public ModelRenderer BeardLeft;
    public ModelRenderer BeardRight;
    public ModelRenderer Beak;
    public ModelRenderer Wattle;
    public ModelRenderer BeakTop;
    public ModelRenderer TailFeathers;
    public ModelRenderer BellyRear;
    public ModelRenderer LegLeft;
    public ModelRenderer FootLeft;
    public ModelRenderer Toe1L;
    public ModelRenderer Toe2L;
    public ModelRenderer LegRight;
    public ModelRenderer FootRight;
    public ModelRenderer Toe1R;
    public ModelRenderer Toe2R;

    public HenModel() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.Toe1L = new ModelRenderer(this, 42, 14);
        this.Toe1L.setRotationPoint(0.0F, 0.0F, 0.2F);
        this.Toe1L.addBox(-0.5F, -1.0F, -2.0F, 1, 1, 2, 0.0F);
        this.setRotateAngle(Toe1L, 0.045553093477052F, -0.27314402793711257F, 0.0F);
        this.Comb = new ModelRenderer(this, 19, 16);
        this.Comb.setRotationPoint(0.0F, -1.5F, -2.0F);
        this.Comb.addBox(-0.5F, 0.0F, 0.0F, 1, 2, 2, 0.0F);
        this.setRotateAngle(Comb, 0.22759093446006054F, 0.0F, 0.0F);
        this.Toe2L = new ModelRenderer(this, 42, 14);
        this.Toe2L.setRotationPoint(0.0F, 0.0F, 0.2F);
        this.Toe2L.addBox(-0.5F, -1.0F, -2.0F, 1, 1, 2, 0.0F);
        this.setRotateAngle(Toe2L, 0.045553093477052F, 0.27314402793711257F, 0.0F);
        this.FootRight = new ModelRenderer(this, 41, 13);
        this.FootRight.setRotationPoint(0.0F, 2.5F, 0.0F);
        this.FootRight.addBox(-0.5F, -1.0F, -2.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(FootRight, 0.22759093446006054F, 0.0F, 0.0F);
        this.WingLeft = new ModelRenderer(this, 25, 16);
        this.WingLeft.setRotationPoint(2.0F, -2.1F, -2.0F);
        this.WingLeft.addBox(0.0F, 0.0F, 0.0F, 1, 3, 4, 0.0F);
        this.Toe2R = new ModelRenderer(this, 42, 14);
        this.Toe2R.setRotationPoint(0.0F, 0.0F, 0.2F);
        this.Toe2R.addBox(-0.5F, -1.0F, -2.0F, 1, 1, 2, 0.0F);
        this.setRotateAngle(Toe2R, 0.045553093477052F, 0.27314402793711257F, 0.0F);
        this.Chest = new ModelRenderer(this, 0, 10);
        this.Chest.setRotationPoint(0.0F, 2.1F, -2.5F);
        this.Chest.addBox(-1.5F, -3.0F, 0.0F, 3, 3, 3, 0.0F);
        this.setRotateAngle(Chest, 0.7740535232594852F, 0.0F, 0.0F);
        this.Tail = new ModelRenderer(this, 24, 9);
        this.Tail.setRotationPoint(0.0F, -2.5F, 1.0F);
        this.Tail.addBox(-1.5F, 0.0F, 0.0F, 3, 5, 2, 0.0F);
        this.setRotateAngle(Tail, 0.40980330836826856F, 0.0F, 0.0F);
        this.ThighLeft = new ModelRenderer(this, 32, 0);
        this.ThighLeft.setRotationPoint(1.7F, 1.1F, 0.5F);
        this.ThighLeft.addBox(-1.0F, -1.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(ThighLeft, 0.22759093446006054F, 0.0F, 0.0F);
        this.BeakBase = new ModelRenderer(this, 0, 22);
        this.BeakBase.setRotationPoint(0.0F, -0.8F, -1.5F);
        this.BeakBase.addBox(-1.0F, 0.0F, -1.0F, 2, 2, 1, 0.0F);
        this.setRotateAngle(BeakBase, 0.27314402793711257F, 0.0F, 0.0F);
        this.LegRight = new ModelRenderer(this, 34, 12);
        this.LegRight.mirror = true;
        this.LegRight.setRotationPoint(0.4F, 3.0F, 0.5F);
        this.LegRight.addBox(-0.5F, 0.0F, -1.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(LegRight, -0.36425021489121656F, 0.0F, 0.0F);
        this.TailFeathers = new ModelRenderer(this, 20, 0);
        this.TailFeathers.setRotationPoint(0.0F, 0.0F, 0.5F);
        this.TailFeathers.addBox(-1.0F, 0.0F, 0.0F, 2, 5, 4, 0.0F);
        this.setRotateAngle(TailFeathers, 0.27314402793711257F, 0.0F, 0.0F);
        this.Body = new ModelRenderer(this, 0, 0);
        this.Body.setRotationPoint(0.0F, 17.6F, 0.0F);
        this.Body.addBox(-2.5F, -2.5F, -2.5F, 5, 5, 5, 0.0F);
        this.setRotateAngle(Body, -0.091106186954104F, 0.0F, 0.0F);
        this.Toe1R = new ModelRenderer(this, 42, 14);
        this.Toe1R.mirror = true;
        this.Toe1R.setRotationPoint(0.0F, 0.0F, 0.2F);
        this.Toe1R.addBox(-0.5F, -1.0F, -2.0F, 1, 1, 2, 0.0F);
        this.setRotateAngle(Toe1R, 0.045553093477052F, -0.27314402793711257F, 0.0F);
        this.ThighRight = new ModelRenderer(this, 40, 0);
        this.ThighRight.mirror = true;
        this.ThighRight.setRotationPoint(-1.7F, 1.1F, 0.5F);
        this.ThighRight.addBox(-1.0F, -1.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(ThighRight, 0.22759093446006054F, 0.0F, 0.0F);
        this.Neck = new ModelRenderer(this, 0, 16);
        this.Neck.setRotationPoint(0.0F, -3.0F, 0.2F);
        this.Neck.addBox(-1.0F, -3.0F, 0.0F, 2, 3, 3, 0.0F);
        this.setRotateAngle(Neck, -0.5009094953223726F, 0.0F, 0.0F);
        this.FootLeft = new ModelRenderer(this, 41, 13);
        this.FootLeft.setRotationPoint(0.0F, 2.5F, 0.0F);
        this.FootLeft.addBox(-0.5F, -1.0F, -2.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(FootLeft, 0.22759093446006054F, 0.0F, 0.0F);
        this.BeakTop = new ModelRenderer(this, 13, 16);
        this.BeakTop.setRotationPoint(0.0F, 0.0F, -2.4F);
        this.BeakTop.addBox(-0.5F, 0.0F, 0.0F, 1, 1, 2, 0.0F);
        this.setRotateAngle(BeakTop, 0.36425021489121656F, 0.0F, 0.0F);
        this.Head = new ModelRenderer(this, 12, 10);
        this.Head.setRotationPoint(0.0F, -4.0F, 1.6F);
        this.Head.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3, 0.0F);
        this.setRotateAngle(Head, -0.091106186954104F, 0.0F, 0.0F);
        this.BeardRight = new ModelRenderer(this, 29, 24);
        this.BeardRight.mirror = true;
        this.BeardRight.setRotationPoint(-0.4F, 0.5F, -1.8F);
        this.BeardRight.addBox(-1.5F, 0.0F, 0.0F, 3, 2, 2, 0.0F);
        this.BeardLeft = new ModelRenderer(this, 19, 24);
        this.BeardLeft.setRotationPoint(0.4F, 0.5F, -1.8F);
        this.BeardLeft.addBox(-1.5F, 0.0F, 0.0F, 3, 2, 2, 0.0F);
        this.Wattle = new ModelRenderer(this, 10, 19);
        this.Wattle.setRotationPoint(0.0F, 1.0F, -0.8F);
        this.Wattle.addBox(-0.5F, 0.0F, 0.0F, 1, 2, 2, 0.0F);
        this.setRotateAngle(Wattle, -0.36425021489121656F, 0.0F, 0.0F);
        this.Beak = new ModelRenderer(this, 7, 16);
        this.Beak.setRotationPoint(0.0F, 1.0F, 0.4F);
        this.Beak.addBox(-0.5F, 0.0F, -2.0F, 1, 1, 2, 0.0F);
        this.setRotateAngle(Beak, -0.31869712141416456F, 0.0F, 0.0F);
        this.BellyRear = new ModelRenderer(this, 48, 0);
        this.BellyRear.setRotationPoint(0.0F, 1.0F, 3.0F);
        this.BellyRear.addBox(-2.0F, -4.0F, 0.0F, 4, 4, 3, 0.0F);
        this.setRotateAngle(BellyRear, 0.5009094953223726F, 0.0F, 0.0F);
        this.LegLeft = new ModelRenderer(this, 34, 12);
        this.LegLeft.setRotationPoint(-0.4F, 3.0F, 0.5F);
        this.LegLeft.addBox(-0.5F, 0.0F, -1.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(LegLeft, -0.36425021489121656F, 0.0F, 0.0F);
        this.Belly = new ModelRenderer(this, 34, 6);
        this.Belly.setRotationPoint(0.0F, 1.5F, -2.0F);
        this.Belly.addBox(-2.0F, -2.0F, 0.0F, 4, 3, 3, 0.0F);
        this.setRotateAngle(Belly, -0.36425021489121656F, 0.0F, 0.0F);
        this.WingRight = new ModelRenderer(this, 35, 16);
        this.WingRight.mirror = true;
        this.WingRight.setRotationPoint(-2.0F, -2.2F, -2.0F);
        this.WingRight.addBox(-1.0F, 0.0F, 0.0F, 1, 3, 4, 0.0F);
        this.FootLeft.addChild(this.Toe1L);
        this.Head.addChild(this.Comb);
        this.FootLeft.addChild(this.Toe2L);
        this.LegRight.addChild(this.FootRight);
        this.Body.addChild(this.WingLeft);
        this.FootRight.addChild(this.Toe2R);
        this.Body.addChild(this.Chest);
        this.Body.addChild(this.Tail);
        this.Body.addChild(this.ThighLeft);
        this.Head.addChild(this.BeakBase);
        this.ThighRight.addChild(this.LegRight);
        this.Tail.addChild(this.TailFeathers);
        this.FootRight.addChild(this.Toe1R);
        this.Body.addChild(this.ThighRight);
        this.Chest.addChild(this.Neck);
        this.LegLeft.addChild(this.FootLeft);
        this.Beak.addChild(this.BeakTop);
        this.Neck.addChild(this.Head);
        this.Head.addChild(this.BeardRight);
        this.Head.addChild(this.BeardLeft);
        this.BeakBase.addChild(this.Wattle);
        this.BeakBase.addChild(this.Beak);
        this.Belly.addChild(this.BellyRear);
        this.ThighLeft.addChild(this.LegLeft);
        this.Body.addChild(this.Belly);
        this.Body.addChild(this.WingRight);
    }

    @Override
    public void setRotationAngles(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float speed = 3.0f;
        float degree = 1.4f;
        this.ThighLeft.rotateAngleX = MathHelper.cos((limbSwing * speed * 0.2F) + (float) Math.PI) * (degree * 0.8F) * limbSwingAmount * 0.5F + 0.22F;
        this.LegLeft.rotateAngleX = MathHelper.cos(5.0F + (limbSwing * speed * 0.2F) + (float) Math.PI) * (degree * -2.0F) * limbSwingAmount * 0.5F + -0.35F;
        this.FootLeft.rotateAngleX = MathHelper.cos(4.0F + (limbSwing * speed * 0.2F) + (float) Math.PI) * (degree * 1.2F) * limbSwingAmount * 0.5F + 0.1F;
        this.ThighRight.rotateAngleX = MathHelper.cos((limbSwing * speed * 0.2F) + (float) Math.PI) * (degree * -0.8F) * limbSwingAmount * 0.5F + 0.22F;
        this.LegRight.rotateAngleX = MathHelper.cos(5.0F + (limbSwing * speed * 0.2F) + (float) Math.PI) * (degree * 2.0F) * limbSwingAmount * 0.5F + -0.35F;
        this.FootRight.rotateAngleX = MathHelper.cos(4.0F + (limbSwing * speed * 0.2F) + (float) Math.PI) * (degree * -1.2F) * limbSwingAmount * 0.5F + 0.1F;
        this.Chest.rotateAngleX = MathHelper.cos((limbSwing * speed * 0.4F) + (float) Math.PI) * (degree * 0.2F) * limbSwingAmount * 0.5F + 0.78F;
        this.Neck.rotateAngleX = MathHelper.cos(6.0F + (limbSwing * speed * 0.4F) + (float) Math.PI) * (degree * -0.2F) * limbSwingAmount * 0.5F + -0.5F;
        this.TailFeathers.rotateAngleX = MathHelper.cos(1.0F + (limbSwing * speed * 0.4F) + (float) Math.PI) * (degree * -0.1F) * limbSwingAmount * 0.5F + -0.36F;
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        this.Body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
    }
}