package com.dmonsters.registry;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.config.DeadlyMonstersConfig;
import com.dmonsters.entity.BloodyMaidenEntity;
import com.dmonsters.entity.ClimberEntity;
import com.dmonsters.entity.EntrailEntity;
import com.dmonsters.entity.FallenLeaderEntity;
import com.dmonsters.entity.FreezerEntity;
import com.dmonsters.entity.HauntedCowEntity;
import com.dmonsters.entity.MutantSteveEntity;
import com.dmonsters.entity.PresentEntity;
import com.dmonsters.entity.StrangerEntity;
import com.dmonsters.entity.TopielecEntity;
import com.dmonsters.entity.UnbornBabyEntity;
import com.dmonsters.entity.ZombieChickenEntity;
import com.dmonsters.projectile.DagonProjectile;
import com.dmonsters.projectile.LuckyEggProjectile;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.Heightmap;

public final class ModEntities {
    public static final RegistryRef<EntityType<MutantSteveEntity>> MUTANT_STEVE = register("mutant_steve", MutantSteveEntity::new, 0.9F, 1.95F);
    public static final RegistryRef<EntityType<FreezerEntity>> FREEZER = register("freezer", FreezerEntity::new, 0.9F, 1.95F);
    public static final RegistryRef<EntityType<ClimberEntity>> CLIMBER = register("climber", ClimberEntity::new, 0.9F, 1.95F);
    public static final RegistryRef<EntityType<EntrailEntity>> ENTRAIL = register("entrail", EntrailEntity::new, 0.9F, 1.95F);
    public static final RegistryRef<EntityType<UnbornBabyEntity>> UNBORN_BABY = register("unborn_baby", UnbornBabyEntity::new, 0.9F, 1.95F);
    public static final RegistryRef<EntityType<FallenLeaderEntity>> FALLEN_LEADER = register("fallen_leader", FallenLeaderEntity::new, 0.9F, 1.95F);
    public static final RegistryRef<EntityType<BloodyMaidenEntity>> BLOODY_MAIDEN = register("bloody_maiden", BloodyMaidenEntity::new, 1.1F, 0.6F);
    public static final RegistryRef<EntityType<ZombieChickenEntity>> ZOMBIE_CHICKEN = register("zombie_chicken", ZombieChickenEntity::new, 0.5F, 0.5F);
    public static final RegistryRef<EntityType<PresentEntity>> PRESENT = register("present", PresentEntity::new, 0.9F, 1.5F);
    public static final RegistryRef<EntityType<StrangerEntity>> STRANGER = register("stranger", StrangerEntity::new, 1.0F, 1.5F);
    public static final RegistryRef<EntityType<HauntedCowEntity>> HAUNTED_COW = register("haunted_cow", HauntedCowEntity::new, 0.9F, 1.4F);
    public static final RegistryRef<EntityType<TopielecEntity>> TOPIELEC = register("topielec", TopielecEntity::new, 1.0F, 1.0F);

    public static final RegistryRef<EntityType<LuckyEggProjectile>> LUCKY_EGG_PROJECTILE =
            registerProjectile("lucky_egg", LuckyEggProjectile::new);
    public static final RegistryRef<EntityType<DagonProjectile>> DAGON_PROJECTILE =
            registerProjectile("dagon", DagonProjectile::new);

    private ModEntities() {
    }

    public static void initialize() {
        registerAttributes();
        registerSpawnPlacements();
    }

    private static void registerAttributes() {
        FabricDefaultAttributeRegistry.register(MUTANT_STEVE.get(), monsterAttributes(35.0D, 0.13D, 16.0D, 2.0D, 40.0D));
        FabricDefaultAttributeRegistry.register(FREEZER.get(), monsterAttributes(35.0D, 0.13D, 16.0D, 2.0D, 45.0D));
        FabricDefaultAttributeRegistry.register(CLIMBER.get(), monsterAttributes(16.0D, 0.1D, 12.0D, 0.0D, 24.0D));
        FabricDefaultAttributeRegistry.register(ENTRAIL.get(), monsterAttributes(35.0D, 0.3D, 10.0D, 2.0D, 30.0D));
        FabricDefaultAttributeRegistry.register(UNBORN_BABY.get(), monsterAttributes(40.0D, 0.25D, 12.0D, 2.0D, 40.0D));
        FabricDefaultAttributeRegistry.register(FALLEN_LEADER.get(), monsterAttributes(35.0D, 0.1D, 20.0D, 1.0D, 60.0D));
        FabricDefaultAttributeRegistry.register(BLOODY_MAIDEN.get(), monsterAttributes(35.0D, 0.2D, 4.0D, 2.0D, 20.0D));
        FabricDefaultAttributeRegistry.register(ZOMBIE_CHICKEN.get(), monsterAttributes(35.0D, 0.26D, 8.0D, 2.0D, 16.0D));
        FabricDefaultAttributeRegistry.register(PRESENT.get(), monsterAttributes(35.0D, 0.3D, 1.0D, 2.0D, 26.0D));
        FabricDefaultAttributeRegistry.register(STRANGER.get(), monsterAttributes(35.0D, 0.15D, 4.0D, 2.0D, 40.0D));
        FabricDefaultAttributeRegistry.register(HAUNTED_COW.get(), monsterAttributes(35.0D, 0.3D, 12.0D, 2.0D, 24.0D));
        FabricDefaultAttributeRegistry.register(TOPIELEC.get(), monsterAttributes(40.0D, 0.4D, 1.0D, 2.0D, 20.0D));
    }

    private static AttributeSupplier monsterAttributes(double follow, double speed, double attack, double armor, double health) {
        return Monster.createMonsterAttributes()
                .add(Attributes.FOLLOW_RANGE, follow)
                .add(Attributes.MOVEMENT_SPEED, speed)
                .add(Attributes.ATTACK_DAMAGE, attack)
                .add(Attributes.ARMOR, armor)
                .add(Attributes.MAX_HEALTH, health)
                .build();
    }

    private static void registerSpawnPlacements() {
        registerCommonMonsterPlacement(MUTANT_STEVE.get());
        registerCommonMonsterPlacement(FREEZER.get());
        registerCommonMonsterPlacement(CLIMBER.get());
        registerCommonMonsterPlacement(ENTRAIL.get());
        registerCommonMonsterPlacement(UNBORN_BABY.get());
        registerCommonMonsterPlacement(FALLEN_LEADER.get());
        registerCommonMonsterPlacement(BLOODY_MAIDEN.get());
        registerCommonMonsterPlacement(ZOMBIE_CHICKEN.get());
        registerCommonMonsterPlacement(STRANGER.get());

        SpawnPlacements.register(HAUNTED_COW.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (type, level, reason, pos, random) -> DeadlyMonstersConfig.naturalSpawnsEnabled(type)
                        && !level.getBiome(pos).is(Biomes.MUSHROOM_FIELDS)
                        && Monster.checkAnyLightMonsterSpawnRules(type, level, reason, pos, random));

        SpawnPlacements.register(PRESENT.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (type, level, reason, pos, random) -> DeadlyMonstersConfig.naturalSpawnsEnabled(type)
                        && level.canSeeSky(pos)
                        && Monster.checkSurfaceMonstersSpawnRules(type, level, reason, pos, random));

        SpawnPlacements.register(TOPIELEC.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (type, level, reason, pos, random) -> DeadlyMonstersConfig.naturalSpawnsEnabled(type)
                        && pos.getY() > 45 && pos.getY() < level.getSeaLevel()
                        && level.getFluidState(pos).is(FluidTags.WATER)
                        && Monster.checkAnyLightMonsterSpawnRules(type, level, reason, pos, random));
    }

    private static <T extends Mob> void registerCommonMonsterPlacement(EntityType<T> type) {
        SpawnPlacements.register(type, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, level, spawnReason, pos, random) -> DeadlyMonstersConfig.naturalSpawnsEnabled(entityType)
                        && !level.getBiome(pos).is(Biomes.MUSHROOM_FIELDS)
                        && Monster.checkMonsterSpawnRules(entityType, level, spawnReason, pos, random));
    }

    private static <T extends Mob> RegistryRef<EntityType<T>> register(
            String name,
            EntityType.EntityFactory<T> factory,
            float width,
            float height) {
        return registerType(name, EntityType.Builder.of(factory, MobCategory.MONSTER)
                .sized(width, height).clientTrackingRange(8).updateInterval(3));
    }

    private static <T extends Entity> RegistryRef<EntityType<T>> registerProjectile(
            String name,
            EntityType.EntityFactory<T> factory) {
        return registerType(name, EntityType.Builder.of(factory, MobCategory.MISC)
                .sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10));
    }

    private static <T extends Entity> RegistryRef<EntityType<T>> registerType(String name, EntityType.Builder<T> builder) {
        ResourceKey<EntityType<?>> key = ResourceKey.create(
                Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(DeadlyMonsters.MOD_ID, name));
        EntityType<T> type = builder.build(key);
        Registry.register(BuiltInRegistries.ENTITY_TYPE, key, type);
        return new RegistryRef<>(type);
    }
}
