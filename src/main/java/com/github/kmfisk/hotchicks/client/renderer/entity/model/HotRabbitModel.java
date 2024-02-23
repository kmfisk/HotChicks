package com.github.kmfisk.hotchicks.client.renderer.entity.model;

import com.github.kmfisk.hotchicks.entity.HotRabbitEntity;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;

public class HotRabbitModel extends ListModel<HotRabbitEntity> {
    public ModelPart Hips;
    public ModelPart Chest;
    public ModelPart Tail;
    public ModelPart ThighLeft;
    public ModelPart ThighRight;
    public ModelPart Head;
    public ModelPart ArmLeft;
    public ModelPart ArmRight;
    public ModelPart DewlapLeft;
    public ModelPart DewlapRight;
    public ModelPart Snout;
    public ModelPart EarLeft;
    public ModelPart EarRight;
    public ModelPart TopSnout;
    public ModelPart EarTag;
    public ModelPart ForearmLeft;
    public ModelPart HandLeft;
    public ModelPart ForearmRight;
    public ModelPart HandRight;
    public ModelPart LegLeft;
    public ModelPart FootLeft;
    public ModelPart LegRight;
    public ModelPart FootRight;
    private float jumpRotation;
    private Iterable<ModelPart> parts;

    public HotRabbitModel() {
        this.texWidth = 64;
        this.texHeight = 32;
        this.Chest = new ModelPart(this, 0, 12);
        this.Chest.setPos(0.0F, -2.5F, -3.0F);
        this.Chest.addBox(-2.5F, 0.0F, -4.0F, 5.0F, 5.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(Chest, 0.2602285888091934F, 0.0F, 0.0F);
        this.Tail = new ModelPart(this, 18, 0);
        this.Tail.setPos(0.0F, 2.0F, 2.5F);
        this.Tail.addBox(-1.5F, -3.0F, -1.5F, 3.0F, 3.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(Tail, -0.3642502295386026F, 0.0F, 0.0F);
        this.FootRight = new ModelPart(this, 51, 22);
        this.FootRight.mirror = true;
        this.FootRight.setPos(0.2F, 2.3F, 1.4F);
        this.FootRight.addBox(-1.0F, -0.5F, -3.0F, 2.0F, 1.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(FootRight, -0.6373942508178124F, 0.27314402127920984F, -0.27314402127920984F);
        this.LegRight = new ModelPart(this, 52, 18);
        this.LegRight.mirror = true;
        this.LegRight.setPos(0.3F, 4.0F, -1.5F);
        this.LegRight.addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(LegRight, 0.8196066007575706F, 0.0F, 0.0F);
        this.Snout = new ModelPart(this, 16, 22);
        this.Snout.setPos(0.0F, -0.3F, -1.7F);
        this.Snout.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(Snout, 0.27366763203903305F, 0.0F, 0.0F);
        this.FootLeft = new ModelPart(this, 42, 12);
        this.FootLeft.setPos(-0.2F, 2.3F, 1.4F);
        this.FootLeft.addBox(-1.0F, -0.5F, -3.0F, 2.0F, 1.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(FootLeft, -0.6373942508178124F, -0.27314402127920984F, 0.27314402127920984F);
        this.Head = new ModelPart(this, 0, 21);
        this.Head.setPos(0.0F, 0.6F, -4.7F);
        this.Head.addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(Head, 0.006632251074354668F, 0.0F, 0.0F);
        this.EarTag = new ModelPart(this, 0, 0);
        this.EarTag.setPos(-0.5F, 0.3F, 0.5F);
        this.EarTag.addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.1F, 0.0F, 0.0F);
        this.HandLeft = new ModelPart(this, 22, 13);
        this.HandLeft.setPos(0.0F, 2.0F, -1.0F);
        this.HandLeft.addBox(-1.0F, -0.5F, -1.7F, 2.0F, 1.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(HandLeft, 0.0F, 0.0F, -0.0911061832922575F);
        this.DewlapLeft = new ModelPart(this, 24, 26);
        this.DewlapLeft.setPos(0.3F, 3.7F, -5.5F);
        this.DewlapLeft.addBox(-2.0F, 0.0F, 0.0F, 4.0F, 2.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(DewlapLeft, 1.0016444470669013F, 0.0F, 0.0F);
        this.LegLeft = new ModelPart(this, 43, 8);
        this.LegLeft.setPos(0.3F, 4.0F, -1.5F);
        this.LegLeft.addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(LegLeft, 0.8196066007575706F, 0.0F, 0.0F);
        this.TopSnout = new ModelPart(this, 16, 26);
        this.TopSnout.setPos(0.0F, 0.0F, -1.7F);
        this.TopSnout.addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(TopSnout, 0.5607743073079057F, 0.0F, 0.0F);
        this.DewlapRight = new ModelPart(this, 24, 26);
        this.DewlapRight.mirror = true;
        this.DewlapRight.setPos(-0.3F, 3.7F, -5.5F);
        this.DewlapRight.addBox(-2.0F, 0.0F, 0.0F, 4.0F, 2.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(DewlapRight, 1.0016444470669013F, 0.0F, 0.0F);
        this.ArmRight = new ModelPart(this, 32, 5);
        this.ArmRight.mirror = true;
        this.ArmRight.setPos(-1.6F, 3.4F, -3.0F);
        this.ArmRight.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(ArmRight, 0.4290019288086257F, 0.0F, 0.0F);
        this.ForearmLeft = new ModelPart(this, 24, 9);
        this.ForearmLeft.setPos(0.0F, 2.0F, 1.0F);
        this.ForearmLeft.addBox(-1.0F, 0.0F, -2.0F, 2.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(ForearmLeft, -0.6373942508178124F, 0.0911061832922575F, 0.0F);
        this.ForearmRight = new ModelPart(this, 32, 9);
        this.ForearmRight.mirror = true;
        this.ForearmRight.setPos(0.0F, 2.0F, 1.0F);
        this.ForearmRight.addBox(-1.0F, 0.0F, -2.0F, 2.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(ForearmRight, -0.6373942508178124F, -0.0911061832922575F, 0.0F);
        this.EarRight = new ModelPart(this, 28, 16);
        this.EarRight.mirror = true;
        this.EarRight.setPos(-1.5F, -1.7F, 0.7F);
        this.EarRight.addBox(-0.5F, -1.0F, -1.0F, 1.0F, 2.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(EarRight, 1.0471975511965976F, -0.8196066007575706F, 0.0F);
        this.HandRight = new ModelPart(this, 30, 13);
        this.HandRight.mirror = true;
        this.HandRight.setPos(0.0F, 2.0F, -1.0F);
        this.HandRight.addBox(-1.0F, -0.5F, -1.7F, 2.0F, 1.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(HandRight, 0.0F, 0.0F, 0.0911061832922575F);
        this.Hips = new ModelPart(this, 0, 0);
        this.Hips.setPos(0.0F, 19.6F, 2.5F);
        this.Hips.addBox(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(Hips, -0.0911061832922575F, 0.0F, 0.0F);
        this.ThighLeft = new ModelPart(this, 40, 0);
        this.ThighLeft.setPos(1.8F, -1.0F, 1.4F);
        this.ThighLeft.addBox(-1.5F, 0.0F, -2.0F, 3.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(ThighLeft, -0.0911061832922575F, 0.0F, 0.0F);
        this.EarLeft = new ModelPart(this, 18, 16);
        this.EarLeft.setPos(1.5F, -1.7F, 0.7F);
        this.EarLeft.addBox(-0.5F, -1.0F, -1.0F, 1.0F, 2.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(EarLeft, 1.0471975511965976F, 0.8196066007575706F, 0.0F);
        this.ArmLeft = new ModelPart(this, 24, 5);
        this.ArmLeft.setPos(1.6F, 3.4F, -3.0F);
        this.ArmLeft.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(ArmLeft, 0.4290019288086257F, 0.0F, 0.0F);
        this.ThighRight = new ModelPart(this, 38, 16);
        this.ThighRight.mirror = true;
        this.ThighRight.setPos(-1.8F, -1.0F, 1.4F);
        this.ThighRight.addBox(-1.5F, 0.0F, -2.0F, 3.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(ThighRight, -0.0911061832922575F, 0.0F, 0.0F);
        this.Hips.addChild(this.Chest);
        this.Hips.addChild(this.Tail);
        this.LegRight.addChild(this.FootRight);
        this.ThighRight.addChild(this.LegRight);
        this.Head.addChild(this.Snout);
        this.LegLeft.addChild(this.FootLeft);
        this.Chest.addChild(this.Head);
        this.EarLeft.addChild(this.EarTag);
        this.ForearmLeft.addChild(this.HandLeft);
        this.Chest.addChild(this.DewlapLeft);
        this.ThighLeft.addChild(this.LegLeft);
        this.Snout.addChild(this.TopSnout);
        this.Chest.addChild(this.DewlapRight);
        this.Chest.addChild(this.ArmRight);
        this.ArmLeft.addChild(this.ForearmLeft);
        this.ArmRight.addChild(this.ForearmRight);
        this.Head.addChild(this.EarRight);
        this.ForearmRight.addChild(this.HandRight);
        this.Hips.addChild(this.ThighLeft);
        this.Head.addChild(this.EarLeft);
        this.Chest.addChild(this.ArmLeft);
        this.Hips.addChild(this.ThighRight);
    }

    public void setRotateAngle(ModelPart modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

    @Override
    public Iterable<ModelPart> parts() {
        if (parts == null)
            parts = ImmutableList.of(Hips);

        return parts;
    }

    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        if (young) {
            matrixStack.pushPose();
            float f1 = 0.5f;
            matrixStack.scale(f1, f1, f1);
            matrixStack.translate(0.0D, 24.0F / 16.0F, 0.0D);
            parts().forEach((renderer) -> renderer.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha));
            matrixStack.popPose();

        } else super.renderToBuffer(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public void setupAnim(HotRabbitEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.Head.xRot = headPitch / (180F / (float) Math.PI) + 0.006F;
        this.Head.yRot = netHeadYaw / (180F / (float) Math.PI);

        if (limbSwingAmount <= 0.05F && !entity.isInWater()) {
            // idle anims here
        }

        // below is the current movement anim... it's different from usual anims due to the jumping code
        float f = ageInTicks - (float) entity.tickCount;
        this.jumpRotation = Mth.sin(entity.getJumpCompletion(f) * (float) Math.PI);
        this.ThighLeft.xRot = this.jumpRotation * 0.87266463f - 0.091F;
        this.ThighRight.xRot = this.jumpRotation * 0.87266463f - 0.091F;
        this.FootLeft.xRot = this.jumpRotation * 0.87266463f - 0.637F;
        this.FootRight.xRot = this.jumpRotation * 0.87266463f - 0.637F;
        this.ArmLeft.xRot = -this.jumpRotation * 0.6981317f + 0.43F;
        this.ArmRight.xRot = -this.jumpRotation * 0.6981317f + 0.43F;
        this.HandLeft.xRot = this.jumpRotation * 0.6981317f;
        this.HandRight.xRot = this.jumpRotation * 0.6981317f;
    }

    @Override
    public void prepareMobModel(HotRabbitEntity entity, float limbSwing, float limbSwingAmount, float partialTick) {
        super.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTick);
        this.jumpRotation = Mth.sin(entity.getJumpCompletion(partialTick) * (float) Math.PI);
    }
}
