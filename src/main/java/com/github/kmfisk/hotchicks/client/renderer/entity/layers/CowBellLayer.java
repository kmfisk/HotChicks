package com.github.kmfisk.hotchicks.client.renderer.entity.layers;

import com.github.kmfisk.hotchicks.HotChicks;
import com.github.kmfisk.hotchicks.client.renderer.entity.model.HotCowModel;
import com.github.kmfisk.hotchicks.entity.HotCowEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CowBellLayer extends LayerRenderer<HotCowEntity, HotCowModel> {
    public static final ResourceLocation COW_BELL_COLLAR = new ResourceLocation(HotChicks.MOD_ID, "textures/entity/cow/cow_bell_collar.png");
    public static final ResourceLocation COW_BELL = new ResourceLocation(HotChicks.MOD_ID, "textures/entity/cow/cow_bell.png");

    public CowBellLayer(IEntityRenderer<HotCowEntity, HotCowModel> renderer) {
        super(renderer);
    }

    @Override
    public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, HotCowEntity cowEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (cowEntity.isTagged() && !cowEntity.isInvisible()) {
            float[] afloat = cowEntity.getTagColor().getTextureDiffuseColors();
            renderColoredCutoutModel(getParentModel(), COW_BELL_COLLAR, matrixStack, buffer, packedLight, cowEntity, afloat[0], afloat[1], afloat[2]);
            renderColoredCutoutModel(getParentModel(), COW_BELL, matrixStack, buffer, packedLight, cowEntity, 1.0F, 1.0F, 1.0F);
        }
    }
}
