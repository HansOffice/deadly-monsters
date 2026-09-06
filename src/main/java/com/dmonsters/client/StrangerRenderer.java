package com.dmonsters.client;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.StrangerEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public final class StrangerRenderer extends MobRenderer<StrangerEntity, StrangerModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(
            DeadlyMonsters.MOD_ID, "textures/entity/stranger.png");

    public StrangerRenderer(EntityRendererProvider.Context context) {
        super(context, new StrangerModel(context.bakeLayer(StrangerModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(StrangerEntity entity) {
        return TEXTURE;
    }

        }
