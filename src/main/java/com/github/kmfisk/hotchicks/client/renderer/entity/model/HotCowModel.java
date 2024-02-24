package com.github.kmfisk.hotchicks.client.renderer.entity.model;

import com.github.kmfisk.hotchicks.entity.HotCowEntity;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class HotCowModel extends ListModel<HotCowEntity> {
    public ModelPart Body;
    public ModelPart Neck;
    public ModelPart Tail1;
    public ModelPart ThighLeft;
    public ModelPart ThighRight;
    public ModelPart Udder;
    public ModelPart HighlandFurBody;
    public ModelPart Hump;
    public ModelPart ArmBaseLeft;
    public ModelPart ArmBaseRight;
    public ModelPart Head;
    public ModelPart NeckLower;
    public ModelPart BellCollar;
    public ModelPart NeckHump;
    public ModelPart Snout;
    public ModelPart Forehead;
    public ModelPart CowEarLeft;
    public ModelPart CowEarRight;
    public ModelPart BrahmaEarLeft;
    public ModelPart BrahmaEarRight;
    public ModelPart HighlandFurFace;
    public ModelPart TopSnout;
    public ModelPart HighlandFurBeard;
    public ModelPart HornUprightLeft1;
    public ModelPart HornUprightRight1;
    public ModelPart HornForwardLeft1;
    public ModelPart HornForwardRight1;
    public ModelPart HornLonghornLeft1;
    public ModelPart HornLonghornRight1;
    public ModelPart HornUprightLeft2;
    public ModelPart HornUprightLeft2_1;
    public ModelPart HornForwardLeft2;
    public ModelPart HornForwardLeft2_1;
    public ModelPart HornLonghornLeft2;
    public ModelPart HornLonghornLeft3;
    public ModelPart HornLonghornRight2;
    public ModelPart HornLonghornRight3;
    public ModelPart NeckFlap;
    public ModelPart Bell;
    public ModelPart BellFlare;
    public ModelPart Tail2;
    public ModelPart TailTuft;
    public ModelPart UpperLegLeft;
    public ModelPart LowerLegLeft;
    public ModelPart UpperLegRight;
    public ModelPart LowerLegRight;
    public ModelPart Nipple1;
    public ModelPart Nipple2;
    public ModelPart Nipple3;
    public ModelPart Nipple4;
    public ModelPart ArmLeft;
    public ModelPart HandLeft;
    public ModelPart ArmRight;
    public ModelPart HandRight;
    private Iterable<ModelPart> parts;

    @Override
    public Iterable<ModelPart> parts() {
        if (parts == null)
            parts = ImmutableList.of(Body);

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

    public static class Adult extends HotCowModel {
        public Adult(ModelPart root) {
            this.Body = root.getChild("Body");
            this.NeckFlap = this.NeckLower.getChild("NeckFlap");
            this.TopSnout = this.Snout.getChild("TopSnout");
            this.BrahmaEarLeft = this.Head.getChild("BrahmaEarLeft");
            this.HighlandFurFace = this.Head.getChild("HighlandFurFace");
            this.HighlandFurBeard = this.Snout.getChild("HighlandFurBeard");
            this.Nipple2 = this.Udder.getChild("Nipple2");
            this.CowEarLeft = this.Head.getChild("CowEarLeft");
            this.Snout = this.Head.getChild("Snout");
            this.BrahmaEarRight = this.Head.getChild("BrahmaEarRight");
            this.Tail2 = this.Tail1.getChild("Tail2");
            this.Nipple1 = this.Udder.getChild("Nipple1");
            this.NeckHump = this.Neck.getChild("NeckHump");
            this.HornForwardLeft1 = this.Forehead.getChild("HornForwardLeft1");
            this.ThighLeft = this.Body.getChild("ThighLeft");
            this.HornLonghornLeft3 = this.HornLonghornLeft2.getChild("HornLonghornLeft3");
            this.Udder = this.Body.getChild("Udder");
            this.ArmBaseLeft = this.Body.getChild("ArmBaseLeft");
            this.HighlandFurBody = this.Body.getChild("HighlandFurBody");
            this.HornLonghornRight2 = this.HornLonghornRight1.getChild("HornLonghornRight2");
            this.ArmLeft = this.ArmBaseLeft.getChild("ArmLeft");
            this.NeckLower = this.Neck.getChild("NeckLower");
            this.HornLonghornLeft1 = this.Forehead.getChild("HornLonghornLeft1");
            this.ArmRight = this.ArmBaseRight.getChild("ArmRight");
            this.HandLeft = this.ArmLeft.getChild("HandLeft");
            this.UpperLegLeft = this.ThighLeft.getChild("UpperLegLeft");
            this.TailTuft = this.Tail2.getChild("TailTuft");
            this.Nipple3 = this.Udder.getChild("Nipple3");
            this.Bell = this.BellCollar.getChild("Bell");
            this.HornForwardLeft2_1 = this.HornForwardRight1.getChild("HornForwardLeft2_1");
            this.UpperLegRight = this.ThighRight.getChild("UpperLegRight");
            this.HandRight = this.ArmRight.getChild("HandRight");
            this.Head = this.Neck.getChild("Head");
            this.LowerLegLeft = this.UpperLegLeft.getChild("LowerLegLeft");
            this.HornForwardRight1 = this.Forehead.getChild("HornForwardRight1");
            this.HornLonghornRight1 = this.Forehead.getChild("HornLonghornRight1");
            this.BellCollar = this.Neck.getChild("BellCollar");
            this.HornLonghornRight3 = this.HornLonghornRight2.getChild("HornLonghornRight3");
            this.Tail1 = this.Body.getChild("Tail1");
            this.LowerLegRight = this.UpperLegRight.getChild("LowerLegRight");
            this.Nipple4 = this.Udder.getChild("Nipple4");
            this.HornLonghornLeft2 = this.HornLonghornLeft1.getChild("HornLonghornLeft2");
            this.Neck = this.Body.getChild("Neck");
            this.ArmBaseRight = this.Body.getChild("ArmBaseRight");
            this.HornUprightLeft1 = this.Forehead.getChild("HornUprightLeft1");
            this.Hump = this.Body.getChild("Hump");
            this.HornUprightLeft2 = this.HornUprightLeft1.getChild("HornUprightLeft2");
            this.ThighRight = this.Body.getChild("ThighRight");
            this.HornUprightLeft2_1 = this.HornUprightRight1.getChild("HornUprightLeft2_1");
            this.HornForwardLeft2 = this.HornForwardLeft1.getChild("HornForwardLeft2");
            this.BellFlare = this.Bell.getChild("BellFlare");
            this.HornUprightRight1 = this.Forehead.getChild("HornUprightRight1");
            this.CowEarRight = this.Head.getChild("CowEarRight");
            this.Forehead = this.Head.getChild("Forehead");
        }

        public static LayerDefinition createBodyLayer() {
            MeshDefinition meshDef = new MeshDefinition();
            PartDefinition partDefinition = meshDef.getRoot();

            partDefinition.addOrReplaceChild("NeckFlap", CubeListBuilder.create().texOffs(58, 57).addBox(0.0F, 0.0F, 0.0F, 0.0F, 12.0F, 11.0F), PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, -1.0016444470669013F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("TopSnout", CubeListBuilder.create().texOffs(0, 12).addBox(-2.0F, 0.0F, -6.0F, 4.0F, 5.0F, 6.0F), PartPose.offsetAndRotation(0.0F, -3.0F, 0.5F, 0.5462880425584197F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("BrahmaEarLeft", CubeListBuilder.create().texOffs(23, 49).addBox(-1.0F, 0.0F, 0.0F, 6.0F, 4.0F, 1.0F), PartPose.offsetAndRotation(5.0F, -4.0F, 1.0F, 0.0F, -0.3642502295386026F, 0.500909508638178F));
            partDefinition.addOrReplaceChild("HighlandFurFace", CubeListBuilder.create().texOffs(24, 68).addBox(-4.5F, 0.0F, 0.0F, 9.0F, 2.0F, 8.0F), PartPose.offset(0.0F, 5.5F, -4.0F));
            partDefinition.addOrReplaceChild("HighlandFurBeard", CubeListBuilder.create().texOffs(108, 29).addBox(-2.5F, 0.0F, 0.0F, 5.0F, 2.0F, 5.0F), PartPose.offset(0.0F, 6.0F, -5.0F));
            partDefinition.addOrReplaceChild("Nipple2", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F), PartPose.offset(-3.0F, 4.0F, 1.0F));
            partDefinition.addOrReplaceChild("CowEarLeft", CubeListBuilder.create().texOffs(16, 0).addBox(0.0F, -1.5F, 0.0F, 5.0F, 3.0F, 1.0F), PartPose.offsetAndRotation(4.5F, -2.0F, 1.5F, 0.0F, -0.9105382388075086F, 0.0F));
            partDefinition.addOrReplaceChild("Snout", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, 0.0F, -6.0F, 5.0F, 6.0F, 6.0F), PartPose.offset(0.0F, -1.0F, -3.5F));
            partDefinition.addOrReplaceChild("BrahmaEarRight", CubeListBuilder.create().texOffs(23, 49).mirror(true).addBox(-5.0F, 0.0F, 0.0F, 6.0F, 4.0F, 1.0F), PartPose.offsetAndRotation(-5.0F, -4.0F, 1.0F, 0.0F, 0.3642502295386026F, -0.500909508638178F));
            partDefinition.addOrReplaceChild("Tail2", CubeListBuilder.create().texOffs(22, 11).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 5.0F, 0.0F), PartPose.offsetAndRotation(0.0F, 7.0F, 0.0F, -0.18203784630933073F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("Nipple1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F), PartPose.offset(2.0F, 4.0F, 1.0F));
            partDefinition.addOrReplaceChild("NeckHump", CubeListBuilder.create().texOffs(80, 68).addBox(-3.0F, 0.0F, -4.0F, 6.0F, 8.0F, 4.0F), PartPose.offsetAndRotation(0.0F, -11.0F, 4.7F, 0.3909537457888271F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("HornForwardLeft1", CubeListBuilder.create().texOffs(58, 53).addBox(0.0F, 0.0F, 0.0F, 3.0F, 2.0F, 2.0F), PartPose.offsetAndRotation(3.6F, 2.5F, 1.0F, 1.8668041519541685F, 0.500909508638178F, 0.0F));
            partDefinition.addOrReplaceChild("ThighLeft", CubeListBuilder.create().texOffs(78, 0).addBox(-2.5F, 0.0F, -4.0F, 5.0F, 10.0F, 8.0F), PartPose.offset(4.6F, -0.3F, 11.0F));
            partDefinition.addOrReplaceChild("HornLonghornLeft3", CubeListBuilder.create().texOffs(46, 78).addBox(0.0F, 0.0F, -0.5F, 5.0F, 1.0F, 1.0F), PartPose.offsetAndRotation(5.0F, -2.0F, 0.0F, 0.0F, 0.0F, 0.27314402127920984F));
            partDefinition.addOrReplaceChild("Udder", CubeListBuilder.create().texOffs(92, 18).addBox(-4.5F, 0.0F, 0.0F, 9.0F, 4.0F, 7.0F), PartPose.offset(0.0F, 9.0F, 6.0F));
            partDefinition.addOrReplaceChild("ArmBaseLeft", CubeListBuilder.create().texOffs(100, 68).addBox(-2.5F, 0.0F, -3.0F, 5.0F, 6.0F, 6.0F), PartPose.offset(4.0F, 2.9F, -12.0F));
            partDefinition.addOrReplaceChild("HighlandFurBody", CubeListBuilder.create().texOffs(66, 49).addBox(-7.0F, 0.0F, 0.0F, 14.0F, 2.0F, 17.0F), PartPose.offset(0.0F, 9.0F, -8.5F));
            partDefinition.addOrReplaceChild("HornLonghornRight2", CubeListBuilder.create().texOffs(48, 86).mirror(true).addBox(-5.0F, -2.5F, -1.0F, 5.0F, 2.0F, 2.0F), PartPose.offsetAndRotation(-7.0F, 1.5F, 0.0F, 0.0F, 0.0F, 0.3642502295386026F));
            partDefinition.addOrReplaceChild("ArmLeft", CubeListBuilder.create().texOffs(60, 0).addBox(-2.0F, 0.0F, -5.0F, 4.0F, 5.0F, 5.0F), PartPose.offset(0.0F, 6.0F, 2.5F));
            partDefinition.addOrReplaceChild("NeckLower", CubeListBuilder.create().texOffs(0, 72).addBox(-2.5F, 0.0F, 0.0F, 5.0F, 12.0F, 7.0F), PartPose.offsetAndRotation(0.0F, -12.0F, -4.5F, -0.27314402127920984F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("HornLonghornLeft1", CubeListBuilder.create().texOffs(48, 80).addBox(0.0F, -1.5F, -1.5F, 7.0F, 3.0F, 3.0F), PartPose.offset(4.0F, 2.0F, 1.5F));
            partDefinition.addOrReplaceChild("ArmRight", CubeListBuilder.create().texOffs(60, 0).mirror(true).addBox(-2.0F, 0.0F, -5.0F, 4.0F, 5.0F, 5.0F), PartPose.offset(0.0F, 6.0F, 2.5F));
            partDefinition.addOrReplaceChild("HandLeft", CubeListBuilder.create().texOffs(60, 10).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 6.0F, 4.0F), PartPose.offset(0.0F, 5.0F, -4.5F));
            partDefinition.addOrReplaceChild("UpperLegLeft", CubeListBuilder.create().texOffs(60, 20).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 5.0F, 5.0F), PartPose.offsetAndRotation(0.0F, 10.0F, -2.5F, 0.500909508638178F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("TailTuft", CubeListBuilder.create().texOffs(20, 16).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F), PartPose.offset(0.0F, 5.0F, 0.0F));
            partDefinition.addOrReplaceChild("Nipple3", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F), PartPose.offset(2.0F, 4.0F, 4.0F));
            partDefinition.addOrReplaceChild("Bell", CubeListBuilder.create().texOffs(92, 29).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 3.0F, 3.0F), PartPose.offset(0.0F, 9.1F, 0.0F));
            partDefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -8.0F, -16.0F, 14.0F, 17.0F, 32.0F), PartPose.offset(0.0F, 4.2F, 1.0F));
            partDefinition.addOrReplaceChild("HornForwardLeft2_1", CubeListBuilder.create().texOffs(0, 53).addBox(0.0F, -3.0F, 0.5F, 1.0F, 3.0F, 1.0F), PartPose.offsetAndRotation(-3.0F, 2.0F, 0.0F, 0.0F, 0.0F, -0.2275909337942703F));
            partDefinition.addOrReplaceChild("UpperLegRight", CubeListBuilder.create().texOffs(60, 20).mirror(true).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 5.0F, 5.0F), PartPose.offsetAndRotation(0.0F, 10.0F, -2.5F, 0.500909508638178F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("HandRight", CubeListBuilder.create().texOffs(60, 10).mirror(true).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 6.0F, 4.0F), PartPose.offset(0.0F, 5.0F, -4.5F));
            partDefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(32, 49).addBox(-4.5F, -5.5F, -4.0F, 9.0F, 11.0F, 8.0F), PartPose.offsetAndRotation(0.0F, -10.0F, -0.7F, -1.1383037594559906F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("LowerLegLeft", CubeListBuilder.create().texOffs(78, 18).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 8.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 5.0F, 4.5F, -0.500909508638178F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("HornForwardRight1", CubeListBuilder.create().texOffs(58, 53).mirror(true).addBox(-3.0F, 0.0F, 0.0F, 3.0F, 2.0F, 2.0F), PartPose.offsetAndRotation(-3.6F, 2.5F, 1.0F, 1.8668041519541685F, -0.500909508638178F, 0.0F));
            partDefinition.addOrReplaceChild("HornLonghornRight1", CubeListBuilder.create().texOffs(48, 80).mirror(true).addBox(-7.0F, -1.5F, -1.5F, 7.0F, 3.0F, 3.0F), PartPose.offset(-4.0F, 2.0F, 1.5F));
            partDefinition.addOrReplaceChild("BellCollar", CubeListBuilder.create().texOffs(104, 3).addBox(-4.0F, -5.0F, -0.5F, 8.0F, 14.0F, 1.0F), PartPose.offsetAndRotation(0.0F, -5.0F, 0.0F, -1.2747885016356248F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("HornLonghornRight3", CubeListBuilder.create().texOffs(46, 78).mirror(true).addBox(-5.0F, 0.0F, -0.5F, 5.0F, 1.0F, 1.0F), PartPose.offsetAndRotation(-5.0F, -2.0F, 0.0F, 0.0F, 0.0F, -0.27314402127920984F));
            partDefinition.addOrReplaceChild("Tail1", CubeListBuilder.create().texOffs(22, 4).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 7.0F, 0.0F), PartPose.offsetAndRotation(0.0F, -6.0F, 16.0F, 0.18203784630933073F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("LowerLegRight", CubeListBuilder.create().texOffs(78, 18).mirror(true).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 8.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 5.0F, 4.5F, -0.500909508638178F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("Nipple4", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F), PartPose.offset(-3.0F, 4.0F, 4.0F));
            partDefinition.addOrReplaceChild("HornLonghornLeft2", CubeListBuilder.create().texOffs(48, 86).addBox(0.0F, -2.5F, -1.0F, 5.0F, 2.0F, 2.0F), PartPose.offsetAndRotation(7.0F, 1.5F, 0.0F, 0.0F, 0.0F, -0.3642502295386026F));
            partDefinition.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(0, 49).addBox(-3.5F, -8.4F, -4.5F, 7.0F, 14.0F, 9.0F), PartPose.offsetAndRotation(0.0F, -2.5F, -13.0F, 1.2747885016356248F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("ArmBaseRight", CubeListBuilder.create().texOffs(100, 68).mirror(true).addBox(-2.5F, 0.0F, -3.0F, 5.0F, 6.0F, 6.0F), PartPose.offset(-4.0F, 2.9F, -12.0F));
            partDefinition.addOrReplaceChild("HornUprightLeft1", CubeListBuilder.create().texOffs(58, 49).addBox(0.0F, 0.0F, 0.0F, 3.0F, 2.0F, 2.0F), PartPose.offsetAndRotation(4.0F, 0.5F, 0.5F, 0.0F, 0.0F, -0.2275909337942703F));
            partDefinition.addOrReplaceChild("Hump", CubeListBuilder.create().texOffs(14, 81).addBox(-6.0F, 0.0F, -5.0F, 12.0F, 4.0F, 10.0F), PartPose.offsetAndRotation(0.0F, -11.0F, -10.0F, -0.18203784630933073F, 0.0F, 0.0F));
            partDefinition.addOrReplaceChild("HornUprightLeft2", CubeListBuilder.create().texOffs(0, 49).addBox(-1.0F, -3.0F, 0.5F, 1.0F, 3.0F, 1.0F), PartPose.offsetAndRotation(3.0F, 2.0F, 0.0F, 0.0F, 0.0F, 0.2275909337942703F));
            partDefinition.addOrReplaceChild("ThighRight", CubeListBuilder.create().texOffs(78, 0).mirror(true).addBox(-2.5F, 0.0F, -4.0F, 5.0F, 10.0F, 8.0F), PartPose.offset(-4.6F, -0.3F, 11.0F));
            partDefinition.addOrReplaceChild("HornUprightLeft2_1", CubeListBuilder.create().texOffs(0, 49).addBox(0.0F, -3.0F, 0.5F, 1.0F, 3.0F, 1.0F), PartPose.offsetAndRotation(-3.0F, 2.0F, 0.0F, 0.0F, 0.0F, -0.2275909337942703F));
            partDefinition.addOrReplaceChild("HornForwardLeft2", CubeListBuilder.create().texOffs(0, 53).addBox(-1.0F, -3.0F, 0.5F, 1.0F, 3.0F, 1.0F), PartPose.offsetAndRotation(3.0F, 2.0F, 0.0F, 0.0F, 0.0F, 0.2275909337942703F));
            partDefinition.addOrReplaceChild("BellFlare", CubeListBuilder.create().texOffs(92, 35).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 1.0F, 4.0F), PartPose.offset(0.0F, 2.2F, 0.0F));
            partDefinition.addOrReplaceChild("HornUprightRight1", CubeListBuilder.create().texOffs(58, 49).mirror(true).addBox(-3.0F, 0.0F, 0.0F, 3.0F, 2.0F, 2.0F), PartPose.offsetAndRotation(-4.0F, 0.5F, 0.5F, 0.0F, 0.0F, 0.2275909337942703F));
            partDefinition.addOrReplaceChild("CowEarRight", CubeListBuilder.create().texOffs(16, 0).mirror(true).addBox(-5.0F, -1.5F, 0.0F, 5.0F, 3.0F, 1.0F), PartPose.offsetAndRotation(-4.5F, -2.0F, 1.5F, 0.0F, 0.9105382388075086F, 0.0F));
            partDefinition.addOrReplaceChild("Forehead", CubeListBuilder.create().texOffs(0, 23).addBox(-4.0F, 0.0F, -0.5F, 8.0F, 2.0F, 4.0F), PartPose.offset(0.0F, -7.0F, 0.0F));

            return LayerDefinition.create(meshDef, 128, 96);
        }

        @Override
        public void setupAnim(HotCowEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
            this.Neck.yRot = netHeadYaw / (180F / (float) Math.PI) * 0.5F;
            this.Head.xRot = headPitch / (180F / (float) Math.PI) - 1.14F;
            this.Head.yRot = netHeadYaw / (180F / (float) Math.PI) * 0.25F;

            if (limbSwingAmount <= 0.05F && !entity.isInWater()) {
                // idle anims here
            }

            // below is the current movement anim
            float speed = 1.5F;
            this.ArmBaseLeft.xRot = limbSwingAmount * 1.5f * Mth.cos(limbSwing * speed * 0.2F);
            this.ArmLeft.xRot = 0f;//limbSwingAmount * -0.5f * MathHelper.cos(limbSwing * speed * 0.2F);
            this.HandLeft.xRot = 0f;//limbSwingAmount * -1.5f * MathHelper.cos(0.5F + limbSwing * speed * 0.2F);
            this.ArmBaseRight.xRot = limbSwingAmount * -1.5f * Mth.cos(limbSwing * speed * 0.2F);
            this.ArmRight.xRot = 0f;//limbSwingAmount * 0.5f * MathHelper.cos(limbSwing * speed * 0.2F);
            this.HandRight.xRot = 0f;//limbSwingAmount * 1.5f * MathHelper.cos(1.5f + limbSwing * speed * 0.2F);
            this.ThighLeft.xRot = limbSwingAmount * 1.0f * Mth.cos(2.0F + limbSwing * speed * 0.2F);
            this.UpperLegLeft.xRot = limbSwingAmount * 1.0f * Mth.cos(limbSwing * speed * 0.2F) + 0.5F;
            this.LowerLegLeft.xRot = limbSwingAmount * -0.5f * Mth.cos(limbSwing * speed * 0.2F) - 0.5F;
            this.ThighRight.xRot = limbSwingAmount * -1.0f * Mth.cos(2.0F + limbSwing * speed * 0.2F);
            this.UpperLegRight.xRot = limbSwingAmount * -1.0f * Mth.cos(limbSwing * speed * 0.2F) + 0.5F;
            this.LowerLegRight.xRot = limbSwingAmount * 0.5f * Mth.cos(limbSwing * speed * 0.2F) - 0.5F;
        }
    }

    public static class Calf extends Adult {
        public Calf(ModelPart root) {
            super(root);
        }

        @Override
        public void setupAnim(HotCowEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
            super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            this.Head.xRot = headPitch / (180F / (float) Math.PI) - 1.14F;
            this.Neck.yRot = netHeadYaw / (180F / (float) Math.PI);

            // babies use the adult stuff already
        }
    }
}
