from pathlib import Path
import re

ROOT = Path("src/main/java/com/dmonsters")
IS_FABRIC = Path("src/main/resources/fabric.mod.json").exists()


def patch(rel: str, fn) -> None:
    path = ROOT / rel
    text = path.read_text(encoding="utf-8")
    path.write_text(fn(text), encoding="utf-8")


def add_import(text: str, anchor: str, new_import: str) -> str:
    if new_import in text:
        return text
    return text.replace(anchor, anchor + new_import, 1)


def fix_entrail(text: str) -> str:
    return text.replace(
        "    public boolean hurt(DamageSource source, float damage) {\n        if (!source.is(DamageTypeTags.IS_FIRE)) {",
        "    public boolean hurt(DamageSource source, float damage) {\n        if (this.level() instanceof ServerLevel level && !source.is(DamageTypeTags.IS_FIRE)) {",
    )

patch("entity/EntrailEntity.java", fix_entrail)
patch("projectile/LuckyEggProjectile.java", lambda t: t.replace("new ItemParticleOption(ParticleTypes.ITEM, Items.EGG)", "new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Items.EGG))"))


def fix_present_box(text: str) -> str:
    text = add_import(text, "import net.minecraft.world.InteractionResult;\n", "import net.minecraft.world.ItemInteractionResult;\n")
    text = text.replace("    protected InteractionResult useItemOn(\n", "    protected ItemInteractionResult useItemOn(\n", 1)
    text = text.replace("        return openPresent(level, pos, player);\n    }\n\n    private static InteractionResult openPresent", "        openPresent(level, pos, player);\n        return ItemInteractionResult.SUCCESS;\n    }\n\n    private static InteractionResult openPresent", 1)
    return text

patch("block/PresentBoxBlock.java", fix_present_box)


def fix_strengthened(text: str) -> str:
    text = add_import(text, "import net.minecraft.world.InteractionResult;\n", "import net.minecraft.world.ItemInteractionResult;\n")
    text = text.replace("    protected InteractionResult useItemOn(\n", "    protected ItemInteractionResult useItemOn(\n", 1)
    text = text.replace("        if (player.isShiftKeyDown()) {\n            return this.revert(level, pos);\n        }\n        return super.useItemOn(itemStack, state, level, pos, player, hand, hitResult);", "        if (player.isShiftKeyDown()) {\n            this.revert(level, pos);\n            return ItemInteractionResult.SUCCESS;\n        }\n        return super.useItemOn(itemStack, state, level, pos, player, hand, hitResult);", 1)
    return text

patch("block/StrengthenedBlock.java", fix_strengthened)


def fix_throwable_item(text: str) -> str:
    text = add_import(text, "import net.minecraft.world.InteractionResult;\n", "import net.minecraft.world.InteractionResultHolder;\n")
    text = text.replace("public InteractionResult use(Level level, Player player, InteractionHand hand)", "public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand)")
    text = text.replace("return InteractionResult.SUCCESS;", "return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());")
    return text

patch("item/LuckyEggItem.java", fix_throwable_item)
patch("item/DagonItem.java", fix_throwable_item)


def fix_sunlight(text: str) -> str:
    text = add_import(text, "import net.minecraft.world.InteractionResult;\n", "import net.minecraft.world.InteractionResultHolder;\n")
    text = text.replace("public InteractionResult use(Level level, Player player, InteractionHand hand) {\n        if (!(level instanceof ServerLevel playerLevel)) {\n            return InteractionResult.SUCCESS;\n        }", "public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {\n        ItemStack stack = player.getItemInHand(hand);\n        if (!(level instanceof ServerLevel playerLevel)) {\n            return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());\n        }")
    text = text.replace("            return InteractionResult.FAIL;", "            return InteractionResultHolder.fail(stack);", 1)
    text = text.replace("        ItemStack stack = player.getItemInHand(hand);\n        stack.shrink(1);", "        stack.shrink(1);")
    text = text.replace("        return InteractionResult.SUCCESS;\n    }", "        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());\n    }", 1)
    return text

patch("item/SunlightDropItem.java", fix_sunlight)


def fix_pill(text: str) -> str:
    text = add_import(text, "import net.minecraft.world.InteractionResult;\n", "import net.minecraft.world.InteractionResultHolder;\n")
    text = text.replace("public InteractionResult use(Level level, Player player, InteractionHand hand) {\n        if (player.canEat(true)) {", "public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {\n        ItemStack stack = player.getItemInHand(hand);\n        if (player.canEat(true)) {")
    text = text.replace("            return InteractionResult.CONSUME;", "            return InteractionResultHolder.consume(stack);", 1)
    text = text.replace("        return InteractionResult.FAIL;", "        return InteractionResultHolder.fail(stack);", 1)
    text = text.replace("player.hurtServer(serverLevel, player.damageSources().generic(), 999.0F)", "player.hurt(player.damageSources().generic(), 999.0F)")
    return text

patch("item/PoopooPillItem.java", fix_pill)

for rel in ["item/BloodyMaidenHeartItem.java", "item/HarpoonItem.java"]:
    def fix_slot(text: str) -> str:
        text = add_import(text, "import net.minecraft.world.InteractionResult;\n", "import net.minecraft.world.InteractionHand;\n")
        anchor = "import net.minecraft.world.entity.LivingEntity;\n" if "import net.minecraft.world.entity.LivingEntity;\n" in text else "import net.minecraft.world.InteractionHand;\n"
        text = add_import(text, anchor, "import net.minecraft.world.entity.EquipmentSlot;\n")
        return text.replace("context.getHand().asEquipmentSlot()", "context.getHand() == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND")
    patch(rel, fix_slot)


def fix_spine(text: str) -> str:
    text = text.replace("public void hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {", "public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {")
    if "        return true;\n    }\n}" not in text:
        text = text.replace("        }\n    }\n}", "        }\n        return true;\n    }\n}", 1)
    return text

patch("item/FallenLeaderSpineItem.java", fix_spine)


def fix_flesh(text: str) -> str:
    text = text.replace("public void hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {", "public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {")
    text = text.replace("        if (!(attacker.level() instanceof ServerLevel level) || target instanceof EntrailEntity) {\n            return;\n        }", "        if (!(attacker.level() instanceof ServerLevel level) || target instanceof EntrailEntity) {\n            return false;\n        }")
    if "        stack.shrink(1);\n        return true;" not in text:
        text = text.replace("        stack.shrink(1);\n    }", "        stack.shrink(1);\n        return true;\n    }", 1)
    return text

patch("item/EntrailFleshItem.java", fix_flesh)


def fix_entities(text: str) -> str:
    text = text.replace("Monster.checkSurfaceMonstersSpawnRules", "Monster.checkMonsterSpawnRules")
    text = text.replace("private static <T extends Mob> void registerCommonMonsterPlacement", "private static <T extends Monster> void registerCommonMonsterPlacement")
    text = text.replace(".build(key(name))", ".build(key(name).location().toString())")
    text = text.replace("EntityType<T> type = builder.build(key);", "EntityType<T> type = builder.build(key.location().toString());")
    return text

patch("registry/ModEntities.java", fix_entities)

if IS_FABRIC:
    patch("registry/ModBlocks.java", lambda t: t.replace("factory.apply(properties.setId(key))", "factory.apply(properties)"))

    def fix_fabric_items(text: str) -> str:
        text = text.replace("properties.useBlockDescriptionPrefix()", "properties")
        text = text.replace("new SpawnEggItem(properties.spawnEgg(type.get()))", "new SpawnEggItem(type.get(), 0xFFFFFF, 0xFFFFFF, properties)")
        text = text.replace("factory.apply(properties.setId(key))", "factory.apply(properties)")
        return text

    patch("registry/ModItems.java", fix_fabric_items)
else:
    def fix_neoforge_items(text: str) -> str:
        if "import net.neoforged.neoforge.common.DeferredSpawnEggItem;\n" not in text:
            text = text.replace("import net.minecraft.world.item.SpawnEggItem;\n", "import net.minecraft.world.item.SpawnEggItem;\nimport net.neoforged.neoforge.common.DeferredSpawnEggItem;\n")
        text = text.replace("return ITEMS.registerSimpleItem(name, properties -> properties);", "return ITEMS.registerSimpleItem(name);")
        text = text.replace("return ITEMS.registerItem(name, properties -> new SpawnEggItem(properties.spawnEgg(type.get())));", "return ITEMS.register(name, () -> new DeferredSpawnEggItem(type, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));")
        return text

    patch("registry/ModItems.java", fix_neoforge_items)
