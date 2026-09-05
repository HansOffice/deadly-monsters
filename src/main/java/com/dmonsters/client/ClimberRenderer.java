package com.dmonsters.client;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.ClimberEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public final class ClimberRenderer extends MobRenderer<ClimberEntity, ClimberRenderState, ClimberModel> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(
            DeadlyMonsters.MOD_ID, "textures/entity/climber.png");

    public ClimberRenderer(EntityRendererProvider.Context context) {
        super(context, new ClimberModel(context.bakeLayer(ClimberModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public Identifier getTextureLocation(ClimberRenderState state) {
        return TEXTURE;
    }

    @Override
    public ClimberRenderState createRenderState() {
        return new ClimberRenderState();
    }

    @Override
    public void extractRenderState(ClimberEntity entity, ClimberRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.attackTime = entity.getAttackAnim(partialTicks);
    }
}
