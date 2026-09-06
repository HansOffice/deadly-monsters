package com.dmonsters.client;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.PresentEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public final class PresentRenderer extends MobRenderer<PresentEntity, PresentModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(
            DeadlyMonsters.MOD_ID, "textures/entity/present.png");

    public PresentRenderer(EntityRendererProvider.Context context) {
        super(context, new PresentModel(context.bakeLayer(PresentModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(PresentEntity entity) {
        return TEXTURE;
    }

    }
