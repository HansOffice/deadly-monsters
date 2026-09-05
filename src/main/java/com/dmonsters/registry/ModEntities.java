package com.dmonsters.registry;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.PortPlaceholderMonster;
import com.dmonsters.entity.ZombieChickenEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.chicken.Chicken;
import net.minecraft.world.entity.monster.Monster;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * NeoForge 26.2 entity registry.
 *
 * All original monster registry IDs are reserved now so later ports can replace
 * placeholder factories without changing save/resource identifiers. Zombie
 * Chicken is the first entry backed by its real gameplay implementation.
 */
public final class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, DeadlyMonsters.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<PortPlaceholderMonster>> MUTANT_STEVE = placeholder("mutant_steve");
    public static final DeferredHolder<EntityType<?>, EntityType<PortPlaceholderMonster>> FREEZER = placeholder("freezer");
    public static final DeferredHolder<EntityType<?>, EntityType<PortPlaceholderMonster>> CLIMBER = placeholder("climber");

    public static final DeferredHolder<EntityType<?>, EntityType<ZombieChickenEntity>> ZOMBIE_CHICKEN =
            ENTITY_TYPES.register("zombie_chicken", () -> EntityType.Builder
                    .of(ZombieChickenEntity::new, MobCategory.MONSTER)
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(8)
                    .updateInterval(3)
                    .build(key("zombie_chicken")));

    public static final DeferredHolder<EntityType<?>, EntityType<PortPlaceholderMonster>> UNBORN_BABY = placeholder("unborn_baby");
    public static final DeferredHolder<EntityType<?>, EntityType<PortPlaceholderMonster>> FALLEN_LEADER = placeholder("fallen_leader");
    public static final DeferredHolder<EntityType<?>, EntityType<PortPlaceholderMonster>> BLOODY_MAIDEN = placeholder("bloody_maiden");
    public static final DeferredHolder<EntityType<?>, EntityType<PortPlaceholderMonster>> ENTRAIL = placeholder("entrail");
    public static final DeferredHolder<EntityType<?>, EntityType<PortPlaceholderMonster>> PRESENT = placeholder("present");
    public static final DeferredHolder<EntityType<?>, EntityType<PortPlaceholderMonster>> STRANGER = placeholder("stranger");
    public static final DeferredHolder<EntityType<?>, EntityType<PortPlaceholderMonster>> HAUNTED_COW = placeholder("haunted_cow");
    public static final DeferredHolder<EntityType<?>, EntityType<PortPlaceholderMonster>> TOPIELEC = placeholder("topielec");

    private ModEntities() {
    }

    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ZOMBIE_CHICKEN.get(), Chicken.createAttributes()
                .add(Attributes.FOLLOW_RANGE, 35.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.26D)
                .add(Attributes.ATTACK_DAMAGE, 8.0D)
                .add(Attributes.ARMOR, 2.0D)
                .add(Attributes.MAX_HEALTH, 16.0D)
                .build());

        var placeholderAttributes = Monster.createMonsterAttributes().build();
        event.put(MUTANT_STEVE.get(), placeholderAttributes);
        event.put(FREEZER.get(), placeholderAttributes);
        event.put(CLIMBER.get(), placeholderAttributes);
        event.put(UNBORN_BABY.get(), placeholderAttributes);
        event.put(FALLEN_LEADER.get(), placeholderAttributes);
        event.put(BLOODY_MAIDEN.get(), placeholderAttributes);
        event.put(ENTRAIL.get(), placeholderAttributes);
        event.put(PRESENT.get(), placeholderAttributes);
        event.put(STRANGER.get(), placeholderAttributes);
        event.put(HAUNTED_COW.get(), placeholderAttributes);
        event.put(TOPIELEC.get(), placeholderAttributes);
    }

    private static DeferredHolder<EntityType<?>, EntityType<PortPlaceholderMonster>> placeholder(String name) {
        return ENTITY_TYPES.register(name, () -> EntityType.Builder
                .of(PortPlaceholderMonster::new, MobCategory.MONSTER)
                .sized(0.6F, 1.8F)
                .clientTrackingRange(8)
                .updateInterval(3)
                .build(key(name)));
    }

    private static ResourceKey<EntityType<?>> key(String name) {
        return ResourceKey.create(
                Registries.ENTITY_TYPE,
                Identifier.fromNamespaceAndPath(DeadlyMonsters.MOD_ID, name));
    }
}
