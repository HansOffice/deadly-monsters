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
- [ ] Entity types
- [ ] Sound events
- [ ] Data components/config equivalents where needed

## Phase 2 — Simple gameplay content

- [ ] Strengthened Stone
- [ ] Strengthened Cobblestone
- [ ] Rebar behavior
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

## Phase 4 — Systems

- [ ] Networking replacement for old packet classes
- [ ] Configuration migration
- [ ] Spawn configuration
- [ ] Recipes and loot tables
- [ ] Models/blockstates/textures/sounds
- [ ] Dedicated server verification
- [ ] Client verification

## Legacy dependencies

The original build references Mantle, Tinkers' Construct, CoroUtil and Hostile Worlds Invasions. They are not blindly copied into the 26.2 build. Each integration will be evaluated independently and either replaced with a current API, made optional, or removed where it is not part of core Deadly Monsters behavior.
