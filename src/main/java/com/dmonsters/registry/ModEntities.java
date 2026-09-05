package com.dmonsters.registry;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.ClimberEntity;
import com.dmonsters.entity.EntrailEntity;
import com.dmonsters.entity.FreezerEntity;
import com.dmonsters.entity.PortPlaceholderMonster;
import com.dmonsters.entity.UnbornBabyEntity;
import com.dmonsters.entity.ZombieChickenEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.chicken.Chicken;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/** NeoForge 26.2 entity registry retaining all original IDs. */
public final class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, DeadlyMonsters.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<PortPlaceholderMonster>> MUTANT_STEVE = placeholder("mutant_steve");

    public static final DeferredHolder<EntityType<?>, EntityType<FreezerEntity>> FREEZER =
            ENTITY_TYPES.register("freezer", () -> EntityType.Builder.of(FreezerEntity::new, MobCategory.MONSTER)
                    .sized(0.9F, 1.95F).clientTrackingRange(8).updateInterval(3).build(key("freezer")));

    public static final DeferredHolder<EntityType<?>, EntityType<ClimberEntity>> CLIMBER =
            ENTITY_TYPES.register("climber", () -> EntityType.Builder.of(ClimberEntity::new, MobCategory.MONSTER)
                    .sized(0.9F, 1.95F).clientTrackingRange(8).updateInterval(3).build(key("climber")));

    public static final DeferredHolder<EntityType<?>, EntityType<EntrailEntity>> ENTRAIL =
            ENTITY_TYPES.register("entrail", () -> EntityType.Builder.of(EntrailEntity::new, MobCategory.MONSTER)
                    .sized(0.9F, 1.95F).clientTrackingRange(8).updateInterval(3).build(key("entrail")));

    public static final DeferredHolder<EntityType<?>, EntityType<UnbornBabyEntity>> UNBORN_BABY =
            ENTITY_TYPES.register("unborn_baby", () -> EntityType.Builder.of(UnbornBabyEntity::new, MobCategory.MONSTER)
                    .sized(0.9F, 1.95F).clientTrackingRange(8).updateInterval(3).build(key("unborn_baby")));

    public static final DeferredHolder<EntityType<?>, EntityType<ZombieChickenEntity>> ZOMBIE_CHICKEN =
            ENTITY_TYPES.register("zombie_chicken", () -> EntityType.Builder.of(ZombieChickenEntity::new, MobCategory.MONSTER)
                    .sized(0.5F, 0.5F).clientTrackingRange(8).updateInterval(3).build(key("zombie_chicken")));

    public static final DeferredHolder<EntityType<?>, EntityType<PortPlaceholderMonster>> FALLEN_LEADER = placeholder("fallen_leader");
    public static final DeferredHolder<EntityType<?>, EntityType<PortPlaceholderMonster>> BLOODY_MAIDEN = placeholder("bloody_maiden");
    public static final DeferredHolder<EntityType<?>, EntityType<PortPlaceholderMonster>> PRESENT = placeholder("present");
    public static final DeferredHolder<EntityType<?>, EntityType<PortPlaceholderMonster>> STRANGER = placeholder("stranger");
    public static final DeferredHolder<EntityType<?>, EntityType<PortPlaceholderMonster>> HAUNTED_COW = placeholder("haunted_cow");
    public static final DeferredHolder<EntityType<?>, EntityType<PortPlaceholderMonster>> TOPIELEC = placeholder("topielec");

    private ModEntities() {
    }

    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(FREEZER.get(), Monster.createMonsterAttributes()
                .add(Attributes.FOLLOW_RANGE, 35.0D).add(Attributes.MOVEMENT_SPEED, 0.13D)
                .add(Attributes.ATTACK_DAMAGE, 16.0D).add(Attributes.ARMOR, 2.0D)
                .add(Attributes.MAX_HEALTH, 45.0D).build());

        event.put(CLIMBER.get(), Monster.createMonsterAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.1D).add(Attributes.ATTACK_DAMAGE, 12.0D)
                .add(Attributes.MAX_HEALTH, 24.0D).build());

        event.put(ENTRAIL.get(), Monster.createMonsterAttributes()
                .add(Attributes.FOLLOW_RANGE, 35.0D).add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.ATTACK_DAMAGE, 10.0D).add(Attributes.ARMOR, 2.0D)
                .add(Attributes.MAX_HEALTH, 30.0D).build());

        event.put(UNBORN_BABY.get(), Monster.createMonsterAttributes()
                .add(Attributes.FOLLOW_RANGE, 40.0D).add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, 12.0D).add(Attributes.ARMOR, 2.0D)
                .add(Attributes.MAX_HEALTH, 40.0D).build());

        event.put(ZOMBIE_CHICKEN.get(), Chicken.createAttributes()
                .add(Attributes.FOLLOW_RANGE, 35.0D).add(Attributes.MOVEMENT_SPEED, 0.26D)
                .add(Attributes.ATTACK_DAMAGE, 8.0D).add(Attributes.ARMOR, 2.0D)
                .add(Attributes.MAX_HEALTH, 16.0D).build());

        var placeholderAttributes = Monster.createMonsterAttributes().build();
        event.put(MUTANT_STEVE.get(), placeholderAttributes);
        event.put(FALLEN_LEADER.get(), placeholderAttributes);
        event.put(BLOODY_MAIDEN.get(), placeholderAttributes);
        event.put(PRESENT.get(), placeholderAttributes);
        event.put(STRANGER.get(), placeholderAttributes);
        event.put(HAUNTED_COW.get(), placeholderAttributes);
        event.put(TOPIELEC.get(), placeholderAttributes);
    }

    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        registerCommonMonsterPlacement(event, FREEZER.get());
        registerCommonMonsterPlacement(event, CLIMBER.get());
        registerCommonMonsterPlacement(event, ENTRAIL.get());
        registerCommonMonsterPlacement(event, UNBORN_BABY.get());
        registerCommonMonsterPlacement(event, ZOMBIE_CHICKEN.get());
    }

    private static <T extends Monster> void registerCommonMonsterPlacement(RegisterSpawnPlacementsEvent event, EntityType<T> type) {
        event.register(type, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, level, spawnReason, pos, random) -> !level.getBiome(pos).is(Biomes.MUSHROOM_FIELDS)
                        && Monster.checkMonsterSpawnRules(entityType, level, spawnReason, pos, random),
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }

    private static DeferredHolder<EntityType<?>, EntityType<PortPlaceholderMonster>> placeholder(String name) {
        return ENTITY_TYPES.register(name, () -> EntityType.Builder.of(PortPlaceholderMonster::new, MobCategory.MONSTER)
                .sized(0.6F, 1.8F).clientTrackingRange(8).updateInterval(3).build(key(name)));
    }

    private static ResourceKey<EntityType<?>> key(String name) {
        return ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(DeadlyMonsters.MOD_ID, name));
    }
}
