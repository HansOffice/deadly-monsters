package com.dmonsters.client;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.FallenLeaderEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public final class FallenLeaderRenderer extends MobRenderer<FallenLeaderEntity, FallenLeaderModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(
            DeadlyMonsters.MOD_ID, "textures/entity/fallen_leader.png");

    public FallenLeaderRenderer(EntityRendererProvider.Context context) {
        super(context, new FallenLeaderModel(context.bakeLayer(FallenLeaderModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(FallenLeaderEntity entity) {
        return TEXTURE;
    }

    }
