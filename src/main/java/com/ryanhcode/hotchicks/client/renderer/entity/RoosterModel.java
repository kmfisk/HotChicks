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
public class RoosterModel<T extends LivingEntity> extends EntityModel {
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
    public ModelRenderer NeckFeathersLeft;
    public ModelRenderer NeckFeathersRight;
    public ModelRenderer BeakBase;
    public ModelRenderer Comb;
    public ModelRenderer BeardLeft;
    public ModelRenderer BeardRight;
    public ModelRenderer Beak;
    public ModelRenderer Wattle;
    public ModelRenderer BeakTop;
    public ModelRenderer TailFeathers;
    public ModelRenderer TailFeathers2;
    public ModelRenderer BellyRear;
    public ModelRenderer LegLeft;
    public ModelRenderer FootLeft;
    public ModelRenderer SpurLeft;
    public ModelRenderer Toe1L;
    public ModelRenderer Toe2L;
    public ModelRenderer LegRight;
    public ModelRenderer FootRight;
    public ModelRenderer SpurRight;
    public ModelRenderer Toe1R;
    public ModelRenderer Toe2R;


    public RoosterModel() {
        this.texWidth = 64;
        this.texHeight = 32;
        this.BeardRight = new ModelRenderer(this, 29, 24);
        this.BeardRight.mirror = true;
        this.BeardRight.setPos(-0.4F, 0.5F, -1.8F);
        this.BeardRight.addBox(-1.5F, 0.0F, 0.0F, 3, 2, 2, 0.0F);
        this.Wattle = new ModelRenderer(this, 8, 20);
        this.Wattle.setPos(0.0F, 1.2F, -1.0F);
        this.Wattle.addBox(-0.5F, 0.0F, 0.0F, 1, 3, 2, 0.0F);
        this.setRotateAngle(Wattle, -0.6373942428283291F, 0.0F, 0.0F);
        this.NeckFeathersRight = new ModelRenderer(this, 0, 25);
        this.NeckFeathersRight.mirror = true;
        this.NeckFeathersRight.setPos(-0.4F, -4.0F, 3.0F);
        this.NeckFeathersRight.addBox(-1.0F, 0.0F, -3.0F, 2, 3, 3, 0.0F);
        this.setRotateAngle(NeckFeathersRight, 0.27314402793711257F, 0.0F, 0.0F);
        this.BeakTop = new ModelRenderer(this, 13, 17);
        this.BeakTop.setPos(0.0F, 0.0F, -2.4F);
        this.BeakTop.addBox(-0.5F, 0.0F, 0.0F, 1, 1, 2, 0.0F);
        this.setRotateAngle(BeakTop, 0.36425021489121656F, 0.0F, 0.0F);
        this.Body = new ModelRenderer(this, 0, 0);
        this.Body.setPos(0.0F, 17.6F, 0.0F);
        this.Body.addBox(-2.5F, -2.5F, -2.5F, 5, 5, 5, 0.0F);
        this.setRotateAngle(Body, -0.091106186954104F, 0.0F, 0.0F);
        this.Tail = new ModelRenderer(this, 24, 10);
        this.Tail.setPos(0.0F, -2.5F, 1.0F);
        this.Tail.addBox(-2.0F, 0.0F, 0.0F, 4, 5, 2, 0.0F);
        this.setRotateAngle(Tail, 0.4553564018453205F, 0.0F, 0.0F);
        this.BeakBase = new ModelRenderer(this, 0, 22);
        this.BeakBase.setPos(0.0F, -0.8F, -1.5F);
        this.BeakBase.addBox(-1.0F, 0.0F, -1.0F, 2, 2, 1, 0.0F);
        this.setRotateAngle(BeakBase, 0.27314402793711257F, 0.0F, 0.0F);
        this.Comb = new ModelRenderer(this, 19, 17);
        this.Comb.setPos(0.0F, -2.6F, -2.4F);
        this.Comb.addBox(-0.5F, 0.0F, 0.0F, 1, 2, 4, 0.0F);
        this.setRotateAngle(Comb, 0.18203784098300857F, 0.0F, 0.0F);
        this.Toe1R = new ModelRenderer(this, 42, 14);
        this.Toe1R.mirror = true;
        this.Toe1R.setPos(0.0F, 0.0F, 0.2F);
        this.Toe1R.addBox(-0.5F, -1.0F, -2.0F, 1, 1, 2, 0.0F);
        this.setRotateAngle(Toe1R, 0.045553093477052F, -0.27314402793711257F, 0.0F);
        this.TailFeathers = new ModelRenderer(this, 20, 0);
        this.TailFeathers.setPos(0.0F, 0.0F, 2.0F);
        this.TailFeathers.addBox(-1.5F, 0.0F, 0.0F, 3, 6, 4, 0.0F);
        this.setRotateAngle(TailFeathers, -0.36425021489121656F, 0.0F, 0.0F);
        this.FootRight = new ModelRenderer(this, 41, 13);
        this.FootRight.mirror = true;
        this.FootRight.setPos(0.0F, 2.5F, 0.0F);
        this.FootRight.addBox(-0.5F, -1.0F, -2.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(FootRight, 0.22759093446006054F, 0.0F, 0.0F);
        this.SpurRight = new ModelRenderer(this, 0, 0);
        this.SpurRight.mirror = true;
        this.SpurRight.setPos(0.0F, 0.0F, 0.0F);
        this.SpurRight.addBox(0.0F, 0.0F, 0.0F, 0, 1, 1, 0.0F);
        this.LegLeft = new ModelRenderer(this, 36, 12);
        this.LegLeft.setPos(-0.4F, 3.0F, 0.5F);
        this.LegLeft.addBox(-0.5F, 0.0F, -1.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(LegLeft, -0.36425021489121656F, 0.0F, 0.0F);
        this.LegRight = new ModelRenderer(this, 36, 12);
        this.LegRight.mirror = true;
        this.LegRight.setPos(0.4F, 3.0F, 0.5F);
        this.LegRight.addBox(-0.5F, 0.0F, -1.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(LegRight, -0.36425021489121656F, 0.0F, 0.0F);
        this.NeckFeathersLeft = new ModelRenderer(this, 0, 25);
        this.NeckFeathersLeft.setPos(0.4F, -4.0F, 3.0F);
        this.NeckFeathersLeft.addBox(-1.0F, 0.0F, -3.0F, 2, 3, 3, 0.0F);
        this.setRotateAngle(NeckFeathersLeft, 0.27314402793711257F, 0.0F, 0.0F);
        this.Neck = new ModelRenderer(this, 0, 16);
        this.Neck.setPos(0.0F, -3.0F, 0.2F);
        this.Neck.addBox(-1.0F, -3.0F, 0.0F, 2, 3, 3, 0.0F);
        this.setRotateAngle(Neck, -0.5009094953223726F, 0.0F, 0.0F);
        this.Toe1L = new ModelRenderer(this, 42, 14);
        this.Toe1L.setPos(0.0F, 0.0F, 0.2F);
        this.Toe1L.addBox(-0.5F, -1.0F, -2.0F, 1, 1, 2, 0.0F);
        this.setRotateAngle(Toe1L, 0.045553093477052F, -0.27314402793711257F, 0.0F);
        this.ThighLeft = new ModelRenderer(this, 34, 0);
        this.ThighLeft.setPos(1.7F, 1.1F, 0.5F);
        this.ThighLeft.addBox(-1.0F, -1.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(ThighLeft, 0.22759093446006054F, 0.0F, 0.0F);
        this.BellyRear = new ModelRenderer(this, 50, 0);
        this.BellyRear.setPos(0.0F, 1.0F, 3.0F);
        this.BellyRear.addBox(-1.0F, -4.0F, 0.0F, 2, 4, 3, 0.0F);
        this.setRotateAngle(BellyRear, 0.5009094953223726F, 0.0F, 0.0F);
        this.Toe2R = new ModelRenderer(this, 42, 14);
        this.Toe2R.setPos(0.0F, 0.0F, 0.2F);
        this.Toe2R.addBox(-0.5F, -1.0F, -2.0F, 1, 1, 2, 0.0F);
        this.setRotateAngle(Toe2R, 0.045553093477052F, 0.27314402793711257F, 0.0F);
        this.Beak = new ModelRenderer(this, 7, 16);
        this.Beak.setPos(0.0F, 1.0F, 0.4F);
        this.Beak.addBox(-0.5F, 0.0F, -2.0F, 1, 1, 2, 0.0F);
        this.setRotateAngle(Beak, -0.31869712141416456F, 0.0F, 0.0F);
        this.Belly = new ModelRenderer(this, 34, 6);
        this.Belly.setPos(0.0F, 1.5F, -2.0F);
        this.Belly.addBox(-2.0F, -2.0F, 0.0F, 4, 3, 3, 0.0F);
        this.setRotateAngle(Belly, -0.36425021489121656F, 0.0F, 0.0F);
        this.FootLeft = new ModelRenderer(this, 41, 13);
        this.FootLeft.setPos(0.0F, 2.5F, 0.0F);
        this.FootLeft.addBox(-0.5F, -1.0F, -2.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(FootLeft, 0.22759093446006054F, 0.0F, 0.0F);
        this.Toe2L = new ModelRenderer(this, 42, 14);
        this.Toe2L.setPos(0.0F, 0.0F, 0.2F);
        this.Toe2L.addBox(-0.5F, -1.0F, -2.0F, 1, 1, 2, 0.0F);
        this.setRotateAngle(Toe2L, 0.045553093477052F, 0.27314402793711257F, 0.0F);
        this.Chest = new ModelRenderer(this, 0, 10);
        this.Chest.setPos(0.0F, 2.1F, -2.5F);
        this.Chest.addBox(-1.5F, -3.0F, 0.0F, 3, 3, 3, 0.0F);
        this.setRotateAngle(Chest, 0.7740535232594852F, 0.0F, 0.0F);
        this.SpurLeft = new ModelRenderer(this, 0, 0);
        this.SpurLeft.setPos(0.0F, 0.0F, 0.0F);
        this.SpurLeft.addBox(0.0F, 0.0F, 0.0F, 0, 1, 1, 0.0F);
        this.TailFeathers2 = new ModelRenderer(this, 10, 23);
        this.TailFeathers2.setPos(0.0F, 0.0F, 4.0F);
        this.TailFeathers2.addBox(-1.0F, 0.0F, 0.0F, 2, 4, 5, 0.0F);
        this.setRotateAngle(TailFeathers2, -0.40980330836826856F, 0.0F, 0.0F);
        this.ThighRight = new ModelRenderer(this, 42, 0);
        this.ThighRight.mirror = true;
        this.ThighRight.setPos(-1.7F, 1.1F, 0.5F);
        this.ThighRight.addBox(-1.0F, -1.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(ThighRight, 0.22759093446006054F, 0.0F, 0.0F);
        this.WingLeft = new ModelRenderer(this, 29, 17);
        this.WingLeft.setPos(2.0F, -2.1F, -2.0F);
        this.WingLeft.addBox(0.0F, 0.0F, 0.0F, 1, 3, 4, 0.0F);
        this.Head = new ModelRenderer(this, 12, 10);
        this.Head.setPos(0.0F, -4.0F, 1.6F);
        this.Head.addBox(-1.5F, -1.5F, -1.5F, 3, 4, 3, 0.0F);
        this.setRotateAngle(Head, -0.091106186954104F, 0.0F, 0.0F);
        this.WingRight = new ModelRenderer(this, 39, 17);
        this.WingRight.mirror = true;
        this.WingRight.setPos(-2.0F, -2.2F, -2.0F);
        this.WingRight.addBox(-1.0F, 0.0F, 0.0F, 1, 3, 4, 0.0F);
        this.BeardLeft = new ModelRenderer(this, 19, 24);
        this.BeardLeft.setPos(0.4F, 0.5F, -1.8F);
        this.BeardLeft.addBox(-1.5F, 0.0F, 0.0F, 3, 2, 2, 0.0F);
        this.Head.addChild(this.BeardRight);
        this.BeakBase.addChild(this.Wattle);
        this.Neck.addChild(this.NeckFeathersRight);
        this.Beak.addChild(this.BeakTop);
        this.Body.addChild(this.Tail);
        this.Head.addChild(this.BeakBase);
        this.Head.addChild(this.Comb);
        this.FootRight.addChild(this.Toe1R);
        this.Tail.addChild(this.TailFeathers);
        this.LegRight.addChild(this.FootRight);
        this.LegRight.addChild(this.SpurRight);
        this.ThighLeft.addChild(this.LegLeft);
        this.ThighRight.addChild(this.LegRight);
        this.Neck.addChild(this.NeckFeathersLeft);
        this.Chest.addChild(this.Neck);
        this.FootLeft.addChild(this.Toe1L);
        this.Body.addChild(this.ThighLeft);
        this.Belly.addChild(this.BellyRear);
        this.FootRight.addChild(this.Toe2R);
        this.BeakBase.addChild(this.Beak);
        this.Body.addChild(this.Belly);
        this.LegLeft.addChild(this.FootLeft);
        this.FootLeft.addChild(this.Toe2L);
        this.Body.addChild(this.Chest);
        this.LegLeft.addChild(this.SpurLeft);
        this.TailFeathers.addChild(this.TailFeathers2);
        this.Body.addChild(this.ThighRight);
        this.Body.addChild(this.WingLeft);
        this.Neck.addChild(this.Head);
        this.Body.addChild(this.WingRight);
        this.Head.addChild(this.BeardLeft);
    }

    @Override
    public void setupAnim(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float speed = 3.0f;
        float degree = 1.4f;
        this.ThighLeft.xRot = MathHelper.cos((limbSwing * speed * 0.2F) + (float) Math.PI) * (degree * 0.8F) * limbSwingAmount * 0.5F + 0.22F;
        this.LegLeft.xRot = MathHelper.cos(5.0F + (limbSwing * speed * 0.2F) + (float) Math.PI) * (degree * -2.0F) * limbSwingAmount * 0.5F + -0.35F;
        this.FootLeft.xRot = MathHelper.cos(4.0F + (limbSwing * speed * 0.2F) + (float) Math.PI) * (degree * 1.2F) * limbSwingAmount * 0.5F + 0.1F;
        this.ThighRight.xRot = MathHelper.cos((limbSwing * speed * 0.2F) + (float) Math.PI) * (degree * -0.8F) * limbSwingAmount * 0.5F + 0.22F;
        this.LegRight.xRot = MathHelper.cos(5.0F + (limbSwing * speed * 0.2F) + (float) Math.PI) * (degree * 2.0F) * limbSwingAmount * 0.5F + -0.35F;
        this.FootRight.xRot = MathHelper.cos(4.0F + (limbSwing * speed * 0.2F) + (float) Math.PI) * (degree * -1.2F) * limbSwingAmount * 0.5F + 0.1F;
        this.Chest.xRot = MathHelper.cos((limbSwing * speed * 0.4F) + (float) Math.PI) * (degree * 0.2F) * limbSwingAmount * 0.5F + 0.78F;
        this.Neck.xRot = MathHelper.cos(6.0F + (limbSwing * speed * 0.4F) + (float) Math.PI) * (degree * -0.2F) * limbSwingAmount * 0.5F + -0.5F;
        this.TailFeathers.xRot = MathHelper.cos(1.0F + (limbSwing * speed * 0.4F) + (float) Math.PI) * (degree * -0.1F) * limbSwingAmount * 0.5F + -0.36F;
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