package com.dmonsters.client;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.StrangerEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public final class StrangerRenderer extends MobRenderer<StrangerEntity, StrangerRenderState, StrangerModel> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(
            DeadlyMonsters.MOD_ID, "textures/entity/stranger.png");

    public StrangerRenderer(EntityRendererProvider.Context context) {
        super(context, new StrangerModel(context.bakeLayer(StrangerModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public Identifier getTextureLocation(StrangerRenderState state) {
        return TEXTURE;
    }

    @Override
    public StrangerRenderState createRenderState() {
        return new StrangerRenderState();
    }

    @Override
    public void extractRenderState(StrangerEntity entity, StrangerRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.attackTime = entity.getAttackAnim(partialTicks);
    }
}
