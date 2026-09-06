package com.dmonsters.registry;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.item.BloodyMaidenHeartItem;
import com.dmonsters.item.DagonItem;
import com.dmonsters.item.EntrailFleshItem;
import com.dmonsters.item.FallenLeaderSpineItem;
import com.dmonsters.item.HarpoonItem;
import com.dmonsters.item.LuckyEggItem;
import com.dmonsters.item.PoopooPillItem;
import com.dmonsters.item.RebarItem;
import com.dmonsters.item.SunlightDropItem;
import com.dmonsters.item.UnbornBabyEyeItem;
import java.util.function.Function;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

public final class ModItems {
    public static final RegistryRef<BlockItem> STRENGTHENED_STONE = blockItem("strengthened_stone", ModBlocks.STRENGTHENED_STONE);
    public static final RegistryRef<BlockItem> STRENGTHENED_COBBLESTONE = blockItem("strengthened_cobblestone", ModBlocks.STRENGTHENED_COBBLESTONE);
    public static final RegistryRef<BlockItem> BARBED_WIRE = blockItem("barbed_wire", ModBlocks.BARBED_WIRE);
    public static final RegistryRef<BlockItem> MESH_FENCE = blockItem("mesh_fence", ModBlocks.MESH_FENCE);
    public static final RegistryRef<BlockItem> MESH_FENCE_POLE = blockItem("mesh_fence_pole", ModBlocks.MESH_FENCE_POLE);
    public static final RegistryRef<BlockItem> DUMP = blockItem("dump", ModBlocks.DUMP);
    public static final RegistryRef<BlockItem> SOUL_EYE = blockItem("soul_eye", ModBlocks.SOUL_EYE);
    public static final RegistryRef<BlockItem> PRESENT_BLOCK = blockItem("present_block", ModBlocks.PRESENT_BLOCK);
    public static final RegistryRef<BlockItem> CHRISTMAS_TREE = blockItem("christmas_tree", ModBlocks.CHRISTMAS_TREE);
    public static final RegistryRef<BlockItem> PRESENT_BOX = blockItem("present_box", ModBlocks.PRESENT_BOX);

    public static final RegistryRef<RebarItem> REBAR = register("rebar", RebarItem::new, new Item.Properties());
    public static final RegistryRef<LuckyEggItem> LUCKY_EGG = register("lucky_egg", LuckyEggItem::new, new Item.Properties());
    public static final RegistryRef<UnbornBabyEyeItem> UNBORN_BABY_EYE = register("unborn_baby_eye", UnbornBabyEyeItem::new, new Item.Properties());
    public static final RegistryRef<BloodyMaidenHeartItem> BLOODY_MAIDEN_HEART = register(
            "bloody_maiden_heart", BloodyMaidenHeartItem::new, new Item.Properties().durability(11));
    public static final RegistryRef<FallenLeaderSpineItem> FALLEN_LEADER_SPINE = register(
            "fallen_leader_spine", FallenLeaderSpineItem::new, new Item.Properties().stacksTo(1));
    public static final RegistryRef<EntrailFleshItem> ENTRAIL_FLESH = register("entrail_flesh", EntrailFleshItem::new, new Item.Properties());
    public static final RegistryRef<PoopooPillItem> POOPOO_PILL = register("poopoo_pill", PoopooPillItem::new, new Item.Properties());
    public static final RegistryRef<DagonItem> DAGON = register("dagon", DagonItem::new, new Item.Properties());
    public static final RegistryRef<Item> FLYING_DAGON = register("flying_dagon", Item::new, new Item.Properties());
    public static final RegistryRef<SunlightDropItem> SUNLIGHT_DROP = register("sunlight_drop", SunlightDropItem::new, new Item.Properties());
    public static final RegistryRef<Item> MOD_ITEM = register("mod_item", Item::new, new Item.Properties());

    public static final RegistryRef<SpawnEggItem> MOB_SPAWNER_ITEM_UNBORN_BABY = spawnEgg("mob_spawner_item_unborn_baby", ModEntities.UNBORN_BABY);
    public static final RegistryRef<SpawnEggItem> MOB_SPAWNER_ITEM_CLIMBER = spawnEgg("mob_spawner_item_climber", ModEntities.CLIMBER);
    public static final RegistryRef<SpawnEggItem> MOB_SPAWNER_ITEM_ENTRAIL = spawnEgg("mob_spawner_item_entrail", ModEntities.ENTRAIL);
    public static final RegistryRef<SpawnEggItem> MOB_SPAWNER_ITEM_FREEZER = spawnEgg("mob_spawner_item_freezer", ModEntities.FREEZER);
    public static final RegistryRef<SpawnEggItem> MOB_SPAWNER_ITEM_MUTANT_STEVE = spawnEgg("mob_spawner_item_mutant_steve", ModEntities.MUTANT_STEVE);
    public static final RegistryRef<SpawnEggItem> MOB_SPAWNER_ITEM_FALLEN_LEADER = spawnEgg("mob_spawner_item_fallen_leader", ModEntities.FALLEN_LEADER);
    public static final RegistryRef<SpawnEggItem> MOB_SPAWNER_ITEM_BLOODY_MAIDEN = spawnEgg("mob_spawner_item_bloody_maiden", ModEntities.BLOODY_MAIDEN);
    public static final RegistryRef<SpawnEggItem> MOB_SPAWNER_ITEM_ZOMBIE_CHICKEN = spawnEgg("mob_spawner_item_zombie_chicken", ModEntities.ZOMBIE_CHICKEN);
    public static final RegistryRef<SpawnEggItem> MOB_SPAWNER_ITEM_PRESENT = spawnEgg("mob_spawner_item_present", ModEntities.PRESENT);
    public static final RegistryRef<SpawnEggItem> MOB_SPAWNER_ITEM_STRANGER = spawnEgg("mob_spawner_item_stranger", ModEntities.STRANGER);
    public static final RegistryRef<SpawnEggItem> MOB_SPAWNER_ITEM_HAUNTED_COW = spawnEgg("mob_spawner_item_haunted_cow", ModEntities.HAUNTED_COW);
    public static final RegistryRef<SpawnEggItem> MOB_SPAWNER_ITEM_TOPIELEC = spawnEgg("mob_spawner_item_topielec", ModEntities.TOPIELEC);

    public static final RegistryRef<HarpoonItem> HARPOON_STONE = register(
            "harpoon_stone", properties -> new HarpoonItem(properties, 3.0F), new Item.Properties().durability(10));
    public static final RegistryRef<HarpoonItem> HARPOON_IRON = register(
            "harpoon_iron", properties -> new HarpoonItem(properties, 6.0F), new Item.Properties().durability(40));
    public static final RegistryRef<HarpoonItem> HARPOON_DIAMOND = register(
            "harpoon_diamond", properties -> new HarpoonItem(properties, 10.0F), new Item.Properties().durability(160));
    public static final RegistryRef<HarpoonItem> HARPOON_OBSIDIAN = register(
            "harpoon_obsidian", properties -> new HarpoonItem(properties, 6.0F), new Item.Properties().durability(80));

    private ModItems() {
    }

    public static void initialize() {
    }

    private static RegistryRef<BlockItem> blockItem(String name, RegistryRef<? extends net.minecraft.world.level.block.Block> block) {
        return register(name, properties -> new BlockItem(block.get(), properties.useBlockDescriptionPrefix()), new Item.Properties());
    }

    private static <T extends Mob> RegistryRef<SpawnEggItem> spawnEgg(String name, RegistryRef<net.minecraft.world.entity.EntityType<T>> type) {
        return register(name, properties -> new SpawnEggItem(properties.spawnEgg(type.get())), new Item.Properties());
    }

    private static <T extends Item> RegistryRef<T> register(
            String name,
            Function<Item.Properties, T> factory,
            Item.Properties properties) {
        ResourceKey<Item> key = ResourceKey.create(
                Registries.ITEM, ResourceLocation.fromNamespaceAndPath(DeadlyMonsters.MOD_ID, name));
        T item = factory.apply(properties.setId(key));
        Registry.register(BuiltInRegistries.ITEM, key, item);
        return new RegistryRef<>(item);
    }
}
