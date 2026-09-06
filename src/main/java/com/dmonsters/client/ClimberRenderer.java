package com.dmonsters.client;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.ClimberEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public final class ClimberRenderer extends MobRenderer<ClimberEntity, ClimberModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(
            DeadlyMonsters.MOD_ID, "textures/entity/climber.png");

    public ClimberRenderer(EntityRendererProvider.Context context) {
        super(context, new ClimberModel(context.bakeLayer(ClimberModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(ClimberEntity entity) {
        return TEXTURE;
    }

        }
