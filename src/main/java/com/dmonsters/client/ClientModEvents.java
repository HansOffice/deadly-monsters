package com.dmonsters.client;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.registry.ModEntities;
import net.minecraft.client.renderer.entity.ChickenRenderer;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = DeadlyMonsters.MOD_ID, value = Dist.CLIENT)
public final class ClientModEvents {
    private ClientModEvents() {
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        // First real port. Vanilla chicken renderer is used as the stable 26.2
        // bridge while the original custom 32x32 model is migrated next.
        event.registerEntityRenderer(ModEntities.ZOMBIE_CHICKEN.get(), ChickenRenderer::new);

        // Reserved registry IDs must remain client-safe while their real
        // renderers/models are migrated. They intentionally render nothing.
        event.registerEntityRenderer(ModEntities.MUTANT_STEVE.get(), NoopRenderer::new);
        event.registerEntityRenderer(ModEntities.FREEZER.get(), NoopRenderer::new);
        event.registerEntityRenderer(ModEntities.CLIMBER.get(), NoopRenderer::new);
        event.registerEntityRenderer(ModEntities.UNBORN_BABY.get(), NoopRenderer::new);
        event.registerEntityRenderer(ModEntities.FALLEN_LEADER.get(), NoopRenderer::new);
        event.registerEntityRenderer(ModEntities.BLOODY_MAIDEN.get(), NoopRenderer::new);
        event.registerEntityRenderer(ModEntities.ENTRAIL.get(), NoopRenderer::new);
        event.registerEntityRenderer(ModEntities.PRESENT.get(), NoopRenderer::new);
        event.registerEntityRenderer(ModEntities.STRANGER.get(), NoopRenderer::new);
        event.registerEntityRenderer(ModEntities.HAUNTED_COW.get(), NoopRenderer::new);
        event.registerEntityRenderer(ModEntities.TOPIELEC.get(), NoopRenderer::new);
    }
}
