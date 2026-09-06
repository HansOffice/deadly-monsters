package com.dmonsters.client;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.UnbornBabyEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public final class UnbornBabyRenderer extends MobRenderer<UnbornBabyEntity, UnbornBabyModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(
            DeadlyMonsters.MOD_ID, "textures/entity/unborn_baby.png");

    public UnbornBabyRenderer(EntityRendererProvider.Context context) {
        super(context, new UnbornBabyModel(context.bakeLayer(UnbornBabyModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(UnbornBabyEntity entity) {
        return TEXTURE;
    }

        }
