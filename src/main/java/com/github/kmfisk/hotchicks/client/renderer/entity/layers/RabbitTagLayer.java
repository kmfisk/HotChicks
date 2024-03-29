package com.github.kmfisk.hotchicks.client.renderer.entity.layers;

import com.github.kmfisk.hotchicks.HotChicks;
import com.github.kmfisk.hotchicks.client.renderer.entity.model.HotRabbitModel;
import com.github.kmfisk.hotchicks.entity.HotRabbitEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RabbitTagLayer extends LayerRenderer<HotRabbitEntity, HotRabbitModel> {
    public static final ResourceLocation EAR_TAG = new ResourceLocation(HotChicks.MOD_ID, "textures/entity/rabbit/ear_tag.png");

    public RabbitTagLayer(IEntityRenderer<HotRabbitEntity, HotRabbitModel> renderer) {
        super(renderer);
    }

    @Override
    public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, HotRabbitEntity rabbitEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (rabbitEntity.isTagged() && !rabbitEntity.isInvisible()) {
            float[] afloat = rabbitEntity.getTagColor().getTextureDiffuseColors();
            renderColoredCutoutModel(getParentModel(), EAR_TAG, matrixStack, buffer, packedLight, rabbitEntity, afloat[0], afloat[1], afloat[2]);
        }
    }
}
