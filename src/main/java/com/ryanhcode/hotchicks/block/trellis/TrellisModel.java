package com.ryanhcode.hotchicks.block.trellis;

import com.google.common.collect.ImmutableList;
import com.ryanhcode.hotchicks.HotChickens;
import com.ryanhcode.hotchicks.block.TrellisBlock;
import com.ryanhcode.hotchicks.block.crop.TrellisCrop;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.data.EmptyModelData;
import net.minecraftforge.client.model.data.IDynamicBakedModel;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.pipeline.BakedQuadBuilder;
import net.minecraft.util.math.vector.Vector3d;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

public class TrellisModel implements IDynamicBakedModel {
    public static final ResourceLocation TEXTURE = new ResourceLocation(HotChickens.MODID, "block/trellis/none/none");



    private TextureAtlasSprite getTexture(BlockState state) {
        String crop = state.get(TrellisBlock.CROP).name;
        int stage = state.get(TrellisBlock.AGE);
        return Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(

                new ResourceLocation(HotChickens.MODID, crop.equals("none") ? "block/trellis/none/none" : "block/trellis/" + crop + "/" + crop + "_stage" + stage)

        );
    }

    private void putVertex(BakedQuadBuilder builder, Vector3d normal,
                           double x, double y, double z, float u, float v, TextureAtlasSprite sprite, float r, float g, float b) {

        ImmutableList<VertexFormatElement> elements = builder.getVertexFormat().getElements().asList();
        for (int j = 0 ; j < elements.size() ; j++) {
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
                            float iu = sprite.getInterpolatedU(u);
                            float iv = sprite.getInterpolatedV(v);
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
                    builder.put(j, (float) normal.getX(), (float) normal.getY(), (float) normal.getZ());
                    break;
                default:
                    builder.put(j);
                    break;
            }
        }
    }

    private BakedQuad createQuad(Vector3d v1, Vector3d v2, Vector3d v3, Vector3d v4, TextureAtlasSprite sprite) {
        Vector3d normal = v3.subtract(v2).crossProduct(v1.subtract(v2)).normalize().mul(-1.0,-1.0,-1.0);
        int tw = sprite.getWidth();
        int th = sprite.getHeight();

        BakedQuadBuilder builder = new BakedQuadBuilder(sprite);
        Direction facingFromVector = Direction.getFacingFromVector(normal.getX(), normal.getY(), normal.getZ()).getOpposite();
        builder.setQuadOrientation(facingFromVector);
        builder.setContractUVs(true);
        putVertex(builder, normal, v1.getX(), v1.getY(), v1.getZ(), 0, 0, sprite, 1.0f, 1.0f, 1.0f);
        putVertex(builder, normal, v2.getX(), v2.getY(), v2.getZ(), 0, th, sprite, 1.0f, 1.0f, 1.0f);
        putVertex(builder, normal, v3.getX(), v3.getY(), v3.getZ(), tw, th, sprite, 1.0f, 1.0f, 1.0f);
        putVertex(builder, normal, v4.getX(), v4.getY(), v4.getZ(), tw, 0, sprite, 1.0f, 1.0f, 1.0f);
        BakedQuad built = builder.build();
        return built;
    }

    private BakedQuad createQuadRev(Vector3d v1, Vector3d v2, Vector3d v3, Vector3d v4, TextureAtlasSprite sprite) {
        Vector3d normal = v3.subtract(v2).crossProduct(v1.subtract(v2)).normalize().mul(-1.0,-1.0,-1.0);
        int tw = sprite.getWidth();
        int th = sprite.getHeight();

        BakedQuadBuilder builder = new BakedQuadBuilder(sprite);
        Direction facingFromVector = Direction.getFacingFromVector(normal.getX(), normal.getY(), normal.getZ()).getOpposite();
        builder.setQuadOrientation(facingFromVector);
        builder.setContractUVs(true);
        putVertex(builder, normal, v1.getX(), v1.getY(), v1.getZ(), 0, 0, sprite, 1.0f, 1.0f, 1.0f);
        putVertex(builder, normal, v2.getX(), v2.getY(), v2.getZ(), 0, th, sprite, 1.0f, 1.0f, 1.0f);
        putVertex(builder, normal, v3.getX(), v3.getY(), v3.getZ(), tw, th, sprite, 1.0f, 1.0f, 1.0f);
        putVertex(builder, normal, v4.getX(), v4.getY(), v4.getZ(), tw, 0, sprite, 1.0f, 1.0f, 1.0f);
        BakedQuad built = builder.build();
        return built;
    }

    private static Vector3d v(double x, double y, double z) {
        return new Vector3d(x, y, z);
    }

    @Nonnull
    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand, @Nonnull IModelData extraData) {
        TextureAtlasSprite texture = getTexture(state);
        List<BakedQuad> quads = new ArrayList<>();
        double l = 0.9999;
        double r = 0.0001;

        Direction direction = state.get(TrellisBlock.FACING);
        double angle = direction.getHorizontalAngle() + 90.0;
        float rad = (float) Math.toRadians(angle);
        if(angle == 0.0 || angle == 360 || angle == 180) {
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

        }



        return quads;
    }

    @Override
    public boolean isAmbientOcclusion() {
        return false;
    }

    @Override
    public boolean isGui3d() {
        return false;
    }

    @Override
    public boolean isSideLit() {
        return false;
    }

    @Override
    public boolean isBuiltInRenderer() {
        return false;
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {
        return Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(TEXTURE);
    }

    @Override
    public ItemOverrideList getOverrides() {
        return ItemOverrideList.EMPTY;
    }

    @Override
    public ItemCameraTransforms getItemCameraTransforms() {
        return ItemCameraTransforms.DEFAULT;
    }
}