package com.github.kmfisk.hotchicks.client.renderer.entity.model;

import com.github.kmfisk.hotchicks.entity.HotRabbitEntity;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
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

    public HotRabbitModel(ModelPart root) {
        this.Hips = root.getChild("Hips");
        this.Chest = this.Hips.getChild("Chest");
        this.Tail = this.Hips.getChild("Tail");
        this.FootRight = this.LegRight.getChild("FootRight");
        this.LegRight = this.ThighRight.getChild("LegRight");
        this.Snout = this.Head.getChild("Snout");
        this.FootLeft = this.LegLeft.getChild("FootLeft");
        this.Head = this.Chest.getChild("Head");
        this.EarTag = this.EarLeft.getChild("EarTag");
        this.HandLeft = this.ForearmLeft.getChild("HandLeft");
        this.DewlapLeft = this.Chest.getChild("DewlapLeft");
        this.LegLeft = this.ThighLeft.getChild("LegLeft");
        this.TopSnout = this.Snout.getChild("TopSnout");
        this.DewlapRight = this.Chest.getChild("DewlapRight");
        this.ArmRight = this.Chest.getChild("ArmRight");
        this.ForearmLeft = this.ArmLeft.getChild("ForearmLeft");
        this.ForearmRight = this.ArmRight.getChild("ForearmRight");
        this.EarRight = this.Head.getChild("EarRight");
        this.HandRight = this.ForearmRight.getChild("HandRight");
        this.ThighLeft = this.Hips.getChild("ThighLeft");
        this.EarLeft = this.Head.getChild("EarLeft");
        this.ArmLeft = this.Chest.getChild("ArmLeft");
        this.ThighRight = this.Hips.getChild("ThighRight");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshDef = new MeshDefinition();
        PartDefinition partDefinition = meshDef.getRoot();

        partDefinition.addOrReplaceChild("Chest", CubeListBuilder.create().texOffs(0, 12).addBox(-2.5F, 0.0F, -4.0F, 5.0F, 5.0F, 4.0F), PartPose.offsetAndRotation(0.0F, -2.5F, -3.0F, 0.2602285888091934F, 0.0F, 0.0F));
        partDefinition.addOrReplaceChild("Tail", CubeListBuilder.create().texOffs(18, 0).addBox(-1.5F, -3.0F, -1.5F, 3.0F, 3.0F, 2.0F), PartPose.offsetAndRotation(0.0F, 2.0F, 2.5F, -0.3642502295386026F, 0.0F, 0.0F));
        partDefinition.addOrReplaceChild("FootRight", CubeListBuilder.create().texOffs(51, 22).mirror(true).addBox(-1.0F, -0.5F, -3.0F, 2.0F, 1.0F, 3.0F), PartPose.offsetAndRotation(0.2F, 2.3F, 1.4F, -0.6373942508178124F, 0.27314402127920984F, -0.27314402127920984F));
        partDefinition.addOrReplaceChild("LegRight", CubeListBuilder.create().texOffs(52, 18).mirror(true).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 2.0F), PartPose.offsetAndRotation(0.3F, 4.0F, -1.5F, 0.8196066007575706F, 0.0F, 0.0F));
        partDefinition.addOrReplaceChild("Snout", CubeListBuilder.create().texOffs(16, 22).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 2.0F, 2.0F), PartPose.offsetAndRotation(0.0F, -0.3F, -1.7F, 0.27366763203903305F, 0.0F, 0.0F));
        partDefinition.addOrReplaceChild("FootLeft", CubeListBuilder.create().texOffs(42, 12).addBox(-1.0F, -0.5F, -3.0F, 2.0F, 1.0F, 3.0F), PartPose.offsetAndRotation(-0.2F, 2.3F, 1.4F, -0.6373942508178124F, -0.27314402127920984F, 0.27314402127920984F));
        partDefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 21).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.6F, -4.7F, 0.006632251074354668F, 0.0F, 0.0F));
        partDefinition.addOrReplaceChild("EarTag", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.1F, 0.0F, 0.0F)), PartPose.offset(-0.5F, 0.3F, 0.5F));
        partDefinition.addOrReplaceChild("HandLeft", CubeListBuilder.create().texOffs(22, 13).addBox(-1.0F, -0.5F, -1.7F, 2.0F, 1.0F, 2.0F), PartPose.offsetAndRotation(0.0F, 2.0F, -1.0F, 0.0F, 0.0F, -0.0911061832922575F));
        partDefinition.addOrReplaceChild("DewlapLeft", CubeListBuilder.create().texOffs(24, 26).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 2.0F, 4.0F), PartPose.offsetAndRotation(0.3F, 3.7F, -5.5F, 1.0016444470669013F, 0.0F, 0.0F));
        partDefinition.addOrReplaceChild("LegLeft", CubeListBuilder.create().texOffs(43, 8).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 2.0F), PartPose.offsetAndRotation(0.3F, 4.0F, -1.5F, 0.8196066007575706F, 0.0F, 0.0F));
        partDefinition.addOrReplaceChild("TopSnout", CubeListBuilder.create().texOffs(16, 26).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 2.0F), PartPose.offsetAndRotation(0.0F, 0.0F, -1.7F, 0.5607743073079057F, 0.0F, 0.0F));
        partDefinition.addOrReplaceChild("DewlapRight", CubeListBuilder.create().texOffs(24, 26).mirror(true).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 2.0F, 4.0F), PartPose.offsetAndRotation(-0.3F, 3.7F, -5.5F, 1.0016444470669013F, 0.0F, 0.0F));
        partDefinition.addOrReplaceChild("ArmRight", CubeListBuilder.create().texOffs(32, 5).mirror(true).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F), PartPose.offsetAndRotation(-1.6F, 3.4F, -3.0F, 0.4290019288086257F, 0.0F, 0.0F));
        partDefinition.addOrReplaceChild("ForearmLeft", CubeListBuilder.create().texOffs(24, 9).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 2.0F, 2.0F), PartPose.offsetAndRotation(0.0F, 2.0F, 1.0F, -0.6373942508178124F, 0.0911061832922575F, 0.0F));
        partDefinition.addOrReplaceChild("ForearmRight", CubeListBuilder.create().texOffs(32, 9).mirror(true).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 2.0F, 2.0F), PartPose.offsetAndRotation(0.0F, 2.0F, 1.0F, -0.6373942508178124F, -0.0911061832922575F, 0.0F));
        partDefinition.addOrReplaceChild("EarRight", CubeListBuilder.create().texOffs(28, 16).mirror(true).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 2.0F, 4.0F), PartPose.offsetAndRotation(-1.5F, -1.7F, 0.7F, 1.0471975511965976F, -0.8196066007575706F, 0.0F));
        partDefinition.addOrReplaceChild("HandRight", CubeListBuilder.create().texOffs(30, 13).mirror(true).addBox(-1.0F, -0.5F, -1.7F, 2.0F, 1.0F, 2.0F), PartPose.offsetAndRotation(0.0F, 2.0F, -1.0F, 0.0F, 0.0F, 0.0911061832922575F));
        partDefinition.addOrReplaceChild("Hips", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F), PartPose.offsetAndRotation(0.0F, 19.6F, 2.5F, -0.0911061832922575F, 0.0F, 0.0F));
        partDefinition.addOrReplaceChild("ThighLeft", CubeListBuilder.create().texOffs(40, 0).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 4.0F, 4.0F), PartPose.offsetAndRotation(1.8F, -1.0F, 1.4F, -0.0911061832922575F, 0.0F, 0.0F));
        partDefinition.addOrReplaceChild("EarLeft", CubeListBuilder.create().texOffs(18, 16).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 2.0F, 4.0F), PartPose.offsetAndRotation(1.5F, -1.7F, 0.7F, 1.0471975511965976F, 0.8196066007575706F, 0.0F));
        partDefinition.addOrReplaceChild("ArmLeft", CubeListBuilder.create().texOffs(24, 5).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F), PartPose.offsetAndRotation(1.6F, 3.4F, -3.0F, 0.4290019288086257F, 0.0F, 0.0F));
        partDefinition.addOrReplaceChild("ThighRight", CubeListBuilder.create().texOffs(38, 16).mirror(true).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 4.0F, 4.0F), PartPose.offsetAndRotation(-1.8F, -1.0F, 1.4F, -0.0911061832922575F, 0.0F, 0.0F));

        return LayerDefinition.create(meshDef, 64, 32);
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
