package com.ryanhcode.hotchicks.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.ryanhcode.hotchicks.HotChickens;
import com.ryanhcode.hotchicks.client.renderer.entity.model.HotChickenModel;
import com.ryanhcode.hotchicks.entity.HotChickenEntity;
import com.ryanhcode.hotchicks.entity.base.ChickenBreed;
import com.ryanhcode.hotchicks.entity.base.Sex;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HotChickenRenderer extends MobRenderer<HotChickenEntity, HotChickenModel> {
    public final HotChickenModel roosterModel;
    public final HotChickenModel henModel;
    public final HotChickenModel chickModel;

    public HotChickenRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new HotChickenModel.Hen(), 0.3F);
        roosterModel = new HotChickenModel.Rooster();
        henModel = new HotChickenModel.Hen();
        chickModel = new HotChickenModel.Chick();
    }

    @Override
    protected boolean shouldShowName(HotChickenEntity p_177070_1_) {
        return false;
    }

    @Override
    public void render(HotChickenEntity chicken, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        boolean isChild = chicken.isBaby();
        if (isChild)
            model = chickModel;
        else
            model = chicken.getSex() == Sex.MALE ? roosterModel : henModel;

        super.render(chicken, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public ResourceLocation getTextureLocation(HotChickenEntity chicken) {
        boolean isChild = chicken.isBaby();
        String path;

        ChickenBreed breed = chicken.getBreed().equals("not_set") ? ChickenBreed.LEGHORN : ChickenBreed.valueOf(chicken.getBreed());
        if (isChild)
            path = "textures/entity/chicken/" + (breed.childTextures.get(chicken.getChildType())) + ".png";
        else {
            if (chicken.getVariant().equals("not_set"))
                path = "textures/entity/chicken/junglefowl/junglefowl_rooster.png";
            else {
                if (!breed.textureMap.containsKey(chicken.getVariant()))
                    path = "textures/entity/chicken/junglefowl/junglefowl_rooster.png";
                else
                    path = "textures/entity/chicken/" + (breed.textureMap.get(chicken.getVariant())).get(chicken.getSex()) + ".png";
            }
        }

        return new ResourceLocation(HotChickens.MOD_ID, path);
    }
}