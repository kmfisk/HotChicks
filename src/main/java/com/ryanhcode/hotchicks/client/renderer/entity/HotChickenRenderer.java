package com.ryanhcode.hotchicks.client.renderer.entity;

import com.ryanhcode.hotchicks.HotChickens;
import com.ryanhcode.hotchicks.entity.base.ChickenBreed;
import com.ryanhcode.hotchicks.entity.base.LivestockEntity;
import com.ryanhcode.hotchicks.entity.base.Sex;
import com.ryanhcode.hotchicks.entity.chicken.HotChickenEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HotChickenRenderer<T extends LivestockEntity, M extends EntityModel<T>> extends LivingRenderer<T, M>{
    private static ResourceLocation CHICKEN_TEXTURES = new ResourceLocation(HotChickens.MODID, "textures/entity/chicken/junglefowl/junglefowl_rooster.png");

    public static RoosterModel roosterModel = new RoosterModel();
    public static HenModel henModel = new HenModel();
    public static ChickModel chickModel = new ChickModel();

    public HotChickenRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, (M) roosterModel, 0.3F);
    }

    @Override
    public ResourceLocation getEntityTexture(T entity) {

        HotChickenEntity chicken = (HotChickenEntity) entity;

        boolean isChild = chicken.isChild();

        if(isChild){
            entityModel = (M)chickModel;
        }else{
            entityModel = chicken.getSex() == Sex.MALE ? (M)roosterModel : (M)henModel;
        }

        return new ResourceLocation(HotChickens.MODID, "textures/entity/chicken/" +
                ((ChickenBreed.ChickenVariant)chicken.breed.textureMap.get(chicken.getVariant())).get(chicken.getSex()) + ".png");

    }
}