package com.dmonsters.client;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.HauntedCowEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public final class HauntedCowRenderer extends MobRenderer<HauntedCowEntity, HauntedCowModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(
            DeadlyMonsters.MOD_ID, "textures/entity/haunted_cow.png");

    public HauntedCowRenderer(EntityRendererProvider.Context context) {
        super(context, new HauntedCowModel(context.bakeLayer(HauntedCowModel.LAYER_LOCATION)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(HauntedCowEntity entity) {
        return TEXTURE;
    }

    }
