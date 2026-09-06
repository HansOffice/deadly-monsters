package com.dmonsters.client;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.MutantSteveEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public final class MutantSteveRenderer extends MobRenderer<MutantSteveEntity, MutantSteveModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(
            DeadlyMonsters.MOD_ID, "textures/entity/mutant_steve.png");

    public MutantSteveRenderer(EntityRendererProvider.Context context) {
        super(context, new MutantSteveModel(context.bakeLayer(MutantSteveModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(MutantSteveEntity entity) {
        return TEXTURE;
    }

            @Override
    protected void scale(MutantSteveEntity entity, PoseStack poseStack, float partialTickTime) {
        poseStack.scale(1.2F, 1.2F, 1.2F);
    }
}
