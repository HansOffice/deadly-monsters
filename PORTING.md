# Deadly Monsters 1.12.2 -> NeoForge 26.2 Port Plan

## Baseline

The upstream project targets Forge 1.12.2 and uses legacy FML lifecycle events, sided proxies, RegistryEvent registration, legacy entity rendering, and several 1.12-era optional/integration dependencies.

The 26.2 branch is intentionally a native NeoForge rewrite around stable registry IDs rather than a source-level compatibility shim.

## Phase 1 — Project and registries

- [x] Java 25 / Minecraft 26.2 / NeoForge 26.2 project
- [x] `dmonsters` mod metadata
- [x] Block registry IDs
- [x] Item registry IDs
- [x] Creative tab
- [x] Import original binary textures, sounds, logo and credits into modern resource paths
- [x] Modern block/item models for Strengthened Stone, Strengthened Cobblestone and Rebar
- [x] Entity type registry IDs for all 12 original monsters
- [x] Sound event registrations from the original 1.12.2 `ModSounds`
- [ ] Data components/config equivalents where needed

The entity registry currently reserves all original IDs. Zombie Chicken, Freezer and Climber now have real 26.2 implementations; the other nine entries use non-spawning, no-render migration placeholders until their individual ports replace them without changing registry names.

## Phase 2 — Simple gameplay content

- [x] Strengthened Stone registry/properties
- [x] Strengthened Cobblestone registry/properties
- [x] Rebar stone/cobblestone strengthening interaction
- [ ] Strengthened block break/drop and sneak-revert behavior
- [ ] Barbed Wire
- [ ] Mesh Fence / Pole
- [ ] Dump
- [ ] Soul Eye
- [ ] Present / Christmas blocks
- [ ] Harpoons and projectiles
- [ ] Remaining consumables/drops

## Phase 3 — Monsters

Original entity IDs to migrate:

- unborn_baby
- climber
- entrail
- freezer
- mutant_steve
- fallen_leader
- bloody_maiden
- zombie_chicken
- present
- stranger
- haunted_cow
- topielec

For each entity: EntityType -> attributes -> goals -> spawn rules -> drops -> sounds -> renderer/model -> client registration -> spawn item compatibility.

### Zombie Chicken

- [x] `dmonsters:zombie_chicken` EntityType
- [x] 16 health / 8 attack / 2 armor / 0.26 movement / 35 follow range
- [x] Hostile player and chicken targeting
- [x] Chicken-to-Zombie-Chicken conversion on successful melee attack
- [x] Direct-daylight burning behavior
- [x] Functional `mob_spawner_item_zombie_chicken` SpawnEggItem
- [x] Client renderer registration
- [x] Original Zombie Chicken texture
- [x] Original custom 32x32 model geometry ported to the modern model-layer API
- [x] Original Lucky Egg loot table
- [x] Natural Overworld spawning with default weight 12 / group 1-8 behavior
- [x] Original per-cluster spawn cap of 1
- [x] Preserve original lack of a registered Zombie Chicken-specific SoundEvent binding
- [ ] Configurable spawn/health/strength/speed multipliers after config-system migration

### Freezer

- [x] `dmonsters:freezer` EntityType replacing the migration placeholder
- [x] 45 health / 16 attack / 2 armor / 0.13 movement / 35 follow range
- [x] Player-targeting melee AI and 600-tick Slowness on successful attacks
- [x] Original attack / ambient / hurt / death SoundEvents
- [x] Direct-daylight burning behavior
- [x] 40-tick environment-freezing cycle: water to ice and survivable snow placement
- [x] Aggressive-state freeze radius expansion
- [x] Client snow particle effect
- [x] Original 64x64 custom model geometry ported to the modern model-layer API
- [x] Original idle / angry texture switching via client render state
- [x] Functional `mob_spawner_item_freezer` SpawnEggItem and item model
- [x] Natural Overworld spawning with default weight 8 / group size 1
- [x] Original per-cluster spawn cap of 1
- [x] Freezer loot table migrated to the modern loot format
- [ ] Configurable spawn/health/strength/speed multipliers after config-system migration

### Climber

- [x] `dmonsters:climber` EntityType replacing the migration placeholder
- [x] 24 health / 12 attack / 0.1 movement
- [x] Wall-climber navigation and synchronized horizontal-collision climbing state
- [x] Leap / melee AI, retaliation, player targeting and iron-golem targeting
- [x] Poison immunity and cobweb movement immunity
- [x] Hard-difficulty random permanent Speed / Strength / Regeneration / Invisibility effect behavior
- [x] Direct-daylight burning behavior
- [x] Original attack / ambient / hurt / death SoundEvents and spider step sound
- [x] Original 64x64 model geometry and attack/walk animation ported to the modern model-layer API
- [x] Original Climber texture and dedicated renderer
- [x] Functional `mob_spawner_item_climber` SpawnEggItem and item model
- [x] Natural Overworld spawning with default weight 8 / group 1-5 behavior
- [x] Original per-cluster spawn cap of 5
- [x] Climber loot table migrated to modern items/loot functions (`minecraft:dye` metadata 0 -> `minecraft:ink_sac`)
- [x] Avoid importing unrelated modern Spider behavior such as skeleton jockeys and armadillo avoidance
- [ ] Configurable spawn/health/strength/speed multipliers after config-system migration

## Phase 4 — Systems

- [ ] Networking replacement for old packet classes
- [ ] Configuration migration
- [ ] Spawn configuration
- [ ] Recipes and loot tables
- [ ] Remaining models/blockstates and data migration
- [ ] Dedicated server verification
- [ ] Client verification

## Legacy dependencies

The original build references Mantle, Tinkers' Construct, CoroUtil and Hostile Worlds Invasions. They are not blindly copied into the 26.2 build. Each integration will be evaluated independently and either replaced with a current API, made optional, or removed where it is not part of core Deadly Monsters behavior.
