package com.dmonsters.client;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.TopielecEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public final class TopielecRenderer extends MobRenderer<TopielecEntity, TopielecModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(
            DeadlyMonsters.MOD_ID, "textures/entity/topielec.png");

    public TopielecRenderer(EntityRendererProvider.Context context) {
        super(context, new TopielecModel(context.bakeLayer(TopielecModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(TopielecEntity entity) {
        return TEXTURE;
    }

        }
