package com.github.kmfisk.hotchicks.client.renderer.entity;

import com.github.kmfisk.hotchicks.HotChicks;
import com.github.kmfisk.hotchicks.client.renderer.entity.model.HotRabbitModel;
import com.github.kmfisk.hotchicks.entity.HotRabbitEntity;
import com.github.kmfisk.hotchicks.entity.base.Sex;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HotRabbitRenderer extends MobRenderer<HotRabbitEntity, HotRabbitModel> {
    public static final String[] AMERICAN_CHINCHILLAS = new String[]{"gray", "silver"};
    public static final String[] DUTCHES = new String[]{"black", "blue", "brown", "chocolate", "tri", "yellow"};
    public static final String[] FLEMISH_GIANTS = new String[]{"black", "blue", "brown", "steel_gray", "white"};
    public static final String[] NEW_ZEALANDS = new String[]{"broken", "red", "white"};
    public static final String[] REXES = new String[]{"black", "black_otter", "broken", "chocolate", "chocolate_otter", "red", "red_otter", "tan", "white"};

    public HotRabbitRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new HotRabbitModel(), 0.3F);
    }

    @Override
    public void render(HotRabbitEntity rabbit, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(rabbit, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public ResourceLocation getTextureLocation(HotRabbitEntity rabbit) {
        String location;
        String sex = rabbit.getSex() == Sex.MALE ? "_buck.png" : "_doe.png";
        int variant = rabbit.getVariant();

        switch (rabbit.getBreedFromVariant(variant)) {
            default:
            case COTTONTAIL:
                location = "textures/entity/rabbit/cottontail/cottontail" + sex;
                break;
            case AMERICAN_CHINCHILLA:
                location = "textures/entity/rabbit/american_chinchilla/" + AMERICAN_CHINCHILLAS[variant - 1] + sex;
                break;
            case CALIFORNIA:
                location = "textures/entity/rabbit/california/california" + sex;
                break;
            case DUTCH:
                location = "textures/entity/rabbit/dutch/" + DUTCHES[variant - 4] + sex;
                break;
            case FLEMISH_GIANT:
                location = "textures/entity/rabbit/flemish_giant/" + FLEMISH_GIANTS[variant - 10] + sex;
                break;
            case NEW_ZEALAND:
                location = "textures/entity/rabbit/new_zealand/" + NEW_ZEALANDS[variant - 15] + sex;
                break;
            case REX:
                location = "textures/entity/rabbit/rex/" + REXES[variant - 18] + sex;
                break;
        }

        return new ResourceLocation(HotChicks.MOD_ID, location);
    }
}