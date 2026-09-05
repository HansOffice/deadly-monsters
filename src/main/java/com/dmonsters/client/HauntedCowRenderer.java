package com.dmonsters.client;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.HauntedCowEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

public final class HauntedCowRenderer extends MobRenderer<HauntedCowEntity, LivingEntityRenderState, HauntedCowModel> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(
            DeadlyMonsters.MOD_ID, "textures/entity/haunted_cow.png");

    public HauntedCowRenderer(EntityRendererProvider.Context context) {
        super(context, new HauntedCowModel(context.bakeLayer(HauntedCowModel.LAYER_LOCATION)), 0.3F);
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
