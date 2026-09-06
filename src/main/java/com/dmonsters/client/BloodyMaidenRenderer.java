package com.dmonsters.client;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.BloodyMaidenEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public final class BloodyMaidenRenderer extends MobRenderer<BloodyMaidenEntity, BloodyMaidenModel> {
    private static final ResourceLocation NORMAL_TEXTURE = ResourceLocation.fromNamespaceAndPath(
            DeadlyMonsters.MOD_ID, "textures/entity/bloody_maiden.png");
    private static final ResourceLocation TRIGGERED_TEXTURE = ResourceLocation.fromNamespaceAndPath(
            DeadlyMonsters.MOD_ID, "textures/entity/bloody_maiden_triggered.png");

    public BloodyMaidenRenderer(EntityRendererProvider.Context context) {
        super(context, new BloodyMaidenModel(context.bakeLayer(BloodyMaidenModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(BloodyMaidenEntity entity) {
        return entity.isTriggered() ? TRIGGERED_TEXTURE : NORMAL_TEXTURE;
    }

        }
