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

The entity registry currently reserves all original IDs. Zombie Chicken has a real 26.2 implementation; the other eleven entries use non-spawning, no-render migration placeholders until their individual ports replace them without changing registry names.

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
- [x] Natural Overworld spawning with original weight 1 / group 1-8 behavior
- [x] Original per-cluster spawn cap of 1
- [x] Preserve original lack of a registered Zombie Chicken-specific SoundEvent binding
- [ ] Configurable spawn/health/strength/speed multipliers after config-system migration

### Freezer

Source behavior/model audit completed; implementation is the next monster migration target.

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
