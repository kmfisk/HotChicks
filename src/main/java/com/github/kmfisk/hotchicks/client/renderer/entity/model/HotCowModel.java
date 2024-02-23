package com.github.kmfisk.hotchicks.client.renderer.entity.model;

import com.github.kmfisk.hotchicks.entity.HotCowEntity;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelPart;
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
        public Adult() {
            this.texWidth = 128;
            this.texHeight = 96;
            this.NeckFlap = new ModelPart(this, 58, 57);
            this.NeckFlap.setPos(0.0F, 2.0F, 0.0F);
            this.NeckFlap.addBox(0.0F, 0.0F, 0.0F, 0.0F, 12.0F, 11.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(NeckFlap, -1.0016444470669013F, 0.0F, 0.0F);
            this.TopSnout = new ModelPart(this, 0, 12);
            this.TopSnout.setPos(0.0F, -3.0F, 0.5F);
            this.TopSnout.addBox(-2.0F, 0.0F, -6.0F, 4.0F, 5.0F, 6.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(TopSnout, 0.5462880425584197F, 0.0F, 0.0F);
            this.BrahmaEarLeft = new ModelPart(this, 23, 49);
            this.BrahmaEarLeft.setPos(5.0F, -4.0F, 1.0F);
            this.BrahmaEarLeft.addBox(-1.0F, 0.0F, 0.0F, 6.0F, 4.0F, 1.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(BrahmaEarLeft, 0.0F, -0.3642502295386026F, 0.500909508638178F);
            this.HighlandFurFace = new ModelPart(this, 24, 68);
            this.HighlandFurFace.setPos(0.0F, 5.5F, -4.0F);
            this.HighlandFurFace.addBox(-4.5F, 0.0F, 0.0F, 9.0F, 2.0F, 8.0F, 0.0F, 0.0F, 0.0F);
            this.HighlandFurBeard = new ModelPart(this, 108, 29);
            this.HighlandFurBeard.setPos(0.0F, 6.0F, -5.0F);
            this.HighlandFurBeard.addBox(-2.5F, 0.0F, 0.0F, 5.0F, 2.0F, 5.0F, 0.0F, 0.0F, 0.0F);
            this.Nipple2 = new ModelPart(this, 0, 0);
            this.Nipple2.setPos(-3.0F, 4.0F, 1.0F);
            this.Nipple2.addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
            this.CowEarLeft = new ModelPart(this, 16, 0);
            this.CowEarLeft.setPos(4.5F, -2.0F, 1.5F);
            this.CowEarLeft.addBox(0.0F, -1.5F, 0.0F, 5.0F, 3.0F, 1.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(CowEarLeft, 0.0F, -0.9105382388075086F, 0.0F);
            this.Snout = new ModelPart(this, 0, 0);
            this.Snout.setPos(0.0F, -1.0F, -3.5F);
            this.Snout.addBox(-2.5F, 0.0F, -6.0F, 5.0F, 6.0F, 6.0F, 0.0F, 0.0F, 0.0F);
            this.BrahmaEarRight = new ModelPart(this, 23, 49);
            this.BrahmaEarRight.mirror = true;
            this.BrahmaEarRight.setPos(-5.0F, -4.0F, 1.0F);
            this.BrahmaEarRight.addBox(-5.0F, 0.0F, 0.0F, 6.0F, 4.0F, 1.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(BrahmaEarRight, 0.0F, 0.3642502295386026F, -0.500909508638178F);
            this.Tail2 = new ModelPart(this, 22, 11);
            this.Tail2.setPos(0.0F, 7.0F, 0.0F);
            this.Tail2.addBox(-0.5F, 0.0F, 0.0F, 1.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(Tail2, -0.18203784630933073F, 0.0F, 0.0F);
            this.Nipple1 = new ModelPart(this, 0, 0);
            this.Nipple1.setPos(2.0F, 4.0F, 1.0F);
            this.Nipple1.addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
            this.NeckHump = new ModelPart(this, 80, 68);
            this.NeckHump.setPos(0.0F, -11.0F, 4.7F);
            this.NeckHump.addBox(-3.0F, 0.0F, -4.0F, 6.0F, 8.0F, 4.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(NeckHump, 0.3909537457888271F, 0.0F, 0.0F);
            this.HornForwardLeft1 = new ModelPart(this, 58, 53);
            this.HornForwardLeft1.setPos(3.6F, 2.5F, 1.0F);
            this.HornForwardLeft1.addBox(0.0F, 0.0F, 0.0F, 3.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(HornForwardLeft1, 1.8668041519541685F, 0.500909508638178F, 0.0F);
            this.ThighLeft = new ModelPart(this, 78, 0);
            this.ThighLeft.setPos(4.6F, -0.3F, 11.0F);
            this.ThighLeft.addBox(-2.5F, 0.0F, -4.0F, 5.0F, 10.0F, 8.0F, 0.0F, 0.0F, 0.0F);
            this.HornLonghornLeft3 = new ModelPart(this, 46, 78);
            this.HornLonghornLeft3.setPos(5.0F, -2.0F, 0.0F);
            this.HornLonghornLeft3.addBox(0.0F, 0.0F, -0.5F, 5.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(HornLonghornLeft3, 0.0F, 0.0F, 0.27314402127920984F);
            this.Udder = new ModelPart(this, 92, 18);
            this.Udder.setPos(0.0F, 9.0F, 6.0F);
            this.Udder.addBox(-4.5F, 0.0F, 0.0F, 9.0F, 4.0F, 7.0F, 0.0F, 0.0F, 0.0F);
            this.ArmBaseLeft = new ModelPart(this, 100, 68);
            this.ArmBaseLeft.setPos(4.0F, 2.9F, -12.0F);
            this.ArmBaseLeft.addBox(-2.5F, 0.0F, -3.0F, 5.0F, 6.0F, 6.0F, 0.0F, 0.0F, 0.0F);
            this.HighlandFurBody = new ModelPart(this, 66, 49);
            this.HighlandFurBody.setPos(0.0F, 9.0F, -8.5F);
            this.HighlandFurBody.addBox(-7.0F, 0.0F, 0.0F, 14.0F, 2.0F, 17.0F, 0.0F, 0.0F, 0.0F);
            this.HornLonghornRight2 = new ModelPart(this, 48, 86);
            this.HornLonghornRight2.mirror = true;
            this.HornLonghornRight2.setPos(-7.0F, 1.5F, 0.0F);
            this.HornLonghornRight2.addBox(-5.0F, -2.5F, -1.0F, 5.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(HornLonghornRight2, 0.0F, 0.0F, 0.3642502295386026F);
            this.ArmLeft = new ModelPart(this, 60, 0);
            this.ArmLeft.setPos(0.0F, 6.0F, 2.5F);
            this.ArmLeft.addBox(-2.0F, 0.0F, -5.0F, 4.0F, 5.0F, 5.0F, 0.0F, 0.0F, 0.0F);
            this.NeckLower = new ModelPart(this, 0, 72);
            this.NeckLower.setPos(0.0F, -12.0F, -4.5F);
            this.NeckLower.addBox(-2.5F, 0.0F, 0.0F, 5.0F, 12.0F, 7.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(NeckLower, -0.27314402127920984F, 0.0F, 0.0F);
            this.HornLonghornLeft1 = new ModelPart(this, 48, 80);
            this.HornLonghornLeft1.setPos(4.0F, 2.0F, 1.5F);
            this.HornLonghornLeft1.addBox(0.0F, -1.5F, -1.5F, 7.0F, 3.0F, 3.0F, 0.0F, 0.0F, 0.0F);
            this.ArmRight = new ModelPart(this, 60, 0);
            this.ArmRight.mirror = true;
            this.ArmRight.setPos(0.0F, 6.0F, 2.5F);
            this.ArmRight.addBox(-2.0F, 0.0F, -5.0F, 4.0F, 5.0F, 5.0F, 0.0F, 0.0F, 0.0F);
            this.HandLeft = new ModelPart(this, 60, 10);
            this.HandLeft.setPos(0.0F, 5.0F, -4.5F);
            this.HandLeft.addBox(-1.5F, 0.0F, 0.0F, 3.0F, 6.0F, 4.0F, 0.0F, 0.0F, 0.0F);
            this.UpperLegLeft = new ModelPart(this, 60, 20);
            this.UpperLegLeft.setPos(0.0F, 10.0F, -2.5F);
            this.UpperLegLeft.addBox(-2.0F, 0.0F, 0.0F, 4.0F, 5.0F, 5.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(UpperLegLeft, 0.500909508638178F, 0.0F, 0.0F);
            this.TailTuft = new ModelPart(this, 20, 16);
            this.TailTuft.setPos(0.0F, 5.0F, 0.0F);
            this.TailTuft.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, 0.0F, 0.0F);
            this.Nipple3 = new ModelPart(this, 0, 0);
            this.Nipple3.setPos(2.0F, 4.0F, 4.0F);
            this.Nipple3.addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
            this.Bell = new ModelPart(this, 92, 29);
            this.Bell.setPos(0.0F, 9.1F, 0.0F);
            this.Bell.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, 0.0F, 0.0F);
            this.Body = new ModelPart(this, 0, 0);
            this.Body.setPos(0.0F, 4.2F, 1.0F);
            this.Body.addBox(-7.0F, -8.0F, -16.0F, 14.0F, 17.0F, 32.0F, 0.0F, 0.0F, 0.0F);
            this.HornForwardLeft2_1 = new ModelPart(this, 0, 53);
            this.HornForwardLeft2_1.setPos(-3.0F, 2.0F, 0.0F);
            this.HornForwardLeft2_1.addBox(0.0F, -3.0F, 0.5F, 1.0F, 3.0F, 1.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(HornForwardLeft2_1, 0.0F, 0.0F, -0.2275909337942703F);
            this.UpperLegRight = new ModelPart(this, 60, 20);
            this.UpperLegRight.mirror = true;
            this.UpperLegRight.setPos(0.0F, 10.0F, -2.5F);
            this.UpperLegRight.addBox(-2.0F, 0.0F, 0.0F, 4.0F, 5.0F, 5.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(UpperLegRight, 0.500909508638178F, 0.0F, 0.0F);
            this.HandRight = new ModelPart(this, 60, 10);
            this.HandRight.mirror = true;
            this.HandRight.setPos(0.0F, 5.0F, -4.5F);
            this.HandRight.addBox(-1.5F, 0.0F, 0.0F, 3.0F, 6.0F, 4.0F, 0.0F, 0.0F, 0.0F);
            this.Head = new ModelPart(this, 32, 49);
            this.Head.setPos(0.0F, -10.0F, -0.7F);
            this.Head.addBox(-4.5F, -5.5F, -4.0F, 9.0F, 11.0F, 8.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(Head, -1.1383037594559906F, 0.0F, 0.0F);
            this.LowerLegLeft = new ModelPart(this, 78, 18);
            this.LowerLegLeft.setPos(0.0F, 5.0F, 4.5F);
            this.LowerLegLeft.addBox(-1.5F, 0.0F, -4.0F, 3.0F, 8.0F, 4.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(LowerLegLeft, -0.500909508638178F, 0.0F, 0.0F);
            this.HornForwardRight1 = new ModelPart(this, 58, 53);
            this.HornForwardRight1.mirror = true;
            this.HornForwardRight1.setPos(-3.6F, 2.5F, 1.0F);
            this.HornForwardRight1.addBox(-3.0F, 0.0F, 0.0F, 3.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(HornForwardRight1, 1.8668041519541685F, -0.500909508638178F, 0.0F);
            this.HornLonghornRight1 = new ModelPart(this, 48, 80);
            this.HornLonghornRight1.mirror = true;
            this.HornLonghornRight1.setPos(-4.0F, 2.0F, 1.5F);
            this.HornLonghornRight1.addBox(-7.0F, -1.5F, -1.5F, 7.0F, 3.0F, 3.0F, 0.0F, 0.0F, 0.0F);
            this.BellCollar = new ModelPart(this, 104, 3);
            this.BellCollar.setPos(0.0F, -5.0F, 0.0F);
            this.BellCollar.addBox(-4.0F, -5.0F, -0.5F, 8.0F, 14.0F, 1.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(BellCollar, -1.2747885016356248F, 0.0F, 0.0F);
            this.HornLonghornRight3 = new ModelPart(this, 46, 78);
            this.HornLonghornRight3.mirror = true;
            this.HornLonghornRight3.setPos(-5.0F, -2.0F, 0.0F);
            this.HornLonghornRight3.addBox(-5.0F, 0.0F, -0.5F, 5.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(HornLonghornRight3, 0.0F, 0.0F, -0.27314402127920984F);
            this.Tail1 = new ModelPart(this, 22, 4);
            this.Tail1.setPos(0.0F, -6.0F, 16.0F);
            this.Tail1.addBox(-0.5F, 0.0F, 0.0F, 1.0F, 7.0F, 0.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(Tail1, 0.18203784630933073F, 0.0F, 0.0F);
            this.LowerLegRight = new ModelPart(this, 78, 18);
            this.LowerLegRight.mirror = true;
            this.LowerLegRight.setPos(0.0F, 5.0F, 4.5F);
            this.LowerLegRight.addBox(-1.5F, 0.0F, -4.0F, 3.0F, 8.0F, 4.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(LowerLegRight, -0.500909508638178F, 0.0F, 0.0F);
            this.Nipple4 = new ModelPart(this, 0, 0);
            this.Nipple4.setPos(-3.0F, 4.0F, 4.0F);
            this.Nipple4.addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
            this.HornLonghornLeft2 = new ModelPart(this, 48, 86);
            this.HornLonghornLeft2.setPos(7.0F, 1.5F, 0.0F);
            this.HornLonghornLeft2.addBox(0.0F, -2.5F, -1.0F, 5.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(HornLonghornLeft2, 0.0F, 0.0F, -0.3642502295386026F);
            this.Neck = new ModelPart(this, 0, 49);
            this.Neck.setPos(0.0F, -2.5F, -13.0F);
            this.Neck.addBox(-3.5F, -8.4F, -4.5F, 7.0F, 14.0F, 9.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(Neck, 1.2747885016356248F, 0.0F, 0.0F);
            this.ArmBaseRight = new ModelPart(this, 100, 68);
            this.ArmBaseRight.mirror = true;
            this.ArmBaseRight.setPos(-4.0F, 2.9F, -12.0F);
            this.ArmBaseRight.addBox(-2.5F, 0.0F, -3.0F, 5.0F, 6.0F, 6.0F, 0.0F, 0.0F, 0.0F);
            this.HornUprightLeft1 = new ModelPart(this, 58, 49);
            this.HornUprightLeft1.setPos(4.0F, 0.5F, 0.5F);
            this.HornUprightLeft1.addBox(0.0F, 0.0F, 0.0F, 3.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(HornUprightLeft1, 0.0F, 0.0F, -0.2275909337942703F);
            this.Hump = new ModelPart(this, 14, 81);
            this.Hump.setPos(0.0F, -11.0F, -10.0F);
            this.Hump.addBox(-6.0F, 0.0F, -5.0F, 12.0F, 4.0F, 10.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(Hump, -0.18203784630933073F, 0.0F, 0.0F);
            this.HornUprightLeft2 = new ModelPart(this, 0, 49);
            this.HornUprightLeft2.setPos(3.0F, 2.0F, 0.0F);
            this.HornUprightLeft2.addBox(-1.0F, -3.0F, 0.5F, 1.0F, 3.0F, 1.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(HornUprightLeft2, 0.0F, 0.0F, 0.2275909337942703F);
            this.ThighRight = new ModelPart(this, 78, 0);
            this.ThighRight.mirror = true;
            this.ThighRight.setPos(-4.6F, -0.3F, 11.0F);
            this.ThighRight.addBox(-2.5F, 0.0F, -4.0F, 5.0F, 10.0F, 8.0F, 0.0F, 0.0F, 0.0F);
            this.HornUprightLeft2_1 = new ModelPart(this, 0, 49);
            this.HornUprightLeft2_1.setPos(-3.0F, 2.0F, 0.0F);
            this.HornUprightLeft2_1.addBox(0.0F, -3.0F, 0.5F, 1.0F, 3.0F, 1.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(HornUprightLeft2_1, 0.0F, 0.0F, -0.2275909337942703F);
            this.HornForwardLeft2 = new ModelPart(this, 0, 53);
            this.HornForwardLeft2.setPos(3.0F, 2.0F, 0.0F);
            this.HornForwardLeft2.addBox(-1.0F, -3.0F, 0.5F, 1.0F, 3.0F, 1.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(HornForwardLeft2, 0.0F, 0.0F, 0.2275909337942703F);
            this.BellFlare = new ModelPart(this, 92, 35);
            this.BellFlare.setPos(0.0F, 2.2F, 0.0F);
            this.BellFlare.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, 0.0F, 0.0F);
            this.HornUprightRight1 = new ModelPart(this, 58, 49);
            this.HornUprightRight1.mirror = true;
            this.HornUprightRight1.setPos(-4.0F, 0.5F, 0.5F);
            this.HornUprightRight1.addBox(-3.0F, 0.0F, 0.0F, 3.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(HornUprightRight1, 0.0F, 0.0F, 0.2275909337942703F);
            this.CowEarRight = new ModelPart(this, 16, 0);
            this.CowEarRight.mirror = true;
            this.CowEarRight.setPos(-4.5F, -2.0F, 1.5F);
            this.CowEarRight.addBox(-5.0F, -1.5F, 0.0F, 5.0F, 3.0F, 1.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(CowEarRight, 0.0F, 0.9105382388075086F, 0.0F);
            this.Forehead = new ModelPart(this, 0, 23);
            this.Forehead.setPos(0.0F, -7.0F, 0.0F);
            this.Forehead.addBox(-4.0F, 0.0F, -0.5F, 8.0F, 2.0F, 4.0F, 0.0F, 0.0F, 0.0F);
            this.NeckLower.addChild(this.NeckFlap);
            this.Snout.addChild(this.TopSnout);
            this.Head.addChild(this.BrahmaEarLeft);
            this.Head.addChild(this.HighlandFurFace);
            this.Snout.addChild(this.HighlandFurBeard);
            this.Udder.addChild(this.Nipple2);
            this.Head.addChild(this.CowEarLeft);
            this.Head.addChild(this.Snout);
            this.Head.addChild(this.BrahmaEarRight);
            this.Tail1.addChild(this.Tail2);
            this.Udder.addChild(this.Nipple1);
            this.Neck.addChild(this.NeckHump);
            this.Forehead.addChild(this.HornForwardLeft1);
            this.Body.addChild(this.ThighLeft);
            this.HornLonghornLeft2.addChild(this.HornLonghornLeft3);
            this.Body.addChild(this.Udder);
            this.Body.addChild(this.ArmBaseLeft);
            this.Body.addChild(this.HighlandFurBody);
            this.HornLonghornRight1.addChild(this.HornLonghornRight2);
            this.ArmBaseLeft.addChild(this.ArmLeft);
            this.Neck.addChild(this.NeckLower);
            this.Forehead.addChild(this.HornLonghornLeft1);
            this.ArmBaseRight.addChild(this.ArmRight);
            this.ArmLeft.addChild(this.HandLeft);
            this.ThighLeft.addChild(this.UpperLegLeft);
            this.Tail2.addChild(this.TailTuft);
            this.Udder.addChild(this.Nipple3);
            this.BellCollar.addChild(this.Bell);
            this.HornForwardRight1.addChild(this.HornForwardLeft2_1);
            this.ThighRight.addChild(this.UpperLegRight);
            this.ArmRight.addChild(this.HandRight);
            this.Neck.addChild(this.Head);
            this.UpperLegLeft.addChild(this.LowerLegLeft);
            this.Forehead.addChild(this.HornForwardRight1);
            this.Forehead.addChild(this.HornLonghornRight1);
            this.Neck.addChild(this.BellCollar);
            this.HornLonghornRight2.addChild(this.HornLonghornRight3);
            this.Body.addChild(this.Tail1);
            this.UpperLegRight.addChild(this.LowerLegRight);
            this.Udder.addChild(this.Nipple4);
            this.HornLonghornLeft1.addChild(this.HornLonghornLeft2);
            this.Body.addChild(this.Neck);
            this.Body.addChild(this.ArmBaseRight);
            this.Forehead.addChild(this.HornUprightLeft1);
            this.Body.addChild(this.Hump);
            this.HornUprightLeft1.addChild(this.HornUprightLeft2);
            this.Body.addChild(this.ThighRight);
            this.HornUprightRight1.addChild(this.HornUprightLeft2_1);
            this.HornForwardLeft1.addChild(this.HornForwardLeft2);
            this.Bell.addChild(this.BellFlare);
            this.Forehead.addChild(this.HornUprightRight1);
            this.Head.addChild(this.CowEarRight);
            this.Head.addChild(this.Forehead);
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
        public Calf() {
            super();
        }

        @Override
        public void setupAnim(HotCowEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
            super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            this.Head.xRot = headPitch / (180F / (float) Math.PI) - 1.14F;
            this.Neck.yRot = netHeadYaw / (180F / (float)Math.PI);

            // babies use the adult stuff already
        }
    }

    public void setRotateAngle(ModelPart modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
