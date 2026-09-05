package com.dmonsters.client;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.registry.ModEntities;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = DeadlyMonsters.MOD_ID, value = Dist.CLIENT)
public final class ClientModEvents {
    private ClientModEvents() {}

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(BloodyMaidenModel.LAYER_LOCATION, BloodyMaidenModel::createBodyLayer);
        event.registerLayerDefinition(ClimberModel.LAYER_LOCATION, ClimberModel::createBodyLayer);
        event.registerLayerDefinition(EntrailModel.LAYER_LOCATION, EntrailModel::createBodyLayer);
        event.registerLayerDefinition(FallenLeaderModel.LAYER_LOCATION, FallenLeaderModel::createBodyLayer);
        event.registerLayerDefinition(FreezerModel.LAYER_LOCATION, FreezerModel::createBodyLayer);
        event.registerLayerDefinition(HauntedCowModel.LAYER_LOCATION, HauntedCowModel::createBodyLayer);
        event.registerLayerDefinition(StrangerModel.LAYER_LOCATION, StrangerModel::createBodyLayer);
        event.registerLayerDefinition(UnbornBabyModel.LAYER_LOCATION, UnbornBabyModel::createBodyLayer);
        event.registerLayerDefinition(ZombieChickenModel.LAYER_LOCATION, ZombieChickenModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.BLOODY_MAIDEN.get(), BloodyMaidenRenderer::new);
        event.registerEntityRenderer(ModEntities.CLIMBER.get(), ClimberRenderer::new);
        event.registerEntityRenderer(ModEntities.ENTRAIL.get(), EntrailRenderer::new);
        event.registerEntityRenderer(ModEntities.FALLEN_LEADER.get(), FallenLeaderRenderer::new);
        event.registerEntityRenderer(ModEntities.FREEZER.get(), FreezerRenderer::new);
        event.registerEntityRenderer(ModEntities.HAUNTED_COW.get(), HauntedCowRenderer::new);
        event.registerEntityRenderer(ModEntities.STRANGER.get(), StrangerRenderer::new);
        event.registerEntityRenderer(ModEntities.UNBORN_BABY.get(), UnbornBabyRenderer::new);
        event.registerEntityRenderer(ModEntities.ZOMBIE_CHICKEN.get(), ZombieChickenRenderer::new);

        event.registerEntityRenderer(ModEntities.MUTANT_STEVE.get(), NoopRenderer::new);
        event.registerEntityRenderer(ModEntities.PRESENT.get(), NoopRenderer::new);
        event.registerEntityRenderer(ModEntities.TOPIELEC.get(), NoopRenderer::new);
    }
}
