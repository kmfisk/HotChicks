package com.github.kmfisk.hotchicks.client.renderer.entity.model;

import com.github.kmfisk.hotchicks.entity.HotChickenEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HotChickenModel extends ListModel<HotChickenEntity> {
    public ModelPart Body;
    public ModelPart Chest;
    public ModelPart Tail;
    public ModelPart Belly;
    public ModelPart ThighLeft;
    public ModelPart ThighRight;
    public ModelPart WingLeft;
    public ModelPart WingRight;
    public ModelPart Neck;
    public ModelPart Head;
    public ModelPart NeckFeathersLeft;
    public ModelPart NeckFeathersRight;
    public ModelPart BeakBase;
    public ModelPart Comb;
    public ModelPart BeardLeft;
    public ModelPart BeardRight;
    public ModelPart Beak;
    public ModelPart Wattle;
    public ModelPart BeakTop;
    public ModelPart TailFeathers;
    public ModelPart TailFeathers2;
    public ModelPart BellyRear;
    public ModelPart LegLeft;
    public ModelPart FootLeft;
    public ModelPart SpurLeft;
    public ModelPart LegBand;
    public ModelPart Toe1L;
    public ModelPart Toe2L;
    public ModelPart LegRight;
    public ModelPart FootRight;
    public ModelPart SpurRight;
    public ModelPart Toe1R;
    public ModelPart Toe2R;
    public ModelPart HeadPuffLeft;
    public ModelPart HeadPuffRight;
    public ModelPart LegFeathersLeft;
    public ModelPart LegFeathersRight;
    private Iterable<ModelPart> parts;

    @Override
    public Iterable<ModelPart> parts() {
        if (parts == null)
            parts = ImmutableList.of(Body);

        return parts;
    }

    @Override
    public void setupAnim(HotChickenEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        if (limbSwingAmount <= 0.05F && !entity.isInWater()) {
            // idle anims here
        }

        // below is the current movement anim, hens and roosters share this here
        float speed = 3.0f;
        float degree = 1.4f;
        this.ThighLeft.xRot = Mth.cos((limbSwing * speed * 0.2F) + (float) Math.PI) * (degree * 0.8F) * limbSwingAmount * 0.5F + 0.22F;
        this.LegLeft.xRot = Mth.cos(5.0F + (limbSwing * speed * 0.2F) + (float) Math.PI) * (degree * -2.0F) * limbSwingAmount * 0.5F + -0.35F;
        this.FootLeft.xRot = Mth.cos(4.0F + (limbSwing * speed * 0.2F) + (float) Math.PI) * (degree * 1.2F) * limbSwingAmount * 0.5F + 0.1F;
        this.ThighRight.xRot = Mth.cos((limbSwing * speed * 0.2F) + (float) Math.PI) * (degree * -0.8F) * limbSwingAmount * 0.5F + 0.22F;
        this.LegRight.xRot = Mth.cos(5.0F + (limbSwing * speed * 0.2F) + (float) Math.PI) * (degree * 2.0F) * limbSwingAmount * 0.5F + -0.35F;
        this.FootRight.xRot = Mth.cos(4.0F + (limbSwing * speed * 0.2F) + (float) Math.PI) * (degree * -1.2F) * limbSwingAmount * 0.5F + 0.1F;
        this.Chest.xRot = Mth.cos((limbSwing * speed * 0.4F) + (float) Math.PI) * (degree * 0.2F) * limbSwingAmount * 0.5F + 0.78F;
        this.Neck.xRot = Mth.cos(6.0F + (limbSwing * speed * 0.4F) + (float) Math.PI) * (degree * -0.2F) * limbSwingAmount * 0.5F + -0.5F;
        this.TailFeathers.xRot = Mth.cos(1.0F + (limbSwing * speed * 0.4F) + (float) Math.PI) * (degree * -0.1F) * limbSwingAmount * 0.5F + -0.36F;
    }

    @Override
    public void prepareMobModel(HotChickenEntity entity, float speed, float walkSpeed, float f4) {
        super.prepareMobModel(entity, speed, walkSpeed, f4);
    }

    public static class Rooster extends HotChickenModel {
        public Rooster(ModelPart root) {
            this.Body = root.getChild("Body");
            this.Chest = this.Body.getChild("Chest");
            this.Neck = this.Chest.getChild("Neck");
            this.Head = this.Neck.getChild("Head");
            this.NeckFeathersLeft = this.Neck.getChild("NeckFeathersLeft");
            this.NeckFeathersRight = this.Neck.getChild("NeckFeathersRight");
            this.WingLeft = this.Body.getChild("WingLeft");
            this.WingRight = this.Body.getChild("WingRight");
            this.Tail = this.Body.getChild("Tail");
            this.Belly = this.Body.getChild("Belly");
            this.ThighLeft = this.Body.getChild("ThighLeft");
            this.ThighRight = this.Body.getChild("ThighRight");
            this.BeakTop = this.Beak.getChild("BeakTop");
            this.BeakBase = this.Head.getChild("BeakBase");
            this.Toe1L = this.FootLeft.getChild("Toe1L");
            this.SpurLeft = this.LegLeft.getChild("SpurLeft");
            this.SpurRight = this.LegRight.getChild("SpurRight");
            this.Beak = this.BeakBase.getChild("Beak");
            this.LegRight = this.ThighRight.getChild("LegRight");
            this.TailFeathers2 = this.TailFeathers.getChild("TailFeathers2");
            this.Comb = this.Head.getChild("Comb");
            this.LegLeft = this.ThighLeft.getChild("LegLeft");
            this.Toe1R = this.FootRight.getChild("Toe1R");
            this.Wattle = this.BeakBase.getChild("Wattle");
            this.TailFeathers = this.Tail.getChild("TailFeathers");
            this.FootLeft = this.LegLeft.getChild("FootLeft");
            this.Toe2R = this.FootRight.getChild("Toe2R");
            this.FootRight = this.LegRight.getChild("FootRight");
            this.BeardRight = this.Head.getChild("BeardRight");
            this.LegBand = this.LegLeft.getChild("LegBand");
            this.Toe2L = this.FootLeft.getChild("Toe2L");
            this.BellyRear = this.Belly.getChild("BellyRear");
            this.BeardLeft = this.Head.getChild("BeardLeft");
        }

        public static LayerDefinition createBodyLayer() {
            MeshDefinition meshDef = new MeshDefinition();
            PartDefinition partDefinition = meshDef.getRoot();

            partDefinition.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, -3.0F, 0.0F, 2.0F, 3.0F, 3.0F), PartPose.offsetAndRotation(0.0F, -3.0F, 0.2F, -0.500909508638178F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("WingLeft", CubeListBuilder.create().texOffs(29, 17).addBox(0.0F, 0.0F, 0.0F, 1.0F, 3.0F, 4.0F), PartPose.offset(2.0F, -2.1F, -2.0F));
            partDefinition.addOrReplaceChild("BeakTop", CubeListBuilder.create().texOffs(13, 17).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 2.0F), PartPose.offsetAndRotation(0.0F, 0.0F, -2.4F, 0.3642502295386026F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("BeakBase", CubeListBuilder.create().texOffs(0, 22).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 1.0F), PartPose.offsetAndRotation(0.0F, -0.8F, -1.5F, 0.27314402127920984F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("Toe1L", CubeListBuilder.create().texOffs(42, 14).addBox(-0.5F, -1.0F, -2.0F, 1.0F, 1.0F, 2.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.2F, 0.04555309164612875F, -0.27314402127920984F, 0.0F));
            partDefinition.addOrReplaceChild("SpurLeft", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F), PartPose.offset(0.0F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("SpurRight", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F), PartPose.offset(0.0F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("Beak", CubeListBuilder.create().texOffs(7, 16).addBox(-0.5F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F), PartPose.offsetAndRotation(0.0F, 1.0F, 0.4F, -0.3186971254089062F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("LegRight", CubeListBuilder.create().texOffs(36, 12).mirror().addBox(-0.5F, 0.0F, -1.0F, 1.0F, 2.0F, 1.0F), PartPose.offsetAndRotation(0.4F, 3.0F, 0.5F, -0.3642502295386026F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("WingRight", CubeListBuilder.create().texOffs(39, 17).mirror().addBox(-1.0F, 0.0F, 0.0F, 1.0F, 3.0F, 4.0F), PartPose.offset(-2.0F, -2.2F, -2.0F));
            partDefinition.addOrReplaceChild("Tail", CubeListBuilder.create().texOffs(24, 10).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 5.0F, 2.0F), PartPose.offsetAndRotation(0.0F, -2.5F, 1.0F, 0.45535640450848164F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(12, 10).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 4.0F, 3.0F), PartPose.offsetAndRotation(0.0F, -4.0F, 1.6F, -0.0911061832922575F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("TailFeathers2", CubeListBuilder.create().texOffs(10, 23).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 4.0F, 5.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 4.0F, -0.4098033003787853F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("Comb", CubeListBuilder.create().texOffs(19, 17).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 4.0F), PartPose.offsetAndRotation(0.0F, -2.6F, -2.4F, 0.18203784630933073F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("Chest", CubeListBuilder.create().texOffs(0, 10).addBox(-1.5F, -3.0F, 0.0F, 3.0F, 3.0F, 3.0F), PartPose.offsetAndRotation(0.0F, 2.1F, -2.5F, 0.7740534966278743F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("Belly", CubeListBuilder.create().texOffs(34, 6).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 3.0F, 3.0F), PartPose.offsetAndRotation(0.0F, 1.5F, -2.0F, -0.3642502295386026F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("ThighLeft", CubeListBuilder.create().texOffs(34, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 4.0F, 2.0F), PartPose.offsetAndRotation(1.7F, 1.1F, 0.5F, 0.2275909337942703F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("NeckFeathersLeft", CubeListBuilder.create().texOffs(0, 25).addBox(-1.0F, 0.0F, -3.0F, 2.0F, 3.0F, 3.0F), PartPose.offsetAndRotation(0.4F, -4.0F, 3.0F, 0.27314402127920984F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("LegLeft", CubeListBuilder.create().texOffs(36, 12).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 2.0F, 1.0F), PartPose.offsetAndRotation(-0.4F, 3.0F, 0.5F, -0.3642502295386026F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("Toe1R", CubeListBuilder.create().texOffs(42, 14).mirror().addBox(-0.5F, -1.0F, -2.0F, 1.0F, 1.0F, 2.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.2F, 0.04555309164612875F, -0.27314402127920984F, 0.0F));
            partDefinition.addOrReplaceChild("Wattle", CubeListBuilder.create().texOffs(8, 20).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 3.0F, 2.0F), PartPose.offsetAndRotation(0.0F, 1.2F, -1.0F, -0.6373942508178124F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("ThighRight", CubeListBuilder.create().texOffs(42, 0).mirror().addBox(-1.0F, -1.0F, -1.0F, 2.0F, 4.0F, 2.0F), PartPose.offsetAndRotation(-1.7F, 1.1F, 0.5F, 0.2275909337942703F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("TailFeathers", CubeListBuilder.create().texOffs(20, 0).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 6.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 2.0F, -0.3642502295386026F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("FootLeft", CubeListBuilder.create().texOffs(41, 13).addBox(-0.5F, -1.0F, -2.0F, 1.0F, 1.0F, 3.0F), PartPose.offsetAndRotation(0.0F, 2.5F, 0.0F, 0.2275909337942703F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("Toe2R", CubeListBuilder.create().texOffs(42, 14).addBox(-0.5F, -1.0F, -2.0F, 1.0F, 1.0F, 2.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.2F, 0.04555309164612875F, 0.27314402127920984F, 0.0F));
            partDefinition.addOrReplaceChild("FootRight", CubeListBuilder.create().texOffs(41, 13).mirror().addBox(-0.5F, -1.0F, -2.0F, 1.0F, 1.0F, 3.0F), PartPose.offsetAndRotation(0.0F, 2.5F, 0.0F, 0.2275909337942703F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("NeckFeathersRight", CubeListBuilder.create().texOffs(0, 25).mirror().addBox(-1.0F, 0.0F, -3.0F, 2.0F, 3.0F, 3.0F), PartPose.offsetAndRotation(-0.4F, -4.0F, 3.0F, 0.27314402127920984F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("BeardRight", CubeListBuilder.create().texOffs(29, 24).mirror().addBox(-1.5F, 0.0F, 0.0F, 3.0F, 2.0F, 2.0F), PartPose.offset(-0.4F, 0.5F, -1.8F));
            partDefinition.addOrReplaceChild("LegBand", CubeListBuilder.create().texOffs(0, 2).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.1F, 0.0F, 0.1F)), PartPose.offset(0.0F, 0.0F, -0.5F));
            partDefinition.addOrReplaceChild("Toe2L", CubeListBuilder.create().texOffs(42, 14).addBox(-0.5F, -1.0F, -2.0F, 1.0F, 1.0F, 2.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.2F, 0.04555309164612875F, 0.27314402127920984F, 0.0F));
            partDefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -2.5F, -2.5F, 5.0F, 5.0F, 5.0F), PartPose.offsetAndRotation(0.0F, 17.6F, 0.0F, -0.0911061832922575F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("BellyRear", CubeListBuilder.create().texOffs(50, 0).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 4.0F, 3.0F), PartPose.offsetAndRotation(0.0F, 1.0F, 3.0F, 0.500909508638178F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("BeardLeft", CubeListBuilder.create().texOffs(19, 24).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 2.0F, 2.0F), PartPose.offset(0.4F, 0.5F, -1.8F));

            return LayerDefinition.create(meshDef, 64, 32);
        }

        @Override
        public void setupAnim(HotChickenEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
            super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            this.Head.xRot = headPitch / (180F / (float) Math.PI) + -0.09F;
            this.Head.yRot = netHeadYaw / (180F / (float) Math.PI);
        }
    }

    public static class RoosterSilkie extends HotChickenModel {
        public RoosterSilkie(ModelPart root) {
            this.Body = root.getChild("Body");
            this.Head = this.Neck.getChild("Head");
            this.LegFeathersLeft = this.LegLeft.getChild("LegFeathersLeft");
            this.ThighRight = this.Body.getChild("ThighRight");
            this.Chest = this.Body.getChild("Chest");
            this.WingRight = this.Body.getChild("WingRight");
            this.Toe1R = this.FootRight.getChild("Toe1R");
            this.BeardRight = this.Head.getChild("BeardRight");
            this.Toe2R = this.FootRight.getChild("Toe2R");
            this.Comb = this.Head.getChild("Comb");
            this.Beak = this.BeakBase.getChild("Beak");
            this.HeadPuffRight = this.Head.getChild("HeadPuffRight");
            this.Belly = this.Body.getChild("Belly");
            this.LegFeathersRight = this.LegRight.getChild("LegFeathersRight");
            this.Toe2L = this.FootLeft.getChild("Toe2L");
            this.Tail = this.Body.getChild("Tail");
            this.ThighLeft = this.Body.getChild("ThighLeft");
            this.NeckFeathersLeft = this.Neck.getChild("NeckFeathersLeft");
            this.LegLeft = this.ThighLeft.getChild("LegLeft");
            this.HeadPuffLeft = this.Head.getChild("HeadPuffLeft");
            this.TailFeathers = this.Tail.getChild("TailFeathers");
            this.LegRight = this.ThighRight.getChild("LegRight");
            this.BeakBase = this.Head.getChild("BeakBase");
            this.FootLeft = this.LegLeft.getChild("FootLeft");
            this.FootRight = this.LegRight.getChild("FootRight");
            this.WingLeft = this.Body.getChild("WingLeft");
            this.NeckFeathersRight = this.Neck.getChild("NeckFeathersRight");
            this.BeardLeft = this.Head.getChild("BeardLeft");
            this.Toe1L = this.FootLeft.getChild("Toe1L");
            this.BellyRear = this.Belly.getChild("BellyRear");
            this.BeakTop = this.Beak.getChild("BeakTop");
            this.Wattle = this.BeakBase.getChild("Wattle");
            this.Neck = this.Chest.getChild("Neck");
        }

        public static LayerDefinition createBodyLayer() {
            MeshDefinition meshDef = new MeshDefinition();
            PartDefinition partDefinition = meshDef.getRoot();

            partDefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(12, 10).addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3), PartPose.offsetAndRotation(0.0F, -4.0F, 1.6F, -0.045553093477052F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("LegFeathersLeft", CubeListBuilder.create().texOffs(33, 24).addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2), PartPose.offset(0.3F, 0.2F, -0.4F));
            partDefinition.addOrReplaceChild("ThighRight", CubeListBuilder.create().texOffs(40, 0).mirror(true).addBox(-1.0F, -1.0F, -1.0F, 2, 4, 2), PartPose.offsetAndRotation(-1.7F, 1.1F, 0.5F, 0.22759093446006054F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("Chest", CubeListBuilder.create().texOffs(0, 10).addBox(-1.5F, -3.0F, 0.0F, 3, 3, 3), PartPose.offsetAndRotation(0.0F, 2.1F, -2.5F, 0.7740535232594852F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("WingRight", CubeListBuilder.create().texOffs(35, 17).mirror(true).addBox(-1.0F, 0.0F, 0.0F, 1, 3, 4), PartPose.offset(-2.0F, -2.2F, -2.0F));
            partDefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -2.5F, -2.5F, 5, 5, 5), PartPose.offsetAndRotation(0.0F, 18.2F, 0.0F, -0.091106186954104F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("Toe1R", CubeListBuilder.create().texOffs(42, 14).mirror(true).addBox(-0.5F, -1.0F, -2.0F, 1, 1, 2), PartPose.offsetAndRotation(0.0F, 0.0F, 0.2F, 0.045553093477052F, -0.27314402793711257F, 0.0F));
            partDefinition.addOrReplaceChild("BeardRight", CubeListBuilder.create().texOffs(22, 24).mirror(true).addBox(-1.5F, 0.0F, 0.0F, 3, 2, 2), PartPose.offset(-0.4F, 0.5F, -1.8F));
            partDefinition.addOrReplaceChild("Toe2R", CubeListBuilder.create().texOffs(42, 14).addBox(-0.5F, -1.0F, -2.0F, 1, 1, 2), PartPose.offsetAndRotation(0.0F, 0.0F, 0.2F, 0.045553093477052F, 0.27314402793711257F, 0.0F));
            partDefinition.addOrReplaceChild("Comb", CubeListBuilder.create().texOffs(19, 16).addBox(-0.5F, 0.0F, 0.0F, 1, 2, 2), PartPose.offsetAndRotation(0.0F, -1.5F, -2.2F, 0.22759093446006054F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("Beak", CubeListBuilder.create().texOffs(7, 16).addBox(-0.5F, 0.0F, -2.0F, 1, 1, 2), PartPose.offsetAndRotation(0.0F, 1.0F, 0.4F, -0.31869712141416456F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("HeadPuffRight", CubeListBuilder.create().texOffs(10, 23).mirror(true).addBox(-1.5F, 0.0F, 0.0F, 3, 2, 3), PartPose.offsetAndRotation(-0.2F, -2.5F, -1.3F, -0.2937389131106456F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("Belly", CubeListBuilder.create().texOffs(36, 6).addBox(-2.0F, -2.0F, 0.0F, 4, 3, 3), PartPose.offsetAndRotation(0.0F, 1.5F, -2.0F, -0.36425021489121656F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("LegFeathersRight", CubeListBuilder.create().texOffs(33, 24).mirror(true).addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2), PartPose.offset(-0.3F, 0.2F, -0.4F));
            partDefinition.addOrReplaceChild("Toe2L", CubeListBuilder.create().texOffs(42, 14).addBox(-0.5F, -1.0F, -2.0F, 1, 1, 2), PartPose.offsetAndRotation(0.0F, 0.0F, 0.2F, 0.045553093477052F, 0.27314402793711257F, 0.0F));
            partDefinition.addOrReplaceChild("Tail", CubeListBuilder.create().texOffs(24, 9).addBox(-2.0F, -0.1F, 0.0F, 4, 6, 2), PartPose.offsetAndRotation(0.0F, -2.4F, 1.3F, 0.3361504139341079F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("ThighLeft", CubeListBuilder.create().texOffs(32, 0).addBox(-1.0F, -1.0F, -1.0F, 2, 4, 2), PartPose.offsetAndRotation(1.7F, 1.1F, 0.5F, 0.22759093446006054F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("NeckFeathersLeft", CubeListBuilder.create().texOffs(0, 25).addBox(-1.0F, 0.0F, -3.0F, 2, 3, 3), PartPose.offsetAndRotation(0.4F, -4.1F, 3.0F, 0.31869712141416456F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("LegLeft", CubeListBuilder.create().texOffs(36, 12).addBox(-0.5F, 0.0F, -1.0F, 1, 2, 1), PartPose.offsetAndRotation(-0.4F, 2.5F, 0.5F, -0.36425021489121656F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("HeadPuffLeft", CubeListBuilder.create().texOffs(10, 23).addBox(-1.5F, 0.0F, 0.0F, 3, 2, 3), PartPose.offsetAndRotation(0.2F, -2.5F, -1.3F, -0.2937389131106456F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("TailFeathers", CubeListBuilder.create().texOffs(50, 6).addBox(-1.5F, 0.0F, 0.0F, 3, 5, 4), PartPose.offsetAndRotation(0.0F, 0.0F, 1.3F, -0.27314402793711257F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("LegRight", CubeListBuilder.create().texOffs(36, 12).mirror(true).addBox(-0.5F, 0.0F, -1.0F, 1, 2, 1), PartPose.offsetAndRotation(0.4F, 2.5F, 0.5F, -0.36425021489121656F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("BeakBase", CubeListBuilder.create().texOffs(0, 22).addBox(-1.0F, 0.0F, -1.0F, 2, 2, 1), PartPose.offsetAndRotation(0.0F, -0.8F, -1.5F, 0.27314402793711257F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("FootLeft", CubeListBuilder.create().texOffs(41, 13).addBox(-0.5F, -1.0F, -2.0F, 1, 1, 3), PartPose.offsetAndRotation(0.0F, 2.5F, 0.0F, 0.22759093446006054F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("FootRight", CubeListBuilder.create().texOffs(41, 13).addBox(-0.5F, -1.0F, -2.0F, 1, 1, 3), PartPose.offsetAndRotation(0.0F, 2.5F, 0.0F, 0.22759093446006054F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("WingLeft", CubeListBuilder.create().texOffs(25, 17).addBox(0.0F, 0.0F, 0.0F, 1, 3, 4), PartPose.offset(2.0F, -2.1F, -2.0F));
            partDefinition.addOrReplaceChild("NeckFeathersRight", CubeListBuilder.create().texOffs(0, 25).mirror(true).addBox(-1.0F, 0.0F, -3.0F, 2, 3, 3), PartPose.offsetAndRotation(-0.4F, -4.1F, 3.0F, 0.31869712141416456F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("BeardLeft", CubeListBuilder.create().texOffs(22, 24).addBox(-1.5F, 0.0F, 0.0F, 3, 2, 2), PartPose.offset(0.4F, 0.5F, -1.8F));
            partDefinition.addOrReplaceChild("Toe1L", CubeListBuilder.create().texOffs(42, 14).addBox(-0.5F, -1.0F, -2.0F, 1, 1, 2), PartPose.offsetAndRotation(0.0F, 0.0F, 0.2F, 0.045553093477052F, -0.27314402793711257F, 0.0F));
            partDefinition.addOrReplaceChild("BellyRear", CubeListBuilder.create().texOffs(48, 0).addBox(-2.0F, -3.0F, 0.0F, 4, 3, 3), PartPose.offsetAndRotation(0.0F, 1.0F, 3.0F, 0.5009094953223726F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("BeakTop", CubeListBuilder.create().texOffs(13, 16).addBox(-0.5F, 0.0F, 0.0F, 1, 1, 2), PartPose.offsetAndRotation(0.0F, 0.0F, -2.4F, 0.36425021489121656F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("Wattle", CubeListBuilder.create().texOffs(10, 19).addBox(-0.5F, 0.0F, 0.0F, 1, 2, 2), PartPose.offsetAndRotation(0.0F, 1.0F, -0.8F, -0.36425021489121656F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, -3.0F, 0.0F, 2, 3, 3), PartPose.offsetAndRotation(0.0F, -3.0F, 0.2F, -0.5009094953223726F, 0.0F, 0.0F));

            return LayerDefinition.create(meshDef, 64, 32);
        }

        @Override
        public void setupAnim(HotChickenEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
            super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            this.Head.xRot = headPitch / (180F / (float) Math.PI) + -0.05F;
            this.Head.yRot = netHeadYaw / (180F / (float) Math.PI);
        }
    }

    public static class Hen extends HotChickenModel {
        public Hen(ModelPart root) {
            this.Body = root.getChild("Body");
            this.Toe2R = this.FootRight.getChild("Toe2R");
            this.Toe1R = this.FootRight.getChild("Toe1R");
            this.BeardLeft = this.Head.getChild("BeardLeft");
            this.BellyRear = this.Belly.getChild("BellyRear");
            this.Tail = this.Body.getChild("Tail");
            this.ThighLeft = this.Body.getChild("ThighLeft");
            this.BeardRight = this.Head.getChild("BeardRight");
            this.LegBand = this.LegLeft.getChild("LegBand");
            this.Neck = this.Chest.getChild("Neck");
            this.Toe1L = this.FootLeft.getChild("Toe1L");
            this.Toe2L = this.FootLeft.getChild("Toe2L");
            this.Beak = this.BeakBase.getChild("Beak");
            this.Wattle = this.BeakBase.getChild("Wattle");
            this.FootLeft = this.LegLeft.getChild("FootLeft");
            this.BeakTop = this.Beak.getChild("BeakTop");
            this.Chest = this.Body.getChild("Chest");
            this.ThighRight = this.Body.getChild("ThighRight");
            this.Belly = this.Body.getChild("Belly");
            this.WingLeft = this.Body.getChild("WingLeft");
            this.FootRight = this.LegRight.getChild("FootRight");
            this.WingRight = this.Body.getChild("WingRight");
            this.Head = this.Neck.getChild("Head");
            this.Comb = this.Head.getChild("Comb");
            this.BeakBase = this.Head.getChild("BeakBase");
            this.LegRight = this.ThighRight.getChild("LegRight");
            this.LegLeft = this.ThighLeft.getChild("LegLeft");
            this.TailFeathers = this.Tail.getChild("TailFeathers");
        }

        public static LayerDefinition createBodyLayer() {
            MeshDefinition meshDef = new MeshDefinition();
            PartDefinition partDefinition = meshDef.getRoot();

            partDefinition.addOrReplaceChild("Toe2R", CubeListBuilder.create().texOffs(42, 14).addBox(-0.5F, -1.0F, -2.0F, 1.0F, 1.0F, 2.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.2F, 0.04555309164612875F, 0.27314402127920984F, 0.0F));
            partDefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -2.5F, -2.5F, 5.0F, 5.0F, 5.0F), PartPose.offsetAndRotation(0.0F, 17.6F, 0.0F, -0.0911061832922575F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("Toe1R", CubeListBuilder.create().texOffs(42, 14).mirror(true).addBox(-0.5F, -1.0F, -2.0F, 1.0F, 1.0F, 2.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.2F, 0.04555309164612875F, -0.27314402127920984F, 0.0F));
            partDefinition.addOrReplaceChild("BeardLeft", CubeListBuilder.create().texOffs(19, 24).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 2.0F, 2.0F), PartPose.offset(0.4F, 0.5F, -1.8F));
            partDefinition.addOrReplaceChild("BellyRear", CubeListBuilder.create().texOffs(48, 0).addBox(-2.0F, -4.0F, 0.0F, 4.0F, 4.0F, 3.0F), PartPose.offsetAndRotation(0.0F, 1.0F, 3.0F, 0.500909508638178F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("Tail", CubeListBuilder.create().texOffs(24, 9).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 5.0F, 2.0F), PartPose.offsetAndRotation(0.0F, -2.5F, 1.0F, 0.4098033003787853F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("ThighLeft", CubeListBuilder.create().texOffs(32, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 4.0F, 2.0F), PartPose.offsetAndRotation(1.7F, 1.1F, 0.5F, 0.2275909337942703F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("BeardRight", CubeListBuilder.create().texOffs(29, 24).mirror(true).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 2.0F, 2.0F), PartPose.offset(-0.4F, 0.5F, -1.8F));
            partDefinition.addOrReplaceChild("LegBand", CubeListBuilder.create().texOffs(0, 2).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.1F, 0.0F, 0.1F)), PartPose.offset(0.0F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, -3.0F, 0.0F, 2.0F, 3.0F, 3.0F), PartPose.offsetAndRotation(0.0F, -3.0F, 0.2F, -0.500909508638178F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("Toe1L", CubeListBuilder.create().texOffs(42, 14).addBox(-0.5F, -1.0F, -2.0F, 1.0F, 1.0F, 2.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.2F, 0.04555309164612875F, -0.27314402127920984F, 0.0F));
            partDefinition.addOrReplaceChild("Toe2L", CubeListBuilder.create().texOffs(42, 14).addBox(-0.5F, -1.0F, -2.0F, 1.0F, 1.0F, 2.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.2F, 0.04555309164612875F, 0.27314402127920984F, 0.0F));
            partDefinition.addOrReplaceChild("Beak", CubeListBuilder.create().texOffs(7, 16).addBox(-0.5F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F), PartPose.offsetAndRotation(0.0F, 1.0F, 0.4F, -0.3186971254089062F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("Wattle", CubeListBuilder.create().texOffs(10, 19).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 2.0F), PartPose.offsetAndRotation(0.0F, 1.0F, -0.8F, -0.3642502295386026F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("FootLeft", CubeListBuilder.create().texOffs(41, 13).addBox(-0.5F, -1.0F, -2.0F, 1.0F, 1.0F, 3.0F), PartPose.offsetAndRotation(0.0F, 2.5F, 0.0F, 0.2275909337942703F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("BeakTop", CubeListBuilder.create().texOffs(13, 16).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 2.0F), PartPose.offsetAndRotation(0.0F, 0.0F, -2.4F, 0.3642502295386026F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("Chest", CubeListBuilder.create().texOffs(0, 10).addBox(-1.5F, -3.0F, 0.0F, 3.0F, 3.0F, 3.0F), PartPose.offsetAndRotation(0.0F, 2.1F, -2.5F, 0.7740534966278743F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("ThighRight", CubeListBuilder.create().texOffs(40, 0).mirror(true).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 4.0F, 2.0F), PartPose.offsetAndRotation(-1.7F, 1.1F, 0.5F, 0.2275909337942703F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("Belly", CubeListBuilder.create().texOffs(34, 6).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 3.0F, 3.0F), PartPose.offsetAndRotation(0.0F, 1.5F, -2.0F, -0.3642502295386026F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("WingLeft", CubeListBuilder.create().texOffs(25, 16).addBox(0.0F, 0.0F, 0.0F, 1.0F, 3.0F, 4.0F), PartPose.offset(2.0F, -2.1F, -2.0F));
            partDefinition.addOrReplaceChild("FootRight", CubeListBuilder.create().texOffs(41, 13).addBox(-0.5F, -1.0F, -2.0F, 1.0F, 1.0F, 3.0F), PartPose.offsetAndRotation(0.0F, 2.5F, 0.0F, 0.2275909337942703F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("WingRight", CubeListBuilder.create().texOffs(35, 16).mirror(true).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 3.0F, 4.0F), PartPose.offset(-2.0F, -2.2F, -2.0F));
            partDefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(12, 10).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F), PartPose.offsetAndRotation(0.0F, -4.0F, 1.6F, -0.0911061832922575F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("Comb", CubeListBuilder.create().texOffs(19, 16).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 2.0F), PartPose.offsetAndRotation(0.0F, -1.5F, -2.0F, 0.2275909337942703F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("BeakBase", CubeListBuilder.create().texOffs(0, 22).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 1.0F), PartPose.offsetAndRotation(0.0F, -0.8F, -1.5F, 0.27314402127920984F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("LegRight", CubeListBuilder.create().texOffs(34, 12).mirror(true).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 2.0F, 1.0F), PartPose.offsetAndRotation(0.4F, 3.0F, 0.5F, -0.3642502295386026F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("LegLeft", CubeListBuilder.create().texOffs(34, 12).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 2.0F, 1.0F), PartPose.offsetAndRotation(-0.4F, 3.0F, 0.5F, -0.3642502295386026F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("TailFeathers", CubeListBuilder.create().texOffs(20, 0).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 5.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.27314402127920984F, 0.0F, 0.0F));

            return LayerDefinition.create(meshDef, 64, 32);
        }

        @Override
        public void setupAnim(HotChickenEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
            super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            this.Head.xRot = headPitch / (180F / (float) Math.PI) + -0.09F;
            this.Head.yRot = netHeadYaw / (180F / (float) Math.PI);
        }
    }

    public static class HenSilkie extends HotChickenModel {
        public HenSilkie(ModelPart root) {
            this.Body = root.getChild("Body");
            this.Tail = this.Body.getChild("Tail");
            this.Toe1L = this.FootLeft.getChild("Toe1L");
            this.LegRight = this.ThighRight.getChild("LegRight");
            this.NeckFeathersRight = this.Neck.getChild("NeckFeathersRight");
            this.HeadPuffLeft = this.Head.getChild("HeadPuffLeft");
            this.LegFeathersLeft = this.LegLeft.getChild("LegFeathersLeft");
            this.Head = this.Neck.getChild("Head");
            this.TailFeathers = this.Tail.getChild("TailFeathers");
            this.Belly = this.Body.getChild("Belly");
            this.Comb = this.Head.getChild("Comb");
            this.BellyRear = this.Belly.getChild("BellyRear");
            this.HeadPuffRight = this.Head.getChild("HeadPuffRight");
            this.FootLeft = this.LegLeft.getChild("FootLeft");
            this.Toe2L = this.FootLeft.getChild("Toe2L");
            this.Toe2R = this.FootRight.getChild("Toe2R");
            this.ThighRight = this.Body.getChild("ThighRight");
            this.BeardRight = this.Head.getChild("BeardRight");
            this.LegLeft = this.ThighLeft.getChild("LegLeft");
            this.WingLeft = this.Body.getChild("WingLeft");
            this.FootRight = this.LegRight.getChild("FootRight");
            this.BeakTop = this.Beak.getChild("BeakTop");
            this.LegFeathersRight = this.LegRight.getChild("LegFeathersRight");
            this.Chest = this.Body.getChild("Chest");
            this.Wattle = this.BeakBase.getChild("Wattle");
            this.BeakBase = this.Head.getChild("BeakBase");
            this.Toe1R = this.FootRight.getChild("Toe1R");
            this.NeckFeathersLeft = this.Neck.getChild("NeckFeathersLeft");
            this.Neck = this.Chest.getChild("Neck");
            this.ThighLeft = this.Body.getChild("ThighLeft");
            this.WingRight = this.Body.getChild("WingRight");
            this.BeardLeft = this.Head.getChild("BeardLeft");
            this.Beak = this.BeakBase.getChild("Beak");
        }

        public static LayerDefinition createBodyLayer() {
            MeshDefinition meshDef = new MeshDefinition();
            PartDefinition partDefinition = meshDef.getRoot();

            partDefinition.addOrReplaceChild("Tail", CubeListBuilder.create().texOffs(24, 9).addBox(-2.0F, -0.1F, 0.0F, 4, 6, 2), PartPose.offsetAndRotation(0.0F, -2.4F, 1.3F, 0.3361504139341079F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("Toe1L", CubeListBuilder.create().texOffs(42, 14).addBox(-0.5F, -1.0F, -2.0F, 1, 1, 2), PartPose.offsetAndRotation(0.0F, 0.0F, 0.2F, 0.045553093477052F, -0.27314402793711257F, 0.0F));
            partDefinition.addOrReplaceChild("LegRight", CubeListBuilder.create().texOffs(36, 12).mirror(true).addBox(-0.5F, 0.0F, -1.0F, 1, 2, 1), PartPose.offsetAndRotation(0.4F, 2.5F, 0.5F, -0.36425021489121656F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("NeckFeathersRight", CubeListBuilder.create().texOffs(0, 25).mirror(true).addBox(-1.0F, 0.0F, -3.0F, 2, 3, 3), PartPose.offsetAndRotation(-0.4F, -4.1F, 3.0F, 0.31869712141416456F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("HeadPuffLeft", CubeListBuilder.create().texOffs(10, 23).addBox(-1.5F, 0.0F, 0.0F, 3, 2, 3), PartPose.offsetAndRotation(0.2F, -2.4F, -1.5F, -0.136659280431156F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -2.5F, -2.5F, 5, 5, 5), PartPose.offsetAndRotation(0.0F, 18.2F, 0.0F, -0.091106186954104F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("LegFeathersLeft", CubeListBuilder.create().texOffs(33, 24).addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2), PartPose.offset(0.3F, 0.2F, -0.4F));
            partDefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(12, 10).addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3), PartPose.offsetAndRotation(0.0F, -4.0F, 1.6F, -0.045553093477052F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("TailFeathers", CubeListBuilder.create().texOffs(20, 0).addBox(-1.5F, 0.0F, 0.0F, 3, 5, 3), PartPose.offsetAndRotation(0.0F, 0.0F, 1.3F, -0.091106186954104F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("Belly", CubeListBuilder.create().texOffs(36, 6).addBox(-2.0F, -2.0F, 0.0F, 4, 3, 3), PartPose.offsetAndRotation(0.0F, 1.5F, -2.0F, -0.36425021489121656F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("Comb", CubeListBuilder.create().texOffs(19, 16).addBox(-0.5F, 0.0F, 0.0F, 1, 2, 2), PartPose.offsetAndRotation(0.0F, -1.5F, -2.0F, 0.22759093446006054F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("BellyRear", CubeListBuilder.create().texOffs(48, 0).addBox(-2.0F, -3.0F, 0.0F, 4, 3, 3), PartPose.offsetAndRotation(0.0F, 1.0F, 3.0F, 0.5009094953223726F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("HeadPuffRight", CubeListBuilder.create().texOffs(10, 23).mirror(true).addBox(-1.5F, 0.0F, 0.0F, 3, 2, 3), PartPose.offsetAndRotation(-0.2F, -2.4F, -1.5F, -0.136659280431156F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("FootLeft", CubeListBuilder.create().texOffs(41, 13).addBox(-0.5F, -1.0F, -2.0F, 1, 1, 3), PartPose.offsetAndRotation(0.0F, 2.5F, 0.0F, 0.22759093446006054F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("Toe2L", CubeListBuilder.create().texOffs(42, 14).addBox(-0.5F, -1.0F, -2.0F, 1, 1, 2), PartPose.offsetAndRotation(0.0F, 0.0F, 0.2F, 0.045553093477052F, 0.27314402793711257F, 0.0F));
            partDefinition.addOrReplaceChild("Toe2R", CubeListBuilder.create().texOffs(42, 14).addBox(-0.5F, -1.0F, -2.0F, 1, 1, 2), PartPose.offsetAndRotation(0.0F, 0.0F, 0.2F, 0.045553093477052F, 0.27314402793711257F, 0.0F));
            partDefinition.addOrReplaceChild("ThighRight", CubeListBuilder.create().texOffs(40, 0).mirror(true).addBox(-1.0F, -1.0F, -1.0F, 2, 4, 2), PartPose.offsetAndRotation(-1.7F, 1.1F, 0.5F, 0.22759093446006054F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("BeardRight", CubeListBuilder.create().texOffs(22, 24).mirror(true).addBox(-1.5F, 0.0F, 0.0F, 3, 2, 2), PartPose.offset(-0.4F, 0.5F, -1.8F));
            partDefinition.addOrReplaceChild("LegLeft", CubeListBuilder.create().texOffs(36, 12).addBox(-0.5F, 0.0F, -1.0F, 1, 2, 1), PartPose.offsetAndRotation(-0.4F, 2.5F, 0.5F, -0.36425021489121656F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("WingLeft", CubeListBuilder.create().texOffs(25, 17).addBox(0.0F, 0.0F, 0.0F, 1, 3, 4), PartPose.offset(2.0F, -2.1F, -2.0F));
            partDefinition.addOrReplaceChild("FootRight", CubeListBuilder.create().texOffs(41, 13).addBox(-0.5F, -1.0F, -2.0F, 1, 1, 3), PartPose.offsetAndRotation(0.0F, 2.5F, 0.0F, 0.22759093446006054F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("BeakTop", CubeListBuilder.create().texOffs(13, 16).addBox(-0.5F, 0.0F, 0.0F, 1, 1, 2), PartPose.offsetAndRotation(0.0F, 0.0F, -2.4F, 0.36425021489121656F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("LegFeathersRight", CubeListBuilder.create().texOffs(33, 24).mirror(true).addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2), PartPose.offset(-0.3F, 0.2F, -0.4F));
            partDefinition.addOrReplaceChild("Chest", CubeListBuilder.create().texOffs(0, 10).addBox(-1.5F, -3.0F, 0.0F, 3, 3, 3), PartPose.offsetAndRotation(0.0F, 2.1F, -2.5F, 0.7740535232594852F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("Wattle", CubeListBuilder.create().texOffs(10, 19).addBox(-0.5F, 0.0F, 0.0F, 1, 2, 2), PartPose.offsetAndRotation(0.0F, 1.0F, -0.8F, -0.36425021489121656F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("BeakBase", CubeListBuilder.create().texOffs(0, 22).addBox(-1.0F, 0.0F, -1.0F, 2, 2, 1), PartPose.offsetAndRotation(0.0F, -0.8F, -1.5F, 0.27314402793711257F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("Toe1R", CubeListBuilder.create().texOffs(42, 14).mirror(true).addBox(-0.5F, -1.0F, -2.0F, 1, 1, 2), PartPose.offsetAndRotation(0.0F, 0.0F, 0.2F, 0.045553093477052F, -0.27314402793711257F, 0.0F));
            partDefinition.addOrReplaceChild("NeckFeathersLeft", CubeListBuilder.create().texOffs(0, 25).addBox(-1.0F, 0.0F, -3.0F, 2, 3, 3), PartPose.offsetAndRotation(0.4F, -4.1F, 3.0F, 0.31869712141416456F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, -3.0F, 0.0F, 2, 3, 3), PartPose.offsetAndRotation(0.0F, -3.0F, 0.2F, -0.5009094953223726F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("ThighLeft", CubeListBuilder.create().texOffs(32, 0).addBox(-1.0F, -1.0F, -1.0F, 2, 4, 2), PartPose.offsetAndRotation(1.7F, 1.1F, 0.5F, 0.22759093446006054F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("WingRight", CubeListBuilder.create().texOffs(35, 17).mirror(true).addBox(-1.0F, 0.0F, 0.0F, 1, 3, 4), PartPose.offset(-2.0F, -2.2F, -2.0F));
            partDefinition.addOrReplaceChild("BeardLeft", CubeListBuilder.create().texOffs(22, 24).addBox(-1.5F, 0.0F, 0.0F, 3, 2, 2), PartPose.offset(0.4F, 0.5F, -1.8F));
            partDefinition.addOrReplaceChild("Beak", CubeListBuilder.create().texOffs(7, 16).addBox(-0.5F, 0.0F, -2.0F, 1, 1, 2), PartPose.offsetAndRotation(0.0F, 1.0F, 0.4F, -0.31869712141416456F, 0.0F, 0.0F));

            return LayerDefinition.create(meshDef, 64, 32);
        }

        @Override
        public void setupAnim(HotChickenEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
            super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            this.Head.xRot = headPitch / (180F / (float) Math.PI) + -0.05F;
            this.Head.yRot = netHeadYaw / (180F / (float) Math.PI);
        }
    }

    public static class Chick extends HotChickenModel {
        public Chick(ModelPart root) {
            this.Body = root.getChild("Body");
            this.LegLeft = this.Body.getChild("LegLeft");
            this.LegRight = this.Body.getChild("LegRight");
            this.Neck = this.Body.getChild("Neck");
            this.Beak = this.Head.getChild("Beak");
            this.Tail = this.Body.getChild("Tail");
            this.Head = this.Neck.getChild("Head");
        }

        public static LayerDefinition createBodyLayer() {
            MeshDefinition meshDef = new MeshDefinition();
            PartDefinition partDefinition = meshDef.getRoot();

            partDefinition.addOrReplaceChild("LegLeft", CubeListBuilder.create().texOffs(9, 7).addBox(-0.5F, -0.8F, -1.0F, 1, 2, 1), PartPose.offsetAndRotation(0.9F, 2.0F, 1.0F, 0.091106186954104F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("LegRight", CubeListBuilder.create().texOffs(13, 7).addBox(-0.5F, -0.8F, -1.0F, 1, 2, 1), PartPose.offsetAndRotation(-0.9F, 2.0F, 1.0F, 0.091106186954104F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(14, 0).addBox(-1.0F, -2.0F, -0.6F, 2, 3, 2), PartPose.offsetAndRotation(0.0F, 0.0F, -1.5F, 0.6373942428283291F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("Beak", CubeListBuilder.create().texOffs(14, 5).addBox(-0.5F, 0.0F, -1.0F, 1, 1, 1), PartPose.offset(0.0F, 0.0F, -0.9F));
            partDefinition.addOrReplaceChild("Tail", CubeListBuilder.create().texOffs(0, 13).addBox(-1.5F, 0.0F, 0.0F, 3, 3, 2), PartPose.offsetAndRotation(0.0F, -1.7F, 1.5F, -0.6373942428283291F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 7).addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3), PartPose.offsetAndRotation(0.0F, -1.8F, 0.6F, -0.4553564018453205F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -2.0F, -1.5F, 4, 4, 3), PartPose.offsetAndRotation(0.0F, 20.7F, 0.0F, -0.091106186954104F, 0.0F, 0.0F));

            return LayerDefinition.create(meshDef, 32, 32);
        }

        @Override
        public void setupAnim(HotChickenEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
            this.Head.xRot = headPitch / (180F / (float) Math.PI) + -0.46F;
            this.Head.yRot = netHeadYaw / (180F / (float) Math.PI);

            if (limbSwingAmount <= 0.05F && !entity.isInWater()) {
                // idle anims here
            }

            // below is the current movement anims
            float speed = 3.0f;
            float degree = 1.4f;
            this.LegLeft.xRot = Mth.cos(5.0F + (limbSwing * speed * 0.2F) + (float) Math.PI) * (degree * -2.0F) * limbSwingAmount * 0.5F + -0.35F;
            this.LegRight.xRot = Mth.cos(5.0F + (limbSwing * speed * 0.2F) + (float) Math.PI) * (degree * 2.0F) * limbSwingAmount * 0.5F + -0.35F;
            this.Neck.xRot = Mth.cos(6.0F + (limbSwing * speed * 0.4F) + (float) Math.PI) * (degree * -0.2F) * limbSwingAmount * 0.5F + 0.64F;
        }
    }
}