package com.dmonsters.client;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.TopielecEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public final class TopielecRenderer extends MobRenderer<TopielecEntity, TopielecRenderState, TopielecModel> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(
            DeadlyMonsters.MOD_ID, "textures/entity/topielec.png");

    public TopielecRenderer(EntityRendererProvider.Context context) {
        super(context, new TopielecModel(context.bakeLayer(TopielecModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public Identifier getTextureLocation(TopielecRenderState state) {
        return TEXTURE;
    }

    @Override
    public TopielecRenderState createRenderState() {
        return new TopielecRenderState();
    }

    @Override
    public void extractRenderState(TopielecEntity entity, TopielecRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.attackTime = entity.getAttackAnim(partialTicks);
    }
}
