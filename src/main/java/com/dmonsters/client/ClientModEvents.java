package com.dmonsters.client;

import com.dmonsters.registry.ModBlocks;
import com.dmonsters.registry.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;

public final class ClientModEvents implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BARBED_WIRE.get(), RenderType.cutout());

        EntityModelLayerRegistry.registerModelLayer(BloodyMaidenModel.LAYER_LOCATION, BloodyMaidenModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ClimberModel.LAYER_LOCATION, ClimberModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(EntrailModel.LAYER_LOCATION, EntrailModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(FallenLeaderModel.LAYER_LOCATION, FallenLeaderModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(FreezerModel.LAYER_LOCATION, FreezerModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(HauntedCowModel.LAYER_LOCATION, HauntedCowModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(MutantSteveModel.LAYER_LOCATION, MutantSteveModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(PresentModel.LAYER_LOCATION, PresentModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(StrangerModel.LAYER_LOCATION, StrangerModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(TopielecModel.LAYER_LOCATION, TopielecModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(UnbornBabyModel.LAYER_LOCATION, UnbornBabyModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ZombieChickenModel.LAYER_LOCATION, ZombieChickenModel::createBodyLayer);

        EntityRendererRegistry.register(ModEntities.BLOODY_MAIDEN.get(), BloodyMaidenRenderer::new);
        EntityRendererRegistry.register(ModEntities.CLIMBER.get(), ClimberRenderer::new);
        EntityRendererRegistry.register(ModEntities.ENTRAIL.get(), EntrailRenderer::new);
        EntityRendererRegistry.register(ModEntities.FALLEN_LEADER.get(), FallenLeaderRenderer::new);
        EntityRendererRegistry.register(ModEntities.FREEZER.get(), FreezerRenderer::new);
        EntityRendererRegistry.register(ModEntities.HAUNTED_COW.get(), HauntedCowRenderer::new);
        EntityRendererRegistry.register(ModEntities.MUTANT_STEVE.get(), MutantSteveRenderer::new);
        EntityRendererRegistry.register(ModEntities.PRESENT.get(), PresentRenderer::new);
        EntityRendererRegistry.register(ModEntities.STRANGER.get(), StrangerRenderer::new);
        EntityRendererRegistry.register(ModEntities.TOPIELEC.get(), TopielecRenderer::new);
        EntityRendererRegistry.register(ModEntities.UNBORN_BABY.get(), UnbornBabyRenderer::new);
        EntityRendererRegistry.register(ModEntities.ZOMBIE_CHICKEN.get(), ZombieChickenRenderer::new);
        EntityRendererRegistry.register(ModEntities.LUCKY_EGG_PROJECTILE.get(), ThrownItemRenderer::new);
        EntityRendererRegistry.register(ModEntities.DAGON_PROJECTILE.get(), ThrownItemRenderer::new);
    }
}
