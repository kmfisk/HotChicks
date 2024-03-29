package com.github.kmfisk.hotchicks.client.renderer.entity;

import com.github.kmfisk.hotchicks.HotChicks;
import com.github.kmfisk.hotchicks.client.renderer.entity.layers.ChickenBandLayer;
import com.github.kmfisk.hotchicks.client.renderer.entity.model.HotChickenModel;
import com.github.kmfisk.hotchicks.entity.HotChickenEntity;
import com.github.kmfisk.hotchicks.entity.LivestockEntity;
import com.github.kmfisk.hotchicks.entity.base.ChickenBreeds;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HotChickenRenderer extends MobRenderer<HotChickenEntity, HotChickenModel> {
    public static final String[] AMERAUCANAS = new String[]{"black", "blue", "blue_wheaten", "brown", "buff", "lavender", "light_brown"};
    public static final String[] MARANS = new String[]{"black_birchen", "black_copper", "cuckoo", "gold_cuckoo"};
    public static final String[] ORPINGTONS = new String[]{"black", "blue", "buff", "white"};
    public static final String[] RHODE_ISLANDS = new String[]{"deep_red", "light_red", "red"};
    public static final String[] SILKIES = new String[]{"black", "blue", "buff", "partridge", "white"};
    public final HotChickenModel roosterModel;
    public final HotChickenModel roosterSilkieModel;
    public final HotChickenModel henModel;
    public final HotChickenModel henSilkieModel;
    public final HotChickenModel chickModel;

    public HotChickenRenderer(EntityRendererManager rendererManager) {
        super(rendererManager, new HotChickenModel.Hen(), 0.3F);
        this.addLayer(new ChickenBandLayer(this));
        roosterModel = new HotChickenModel.Rooster();
        roosterSilkieModel = new HotChickenModel.RoosterSilkie();
        henModel = new HotChickenModel.Hen();
        henSilkieModel = new HotChickenModel.HenSilkie();
        chickModel = new HotChickenModel.Chick();
    }

    @Override
    public void render(HotChickenEntity chicken, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
        boolean isChild = chicken.isBaby();
        if (isChild)
            model = chickModel;
        else if (chicken.getBreedFromVariant() == ChickenBreeds.SILKIE)
            model = chicken.getSex() == LivestockEntity.Sex.MALE ? roosterSilkieModel : henSilkieModel;
        else
            model = chicken.getSex() == LivestockEntity.Sex.MALE ? roosterModel : henModel;

        super.render(chicken, entityYaw, partialTicks, matrixStack, buffer, packedLight);
    }

    @Override
    protected void scale(HotChickenEntity chicken, MatrixStack matrixStack, float partialTicks) {
        ChickenBreeds chickenBreed = chicken.getBreedFromVariant();
        if (chickenBreed.equals(ChickenBreeds.LEGHORN)) matrixStack.scale(0.9F, 0.9F, 0.9F);
        if (chicken.isBaby()) matrixStack.scale(0.5F, 0.5F, 0.5F);
        super.scale(chicken, matrixStack, partialTicks);
    }

    @Override
    public ResourceLocation getTextureLocation(HotChickenEntity chicken) {
        String location;
        String sex = chicken.getSex() == LivestockEntity.Sex.MALE ? "_rooster.png" : "_hen.png";
        int variant = chicken.getVariant();

        if (chicken.isBaby()) {
            int baby;
            switch (variant) {
                case 9:
                case 24:
                case 32:
                default:
                    baby = 1;
                    break;
                case 26:
                case 30:
                    baby = 2;
                    break;
                case 23:
                case 27:
                    baby = 3;
                    break;
                case 13:
                case 25:
                case 31:
                    baby = 4;
                    break;
                case 29:
                    baby = 5;
                    break;
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 10:
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                case 22:
                    baby = 6;
                    break;
                case 1:
                case 14:
                case 21:
                case 28:
                    baby = 7;
                    break;
                case 8:
                case 11:
                case 12:
                    baby = 8;
                    break;
            }

            return new ResourceLocation(HotChicks.MOD_ID, "textures/entity/chicken/chick_" + baby + ".png");
        }

        switch (chicken.getBreedFromVariant()) {
            default:
            case JUNGLEFOWL:
                location = "textures/entity/chicken/junglefowl/junglefowl" + sex;
                break;
            case AMERAUCANA:
                location = "textures/entity/chicken/ameraucana/" + AMERAUCANAS[variant - 1] + sex;
                break;
            case BARRED_ROCK:
                location = "textures/entity/chicken/barred_rock/barred_rock" + sex;
                break;
            case LEGHORN:
                location = "textures/entity/chicken/leghorn/leghorn" + sex;
                break;
            case MARANS:
                location = "textures/entity/chicken/marans/" + MARANS[variant - 10] + sex;
                break;
            case OLIVE_EGGER:
                location = "textures/entity/chicken/ameraucana/" + AMERAUCANAS[variant - 14] + sex;
                break;
            case ORPINGTON:
                location = "textures/entity/chicken/orpington/" + ORPINGTONS[variant - 21] + sex;
                break;
            case RHODE_ISLAND_RED:
                location = "textures/entity/chicken/rhode_island/" + RHODE_ISLANDS[variant - 25] + sex;
                break;
            case SILKIE:
                location = "textures/entity/chicken/silkie/" + SILKIES[variant - 28] + sex;
                break;
        }

        return new ResourceLocation(HotChicks.MOD_ID, location);
    }
}