package com.dmonsters.client;

import com.dmonsters.registry.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;

public final class ClientModEvents implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModelLayerRegistry.registerModelLayer(BloodyMaidenModel.LAYER_LOCATION, BloodyMaidenModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(ClimberModel.LAYER_LOCATION, ClimberModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(EntrailModel.LAYER_LOCATION, EntrailModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(FallenLeaderModel.LAYER_LOCATION, FallenLeaderModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(FreezerModel.LAYER_LOCATION, FreezerModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(HauntedCowModel.LAYER_LOCATION, HauntedCowModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(MutantSteveModel.LAYER_LOCATION, MutantSteveModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(PresentModel.LAYER_LOCATION, PresentModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(StrangerModel.LAYER_LOCATION, StrangerModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(TopielecModel.LAYER_LOCATION, TopielecModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(UnbornBabyModel.LAYER_LOCATION, UnbornBabyModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(ZombieChickenModel.LAYER_LOCATION, ZombieChickenModel::createBodyLayer);

        EntityRenderers.register(ModEntities.BLOODY_MAIDEN.get(), BloodyMaidenRenderer::new);
        EntityRenderers.register(ModEntities.CLIMBER.get(), ClimberRenderer::new);
        EntityRenderers.register(ModEntities.ENTRAIL.get(), EntrailRenderer::new);
        EntityRenderers.register(ModEntities.FALLEN_LEADER.get(), FallenLeaderRenderer::new);
        EntityRenderers.register(ModEntities.FREEZER.get(), FreezerRenderer::new);
        EntityRenderers.register(ModEntities.HAUNTED_COW.get(), HauntedCowRenderer::new);
        EntityRenderers.register(ModEntities.MUTANT_STEVE.get(), MutantSteveRenderer::new);
        EntityRenderers.register(ModEntities.PRESENT.get(), PresentRenderer::new);
        EntityRenderers.register(ModEntities.STRANGER.get(), StrangerRenderer::new);
        EntityRenderers.register(ModEntities.TOPIELEC.get(), TopielecRenderer::new);
        EntityRenderers.register(ModEntities.UNBORN_BABY.get(), UnbornBabyRenderer::new);
        EntityRenderers.register(ModEntities.ZOMBIE_CHICKEN.get(), ZombieChickenRenderer::new);
        EntityRenderers.register(ModEntities.LUCKY_EGG_PROJECTILE.get(), ThrownItemRenderer::new);
        EntityRenderers.register(ModEntities.DAGON_PROJECTILE.get(), ThrownItemRenderer::new);
    }
}
