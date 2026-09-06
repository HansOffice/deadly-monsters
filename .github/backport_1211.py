from pathlib import Path
import re

ROOT = Path("src/main/java/com/dmonsters")
IS_FABRIC = Path("src/main/resources/fabric.mod.json").exists()


def write(path: Path, text: str) -> None:
    path.write_text(text, encoding="utf-8")


for path in ROOT.rglob("*.java"):
    text = path.read_text(encoding="utf-8")
    text = text.replace("import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;\n", "")
    text = text.replace("import javax.annotation.Nullable;\n", "")
    text = text.replace("@Nullable ", "")
    text = text.replace("@Nullable\n", "")
    text = text.replace(
        "net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile",
        "net.minecraft.world.entity.projectile.ThrowableItemProjectile",
    )
    text = text.replace("import net.minecraft.world.entity.EntityTypes;\n", "")
    text = text.replace("EntityTypes.", "EntityType.")
    text = text.replace(".isBrightOutside()", ".isDay()")
    text = text.replace(".getMinY()", ".getMinBuildHeight()")
    text = text.replace("MobEffects.SLOWNESS", "MobEffects.MOVEMENT_SLOWDOWN")
    text = text.replace("MobEffects.SPEED", "MobEffects.MOVEMENT_SPEED")
    text = text.replace("MobEffects.STRENGTH", "MobEffects.DAMAGE_BOOST")
    text = text.replace("BuiltInRegistries.ENTITY_TYPE.getValue(", "BuiltInRegistries.ENTITY_TYPE.get(")
    text = text.replace(".snapTo(", ".moveTo(")
    text = text.replace(".create(level, MobSpawnType.TRIGGERED)", ".create(level)")
    text = text.replace(".spawnAtLocation(level, ", ".spawnAtLocation(")
    text = text.replace(".hurtServer(level, ", ".hurt(")
    text = text.replace("super.hurtServer(level, ", "super.hurt(")
    text = text.replace(
        "level.getGameRules().get(GameRules.MOB_GRIEFING)",
        "level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)",
    )

    old_sig = "public boolean doHurtTarget(ServerLevel level, Entity target) {"
    if old_sig in text:
        text = text.replace(
            old_sig,
            "public boolean doHurtTarget(Entity target) {\n        ServerLevel level = (ServerLevel) this.level();",
        )
        text = text.replace("super.doHurtTarget(level, target)", "super.doHurtTarget(target)")

    text = re.sub(
        r"public boolean hurtServer\(ServerLevel level, DamageSource source, float damage\)",
        "public boolean hurt(DamageSource source, float damage)",
        text,
    )
    write(path, text)


for rel, marker in [
    ("config/DeadlyMonstersConfig.java", "NIGHT"),
    ("item/SunlightDropItem.java", "DAY"),
]:
    path = ROOT / rel
    text = path.read_text(encoding="utf-8")
    for imp in [
        "import net.minecraft.world.clock.ClockTimeMarkers;\n",
        "import net.minecraft.world.clock.WorldClock;\n",
        "import net.minecraft.world.clock.WorldClocks;\n",
    ]:
        text = text.replace(imp, "")
    text = text.replace(
        "long time = overworld.getOverworldClockTime() % 24000L;",
        "long time = overworld.getDayTime() % 24000L;",
    )
    old = (
        "if (overworld.getGameRules().get(GameRules.ADVANCE_TIME)) {\n"
        "            Registry<WorldClock> clocks = overworld.registryAccess().lookupOrThrow(Registries.WORLD_CLOCK);\n"
        "            Holder<WorldClock> overworldClock = clocks.getOrThrow(WorldClocks.OVERWORLD);\n"
        f"            overworld.clockManager().moveToTimeMarker(overworldClock, ClockTimeMarkers.{marker});\n"
        "        }"
    )
    if marker == "NIGHT":
        new = (
            "if (overworld.getGameRules().getBoolean(GameRules.RULE_DAYLIGHT)) {\n"
            "            long delta = (13000L - time + 24000L) % 24000L;\n"
            "            overworld.setDayTime(overworld.getDayTime() + delta);\n"
            "        }"
        )
    else:
        new = (
            "if (overworld.getGameRules().getBoolean(GameRules.RULE_DAYLIGHT)) {\n"
            "            long delta = (24000L - time) % 24000L;\n"
            "            overworld.setDayTime(overworld.getDayTime() + delta);\n"
            "        }"
        )
    text = text.replace(old, new)
    write(path, text)


barbed = ROOT / "block/BarbedWireBlock.java"
text = barbed.read_text(encoding="utf-8")
text = text.replace("import net.minecraft.world.entity.InsideBlockEffectApplier;\n", "")
text = text.replace(
    "import net.minecraft.world.level.ScheduledTickAccess;\n",
    "import net.minecraft.world.level.LevelAccessor;\n",
)
text = re.sub(
    r"    @Override\n    protected BlockState updateShape\(\n            BlockState state,\n            LevelReader level,\n            ScheduledTickAccess ticks,\n            BlockPos pos,\n            Direction directionToNeighbour,\n            BlockPos neighbourPos,\n            BlockState neighbourState,\n            RandomSource random\) \{\n        if \(directionToNeighbour == Direction.DOWN && !state.canSurvive\(level, pos\)\) \{\n            ticks.scheduleTick\(pos, this, 1\);\n        \}\n        return super.updateShape\(state, level, ticks, pos, directionToNeighbour, neighbourPos, neighbourState, random\);\n    \}",
    "    @Override\n    protected BlockState updateShape(\n            BlockState state,\n            Direction directionToNeighbour,\n            BlockState neighbourState,\n            LevelAccessor level,\n            BlockPos pos,\n            BlockPos neighbourPos) {\n        if (directionToNeighbour == Direction.DOWN && !state.canSurvive(level, pos)) {\n            level.scheduleTick(pos, this, 1);\n        }\n        return super.updateShape(state, directionToNeighbour, neighbourState, level, pos, neighbourPos);\n    }",
    text,
)
text = re.sub(
    r"    @Override\n    protected void entityInside\(\n            BlockState state,\n            Level level,\n            BlockPos pos,\n            Entity entity,\n            InsideBlockEffectApplier effectApplier,\n            boolean isPrecise\) \{",
    "    @Override\n    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {",
    text,
)
write(barbed, text)

mesh = ROOT / "block/MeshFenceBlock.java"
text = mesh.read_text(encoding="utf-8")
text = text.replace(
    "import net.minecraft.world.level.LevelReader;\nimport net.minecraft.world.level.ScheduledTickAccess;\n",
    "import net.minecraft.world.level.LevelReader;\nimport net.minecraft.world.level.LevelAccessor;\n",
)
text = re.sub(
    r"    @Override\n    protected BlockState updateShape\(\n            BlockState state,\n            LevelReader level,\n            ScheduledTickAccess ticks,\n            BlockPos pos,\n            Direction directionToNeighbour,\n            BlockPos neighbourPos,\n            BlockState neighbourState,\n            RandomSource random\) \{\n        BlockState updated = super.updateShape\(state, level, ticks, pos, directionToNeighbour, neighbourPos, neighbourState, random\);\n        if \(directionToNeighbour.getAxis\(\).isHorizontal\(\) && !this.hasPolePath\(level, pos\)\) \{\n            ticks.scheduleTick\(pos, this, 1\);\n        \}\n        return updated;\n    \}",
    "    @Override\n    protected BlockState updateShape(\n            BlockState state,\n            Direction directionToNeighbour,\n            BlockState neighbourState,\n            LevelAccessor level,\n            BlockPos pos,\n            BlockPos neighbourPos) {\n        BlockState updated = super.updateShape(state, directionToNeighbour, neighbourState, level, pos, neighbourPos);\n        if (directionToNeighbour.getAxis().isHorizontal() && !this.hasPolePath(level, pos)) {\n            level.scheduleTick(pos, this, 1);\n        }\n        return updated;\n    }",
    text,
)
write(mesh, text)

dump = ROOT / "block/DumpBlock.java"
text = dump.read_text(encoding="utf-8")
text = text.replace("import net.minecraft.world.level.redstone.Orientation;\n", "")
text = text.replace(
    "            Block changedBlock,\n            Orientation orientation,\n            boolean movedByPiston)",
    "            Block changedBlock,\n            BlockPos changedPos,\n            boolean movedByPiston)",
)
write(dump, text)


for rel in ["projectile/LuckyEggProjectile.java", "projectile/DagonProjectile.java"]:
    path = ROOT / rel
    text = path.read_text(encoding="utf-8")
    text = re.sub(
        r"super\((ModEntities\.[A-Z_]+\.get\(\)), owner, level, stack\);",
        r"super(\1, owner, level);\n        this.setItem(stack);",
        text,
    )
    write(path, text)


path = ROOT / "entity/TopielecEntity.java"
text = path.read_text(encoding="utf-8")
text = re.sub(
    r"\n    @Override\n    public boolean canBreatheUnderwater\(\) \{\n        return true;\n    \}\n",
    "\n",
    text,
)
write(path, text)


path = ROOT / "entity/MutantSteveEntity.java"
text = path.read_text(encoding="utf-8")
text = re.sub(
    r"living.knockback\(\n                    2\.0D,\n                    target.getX\(\) - this.getX\(\),\n                    target.getZ\(\) - this.getZ\(\),\n                    level.damageSources\(\).mobAttack\(this\),\n                    \(float\) this.getAttributeValue\(Attributes.ATTACK_DAMAGE\)\);",
    "living.knockback(\n                    2.0D,\n                    target.getX() - this.getX(),\n                    target.getZ() - this.getZ());",
    text,
)
write(path, text)


path = ROOT / "item/HarpoonItem.java"
text = path.read_text(encoding="utf-8")
text = text.replace("import java.util.function.Consumer;\n", "import java.util.List;\n")
text = text.replace("import net.minecraft.world.item.component.TooltipDisplay;\n", "")
text = re.sub(
    r"public void appendHoverText\(\n            ItemStack stack,\n            Item.TooltipContext context,\n            TooltipDisplay display,\n            Consumer<Component> tooltip,\n            TooltipFlag flag\)",
    "public void appendHoverText(\n            ItemStack stack,\n            Item.TooltipContext context,\n            List<Component> tooltip,\n            TooltipFlag flag)",
    text,
)
text = text.replace("tooltip.accept(", "tooltip.add(")
text = text.replace(
    "public void hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {",
    "public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {",
)
text = text.replace(
    "        stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);\n    }\n\n    @Override\n    public void appendHoverText",
    "        stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);\n        return true;\n    }\n\n    @Override\n    public void appendHoverText",
)
write(path, text)

path = ROOT / "item/PoopooPillItem.java"
text = path.read_text(encoding="utf-8").replace("ItemUseAnimation", "UseAnim")
write(path, text)


if IS_FABRIC:
    path = ROOT / "registry/ModCreativeTabs.java"
    text = path.read_text(encoding="utf-8")
    text = text.replace(
        "net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab",
        "net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup",
    )
    text = text.replace("FabricCreativeModeTab.builder()", "FabricItemGroup.builder()")
    write(path, text)

    path = ROOT / "DeadlyMonsters.java"
    text = path.read_text(encoding="utf-8")
    text = text.replace(
        "DeadlyMonstersConfig.onEntityJoinLevel(entity, entity.isLoadedFromDisk())",
        "DeadlyMonstersConfig.onEntityJoinLevel(entity, false)",
    )
    write(path, text)
