package com.dmonsters.registry;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.config.DeadlyMonstersConfig;
import java.util.function.Predicate;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.biome.Biome;

public final class ModNaturalSpawns {
    private static final TagKey<Biome> SNOW_BIOMES = tag("snow_biomes");
    private static final TagKey<Biome> WATER_BIOMES = tag("water_biomes");

    private ModNaturalSpawns() {
    }

    public static void initialize() {
        Predicate<BiomeSelectionContext> overworld = BiomeSelectors.tag(BiomeTags.IS_OVERWORLD);

        add(overworld, ModEntities.MUTANT_STEVE.get(), 1, 1);
        add(overworld, ModEntities.FREEZER.get(), 1, 1);
        add(overworld, ModEntities.CLIMBER.get(), 1, 5);
        add(overworld, ModEntities.ENTRAIL.get(), 1, 8);
        add(overworld, ModEntities.UNBORN_BABY.get(), 1, 8);
        add(overworld, ModEntities.FALLEN_LEADER.get(), 1, 8);
        add(overworld, ModEntities.BLOODY_MAIDEN.get(), 1, 8);
        add(overworld, ModEntities.ZOMBIE_CHICKEN.get(), 1, 8);
        add(BiomeSelectors.tag(SNOW_BIOMES), ModEntities.PRESENT.get(), 1, 8);
        add(overworld, ModEntities.STRANGER.get(), 1, 8);
        add(overworld, ModEntities.HAUNTED_COW.get(), 1, 8);
        add(BiomeSelectors.tag(WATER_BIOMES), ModEntities.TOPIELEC.get(), 1, 1);
    }

    private static void add(
            Predicate<BiomeSelectionContext> selector,
            EntityType<? extends Mob> type,
            int minCount,
            int maxCount) {
        int weight = DeadlyMonstersConfig.spawnRate(type);
        if (!DeadlyMonstersConfig.naturalSpawnsEnabled(type) || weight <= 0) {
            return;
        }
        BiomeModifications.addSpawn(selector, type.getCategory(), type, weight, minCount, maxCount);
    }

    private static TagKey<Biome> tag(String name) {
        return TagKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(DeadlyMonsters.MOD_ID, name));
    }
}
