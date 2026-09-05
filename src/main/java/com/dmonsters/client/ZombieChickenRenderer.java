package com.dmonsters.client;

import com.dmonsters.DeadlyMonsters;
import net.minecraft.client.renderer.entity.ChickenRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ChickenRenderState;
import net.minecraft.resources.Identifier;

/**
 * 26.2 renderer bridge for Zombie Chicken.
 *
 * It intentionally reuses Mojang's current Chicken model/animation pipeline,
 * but restores the original Deadly Monsters texture. The legacy custom model
 * geometry will be migrated separately onto the modern model-layer API.
 */
public final class ZombieChickenRenderer extends ChickenRenderer {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(
            DeadlyMonsters.MOD_ID, "textures/entity/zombie_chicken.png");

    public ZombieChickenRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public Identifier getTextureLocation(ChickenRenderState state) {
        return TEXTURE;
    }
}
