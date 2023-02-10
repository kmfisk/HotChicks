package com.github.kmfisk.hotchicks.client.renderer.entity.model;

import com.github.kmfisk.hotchicks.entity.HotCowEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HotCowModel extends SegmentedModel<HotCowEntity> {
    public ModelRenderer Body;
    public ModelRenderer Neck;
    public ModelRenderer Tail1;
    public ModelRenderer ThighLeft;
    public ModelRenderer ThighRight;
    public ModelRenderer Udder;
    public ModelRenderer HighlandFurBody;
    public ModelRenderer Hump;
    public ModelRenderer ArmBaseLeft;
    public ModelRenderer ArmBaseRight;
    public ModelRenderer Head;
    public ModelRenderer NeckLower;
    public ModelRenderer BellCollar;
    public ModelRenderer NeckHump;
    public ModelRenderer Snout;
    public ModelRenderer Forehead;
    public ModelRenderer CowEarLeft;
    public ModelRenderer CowEarRight;
    public ModelRenderer BrahmaEarLeft;
    public ModelRenderer BrahmaEarRight;
    public ModelRenderer HighlandFurFace;
    public ModelRenderer TopSnout;
    public ModelRenderer HighlandFurBeard;
    public ModelRenderer HornUprightLeft1;
    public ModelRenderer HornUprightRight1;
    public ModelRenderer HornForwardLeft1;
    public ModelRenderer HornForwardRight1;
    public ModelRenderer HornLonghornLeft1;
    public ModelRenderer HornLonghornRight1;
    public ModelRenderer HornUprightLeft2;
    public ModelRenderer HornUprightLeft2_1;
    public ModelRenderer HornForwardLeft2;
    public ModelRenderer HornForwardLeft2_1;
    public ModelRenderer HornLonghornLeft2;
    public ModelRenderer HornLonghornLeft3;
    public ModelRenderer HornLonghornRight2;
    public ModelRenderer HornLonghornRight3;
    public ModelRenderer NeckFlap;
    public ModelRenderer Bell;
    public ModelRenderer BellFlare;
    public ModelRenderer Tail2;
    public ModelRenderer TailTuft;
    public ModelRenderer UpperLegtLeft;
    public ModelRenderer LowerLegLeft;
    public ModelRenderer UpperLegRight;
    public ModelRenderer LowerLegRight;
    public ModelRenderer Nipple1;
    public ModelRenderer Nipple2;
    public ModelRenderer Nipple3;
    public ModelRenderer Nipple4;
    public ModelRenderer ArmLeft;
    public ModelRenderer HandLeft;
    public ModelRenderer ArmRight;
    public ModelRenderer HandRight;
    private Iterable<ModelRenderer> parts;

    @Override
    public Iterable<ModelRenderer> parts() {
        if (parts == null)
            parts = ImmutableList.of(Body);

        return parts;
    }

    @Override
    public void setupAnim(HotCowEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {

    }

    @Override
    public void prepareMobModel(HotCowEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTick) {
        super.prepareMobModel(pEntity, pLimbSwing, pLimbSwingAmount, pPartialTick);
    }

    public static class Adult extends HotCowModel {
        public Adult() {
            this.texWidth = 128;
            this.texHeight = 96;
            this.NeckLower = new ModelRenderer(this, 0, 72);
            this.NeckLower.setPos(0.0F, -12.0F, -4.5F);
            this.NeckLower.addBox(-2.5F, 0.0F, 0.0F, 5.0F, 12.0F, 7.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(NeckLower, -0.27314402127920984F, 0.0F, 0.0F);
            this.HornLonghornLeft2 = new ModelRenderer(this, 48, 86);
            this.HornLonghornLeft2.setPos(7.0F, 1.5F, 0.0F);
            this.HornLonghornLeft2.addBox(0.0F, -2.5F, -1.0F, 5.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(HornLonghornLeft2, 0.0F, 0.0F, -0.3642502295386026F);
            this.Forehead = new ModelRenderer(this, 0, 23);
            this.Forehead.setPos(0.0F, -7.0F, 0.0F);
            this.Forehead.addBox(-4.0F, 0.0F, -0.5F, 8.0F, 2.0F, 4.0F, 0.0F, 0.0F, 0.0F);
            this.Tail2 = new ModelRenderer(this, 22, 11);
            this.Tail2.setPos(0.0F, 7.0F, 0.0F);
            this.Tail2.addBox(-0.5F, 0.0F, 0.0F, 1.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(Tail2, -0.18203784630933073F, 0.0F, 0.0F);
            this.Head = new ModelRenderer(this, 32, 49);
            this.Head.setPos(0.0F, -10.0F, -0.7F);
            this.Head.addBox(-4.5F, -5.5F, -4.0F, 9.0F, 11.0F, 8.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(Head, -1.1383037594559906F, 0.0F, 0.0F);
            this.LowerLegRight = new ModelRenderer(this, 78, 18);
            this.LowerLegRight.mirror = true;
            this.LowerLegRight.setPos(0.0F, 5.0F, 4.5F);
            this.LowerLegRight.addBox(-1.5F, 0.0F, -4.0F, 3.0F, 8.0F, 4.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(LowerLegRight, -0.500909508638178F, 0.0F, 0.0F);
            this.HandLeft = new ModelRenderer(this, 60, 10);
            this.HandLeft.setPos(0.0F, 5.0F, -4.5F);
            this.HandLeft.addBox(-1.5F, 0.0F, 0.0F, 3.0F, 6.0F, 4.0F, 0.0F, 0.0F, 0.0F);
            this.CowEarLeft = new ModelRenderer(this, 16, 0);
            this.CowEarLeft.setPos(4.5F, -2.0F, 1.5F);
            this.CowEarLeft.addBox(0.0F, -1.5F, 0.0F, 5.0F, 3.0F, 1.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(CowEarLeft, 0.0F, -0.9105382388075086F, 0.0F);
            this.HighlandFurBody = new ModelRenderer(this, 66, 49);
            this.HighlandFurBody.setPos(0.0F, 9.0F, -8.5F);
            this.HighlandFurBody.addBox(-7.0F, 0.0F, 0.0F, 14.0F, 2.0F, 17.0F, 0.0F, 0.0F, 0.0F);
            this.HornLonghornRight3 = new ModelRenderer(this, 46, 78);
            this.HornLonghornRight3.mirror = true;
            this.HornLonghornRight3.setPos(-5.0F, -2.0F, 0.0F);
            this.HornLonghornRight3.addBox(-5.0F, 0.0F, -0.5F, 5.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(HornLonghornRight3, 0.0F, 0.0F, -0.27314402127920984F);
            this.LowerLegLeft = new ModelRenderer(this, 78, 18);
            this.LowerLegLeft.setPos(0.0F, 5.0F, 4.5F);
            this.LowerLegLeft.addBox(-1.5F, 0.0F, -4.0F, 3.0F, 8.0F, 4.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(LowerLegLeft, -0.500909508638178F, 0.0F, 0.0F);
            this.Snout = new ModelRenderer(this, 0, 0);
            this.Snout.setPos(0.0F, -1.0F, -3.5F);
            this.Snout.addBox(-2.5F, 0.0F, -6.0F, 5.0F, 6.0F, 6.0F, 0.0F, 0.0F, 0.0F);
            this.NeckFlap = new ModelRenderer(this, 58, 57);
            this.NeckFlap.setPos(0.0F, 2.0F, 0.0F);
            this.NeckFlap.addBox(0.0F, 0.0F, 0.0F, 0.0F, 12.0F, 11.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(NeckFlap, -1.0016444470669013F, 0.0F, 0.0F);
            this.CowEarRight = new ModelRenderer(this, 16, 0);
            this.CowEarRight.mirror = true;
            this.CowEarRight.setPos(-4.5F, -2.0F, 1.5F);
            this.CowEarRight.addBox(-5.0F, -1.5F, 0.0F, 5.0F, 3.0F, 1.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(CowEarRight, 0.0F, 0.9105382388075086F, 0.0F);
            this.UpperLegRight = new ModelRenderer(this, 60, 20);
            this.UpperLegRight.mirror = true;
            this.UpperLegRight.setPos(0.0F, 10.0F, -2.5F);
            this.UpperLegRight.addBox(-2.0F, 0.0F, 0.0F, 4.0F, 5.0F, 5.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(UpperLegRight, 0.500909508638178F, 0.0F, 0.0F);
            this.HornLonghornRight2 = new ModelRenderer(this, 48, 86);
            this.HornLonghornRight2.mirror = true;
            this.HornLonghornRight2.setPos(-7.0F, 1.5F, 0.0F);
            this.HornLonghornRight2.addBox(-5.0F, -2.5F, -1.0F, 5.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(HornLonghornRight2, 0.0F, 0.0F, 0.3642502295386026F);
            this.HornForwardLeft2_1 = new ModelRenderer(this, 0, 53);
            this.HornForwardLeft2_1.setPos(-3.0F, 2.0F, 0.0F);
            this.HornForwardLeft2_1.addBox(0.0F, -3.0F, 0.5F, 1.0F, 3.0F, 1.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(HornForwardLeft2_1, 0.0F, 0.0F, -0.2275909337942703F);
            this.HornLonghornRight1 = new ModelRenderer(this, 48, 80);
            this.HornLonghornRight1.mirror = true;
            this.HornLonghornRight1.setPos(-4.0F, 2.0F, 1.5F);
            this.HornLonghornRight1.addBox(-7.0F, -1.5F, -1.5F, 7.0F, 3.0F, 3.0F, 0.0F, 0.0F, 0.0F);
            this.Body = new ModelRenderer(this, 0, 0);
            this.Body.setPos(0.0F, 4.2F, 1.0F);
            this.Body.addBox(-7.0F, -8.0F, -16.0F, 14.0F, 17.0F, 32.0F, 0.0F, 0.0F, 0.0F);
            this.HornForwardRight1 = new ModelRenderer(this, 58, 53);
            this.HornForwardRight1.mirror = true;
            this.HornForwardRight1.setPos(-3.6F, 2.5F, 1.0F);
            this.HornForwardRight1.addBox(-3.0F, 0.0F, 0.0F, 3.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(HornForwardRight1, 1.8668041519541685F, -0.500909508638178F, 0.0F);
            this.HornForwardLeft2 = new ModelRenderer(this, 0, 53);
            this.HornForwardLeft2.setPos(3.0F, 2.0F, 0.0F);
            this.HornForwardLeft2.addBox(-1.0F, -3.0F, 0.5F, 1.0F, 3.0F, 1.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(HornForwardLeft2, 0.0F, 0.0F, 0.2275909337942703F);
            this.TailTuft = new ModelRenderer(this, 20, 16);
            this.TailTuft.setPos(0.0F, 5.0F, 0.0F);
            this.TailTuft.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, 0.0F, 0.0F);
            this.HornUprightLeft2_1 = new ModelRenderer(this, 0, 49);
            this.HornUprightLeft2_1.setPos(-3.0F, 2.0F, 0.0F);
            this.HornUprightLeft2_1.addBox(0.0F, -3.0F, 0.5F, 1.0F, 3.0F, 1.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(HornUprightLeft2_1, 0.0F, 0.0F, -0.2275909337942703F);
            this.HandRight = new ModelRenderer(this, 60, 10);
            this.HandRight.mirror = true;
            this.HandRight.setPos(0.0F, 5.0F, -4.5F);
            this.HandRight.addBox(-1.5F, 0.0F, 0.0F, 3.0F, 6.0F, 4.0F, 0.0F, 0.0F, 0.0F);
            this.Hump = new ModelRenderer(this, 14, 81);
            this.Hump.setPos(0.0F, -11.0F, -10.0F);
            this.Hump.addBox(-6.0F, 0.0F, -5.0F, 12.0F, 4.0F, 10.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(Hump, -0.18203784630933073F, 0.0F, 0.0F);
            this.BellCollar = new ModelRenderer(this, 104, 3);
            this.BellCollar.setPos(0.0F, -5.0F, 0.0F);
            this.BellCollar.addBox(-4.0F, -5.0F, -0.5F, 8.0F, 14.0F, 1.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(BellCollar, -1.2747885016356248F, 0.0F, 0.0F);
            this.HornUprightLeft2 = new ModelRenderer(this, 0, 49);
            this.HornUprightLeft2.setPos(3.0F, 2.0F, 0.0F);
            this.HornUprightLeft2.addBox(-1.0F, -3.0F, 0.5F, 1.0F, 3.0F, 1.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(HornUprightLeft2, 0.0F, 0.0F, 0.2275909337942703F);
            this.HornForwardLeft1 = new ModelRenderer(this, 58, 53);
            this.HornForwardLeft1.setPos(3.6F, 2.5F, 1.0F);
            this.HornForwardLeft1.addBox(0.0F, 0.0F, 0.0F, 3.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(HornForwardLeft1, 1.8668041519541685F, 0.500909508638178F, 0.0F);
            this.ArmRight = new ModelRenderer(this, 60, 0);
            this.ArmRight.mirror = true;
            this.ArmRight.setPos(0.0F, 6.0F, 2.5F);
            this.ArmRight.addBox(-2.0F, 0.0F, -5.0F, 4.0F, 5.0F, 5.0F, 0.0F, 0.0F, 0.0F);
            this.HighlandFurFace = new ModelRenderer(this, 24, 68);
            this.HighlandFurFace.setPos(0.0F, 5.5F, -4.0F);
            this.HighlandFurFace.addBox(-4.5F, 0.0F, 0.0F, 9.0F, 2.0F, 8.0F, 0.0F, 0.0F, 0.0F);
            this.Tail1 = new ModelRenderer(this, 22, 4);
            this.Tail1.setPos(0.0F, -6.0F, 16.0F);
            this.Tail1.addBox(-0.5F, 0.0F, 0.0F, 1.0F, 7.0F, 0.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(Tail1, 0.18203784630933073F, 0.0F, 0.0F);
            this.UpperLegtLeft = new ModelRenderer(this, 60, 20);
            this.UpperLegtLeft.setPos(0.0F, 10.0F, -2.5F);
            this.UpperLegtLeft.addBox(-2.0F, 0.0F, 0.0F, 4.0F, 5.0F, 5.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(UpperLegtLeft, 0.500909508638178F, 0.0F, 0.0F);
            this.HighlandFurBeard = new ModelRenderer(this, 108, 29);
            this.HighlandFurBeard.setPos(0.0F, 6.0F, -5.0F);
            this.HighlandFurBeard.addBox(-2.5F, 0.0F, 0.0F, 5.0F, 2.0F, 5.0F, 0.0F, 0.0F, 0.0F);
            this.Neck = new ModelRenderer(this, 0, 49);
            this.Neck.setPos(0.0F, -2.5F, -13.0F);
            this.Neck.addBox(-3.5F, -12.0F, -4.5F, 7.0F, 14.0F, 9.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(Neck, 1.2747885016356248F, 0.0F, 0.0F);
            this.ThighRight = new ModelRenderer(this, 78, 0);
            this.ThighRight.mirror = true;
            this.ThighRight.setPos(-4.6F, -0.3F, 11.0F);
            this.ThighRight.addBox(-2.5F, 0.0F, -4.0F, 5.0F, 10.0F, 8.0F, 0.0F, 0.0F, 0.0F);
            this.Nipple2 = new ModelRenderer(this, 0, 0);
            this.Nipple2.setPos(-3.0F, 4.0F, 1.0F);
            this.Nipple2.addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
            this.NeckHump = new ModelRenderer(this, 80, 68);
            this.NeckHump.setPos(0.0F, -11.0F, 4.7F);
            this.NeckHump.addBox(-3.0F, 0.0F, -4.0F, 6.0F, 8.0F, 4.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(NeckHump, 0.3909537457888271F, 0.0F, 0.0F);
            this.HornUprightLeft1 = new ModelRenderer(this, 58, 49);
            this.HornUprightLeft1.setPos(4.0F, 0.5F, 0.5F);
            this.HornUprightLeft1.addBox(0.0F, 0.0F, 0.0F, 3.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(HornUprightLeft1, 0.0F, 0.0F, -0.2275909337942703F);
            this.Nipple4 = new ModelRenderer(this, 0, 0);
            this.Nipple4.setPos(-3.0F, 4.0F, 4.0F);
            this.Nipple4.addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
            this.Udder = new ModelRenderer(this, 92, 18);
            this.Udder.setPos(0.0F, 9.0F, 6.0F);
            this.Udder.addBox(-4.5F, 0.0F, 0.0F, 9.0F, 4.0F, 7.0F, 0.0F, 0.0F, 0.0F);
            this.TopSnout = new ModelRenderer(this, 0, 12);
            this.TopSnout.setPos(0.0F, -3.0F, 0.5F);
            this.TopSnout.addBox(-2.0F, 0.0F, -6.0F, 4.0F, 5.0F, 6.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(TopSnout, 0.5462880425584197F, 0.0F, 0.0F);
            this.HornUprightRight1 = new ModelRenderer(this, 58, 49);
            this.HornUprightRight1.mirror = true;
            this.HornUprightRight1.setPos(-4.0F, 0.5F, 0.5F);
            this.HornUprightRight1.addBox(-3.0F, 0.0F, 0.0F, 3.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(HornUprightRight1, 0.0F, 0.0F, 0.2275909337942703F);
            this.BrahmaEarRight = new ModelRenderer(this, 23, 49);
            this.BrahmaEarRight.mirror = true;
            this.BrahmaEarRight.setPos(-5.0F, -4.0F, 1.0F);
            this.BrahmaEarRight.addBox(-5.0F, 0.0F, 0.0F, 6.0F, 4.0F, 1.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(BrahmaEarRight, 0.0F, 0.3642502295386026F, -0.500909508638178F);
            this.BrahmaEarLeft = new ModelRenderer(this, 23, 49);
            this.BrahmaEarLeft.setPos(5.0F, -4.0F, 1.0F);
            this.BrahmaEarLeft.addBox(-1.0F, 0.0F, 0.0F, 6.0F, 4.0F, 1.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(BrahmaEarLeft, 0.0F, -0.3642502295386026F, 0.500909508638178F);
            this.BellFlare = new ModelRenderer(this, 92, 35);
            this.BellFlare.setPos(0.0F, 2.2F, 0.0F);
            this.BellFlare.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, 0.0F, 0.0F);
            this.HornLonghornLeft3 = new ModelRenderer(this, 46, 78);
            this.HornLonghornLeft3.setPos(5.0F, -2.0F, 0.0F);
            this.HornLonghornLeft3.addBox(0.0F, 0.0F, -0.5F, 5.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(HornLonghornLeft3, 0.0F, 0.0F, 0.27314402127920984F);
            this.Nipple1 = new ModelRenderer(this, 0, 0);
            this.Nipple1.setPos(2.0F, 4.0F, 1.0F);
            this.Nipple1.addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
            this.ArmBaseLeft = new ModelRenderer(this, 100, 68);
            this.ArmBaseLeft.setPos(4.0F, 2.9F, -12.0F);
            this.ArmBaseLeft.addBox(-2.5F, 0.0F, -3.0F, 5.0F, 6.0F, 6.0F, 0.0F, 0.0F, 0.0F);
            this.ArmLeft = new ModelRenderer(this, 60, 0);
            this.ArmLeft.setPos(0.0F, 6.0F, 2.5F);
            this.ArmLeft.addBox(-2.0F, 0.0F, -5.0F, 4.0F, 5.0F, 5.0F, 0.0F, 0.0F, 0.0F);
            this.Bell = new ModelRenderer(this, 92, 29);
            this.Bell.setPos(0.0F, 9.1F, 0.0F);
            this.Bell.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, 0.0F, 0.0F);
            this.Nipple3 = new ModelRenderer(this, 0, 0);
            this.Nipple3.setPos(2.0F, 4.0F, 4.0F);
            this.Nipple3.addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
            this.ArmBaseRight = new ModelRenderer(this, 100, 68);
            this.ArmBaseRight.mirror = true;
            this.ArmBaseRight.setPos(-4.0F, 2.9F, -12.0F);
            this.ArmBaseRight.addBox(-2.5F, 0.0F, -3.0F, 5.0F, 6.0F, 6.0F, 0.0F, 0.0F, 0.0F);
            this.HornLonghornLeft1 = new ModelRenderer(this, 48, 80);
            this.HornLonghornLeft1.setPos(4.0F, 2.0F, 1.5F);
            this.HornLonghornLeft1.addBox(0.0F, -1.5F, -1.5F, 7.0F, 3.0F, 3.0F, 0.0F, 0.0F, 0.0F);
            this.ThighLeft = new ModelRenderer(this, 78, 0);
            this.ThighLeft.setPos(4.6F, -0.3F, 11.0F);
            this.ThighLeft.addBox(-2.5F, 0.0F, -4.0F, 5.0F, 10.0F, 8.0F, 0.0F, 0.0F, 0.0F);
            this.Neck.addChild(this.NeckLower);
            this.HornLonghornLeft1.addChild(this.HornLonghornLeft2);
            this.Head.addChild(this.Forehead);
            this.Tail1.addChild(this.Tail2);
            this.Neck.addChild(this.Head);
            this.UpperLegRight.addChild(this.LowerLegRight);
            this.ArmLeft.addChild(this.HandLeft);
            this.Head.addChild(this.CowEarLeft);
            this.Body.addChild(this.HighlandFurBody);
            this.HornLonghornRight2.addChild(this.HornLonghornRight3);
            this.UpperLegtLeft.addChild(this.LowerLegLeft);
            this.Head.addChild(this.Snout);
            this.NeckLower.addChild(this.NeckFlap);
            this.Head.addChild(this.CowEarRight);
            this.ThighRight.addChild(this.UpperLegRight);
            this.HornLonghornRight1.addChild(this.HornLonghornRight2);
            this.HornForwardRight1.addChild(this.HornForwardLeft2_1);
            this.Forehead.addChild(this.HornLonghornRight1);
            this.Forehead.addChild(this.HornForwardRight1);
            this.HornForwardLeft1.addChild(this.HornForwardLeft2);
            this.Tail2.addChild(this.TailTuft);
            this.HornUprightRight1.addChild(this.HornUprightLeft2_1);
            this.ArmRight.addChild(this.HandRight);
            this.Body.addChild(this.Hump);
            this.Neck.addChild(this.BellCollar);
            this.HornUprightLeft1.addChild(this.HornUprightLeft2);
            this.Forehead.addChild(this.HornForwardLeft1);
            this.ArmBaseRight.addChild(this.ArmRight);
            this.Head.addChild(this.HighlandFurFace);
            this.Body.addChild(this.Tail1);
            this.ThighLeft.addChild(this.UpperLegtLeft);
            this.Snout.addChild(this.HighlandFurBeard);
            this.Body.addChild(this.Neck);
            this.Body.addChild(this.ThighRight);
            this.Udder.addChild(this.Nipple2);
            this.Neck.addChild(this.NeckHump);
            this.Forehead.addChild(this.HornUprightLeft1);
            this.Udder.addChild(this.Nipple4);
            this.Body.addChild(this.Udder);
            this.Snout.addChild(this.TopSnout);
            this.Forehead.addChild(this.HornUprightRight1);
            this.Head.addChild(this.BrahmaEarRight);
            this.Head.addChild(this.BrahmaEarLeft);
            this.Bell.addChild(this.BellFlare);
            this.HornLonghornLeft2.addChild(this.HornLonghornLeft3);
            this.Udder.addChild(this.Nipple1);
            this.Body.addChild(this.ArmBaseLeft);
            this.ArmBaseLeft.addChild(this.ArmLeft);
            this.BellCollar.addChild(this.Bell);
            this.Udder.addChild(this.Nipple3);
            this.Body.addChild(this.ArmBaseRight);
            this.Forehead.addChild(this.HornLonghornLeft1);
            this.Body.addChild(this.ThighLeft);
        }

        @Override
        public void setupAnim(HotCowEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
            super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            this.Neck.xRot = (headPitch / (180F / (float) Math.PI) + 1.27F) / 2;
            this.Neck.yRot = (netHeadYaw / (180F / (float) Math.PI)) / 2;
            this.Head.xRot = (headPitch / (180F / (float) Math.PI) + -1.14F) / 2;
            this.Head.yRot = (netHeadYaw / (180F / (float) Math.PI)) / 2;
        }
    }

    public static class Calf extends HotCowModel {
        public Calf() {
        }

        @Override
        public void setupAnim(HotCowEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
            super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
//            this.Head.xRot = headPitch / (180F / (float)Math.PI) + -0.09F;
//            this.Head.yRot = netHeadYaw / (180F / (float)Math.PI);
        }
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
