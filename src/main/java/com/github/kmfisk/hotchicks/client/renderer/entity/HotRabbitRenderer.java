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
    public static final String[] FLEMISH_GIANTS = new String[]{"black", "blue", "brown", "steelgray", "white"};
    public static final String[] NEW_ZEALANDS = new String[]{"broken", "red", "white"};
    public static final String[] REXES = new String[]{"black", "blackotter", "broken", "chocolate", "chocolateotter", "red", "redotter", "tan", "white"};

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

        if (rabbit.isBaby()) {
            int baby;
            switch (variant) {
                default: case 9: case 24: case 32:
                    baby = 1;
                    break;
                case 26: case 30:
                    baby = 2;
                    break;
                case 23: case 27:
                    baby = 3;
                    break;
                case 13: case 25: case 31:
                    baby = 4;
                    break;
                case 29:
                    baby = 5;
                    break;
                case 2: case 3: case 4: case 5: case 6: case 7: case 10: case 15: case 16: case 17:
                    case 18: case 19: case 20: case 22:
                    baby = 6;
                    break;
                case 1: case 14: case 21: case 28:
                    baby = 7;
                    break;
                case 8: case 11: case 12:
                    baby = 8;
                    break;
            }

            return new ResourceLocation(HotChicks.MOD_ID, "textures/entity/rabbit/kit_" + baby + ".png");
        }

        switch (rabbit.getBreedFromVariant(variant)) {
            default:
            case COTTONTAIL:
                location = "textures/entity/rabbit/cottontail/rabbit_cottontail" + sex;
                break;
            case AMERICAN_CHINCHILLA:
                location = "textures/entity/rabbit/american_chinchilla/rabbit_amchin_" + AMERICAN_CHINCHILLAS[variant - 1] + sex;
                break;
            case CALIFORNIA:
                location = "textures/entity/rabbit/california/rabbit_california" + sex;
                break;
            case DUTCH:
                location = "textures/entity/rabbit/dutch/rabbit_dutch_" + DUTCHES[variant - 4] + sex;
                break;
            case FLEMISH_GIANT:
                location = "textures/entity/rabbit/flemish_giant/rabbit_flemish_" + FLEMISH_GIANTS[variant - 10] + sex;
                break;
            case NEW_ZEALAND:
                location = "textures/entity/rabbit/new_zealand/rabbit_nz_" + NEW_ZEALANDS[variant - 15] + sex;
                break;
            case REX:
                location = "textures/entity/rabbit/rex/rabbit_rex_" + REXES[variant - 18] + sex;
                break;
        }

        return new ResourceLocation(HotChicks.MOD_ID, location);
    }
}