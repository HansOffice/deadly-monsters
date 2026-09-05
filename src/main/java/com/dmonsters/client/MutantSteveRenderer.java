package com.dmonsters.client;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.MutantSteveEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public final class MutantSteveRenderer extends MobRenderer<MutantSteveEntity, MutantSteveRenderState, MutantSteveModel> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(
            DeadlyMonsters.MOD_ID, "textures/entity/mutant_steve.png");

    public MutantSteveRenderer(EntityRendererProvider.Context context) {
        super(context, new MutantSteveModel(context.bakeLayer(MutantSteveModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public Identifier getTextureLocation(MutantSteveRenderState state) {
        return TEXTURE;
    }

    @Override
    public MutantSteveRenderState createRenderState() {
        return new MutantSteveRenderState();
    }

    @Override
    public void extractRenderState(MutantSteveEntity entity, MutantSteveRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.armsRaised = entity.isArmsRaised();
        state.attackTime = entity.getAttackAnim(partialTicks);
    }

    @Override
    protected void scale(MutantSteveRenderState state, PoseStack poseStack) {
        poseStack.scale(1.2F, 1.2F, 1.2F);
    }
}
