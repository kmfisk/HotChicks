package com.ryanhcode.hotchicks.block.trellis;

import com.google.common.collect.ImmutableList;
import com.ryanhcode.hotchicks.HotChickens;
import com.ryanhcode.hotchicks.block.TrellisBlock;
import com.ryanhcode.hotchicks.block.crop.TrellisCropBlock;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.client.model.data.IDynamicBakedModel;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.pipeline.BakedQuadBuilder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TrellisModel implements IDynamicBakedModel {
    public static final ResourceLocation TEXTURE = new ResourceLocation(HotChickens.MOD_ID, "block/trellis/none/none");


    private TextureAtlasSprite getTexture(BlockState state) {
        String crop = state.getValue(TrellisBlock.CROP).name;
        int stage = state.getValue(TrellisBlock.AGE);
        return Minecraft.getInstance().getTextureAtlas(AtlasTexture.LOCATION_BLOCKS).apply(

                new ResourceLocation(HotChickens.MOD_ID, crop.equals("none") ? "block/trellis/none/none" : "block/trellis/" + crop + "/" + crop + "_stage" + stage)

        );
    }

    private void putVertex(BakedQuadBuilder builder, Vector3d normal,
                           double x, double y, double z, float u, float v, TextureAtlasSprite sprite, float r, float g, float b) {

        ImmutableList<VertexFormatElement> elements = builder.getVertexFormat().getElements().asList();
        for (int j = 0; j < elements.size(); j++) {
            VertexFormatElement e = elements.get(j);
            switch (e.getUsage()) {
                case POSITION:
                    builder.put(j, (float) x, (float) y, (float) z, 1.0f);
                    break;
                case COLOR:
                    builder.put(j, r, g, b, 1.0f);
                    break;
                case UV:
                    switch (e.getIndex()) {
                        case 0:
                            float iu = sprite.getU(u);
                            float iv = sprite.getV(v);
                            builder.put(j, iu, iv);
                            break;
                        case 2:
                            builder.put(j, (short) 0, (short) 0);
                            break;
                        default:
                            builder.put(j);
                            break;
                    }
                    break;
                case NORMAL:
                    builder.put(j, (float) normal.x(), (float) normal.y(), (float) normal.z());
                    break;
                default:
                    builder.put(j);
                    break;
            }
        }
    }

    private BakedQuad createQuad(Vector3d v1, Vector3d v2, Vector3d v3, Vector3d v4, TextureAtlasSprite sprite) {
        Vector3d normal = v3.subtract(v2).cross(v1.subtract(v2)).normalize().multiply(-1.0, -1.0, -1.0);
        int tw = sprite.getWidth();
        int th = sprite.getHeight();

        BakedQuadBuilder builder = new BakedQuadBuilder(sprite);
        Direction facingFromVector = Direction.getNearest(normal.x(), normal.y(), normal.z()).getOpposite();
        builder.setQuadOrientation(facingFromVector);
        builder.setContractUVs(true);
        putVertex(builder, normal, v1.x(), v1.y(), v1.z(), 0, 0, sprite, 1.0f, 1.0f, 1.0f);
        putVertex(builder, normal, v2.x(), v2.y(), v2.z(), 0, th, sprite, 1.0f, 1.0f, 1.0f);
        putVertex(builder, normal, v3.x(), v3.y(), v3.z(), tw, th, sprite, 1.0f, 1.0f, 1.0f);
        putVertex(builder, normal, v4.x(), v4.y(), v4.z(), tw, 0, sprite, 1.0f, 1.0f, 1.0f);
        BakedQuad built = builder.build();
        return built;
    }

    private BakedQuad createQuadRev(Vector3d v1, Vector3d v2, Vector3d v3, Vector3d v4, TextureAtlasSprite sprite) {
        Vector3d normal = v3.subtract(v2).cross(v1.subtract(v2)).normalize().multiply(-1.0, -1.0, -1.0);
        int tw = sprite.getWidth();
        int th = sprite.getHeight();

        BakedQuadBuilder builder = new BakedQuadBuilder(sprite);
        Direction facingFromVector = Direction.getNearest(normal.x(), normal.y(), normal.z()).getOpposite();
        builder.setQuadOrientation(facingFromVector);
        builder.setContractUVs(true);
        putVertex(builder, normal, v1.x(), v1.y(), v1.z(), 0, th, sprite, 1.0f, 1.0f, 1.0f);
        putVertex(builder, normal, v2.x(), v2.y(), v2.z(), 0, 0, sprite, 1.0f, 1.0f, 1.0f);
        putVertex(builder, normal, v3.x(), v3.y(), v3.z(), tw, 0, sprite, 1.0f, 1.0f, 1.0f);
        putVertex(builder, normal, v4.x(), v4.y(), v4.z(), tw, th, sprite, 1.0f, 1.0f, 1.0f);
        BakedQuad built = builder.build();
        return built;
    }

    private static Vector3d v(double x, double y, double z) {
        return new Vector3d(x, y, z);
    }

    @Nonnull
    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand, @Nonnull IModelData extraData) {
        TextureAtlasSprite none = getTexture(state.setValue(TrellisBlock.CROP, TrellisCropBlock.NONE));
        TextureAtlasSprite texture = getTexture(state);
        List<BakedQuad> quads = new ArrayList<>();
        double l = 1.0;
        double r = 0.0;

        draw(state, none, quads);
        draw(state, texture, quads);


        /*quads.add(
                createQuad(
                        v(0.5, 0, 0).add(vec).rotateYaw(toRad2),
                        v(0.5, 1, 0).add(vec).rotateYaw(toRad2),
                        v(0.5, 1, 1).add(vec).rotateYaw(toRad2),
                        v(0.5, 0, 1).add(vec).rotateYaw(toRad2),
                        texture));*/

        /*if(angle == 0.0 || angle == 360 || angle == 180) {
            quads.add(
                    createQuad(
                            v(r, r, r).subtract(0.5, 0.0, 0.5).rotateYaw(rad).add(0.5, 0.0, 0.5),
                            v(r, l, r).subtract(0.5, 0.0, 0.5).rotateYaw(rad).add(0.5, 0.0, 0.5),
                            v(r, l, l).subtract(0.5, 0.0, 0.5).rotateYaw(rad).add(0.5, 0.0, 0.5),
                            v(r, r, l).subtract(0.5, 0.0, 0.5).rotateYaw(rad).add(0.5, 0.0, 0.5),
                            texture));
            quads.add(
                    createQuad(
                            v(r, r, l).subtract(0.5, 0.0, 0.5).rotateYaw(rad).add(0.5, 0.0, 0.5),
                            v(r, l, l).subtract(0.5, 0.0, 0.5).rotateYaw(rad).add(0.5, 0.0, 0.5),
                            v(r, l, r).subtract(0.5, 0.0, 0.5).rotateYaw(rad).add(0.5, 0.0, 0.5),
                            v(r, r, r).subtract(0.5, 0.0, 0.5).rotateYaw(rad).add(0.5, 0.0, 0.5),
                            texture));
        }else{
            float radp180 = (float) Math.toRadians(angle + 180);
            quads.add(
                    createQuad(
                            v(r, r, r).subtract(0.5, 0, 0.5).rotateYaw(radp180).add(0.5, 0, 0.5),
                            v(r, l, r).subtract(0.5, 0, 0.5).rotateYaw(radp180).add(0.5, 0, 0.5),
                            v(r, l, l).subtract(0.5, 0, 0.5).rotateYaw(radp180).add(0.5, 0, 0.5),
                            v(r, r, l).subtract(0.5, 0, 0.5).rotateYaw(radp180).add(0.5, 0, 0.5),
                            texture));

            if(rad == 90) {
                quads.add(
                        createQuadRev(
                                v(r, l, r).subtract(0.5, 0, 0.5).rotateYaw(radp180).add(0.5, 0.0, 0.5).rotateRoll((float) Math.toRadians(-90)).add(1.0, 0.0, 0.0),
                                v(r, r, r).subtract(0.5, 0, 0.5).rotateYaw(radp180).add(0.5, 0.0, 0.5).rotateRoll((float) Math.toRadians(-90)).add(1.0, 0.0, 0.0),
                                v(r, r, l).subtract(0.5, 0, 0.5).rotateYaw(radp180).add(0.5, 0.0, 0.5).rotateRoll((float) Math.toRadians(-90)).add(1.0, 0.0, 0.0),
                                v(r, l, l).subtract(0.5, 0, 0.5).rotateYaw(radp180).add(0.5, 0.0, 0.5).rotateRoll((float) Math.toRadians(-90)).add(1.0, 0.0, 0.0),
                                texture));
            }

        }*/


        return quads;
    }

    private void draw(BlockState state, TextureAtlasSprite texture, List<BakedQuad> quads) {
        Direction direction = state.getValue(TrellisBlock.FACING);
        double angle = direction.toYRot() + 90.0;

        float rad = (float) Math.toRadians(angle);

        //.rotateYaw(rad)
        float toRad = (float) Math.toRadians(15);
        float toRad3 = (float) Math.toRadians(180);
        float toRad2 = (float) Math.toRadians(90);

        Vector3d vec = new Vector3d(-0.5, 0.0, -0.5);
        Vector3d v = new Vector3d(0.45, 0, 0).yRot(rad).reverse();
        Vector3d v2 = new Vector3d(-0.45, 0, -1).yRot(rad).reverse();
        Vector3d v3 = new Vector3d(0, 1.0, 1.0);
        if (direction == Direction.WEST || direction == Direction.EAST) {
            quads.add(
                    createQuad(
                            v(0.5, 0, 0).add(vec).yRot(rad).add(0.5, 0, 0.5).add(v),
                            v(0.5, 1, 0).add(vec).yRot(rad).add(0.5, 0, 0.5).add(v),
                            v(0.5, 1, 1).add(vec).yRot(rad).add(0.5, 0, 0.5).add(v),
                            v(0.5, 0, 1).add(vec).yRot(rad).add(0.5, 0, 0.5).add(v),
                            texture));

            quads.add(
                    createQuadRev(
                            v(0.5, 0, 1).add(vec).yRot(rad).add(0.5, 0, 0.5).add(v).xRot(toRad3).add(v3),
                            v(0.5, 1, 1).add(vec).yRot(rad).add(0.5, 0, 0.5).add(v).xRot(toRad3).add(v3),
                            v(0.5, 1, 0).add(vec).yRot(rad).add(0.5, 0, 0.5).add(v).xRot(toRad3).add(v3),
                            v(0.5, 0, 0).add(vec).yRot(rad).add(0.5, 0, 0.5).add(v).xRot(toRad3).add(v3),
                            texture));
        } else {
            if (direction == Direction.NORTH) {
                quads.add(
                        createQuad(
                                v(0.5, 0, 0).add(vec).yRot(rad).add(0.5, 0, 0.5).add(v2).yRot(toRad3).add(new Vector3d(1.9, 0, 0).yRot(rad)),
                                v(0.5, 1, 0).add(vec).yRot(rad).add(0.5, 0, 0.5).add(v2).yRot(toRad3).add(new Vector3d(1.9, 0, 0).yRot(rad)),
                                v(0.5, 1, 1).add(vec).yRot(rad).add(0.5, 0, 0.5).add(v2).yRot(toRad3).add(new Vector3d(1.9, 0, 0).yRot(rad)),
                                v(0.5, 0, 1).add(vec).yRot(rad).add(0.5, 0, 0.5).add(v2).yRot(toRad3).add(new Vector3d(1.9, 0, 0).yRot(rad)),
                                texture));
            } else {
                quads.add(
                        createQuad(
                                v(0.5, 0, 0).add(vec).yRot(rad).add(0.5, 0, 0.5).add(v2).yRot(toRad3).add(new Vector3d(2, 0, 0).yRot(rad)).add(2, 0, 2.1),
                                v(0.5, 1, 0).add(vec).yRot(rad).add(0.5, 0, 0.5).add(v2).yRot(toRad3).add(new Vector3d(2, 0, 0).yRot(rad)).add(2, 0, 2.1),
                                v(0.5, 1, 1).add(vec).yRot(rad).add(0.5, 0, 0.5).add(v2).yRot(toRad3).add(new Vector3d(2, 0, 0).yRot(rad)).add(2, 0, 2.1),
                                v(0.5, 0, 1).add(vec).yRot(rad).add(0.5, 0, 0.5).add(v2).yRot(toRad3).add(new Vector3d(2, 0, 0).yRot(rad)).add(2, 0, 2.1),
                                texture));
            }
            quads.add(
                    createQuadRev(
                            v(0.5, 0, 1).add(vec).yRot(rad).add(0.5, 0, 0.5).add(v).xRot(toRad3).add(v3),
                            v(0.5, 1, 1).add(vec).yRot(rad).add(0.5, 0, 0.5).add(v).xRot(toRad3).add(v3),
                            v(0.5, 1, 0).add(vec).yRot(rad).add(0.5, 0, 0.5).add(v).xRot(toRad3).add(v3),
                            v(0.5, 0, 0).add(vec).yRot(rad).add(0.5, 0, 0.5).add(v).xRot(toRad3).add(v3),
                            texture));
        }
    }

    @Override
    public boolean useAmbientOcclusion() {
        return false;
    }

    @Override
    public boolean isGui3d() {
        return false;
    }

    @Override
    public boolean usesBlockLight() {
        return false;
    }

    @Override
    public boolean isCustomRenderer() {
        return false;
    }

    @Override
    public TextureAtlasSprite getParticleIcon() {
        return Minecraft.getInstance().getTextureAtlas(AtlasTexture.LOCATION_BLOCKS).apply(TEXTURE);
    }

    @Override
    public ItemOverrideList getOverrides() {
        return ItemOverrideList.EMPTY;
    }

    @Override
    public ItemCameraTransforms getTransforms() {
        return ItemCameraTransforms.NO_TRANSFORMS;
    }
}