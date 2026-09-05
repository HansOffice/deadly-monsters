package com.dmonsters.client;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.FreezerEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public final class FreezerRenderer extends MobRenderer<FreezerEntity, FreezerRenderState, FreezerModel> {
    private static final Identifier IDLE_TEXTURE = Identifier.fromNamespaceAndPath(
            DeadlyMonsters.MOD_ID, "textures/entity/freezer_idle.png");
    private static final Identifier ANGRY_TEXTURE = Identifier.fromNamespaceAndPath(
            DeadlyMonsters.MOD_ID, "textures/entity/freezer_angry.png");

    public FreezerRenderer(EntityRendererProvider.Context context) {
        super(context, new FreezerModel(context.bakeLayer(FreezerModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public Identifier getTextureLocation(FreezerRenderState state) {
        return state.aggressive ? ANGRY_TEXTURE : IDLE_TEXTURE;
    }

    @Override
    public FreezerRenderState createRenderState() {
        return new FreezerRenderState();
    }

    @Override
    public void extractRenderState(FreezerEntity entity, FreezerRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.aggressive = entity.isAggressive();
        state.attackTime = entity.getAttackAnim(partialTicks);
    }
}
