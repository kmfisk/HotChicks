package com.github.kmfisk.hotchicks.client.renderer.entity;

import com.github.kmfisk.hotchicks.HotChicks;
import com.github.kmfisk.hotchicks.client.renderer.entity.layers.CowBellLayer;
import com.github.kmfisk.hotchicks.client.renderer.entity.model.HotCowModel;
import com.github.kmfisk.hotchicks.entity.HotCowEntity;
import com.github.kmfisk.hotchicks.entity.LivestockEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HotCowRenderer extends MobRenderer<HotCowEntity, HotCowModel> {
    public static final String[] ANGUS_LAKENVELDER = new String[]{"black", "red"};
    public static final String[] BRAHMA = new String[]{"black", "gray", "red", "tan", "white"};
    public static final String[] GUERNSEY = new String[]{"red", "tan"};
    public static final String[] HEREFORD_HOLSTEIN = new String[]{"heavy", "lowfat", "skim", "whole"};
    public static final String[] HIGHLAND = new String[]{"black", "red", "tan", "white"};
    public static final String[] LONGHORN = new String[]{"black", "blue", "red", "tan", "white"};
    public final HotCowModel adultModel;
    public final HotCowModel calfModel;

    public HotCowRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new HotCowModel.Adult(), 1.0F);
        this.addLayer(new CowBellLayer(this));
        adultModel = new HotCowModel.Adult();
        calfModel = new HotCowModel.Calf();
    }

    @Override
    public void render(HotCowEntity cow, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        model = cow.isBaby() ? calfModel : adultModel;
        super.render(cow, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public ResourceLocation getTextureLocation(HotCowEntity cow) {
        String location;
        String sex = cow.getSex() == LivestockEntity.Sex.MALE ? "_bull.png" : "_cow.png";
        int variant = cow.getVariant();

        if (cow.isBaby()) sex = "_cow.png";

        switch (cow.getBreedFromVariant()) {
            default:
            case AUROCHS:
                location = "textures/entity/cow/aurochs/aurochs" + sex;
                break;
            case ANGUS:
                location = "textures/entity/cow/angus/angus_" + ANGUS_LAKENVELDER[variant - 1] + sex;
                break;
            case BRAHMA:
                location = "textures/entity/cow/brahma/brahma_" + BRAHMA[variant - 3] + sex;
                break;
            case BROWN_SWISS:
                location = "textures/entity/cow/brown_swiss/brown_swiss_tan" + sex;
                break;
            case GUERNSEY:
                location = "textures/entity/cow/guernsey/guernsey_" + GUERNSEY[variant - 9] + sex;
                break;
            case HEREFORD:
                location = "textures/entity/cow/hereford/hereford_" + HEREFORD_HOLSTEIN[variant - 11] + sex;
                break;
            case HIGHLAND:
                location = "textures/entity/cow/highland/highland_" + HIGHLAND[variant - 15] + sex;
                break;
            case HOLSTEIN:
                location = "textures/entity/cow/holstein/holstein_" + HEREFORD_HOLSTEIN[variant - 19] + sex;
                break;
            case JERSEY:
                location = "textures/entity/cow/jersey/jersey_tan" + sex;
                break;
            case LAKENVELDER:
                location = "textures/entity/cow/lakenvelder/lakenvelder_" + ANGUS_LAKENVELDER[variant - 24] + sex;
                break;
            case LONGHORN:
                location = "textures/entity/cow/longhorn/longhorn_" + LONGHORN[variant - 26] + sex;
                break;
        }

        return new ResourceLocation(HotChicks.MOD_ID, location);
    }
}