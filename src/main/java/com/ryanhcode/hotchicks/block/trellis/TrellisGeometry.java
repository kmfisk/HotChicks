package com.ryanhcode.hotchicks.block.trellis;

import com.mojang.datafixers.util.Pair;
import com.ryanhcode.hotchicks.HotChickens;
import com.ryanhcode.hotchicks.block.crop.TrellisCropBlock;
import net.minecraft.client.renderer.model.*;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelConfiguration;
import net.minecraftforge.client.model.geometry.IModelGeometry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.function.Function;

public class TrellisGeometry implements IModelGeometry<TrellisGeometry> {

    @Override
    public IBakedModel bake(IModelConfiguration owner, ModelBakery bakery, Function<RenderMaterial, TextureAtlasSprite> spriteGetter, IModelTransform modelTransform, ItemOverrideList overrides, ResourceLocation modelLocation) {
        return new TrellisModel();
    }

    @Override
    public Collection<RenderMaterial> getTextures(IModelConfiguration owner, Function<ResourceLocation, IUnbakedModel> modelGetter, Set<Pair<String, String>> missingTextureErrors) {
        return new ArrayList<RenderMaterial>() {{
            add(new RenderMaterial(AtlasTexture.LOCATION_BLOCKS, new ResourceLocation(HotChickens.MOD_ID, "block/trellis/none/none")));
            for (TrellisCropBlock t : TrellisCropBlock.values()) {
                if (t == TrellisCropBlock.NONE) continue;
                add(new RenderMaterial(AtlasTexture.LOCATION_BLOCKS, new ResourceLocation(HotChickens.MOD_ID, "block/trellis/" + t.name + "/" + t.name + "_stage0")));
                add(new RenderMaterial(AtlasTexture.LOCATION_BLOCKS, new ResourceLocation(HotChickens.MOD_ID, "block/trellis/" + t.name + "/" + t.name + "_stage1")));
                add(new RenderMaterial(AtlasTexture.LOCATION_BLOCKS, new ResourceLocation(HotChickens.MOD_ID, "block/trellis/" + t.name + "/" + t.name + "_stage2")));
                add(new RenderMaterial(AtlasTexture.LOCATION_BLOCKS, new ResourceLocation(HotChickens.MOD_ID, "block/trellis/" + t.name + "/" + t.name + "_stage3")));
                add(new RenderMaterial(AtlasTexture.LOCATION_BLOCKS, new ResourceLocation(HotChickens.MOD_ID, "block/trellis/" + t.name + "/" + t.name + "_stage4")));
                add(new RenderMaterial(AtlasTexture.LOCATION_BLOCKS, new ResourceLocation(HotChickens.MOD_ID, "block/trellis/" + t.name + "/" + t.name + "_stage5")));
            }
        }};
    }
}