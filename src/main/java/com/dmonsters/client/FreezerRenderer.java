package com.dmonsters.client;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.FreezerEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public final class FreezerRenderer extends MobRenderer<FreezerEntity, FreezerModel> {
    private static final ResourceLocation IDLE_TEXTURE = ResourceLocation.fromNamespaceAndPath(
            DeadlyMonsters.MOD_ID, "textures/entity/freezer_idle.png");
    private static final ResourceLocation ANGRY_TEXTURE = ResourceLocation.fromNamespaceAndPath(
            DeadlyMonsters.MOD_ID, "textures/entity/freezer_angry.png");

    public FreezerRenderer(EntityRendererProvider.Context context) {
        super(context, new FreezerModel(context.bakeLayer(FreezerModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(FreezerEntity entity) {
        return entity.isAggressive() ? ANGRY_TEXTURE : IDLE_TEXTURE;
    }

        }
