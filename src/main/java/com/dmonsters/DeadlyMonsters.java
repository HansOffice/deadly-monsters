package com.dmonsters;

import com.dmonsters.config.DeadlyMonstersConfig;
import com.dmonsters.registry.ModBlocks;
import com.dmonsters.registry.ModCreativeTabs;
import com.dmonsters.registry.ModEntities;
import com.dmonsters.registry.ModItems;
import com.dmonsters.registry.ModNaturalSpawns;
import com.dmonsters.registry.ModSounds;
import com.mojang.logging.LogUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.EntityLoadData;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import org.slf4j.Logger;

public final class DeadlyMonsters implements ModInitializer {
    public static final String MOD_ID = "dmonsters";
    public static final Logger LOGGER = LogUtils.getLogger();

    @Override
    public void onInitialize() {
        DeadlyMonstersConfig.load();
        ModBlocks.initialize();
        ModEntities.initialize();
        ModItems.initialize();
        ModSounds.initialize();
        ModCreativeTabs.initialize();
        ModNaturalSpawns.initialize();

        ServerEntityEvents.ENTITY_LOAD.register((entity, level) ->
                DeadlyMonstersConfig.onEntityJoinLevel(entity, ((EntityLoadData) entity).isLoadedFromDisk()));
        AttackEntityCallback.EVENT.register(DeadlyMonstersConfig::onPlayerAttack);

        LOGGER.info("Loading Deadly Monsters Fabric port for Minecraft 26.2");
    }
}
