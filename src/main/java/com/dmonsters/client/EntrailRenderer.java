package com.dmonsters.client;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.EntrailEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public final class EntrailRenderer extends MobRenderer<EntrailEntity, EntrailModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(
            DeadlyMonsters.MOD_ID, "textures/entity/entrail.png");

    public EntrailRenderer(EntityRendererProvider.Context context) {
        super(context, new EntrailModel(context.bakeLayer(EntrailModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntrailEntity entity) {
        return TEXTURE;
    }

    }
