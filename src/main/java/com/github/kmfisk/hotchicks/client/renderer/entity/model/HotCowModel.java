package com.github.kmfisk.hotchicks.client.renderer.entity.model;

import com.github.kmfisk.hotchicks.entity.HotCowEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HotCowModel extends SegmentedModel<HotCowEntity> {
    private Iterable<ModelRenderer> parts;

    @Override
    public Iterable<ModelRenderer> parts() {
        if (parts == null)
            parts = ImmutableList.of(/*Body*/);

        return parts;
    }

    @Override
    public void setupAnim(HotCowEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {

    }

    @Override
    public void prepareMobModel(HotCowEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTick) {
        super.prepareMobModel(pEntity, pLimbSwing, pLimbSwingAmount, pPartialTick);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

    public static class Bull extends HotCowModel {
        public Bull() {
        }

        @Override
        public void setupAnim(HotCowEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
            super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
//            this.Head.xRot = headPitch / (180F / (float)Math.PI) + -0.09F;
//            this.Head.yRot = netHeadYaw / (180F / (float)Math.PI);
        }
    }

    public static class Cow extends HotCowModel {
        public Cow() {
        }

        @Override
        public void setupAnim(HotCowEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
            super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
//            this.Head.xRot = headPitch / (180F / (float)Math.PI) + -0.09F;
//            this.Head.yRot = netHeadYaw / (180F / (float)Math.PI);
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
}
