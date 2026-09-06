package com.dmonsters.registry;

import com.dmonsters.DeadlyMonsters;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public final class ModSounds {
    public static final RegistryRef<SoundEvent> DUMP_MAKE = register("block.dump.make");
    public static final RegistryRef<SoundEvent> BLOCK_SOULEYE_KILL = register("block.souleye.kill");
    public static final RegistryRef<SoundEvent> SUNLIGHTDROP_USE = register("item.sunlightdrop.use");
    public static final RegistryRef<SoundEvent> HAUNTEDCOW_TIMECHANGE = register("mob.hauntedcow.timechange");

    public static final RegistryRef<SoundEvent> TOPIELEC_HURT = register("mob.topielec.hurt");
    public static final RegistryRef<SoundEvent> TOPIELEC_AMBIENT = register("mob.topielec.ambient");
    public static final RegistryRef<SoundEvent> TOPIELEC_DEATH = register("mob.topielec.death");

    public static final RegistryRef<SoundEvent> HAUNTEDCOW_DEATH = register("mob.hauntedcow.death");
    public static final RegistryRef<SoundEvent> HAUNTEDCOW_HURT = register("mob.hauntedcow.hurt");
    public static final RegistryRef<SoundEvent> HAUNTEDCOW_AMBIENT = register("mob.hauntedcow.ambient");
    public static final RegistryRef<SoundEvent> HAUNTEDCOW_STEP = register("mob.hauntedcow.step");

    public static final RegistryRef<SoundEvent> STRANGER_IMPACT = register("mob.stranger.impact");
    public static final RegistryRef<SoundEvent> STRANGER_DEATH = register("mob.stranger.death");
    public static final RegistryRef<SoundEvent> STRANGER_HURT = register("mob.stranger.hurt");
    public static final RegistryRef<SoundEvent> STRANGER_AMBIENT = register("mob.stranger.idle");
    public static final RegistryRef<SoundEvent> STRANGER_ATTACK = register("mob.stranger.attack");

    public static final RegistryRef<SoundEvent> MUTANT_DEATH = register("mob.mutant.death");
    public static final RegistryRef<SoundEvent> MUTANT_HURT = register("mob.mutant.hurt");
    public static final RegistryRef<SoundEvent> MUTANT_AMBIENT = register("mob.mutant.idle");
    public static final RegistryRef<SoundEvent> MUTANT_ATTACK = register("mob.mutant.attack");

    public static final RegistryRef<SoundEvent> FREEZER_DEATH = register("mob.freezer.death");
    public static final RegistryRef<SoundEvent> FREEZER_HURT = register("mob.freezer.hurt");
    public static final RegistryRef<SoundEvent> FREEZER_AMBIENT = register("mob.freezer.idle");
    public static final RegistryRef<SoundEvent> FREEZER_ATTACK = register("mob.freezer.attack");

    public static final RegistryRef<SoundEvent> CLIMBER_DEATH = register("mob.climber.death");
    public static final RegistryRef<SoundEvent> CLIMBER_HURT = register("mob.climber.hurt");
    public static final RegistryRef<SoundEvent> CLIMBER_AMBIENT = register("mob.climber.idle");
    public static final RegistryRef<SoundEvent> CLIMBER_ATTACK = register("mob.climber.attack");

    public static final RegistryRef<SoundEvent> BABY_DEATH = register("mob.baby.death");
    public static final RegistryRef<SoundEvent> BABY_HURT = register("mob.baby.hurt");
    public static final RegistryRef<SoundEvent> BABY_AMBIENT = register("mob.baby.idle");
    public static final RegistryRef<SoundEvent> BABY_ATTACK = register("mob.baby.attack");

    public static final RegistryRef<SoundEvent> WIDEMAN_DEATH = register("mob.wideman.death");
    public static final RegistryRef<SoundEvent> WIDEMAN_HURT = register("mob.wideman.hurt");
    public static final RegistryRef<SoundEvent> WIDEMAN_AMBIENT = register("mob.wideman.idle");
    public static final RegistryRef<SoundEvent> WIDEMAN_ATTACK = register("mob.wideman.attack");

    public static final RegistryRef<SoundEvent> MAIDEN_DEATH = register("mob.maiden.death");
    public static final RegistryRef<SoundEvent> MAIDEN_HURT = register("mob.maiden.hurt");
    public static final RegistryRef<SoundEvent> MAIDEN_AMBIENT = register("mob.maiden.idle");
    public static final RegistryRef<SoundEvent> MAIDEN_ATTACK = register("mob.maiden.attack");

    public static final RegistryRef<SoundEvent> ENTRAIL_DEATH = register("mob.entrail.death");
    public static final RegistryRef<SoundEvent> ENTRAIL_HURT = register("mob.entrail.hurt");
    public static final RegistryRef<SoundEvent> ENTRAIL_AMBIENT = register("mob.entrail.idle");
    public static final RegistryRef<SoundEvent> ENTRAIL_ATTACK = register("mob.entrail.attack");

    public static final RegistryRef<SoundEvent> PRESENT_DEATH = register("mob.present.death");
    public static final RegistryRef<SoundEvent> PRESENT_HURT = register("mob.present.hurt");
    public static final RegistryRef<SoundEvent> PRESENT_AMBIENT = register("mob.present.idle");
    public static final RegistryRef<SoundEvent> PRESENT_ATTACK = register("mob.present.attack");

    public static final RegistryRef<SoundEvent> ZOMBIE_CHICKEN_DEATH = register("mob.zombie_chicken.death");
    public static final RegistryRef<SoundEvent> ZOMBIE_CHICKEN_HURT = register("mob.zombie_chicken.hurt");
    public static final RegistryRef<SoundEvent> ZOMBIE_CHICKEN_AMBIENT = register("mob.zombie_chicken.idle");
    public static final RegistryRef<SoundEvent> ZOMBIE_CHICKEN_ATTACK = register("mob.zombie_chicken.attack");

    private ModSounds() {
    }

    public static void initialize() {
    }

    private static RegistryRef<SoundEvent> register(String name) {
        Identifier id = Identifier.fromNamespaceAndPath(DeadlyMonsters.MOD_ID, name);
        SoundEvent sound = SoundEvent.createVariableRangeEvent(id);
        Registry.register(BuiltInRegistries.SOUND_EVENT, id, sound);
        return new RegistryRef<>(sound);
    }
}
