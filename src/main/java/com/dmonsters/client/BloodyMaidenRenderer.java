package com.dmonsters.client;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.BloodyMaidenEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public final class BloodyMaidenRenderer extends MobRenderer<BloodyMaidenEntity, BloodyMaidenRenderState, BloodyMaidenModel> {
    private static final Identifier NORMAL_TEXTURE = Identifier.fromNamespaceAndPath(
            DeadlyMonsters.MOD_ID, "textures/entity/bloody_maiden.png");
    private static final Identifier TRIGGERED_TEXTURE = Identifier.fromNamespaceAndPath(
            DeadlyMonsters.MOD_ID, "textures/entity/bloody_maiden_triggered.png");

    public BloodyMaidenRenderer(EntityRendererProvider.Context context) {
        super(context, new BloodyMaidenModel(context.bakeLayer(BloodyMaidenModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public Identifier getTextureLocation(BloodyMaidenRenderState state) {
        return state.triggered ? TRIGGERED_TEXTURE : NORMAL_TEXTURE;
    }

    @Override
    public BloodyMaidenRenderState createRenderState() {
        return new BloodyMaidenRenderState();
    }

    @Override
    public void extractRenderState(BloodyMaidenEntity entity, BloodyMaidenRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.triggered = entity.isTriggered();
    }
}
