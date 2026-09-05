package com.dmonsters;

import com.dmonsters.registry.ModBlocks;
import com.dmonsters.registry.ModCreativeTabs;
import com.dmonsters.registry.ModEntities;
import com.dmonsters.registry.ModItems;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(DeadlyMonsters.MOD_ID)
public final class DeadlyMonsters {
    public static final String MOD_ID = "dmonsters";
    public static final Logger LOGGER = LogUtils.getLogger();

    public DeadlyMonsters(IEventBus modEventBus, ModContainer modContainer) {
        ModBlocks.BLOCKS.register(modEventBus);
        ModEntities.ENTITY_TYPES.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModCreativeTabs.TABS.register(modEventBus);

        modEventBus.addListener(ModEntities::registerAttributes);

        LOGGER.info("Loading Deadly Monsters NeoForge port for Minecraft 26.2");
    }
}
