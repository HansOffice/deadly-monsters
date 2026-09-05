package com.dmonsters.client;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.UnbornBabyEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public final class UnbornBabyRenderer extends MobRenderer<UnbornBabyEntity, UnbornBabyRenderState, UnbornBabyModel> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(
            DeadlyMonsters.MOD_ID, "textures/entity/unborn_baby.png");

    public UnbornBabyRenderer(EntityRendererProvider.Context context) {
        super(context, new UnbornBabyModel(context.bakeLayer(UnbornBabyModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public Identifier getTextureLocation(UnbornBabyRenderState state) {
        return TEXTURE;
    }

    @Override
    public UnbornBabyRenderState createRenderState() {
        return new UnbornBabyRenderState();
    }

    @Override
    public void extractRenderState(UnbornBabyEntity entity, UnbornBabyRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.attackTime = entity.getAttackAnim(partialTicks);
    }
}
