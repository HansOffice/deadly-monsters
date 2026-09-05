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
- [ ] Port block behavior
- [ ] Port item behavior
- [ ] Port entities and attributes
- [ ] Port AI goals
- [ ] Port networking
- [ ] Port client models/renderers
- [ ] Migrate recipes, loot tables, language data and assets
- [ ] Replace or remove legacy Mantle/TConstruct/CoroUtil/Hostile Worlds integrations
- [ ] Game-test and balance pass

## Upstream

Original project: https://github.com/ACGaming/deadly-monsters

The upstream project is licensed under the MIT License. The original copyright and license notice are retained in `LICENSE`.

## Building

Use JDK 25. Once the Gradle wrapper is present, build with:

```bash
./gradlew build
```

Windows:

```powershell
.\gradlew.bat build
```

## Compatibility note

This is not yet a feature-complete port. Registry scaffolding is intentionally separated from behavioral migration so each old 1.12.2 subsystem can be replaced with its current NeoForge equivalent and tested independently.
