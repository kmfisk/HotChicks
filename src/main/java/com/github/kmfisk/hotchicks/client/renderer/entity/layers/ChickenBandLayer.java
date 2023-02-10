package com.github.kmfisk.hotchicks.client.renderer.entity.layers;

import com.github.kmfisk.hotchicks.HotChicks;
import com.github.kmfisk.hotchicks.client.renderer.entity.model.HotChickenModel;
import com.github.kmfisk.hotchicks.entity.HotChickenEntity;
import com.github.kmfisk.hotchicks.entity.base.ChickenBreeds;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChickenBandLayer extends LayerRenderer<HotChickenEntity, HotChickenModel> {
    public static final ResourceLocation LEG_BAND = new ResourceLocation(HotChicks.MOD_ID, "textures/entity/chicken/leg_band.png");
    public static final ResourceLocation LEG_BAND_SILKIE = new ResourceLocation(HotChicks.MOD_ID, "textures/entity/chicken/leg_band_silkie.png");

    public ChickenBandLayer(IEntityRenderer<HotChickenEntity, HotChickenModel> renderer) {
        super(renderer);
    }

    @Override
    public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, HotChickenEntity chickenEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (chickenEntity.isTagged() && !chickenEntity.isInvisible()) {
            float[] afloat = chickenEntity.getTagColor().getTextureDiffuseColors();
            if (chickenEntity.getBreedFromVariant() == ChickenBreeds.SILKIE)
                renderColoredCutoutModel(getParentModel(), LEG_BAND_SILKIE, matrixStack, buffer, packedLight, chickenEntity, afloat[0], afloat[1], afloat[2]);
            else
                renderColoredCutoutModel(getParentModel(), LEG_BAND, matrixStack, buffer, packedLight, chickenEntity, afloat[0], afloat[1], afloat[2]);
        }
    }
}
