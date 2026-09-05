package com.dmonsters.client;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.FallenLeaderEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

public final class FallenLeaderRenderer extends MobRenderer<FallenLeaderEntity, LivingEntityRenderState, FallenLeaderModel> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(
            DeadlyMonsters.MOD_ID, "textures/entity/fallen_leader.png");

    public FallenLeaderRenderer(EntityRendererProvider.Context context) {
        super(context, new FallenLeaderModel(context.bakeLayer(FallenLeaderModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public Identifier getTextureLocation(LivingEntityRenderState state) {
        return TEXTURE;
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }
}
