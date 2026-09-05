# Deadly Monsters — NeoForge 26.2 Port

This repository is a community port of [ACGaming/deadly-monsters](https://github.com/ACGaming/deadly-monsters) from Minecraft Forge 1.12.2 to Minecraft 26.2 on NeoForge.

## Target

- Minecraft: 26.2
- NeoForge: 26.2.0.75
- ModDevGradle: 2.0.146
- Java: 25
- Mod ID: `dmonsters`

## Port status

The original project is a Forge 1.12.2 mod. This port is being rebuilt against modern NeoForge APIs rather than attempting to compile the old FML/Forge code unchanged.

Current stage:

- [x] NeoForge 26.2 project scaffold
- [x] Preserve original MIT license and attribution
- [x] Modern DeferredRegister-based block/item registry skeleton
- [x] Restore original registry IDs as placeholders where practical
- [x] Port Rebar stone/cobblestone strengthening behavior
- [x] Restore strengthened block hardness/resistance
- [x] Import original textures, sounds, logo and credits into modern resource paths
- [ ] Finish modern block/item model migration
- [ ] Port block behavior
- [ ] Port remaining item behavior
- [ ] Port entities and attributes
- [ ] Port AI goals
- [ ] Port networking
- [ ] Port client models/renderers
- [ ] Migrate recipes, loot tables and remaining data
- [ ] Replace or remove legacy Mantle/TConstruct/CoroUtil/Hostile Worlds integrations
- [ ] Game-test and balance pass

## Upstream

Original project: https://github.com/ACGaming/deadly-monsters

The upstream project is licensed under the MIT License. The original copyright and license notice are retained in `LICENSE`.

## Building

Use JDK 25. CI builds with Gradle 9.2.1.

```bash
gradle build
```

A standard Gradle wrapper can be added later from the official NeoForge 26.2 MDK.

## Compatibility note

This is not yet a feature-complete port. Registry scaffolding is intentionally separated from behavioral migration so each old 1.12.2 subsystem can be replaced with its current NeoForge equivalent and tested independently.
