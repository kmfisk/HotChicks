package com.ryanhcode.hotchicks.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.ryanhcode.hotchicks.HotChickens;
import com.ryanhcode.hotchicks.entity.base.ChickenBreed;
import com.ryanhcode.hotchicks.entity.base.LivestockEntity;
import com.ryanhcode.hotchicks.entity.base.Sex;
import com.ryanhcode.hotchicks.entity.chicken.HotChickenEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.ChickenRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HotChickenRenderer<T extends LivestockEntity, M extends EntityModel<T>> extends LivingRenderer<T, M> {

    public RoosterModel roosterModel = new RoosterModel();
    public HenModel henModel = new HenModel();
    public ChickModel chickModel = new ChickModel();

    public HotChickenRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, (M) new RoosterModel<>(), 0.3F);
    }

    @Override
    protected boolean canRenderName(T entity) {
        return false;
    }

    @Override
    public void render(T entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        HotChickenEntity chicken = (HotChickenEntity) entityIn;
        boolean isChild = chicken.isChild();

        if (isChild) {
            entityModel = (M) chickModel;
        } else {
            entityModel = chicken.getSex() == Sex.MALE ? (M) roosterModel : (M) henModel;
        }

        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public ResourceLocation getEntityTexture(T entity) {

        if (entity instanceof HotChickenEntity) {
            HotChickenEntity chicken = (HotChickenEntity) entity;

            boolean isChild = chicken.isChild();
            String path;

            ChickenBreed breed = chicken.getBreed().equals("not_set") ? ChickenBreed.LEGHORN : ChickenBreed.valueOf(chicken.getBreed());
            if (isChild) {
                path = "textures/entity/chicken/" + (breed.childTextures.get(chicken.getChildType())) + ".png";
            } else {
                path = "textures/entity/chicken/" + ((ChickenBreed.ChickenVariant) breed.textureMap.get(chicken.getVariant())).get(chicken.getSex()) + ".png";
            }

            return new ResourceLocation(HotChickens.MODID, path);
        }
        return new ResourceLocation(HotChickens.MODID, "none");
    }
}