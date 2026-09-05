package com.dmonsters.client;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.ZombieChickenEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.ChickenRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

/**
 * Dedicated Zombie Chicken renderer using the original model geometry and texture.
 */
public final class ZombieChickenRenderer extends MobRenderer<ZombieChickenEntity, ChickenRenderState, ZombieChickenModel> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(
            DeadlyMonsters.MOD_ID, "textures/entity/zombie_chicken.png");

    public ZombieChickenRenderer(EntityRendererProvider.Context context) {
        super(context, new ZombieChickenModel(context.bakeLayer(ZombieChickenModel.LAYER_LOCATION)), 0.3F);
    }

    @Override
    public Identifier getTextureLocation(ChickenRenderState state) {
        return TEXTURE;
    }

    @Override
    public ChickenRenderState createRenderState() {
        return new ChickenRenderState();
    }

    @Override
    public void extractRenderState(ZombieChickenEntity entity, ChickenRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.flap = Mth.lerp(partialTicks, entity.oFlap, entity.flap);
        state.flapSpeed = Mth.lerp(partialTicks, entity.oFlapSpeed, entity.flapSpeed);
    }
}
