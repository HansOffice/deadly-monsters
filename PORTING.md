# Deadly Monsters 1.12.2 -> NeoForge 26.2 Port

## Port baseline

The upstream mod targets Forge 1.12.2. This branch is a native rewrite for Minecraft 26.2 / NeoForge rather than a source-compatibility layer. Original `dmonsters` registry IDs are preserved wherever the content still exists.

Target toolchain:

- Minecraft 26.2
- NeoForge 26.2.0.75
- ModDevGradle 2.0.146
- Gradle 9.2.1
- Java 25

## Completion status

The code/resource migration is complete. Actual gameplay and deployment validation is owned by the project maintainer and is listed in `TESTING.md`.

Completed systems:

- [x] block, item, entity, sound, creative-tab and custom biome-modifier registration
- [x] all original textures, sounds, logo and credits
- [x] modern blockstates, block models, item models and 26.2 item definitions
- [x] all original gameplay blocks and interactions
- [x] all original functional items and projectile behavior
- [x] all 12 original monsters as real 26.2 entities
- [x] all entity models, render states and renderers
- [x] spawn eggs for all original monsters
- [x] natural spawn placement and biome modifiers
- [x] original default spawn rates and group sizes
- [x] entity and block loot tables
- [x] all 17 original recipes
- [x] common configuration and original default multipliers
- [x] special Mutant Steve, Unborn Baby, Haunted Cow and Topielec options
- [x] undead entity classification for the original undead monsters
- [x] current Minecraft world-clock handling for time-changing mechanics
- [x] `gradle build` verification on Java 25 / Gradle 9.2.1 / NeoForge 26.2.0.75

Final compile/package verification succeeded in GitHub Actions run **#112** on implementation commit `3c335278324c2a215631a7956ee72c1ebec730d6`. Later commits only restore the workflow to manual-only mode and record completion status.

## Monsters

All 12 original monster IDs are implemented:

- `mutant_steve` — hostile melee monster; optional environment-breaking attack; daylight burning.
- `freezer` — applies Slowness, freezes water/places snow, switches idle/aggressive appearance.
- `climber` — wall climbing, web/poison immunity and hard-difficulty random permanent buffs.
- `entrail` — slow-fall movement and slime spawning when damaged by non-fire damage.
- `unborn_baby` — Slowness on hit and optional Blindness behavior.
- `fallen_leader` — heals itself after successful melee hits.
- `bloody_maiden` — first-hit trigger state followed by the original lethal follow-up behavior.
- `zombie_chicken` — hostile monster using the original chicken-like model; attacks players/chickens and converts normal chickens without inheriting vanilla egg-laying/breeding behavior.
- `present` — exposed-surface spawning and the original cage attack, including the original loop's two Creepers.
- `stranger` — player avoidance/flee behavior with original attack/impact audio behavior.
- `haunted_cow` — hostile cow-like monster and wrong-weapon daytime clock-changing mechanic.
- `topielec` — underwater monster that drags nearby players toward deeper water and returns to water when stranded.

Natural spawning uses a custom config-aware NeoForge biome modifier so each monster's common-config `spawnRate` is applied when biome spawn lists are built.

## Blocks

Ported block behavior includes:

- strengthened stone/cobblestone: original hardness/resistance, Rebar recovery and sneak-revert behavior;
- barbed wire: support requirement, damage and strong horizontal slowing;
- mesh fence/pole: current fence connectivity while retaining the original eight-block pole-anchor rule;
- Dump: age/model state and crop/sapling/bonemealable growth utility;
- Soul Eye: sleeping/awaking/awake cycle, nearby mob consumption and special Villager/iron drops;
- Christmas Tree: periodic Present Box generation;
- Present Box: original four reward/hazard tiers;
- Present Block: hidden temporary cage block with color state, timed disappearance and no drops.

## Items and projectiles

Ported behavior includes Rebar, four Harpoons, Lucky Egg, Dagon, Unborn Baby Eye, Bloody Maiden Heart, Fallen Leader Spine, Entrail Flesh, PooPoo Pill and Sunlight Drop.

Important behavior-preservation details:

- Harpoon fishing drops Cod because the 1.12.2 implementation discarded the selected fish metadata before spawning the item; this matches the old observable result rather than its apparent intent.
- Lucky Egg preserves the original chained random calls/outcomes and one-chicken result, plus egg-crack item particles.
- block-changing utility items use current interaction permission checks.

## Configuration

Preserved configuration concepts:

- global health/strength/speed multipliers;
- per-monster health/strength/speed multipliers;
- per-monster natural-spawn rate and disabled switch;
- Mutant Steve `breakBlocks`;
- Unborn Baby `blindness`;
- Topielec `searchDistance` and `harpoonOnly`;
- Haunted Cow `validWeapons` and `disableTimeChange`.

Spawn-rate changes are consumed while biome spawn lists are constructed; restart the world/server after changing spawn rate or disabled settings.

## Intentional modernizations / retired integrations

### World clock

Forge 1.12.2 used a configurable integer `dayLengthTicks` and direct world-time mutation. Minecraft 26.2 uses data-driven world clocks/timelines. The numeric `dayLengthTicks` config is therefore retired; Sunlight Drop and Haunted Cow target the active Overworld clock's day/night markers instead of assuming a custom integer day length.

### Hostile Worlds Invasions

The old optional Hostile Worlds Invasions check (`disableTimeChangeInvasions`) is not carried forward because this port has no 26.2 dependency or integration contract for that legacy mod.

### Tinkers' Construct / Mantle / CoroUtil

The old build-time/runtime integrations are not required by the core port. Haunted Cow still supports configurable item registry IDs and standard sword/bow behavior without hard-linking obsolete APIs.

### Topielec scan cadence

The 1.12.2 deep-water search timer resets in a way that makes the expensive search run effectively every tick. The 26.2 port preserves the visible drag-to-deeper-water behavior but refreshes the search on a bounded cadence instead of reproducing that performance bug.

### Legacy metadata

Removed metadata-based items/blocks are represented by their current dedicated IDs, including fish variants, clay balls, firework rockets, dyes and stained glass.

## Validation boundary

Repository-side completion means source/resources are migrated and the project builds/packages against the target toolchain. Interactive checks such as AI feel, model alignment, particles, natural-spawn distribution, client/server play and balance are intentionally left to the maintainer's runtime pass in `TESTING.md`.
