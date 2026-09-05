package com.dmonsters;

import com.dmonsters.config.DeadlyMonstersConfig;
import com.dmonsters.registry.ModBiomeModifiers;
import com.dmonsters.registry.ModBlocks;
import com.dmonsters.registry.ModCreativeTabs;
import com.dmonsters.registry.ModEntities;
import com.dmonsters.registry.ModItems;
import com.dmonsters.registry.ModSounds;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

@Mod(DeadlyMonsters.MOD_ID)
public final class DeadlyMonsters {
    public static final String MOD_ID = "dmonsters";
    public static final Logger LOGGER = LogUtils.getLogger();

    public DeadlyMonsters(IEventBus modEventBus, ModContainer modContainer) {
        ModBlocks.BLOCKS.register(modEventBus);
        ModEntities.ENTITY_TYPES.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModSounds.SOUND_EVENTS.register(modEventBus);
        ModCreativeTabs.TABS.register(modEventBus);
        ModBiomeModifiers.BIOME_MODIFIER_SERIALIZERS.register(modEventBus);

        modEventBus.addListener(ModEntities::registerAttributes);
        modEventBus.addListener(ModEntities::registerSpawnPlacements);

        modContainer.registerConfig(ModConfig.Type.COMMON, DeadlyMonstersConfig.SPEC);
        NeoForge.EVENT_BUS.addListener(DeadlyMonstersConfig::onEntityJoinLevel);
        NeoForge.EVENT_BUS.addListener(DeadlyMonstersConfig::onPlayerAttack);

        LOGGER.info("Loading Deadly Monsters NeoForge port for Minecraft 26.2");
    }
}
