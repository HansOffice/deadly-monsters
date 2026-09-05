# Deadly Monsters — NeoForge 26.2 Port

A native NeoForge port of [ACGaming/deadly-monsters](https://github.com/ACGaming/deadly-monsters) from Minecraft Forge 1.12.2 to Minecraft 26.2.

The port keeps the original `dmonsters` registry IDs and gameplay identity while replacing the removed Forge 1.12.2 APIs with current Minecraft/NeoForge systems.

## Target

- Minecraft: **26.2**
- NeoForge: **26.2.0.75**
- ModDevGradle: **2.0.146**
- Gradle: **9.2.1**
- Java: **25**
- Mod ID: `dmonsters`

## Status

The source, gameplay content, client renderers, resources and data migration are complete. Runtime/gameplay validation is intentionally tracked separately in [`TESTING.md`](TESTING.md).

Ported content includes:

- all 12 original monsters with native 26.2 entity types, attributes, AI and renderers;
- all original monster spawn eggs;
- the original blocks and their special behavior;
- Rebar, four Harpoons, Lucky Egg, Dagon and the original monster-drop utility items;
- Lucky Egg and Dagon projectiles;
- original textures and sounds;
- modern blockstates, models and item definitions;
- all 17 original crafting recipes;
- entity/block loot tables using current item IDs and loot syntax;
- configurable monster health, strength, speed, natural-spawn weighting and disable switches;
- special configuration for Mutant Steve, Unborn Baby, Haunted Cow and Topielec;
- natural spawning through a config-aware NeoForge biome modifier.

See [`PORTING.md`](PORTING.md) for compatibility decisions and deliberate modernizations.

## Development runs

A system Gradle 9.2.1 installation is currently used; this repository does not ship a Gradle wrapper.

```bash
gradle runClient
gradle runServer
gradle runData
gradle build
```

Use JDK 25 for all development and build commands.

## Configuration

NeoForge generates the Deadly Monsters common configuration on first run. The port preserves the original default monster multipliers and spawn rates.

Natural-spawn `spawnRate` and `disabled` settings affect biome spawn lists and should be changed before starting/restarting the world or dedicated server.

## Compatibility decisions

This is a native port, not a compatibility shim. A few 1.12.2 implementation details cannot or should not be reproduced literally:

- **Hostile Worlds Invasions integration** is not included because the 26.2 port has no dependency on that legacy integration.
- The old numeric **`dayLengthTicks`** option is retired. Minecraft 26.2 uses data-driven world clocks/timelines; Sunlight Drop and Haunted Cow use the active Overworld clock markers instead.
- The original Topielec deep-water search contained an effectively every-tick wide-area scan. The visible behavior is preserved with a bounded refresh cadence rather than copying the performance bug.
- Legacy metadata items were mapped to their modern IDs (for example fish, clay balls, fireworks, dyes and stained glass).
- Modern interaction permission checks are used where old direct block replacement would otherwise bypass current protection APIs.

## Upstream and license

Original project: [ACGaming/deadly-monsters](https://github.com/ACGaming/deadly-monsters)

The upstream project is licensed under the MIT License. The original copyright and license notice are retained in [`LICENSE`](LICENSE).
