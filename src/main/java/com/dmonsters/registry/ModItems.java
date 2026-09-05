package com.dmonsters.registry;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.item.RebarItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

/** Item registry scaffold retaining the original 1.12.2 registry names. */
public final class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(DeadlyMonsters.MOD_ID);

    public static final DeferredItem<BlockItem> STRENGTHENED_STONE = ITEMS.registerSimpleBlockItem("strengthened_stone", ModBlocks.STRENGTHENED_STONE);
    public static final DeferredItem<BlockItem> STRENGTHENED_COBBLESTONE = ITEMS.registerSimpleBlockItem("strengthened_cobblestone", ModBlocks.STRENGTHENED_COBBLESTONE);
    public static final DeferredItem<BlockItem> BARBED_WIRE = ITEMS.registerSimpleBlockItem("barbed_wire", ModBlocks.BARBED_WIRE);
    public static final DeferredItem<BlockItem> MESH_FENCE = ITEMS.registerSimpleBlockItem("mesh_fence", ModBlocks.MESH_FENCE);
    public static final DeferredItem<BlockItem> MESH_FENCE_POLE = ITEMS.registerSimpleBlockItem("mesh_fence_pole", ModBlocks.MESH_FENCE_POLE);
    public static final DeferredItem<BlockItem> DUMP = ITEMS.registerSimpleBlockItem("dump", ModBlocks.DUMP);
    public static final DeferredItem<BlockItem> SOUL_EYE = ITEMS.registerSimpleBlockItem("soul_eye", ModBlocks.SOUL_EYE);
    public static final DeferredItem<BlockItem> PRESENT_BLOCK = ITEMS.registerSimpleBlockItem("present_block", ModBlocks.PRESENT_BLOCK);
    public static final DeferredItem<BlockItem> CHRISTMAS_TREE = ITEMS.registerSimpleBlockItem("christmas_tree", ModBlocks.CHRISTMAS_TREE);
    public static final DeferredItem<BlockItem> PRESENT_BOX = ITEMS.registerSimpleBlockItem("present_box", ModBlocks.PRESENT_BOX);

    public static final DeferredItem<RebarItem> REBAR = ITEMS.registerItem("rebar", RebarItem::new);
    public static final DeferredItem<Item> LUCKY_EGG = simple("lucky_egg");
    public static final DeferredItem<Item> UNBORN_BABY_EYE = simple("unborn_baby_eye");
    public static final DeferredItem<Item> BLOODY_MAIDEN_HEART = simple("bloody_maiden_heart");
    public static final DeferredItem<Item> FALLEN_LEADER_SPINE = simple("fallen_leader_spine");
    public static final DeferredItem<Item> ENTRAIL_FLESH = simple("entrail_flesh");
    public static final DeferredItem<Item> POOPOO_PILL = simple("poopoo_pill");
    public static final DeferredItem<Item> DAGON = simple("dagon");
    public static final DeferredItem<Item> FLYING_DAGON = simple("flying_dagon");
    public static final DeferredItem<Item> SUNLIGHT_DROP = simple("sunlight_drop");
    public static final DeferredItem<Item> MOD_ITEM = simple("mod_item");

    public static final DeferredItem<Item> MOB_SPAWNER_ITEM_UNBORN_BABY = simple("mob_spawner_item_unborn_baby");
    public static final DeferredItem<SpawnEggItem> MOB_SPAWNER_ITEM_CLIMBER = spawnEgg("mob_spawner_item_climber", ModEntities.CLIMBER);
    public static final DeferredItem<SpawnEggItem> MOB_SPAWNER_ITEM_ENTRAIL = spawnEgg("mob_spawner_item_entrail", ModEntities.ENTRAIL);
    public static final DeferredItem<SpawnEggItem> MOB_SPAWNER_ITEM_FREEZER = spawnEgg("mob_spawner_item_freezer", ModEntities.FREEZER);
    public static final DeferredItem<Item> MOB_SPAWNER_ITEM_MUTANT_STEVE = simple("mob_spawner_item_mutant_steve");
    public static final DeferredItem<Item> MOB_SPAWNER_ITEM_FALLEN_LEADER = simple("mob_spawner_item_fallen_leader");
    public static final DeferredItem<Item> MOB_SPAWNER_ITEM_BLOODY_MAIDEN = simple("mob_spawner_item_bloody_maiden");
    public static final DeferredItem<SpawnEggItem> MOB_SPAWNER_ITEM_ZOMBIE_CHICKEN = spawnEgg("mob_spawner_item_zombie_chicken", ModEntities.ZOMBIE_CHICKEN);
    public static final DeferredItem<Item> MOB_SPAWNER_ITEM_PRESENT = simple("mob_spawner_item_present");
    public static final DeferredItem<Item> MOB_SPAWNER_ITEM_STRANGER = simple("mob_spawner_item_stranger");
    public static final DeferredItem<Item> MOB_SPAWNER_ITEM_HAUNTED_COW = simple("mob_spawner_item_haunted_cow");
    public static final DeferredItem<Item> MOB_SPAWNER_ITEM_TOPIELEC = simple("mob_spawner_item_topielec");

    public static final DeferredItem<Item> HARPOON_STONE = simple("harpoon_stone");
    public static final DeferredItem<Item> HARPOON_IRON = simple("harpoon_iron");
    public static final DeferredItem<Item> HARPOON_DIAMOND = simple("harpoon_diamond");
    public static final DeferredItem<Item> HARPOON_OBSIDIAN = simple("harpoon_obsidian");

    private ModItems() {
    }

    private static DeferredItem<Item> simple(String name) {
        return ITEMS.registerSimpleItem(name, properties -> properties);
    }

    private static <T extends net.minecraft.world.entity.Mob> DeferredItem<SpawnEggItem> spawnEgg(
            String name,
            net.neoforged.neoforge.registries.DeferredHolder<net.minecraft.world.entity.EntityType<?>, net.minecraft.world.entity.EntityType<T>> type) {
        return ITEMS.registerItem(name, properties -> new SpawnEggItem(properties.spawnEgg(type.get())));
    }
}
