package com.ryanhcode.hotchicks.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.ryanhcode.hotchicks.HotChickens;
import com.ryanhcode.hotchicks.client.renderer.entity.model.HotChickenModel;
import com.ryanhcode.hotchicks.entity.HotChickenEntity;
import com.ryanhcode.hotchicks.entity.base.ChickenBreeds;
import com.ryanhcode.hotchicks.entity.base.Sex;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HotChickenRenderer extends MobRenderer<HotChickenEntity, HotChickenModel> {
    public static final String[] AMERAUCANAS = new String[]{"black", "blue", "bluewheaten", "brown", "buff", "lavender", "lightbrown"};
    public static final String[] MARANS = new String[]{"blackbirchen", "blackcopper", "cuckoo", "goldcuckoo"};
    public static final String[] ORPINGTONS = new String[]{"black", "blue", "buff", "white"};
    public static final String[] RHODE_ISLANDS = new String[]{"deepred", "lightred", "red"};
    public static final String[] SILKIES = new String[]{"black", "blue", "buff", "partridge", "white"};
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
        String location;
        String sex = chicken.getSex() == Sex.MALE ? "_rooster.png" : "_hen.png";
        int variant = chicken.getVariant();

        if (chicken.isBaby()) {
            int baby;
            switch (variant) {
                case 9: case 24: case 32: default:
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
                case 2: case 3: case 4: case 5: case 6: case 7: case 10:
                case 15: case 16: case 17: case 18: case 19: case 20: case 22:
                    baby = 6;
                    break;
                case 1: case 14: case 21: case 28:
                    baby = 7;
                    break;
                case 8: case 11: case 12:
                    baby = 8;
                    break;
            }

            return new ResourceLocation(HotChickens.MOD_ID, "textures/entity/chicken/chicks/chick_" + baby + ".png");
        }

        switch (chicken.getBreedFromVariant(variant)) {
            default: case JUNGLEFOWL:
                location = "textures/entity/chicken/junglefowl/junglefowl" + sex;
                break;
            case AMERAUCANA:
                location = "textures/entity/chicken/ameraucanas/ameraucana_" + AMERAUCANAS[variant - 1] + sex;
                break;
            case BARRED_ROCK:
                location = "textures/entity/chicken/barred_rocks/barredrock" + sex;
                break;
            case LEGHORN:
                location = "textures/entity/chicken/leghorns/leghorn" + sex;
                break;
            case MARANS:
                location = "textures/entity/chicken/marans/marans_" + MARANS[variant - 10] + sex;
                break;
            case OLIVE_EGGER:
                location = "textures/entity/chicken/ameraucanas/ameraucana_" + AMERAUCANAS[variant - 14] + sex;
                break;
            case ORPINGTON:
                location = "textures/entity/chicken/orpingtons/orpington_" + ORPINGTONS[variant - 21] + sex;
                break;
            case RHODE_ISLAND_RED:
                location = "textures/entity/chicken/rhode_islands/rhodeisland_" + RHODE_ISLANDS[variant - 25] + sex;
                break;
            case SILKIE:
                location = "textures/entity/chicken/silkies/silkie_" + SILKIES[variant - 28] + sex;
                break;
        }

        return new ResourceLocation(HotChickens.MOD_ID, location);
    }
}