package com.ryanhcode.hotchicks.block.trellis;

import net.minecraftforge.client.model.IModelLoader;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraft.resources.IResourceManager;

public class TrellisModelLoader implements IModelLoader<TrellisGeometry> {

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {

    }

    @Override
    public TrellisGeometry read(JsonDeserializationContext deserializationContext, JsonObject modelContents) {
        return new TrellisGeometry();
    }
}