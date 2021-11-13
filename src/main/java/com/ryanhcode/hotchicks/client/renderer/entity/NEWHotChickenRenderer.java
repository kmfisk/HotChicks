package com.ryanhcode.hotchicks.client.renderer.entity;

/*
import com.mojang.blaze3d.matrix.MatrixStack;
import com.ryanhcode.hotchicks.HotChickens;
import com.ryanhcode.hotchicks.client.renderer.entity.model.HotChickenModel;
import com.ryanhcode.hotchicks.entity.NEWHotChickenEntity;
import com.ryanhcode.hotchicks.entity.base.Sex;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NEWHotChickenRenderer extends MobRenderer<NEWHotChickenEntity, HotChickenModel> {
    public static final String[] AMERAUCANAS = new String[]{"black", "blue", "bluewheaten", "brown", "buff", "lavender", "lightbrown"};
    public static final String[] MARANS = new String[]{"blackbirchen", "blackcopper", "cuckoo", "goldcuckoo"};
    public static final String[] ORPINGTONS = new String[]{"black", "blue", "buff", "white"};
    public static final String[] RHODE_ISLANDS = new String[]{"deepred", "lightred", "red"};
    public static final String[] SILKIES = new String[]{"black", "blue", "buff", "partridge", "white"};
    private final HotChickenModel roosterModel;
    private final HotChickenModel henModel;
    private final HotChickenModel chickModel;

    public NEWHotChickenRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new HotChickenModel.Hen(), 0.3F);
        roosterModel = new HotChickenModel.Rooster();
        henModel = new HotChickenModel.Hen();
        chickModel = new HotChickenModel.Chick();
    }

    @Override
    public void render(NEWHotChickenEntity chicken, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        boolean isChild = chicken.isBaby();
        if (isChild)
            model = chickModel;
        else
            model = chicken.getSex() == Sex.MALE ? roosterModel : henModel;

        super.render(chicken, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public ResourceLocation getTextureLocation(NEWHotChickenEntity entity) {
        String location;
        String sex = entity.getSex() == Sex.MALE ? "rooster.png" : "hen.png";
        int baby;
        int variant = entity.getVariant();

        switch (entity.getBreedFromVariant(variant)) {
            default: case JUNGLEFOWL:
                location = "textures/entity/chicken/junglefowl/junglefowl_" + sex;
                baby = 1;
                break;
            case AMERAUCANA:
                location = "textures/entity/chicken/ameraucanas/ameraucana_" + AMERAUCANAS[variant - 1] + sex;
                baby = 2;
                break;
            case BARRED_ROCK:
                location = "textures/entity/chicken/barred_rocks/barredrock_" + sex;
                baby = 3;
                break;
            case LEGHORN:
                location = "textures/entity/chicken/leghorns/leghorn_" + sex;
                baby = 4;
                break;
            case MARANS:
                location = "textures/entity/chicken/marans/marans_" + MARANS[variant - 10] + sex;
                baby = 5;
                break;
            case ORPINGTON:
                location = "textures/entity/chicken/orpingtons/orpington_" + ORPINGTONS[variant - 14] + sex;
                baby = 6;
                break;
            case RHODE_ISLAND_RED:
                location = "textures/entity/chicken/rhode_islands/rhodeisland_" + RHODE_ISLANDS[variant - 18] + sex;
                baby = 7;
                break;
            case SILKIE:
                location = "textures/entity/chicken/silkies/silkie_" + SILKIES[variant - 21] + sex;
                baby = 8;
                break;
        }

        return new ResourceLocation(HotChickens.MOD_ID, !entity.isBaby() ? location : "textures/entity/chicken/chicks/chick_" + baby + ".png");
    }
}
*/
