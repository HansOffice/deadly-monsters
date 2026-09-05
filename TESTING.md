# Deadly Monsters 26.2 — Manual Runtime Checklist

This checklist covers the gameplay/runtime validation that is intentionally not represented by automated tests. Complete it on the exact Minecraft/NeoForge versions used for release.

## Environment

- Minecraft 26.2
- NeoForge 26.2.0.75
- Java 25

Run both a normal client world and a dedicated server before release.

## Startup

- [ ] `gradle runClient` reaches the title screen without mod-loading/resource errors.
- [ ] Create/open a world and verify `dmonsters` content is present.
- [ ] `gradle runServer` reaches a ready dedicated-server state without client-class loading errors.
- [ ] Connect a client to the dedicated server.

## All monsters

Use all 12 spawn eggs and verify that each entity appears, animates, has the expected texture/model, can target/attack where appropriate, plays its sounds, dies cleanly and uses its loot table.

- [ ] Mutant Steve
- [ ] Freezer
- [ ] Climber
- [ ] Entrail
- [ ] Unborn Baby
- [ ] Fallen Leader
- [ ] Bloody Maiden
- [ ] Zombie Chicken
- [ ] Present
- [ ] Stranger
- [ ] Haunted Cow
- [ ] Topielec

## Monster-specific behavior

- [ ] **Mutant Steve:** burns in daylight; with `breakBlocks=true` its special attack can break allowed surrounding blocks; with it disabled the special breaking attack is not used.
- [ ] **Freezer:** applies Slowness on hit; converts nearby water to ice; places snow where valid; aggressive state uses the wider freeze area; particles/texture state look correct.
- [ ] **Climber:** climbs walls; is not trapped by cobwebs; poison does not affect it; hard difficulty can assign the original random permanent buff set.
- [ ] **Entrail:** falls more slowly; non-fire damage can spawn a slime; fire damage does not trigger that slime behavior.
- [ ] **Unborn Baby:** applies Slowness; with `blindness=true`, an actively targeted player receives the periodic Blindness effect.
- [ ] **Fallen Leader:** successful melee hits restore its health as expected.
- [ ] **Bloody Maiden:** first successful attack/hit state switches appearance/behavior; subsequent lethal behavior matches the original mechanic.
- [ ] **Zombie Chicken:** is hostile; attacks players and normal chickens; converts a chicken after successful melee; does not lay eggs or expose vanilla chicken breeding behavior.
- [ ] **Present:** natural spawn requires exposed sky; melee cage teleports the player into the cage and creates **two Creepers** at the center; temporary cage blocks decay.
- [ ] **Stranger:** maintains its avoidance/flee behavior around players and plays the impact sound at the appropriate interaction.
- [ ] **Haunted Cow:** can naturally spawn regardless of hostile-light restrictions; daytime hit with a non-valid weapon triggers the night change unless disabled; swords/bow/configured items remain valid.
- [ ] **Topielec:** behaves underwater, targets within configured `searchDistance`, drags close players toward deeper water, returns toward water when stranded, and `harpoonOnly` blocks direct player attacks from non-Harpoons when the monster is enabled.

## Natural spawning

- [ ] Confirm each enabled monster can naturally spawn in its intended biome/environment.
- [ ] Zombie Chicken and other ordinary land monsters do not naturally spawn in Mushroom Fields.
- [ ] Present respects its snow-biome data selection and sky-visibility rule.
- [ ] Topielec respects its water-biome selection, water placement and vertical range.
- [ ] Spawn group behavior does not exceed the intended per-entity cluster restrictions.
- [ ] Change a monster `spawnRate`, restart the world/server, and verify the setting affects natural-spawn weighting.
- [ ] Set a monster `disabled=true`, restart, and verify it no longer appears naturally.

## Blocks

- [ ] **Strengthened Stone:** normal breaking yields Rebar; sneak-use returns the block to vanilla Stone and recovers Rebar.
- [ ] **Strengthened Cobblestone:** normal breaking yields Rebar; sneak-use returns the block to vanilla Cobblestone and recovers Rebar.
- [ ] **Barbed Wire:** requires support below; hurts colliding living entities and strongly slows horizontal movement.
- [ ] **Mesh Fence:** connects only through the mesh system and cannot be extended beyond the original eight-block pole-anchor rule; unsupported runs self-break.
- [ ] **Mesh Fence Pole:** connects correctly to mesh fence/poles.
- [ ] **Dump:** growth interaction works with representative crops, saplings and modern bonemealable plants; Dump is consumed after successful behavior.
- [ ] **Soul Eye:** cycles Sleeping -> Awaking -> Awake; consumes a nearby mob; Villager emerald chance and generic iron chance behave plausibly; resets afterward.
- [ ] **Christmas Tree:** periodically creates Present Boxes in valid adjacent positions.
- [ ] **Present Box:** exercise the common reward result and hazard result; rare gold/diamond tiers can be checked by repeated breaking or commands.
- [ ] **Present Block:** does not drop itself and disappears randomly as the temporary cage wall.

## Items and projectiles

- [ ] **Rebar:** strengthens both Stone and Cobblestone and is consumed appropriately outside creative mode.
- [ ] **Stone/Iron/Diamond/Obsidian Harpoons:** melee damage/durability feel correct; water-use fishing success drops Cod as the original implementation actually did.
- [ ] **Lucky Egg:** throw/sound/egg-crack particles work; repeated throws exercise equipment reward, short-fuse TNT, baby chicken and Zombie Chicken outcomes.
- [ ] **Dagon:** projectile launches correctly and its random impact behavior/drops match the intended port.
- [ ] **Unborn Baby Eye:** block extraction obeys interaction permission and consumes the eye.
- [ ] **Bloody Maiden Heart:** creates lava normally and water while sneaking, obeys permissions, and loses durability.
- [ ] **Fallen Leader Spine:** produces the heavy knockback effect.
- [ ] **Entrail Flesh:** use/consumption behavior matches the original utility effect.
- [ ] **PooPoo Pill:** creates Dump using the original probability/consumption behavior.
- [ ] **Sunlight Drop:** only works at night, moves the Overworld clock to day when time advancement is enabled, plays its effect and consumes correctly.

## Recipes

Verify the recipe book/crafting table accepts all original recipe families:

- [ ] Barbed Wire recipes (2)
- [ ] Christmas Tree
- [ ] Dagon recipes (4)
- [ ] Glowstone + Sunlight Drop
- [ ] Harpoons: Stone, Iron, Diamond, Obsidian
- [ ] Mesh Fence
- [ ] Mesh Fence Pole
- [ ] PooPoo Pill
- [ ] Rebar
- [ ] Soul Eye

## Loot, visuals and audio

- [ ] Kill every monster at least once and confirm no missing/invalid loot-table errors appear in logs.
- [ ] Check Looting behavior on several special monster pools, especially those that become guaranteed with Looting I.
- [ ] Check entity textures, animation pivots, scale and shadow size from multiple angles.
- [ ] Check original ambient/hurt/attack/death sounds and utility-item/block sounds.
- [ ] Check Lucky Egg item particles and Freezer/utility particles.

## Configuration regression

- [ ] Global health multiplier changes spawned monster max health.
- [ ] Global strength multiplier changes attack damage.
- [ ] Global speed multiplier changes movement speed.
- [ ] Representative per-monster health/strength/speed multipliers stack with the global multiplier.
- [ ] `mutant_steve.breakBlocks`, `unborn_baby.blindness`, `topielec.searchDistance`, `topielec.harpoonOnly`, `haunted_cow.validWeapons` and `haunted_cow.disableTimeChange` all change behavior as documented.

## Release check

- [ ] No missing texture/model (`purple/black`) content.
- [ ] No unknown recipe/loot/biome-modifier errors in `latest.log`.
- [ ] No client-only class crash on dedicated server.
- [ ] Existing world can save/reload all `dmonsters` entities and blocks used during the pass.
- [ ] Final built JAR loads without a development environment.
