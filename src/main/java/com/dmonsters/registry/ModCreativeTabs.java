package com.dmonsters.registry;

import com.dmonsters.DeadlyMonsters;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;

public final class ModCreativeTabs {
    private static final ResourceKey<CreativeModeTab> MAIN_KEY = ResourceKey.create(
            Registries.CREATIVE_MODE_TAB,
            ResourceLocation.fromNamespaceAndPath(DeadlyMonsters.MOD_ID, "main"));

    public static final RegistryRef<CreativeModeTab> MAIN = new RegistryRef<>(Registry.register(
            BuiltInRegistries.CREATIVE_MODE_TAB,
            MAIN_KEY,
            FabricItemGroup.builder()
                    .title(Component.translatable("itemGroup.dmonsters"))
                    .icon(() -> ModItems.REBAR.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.REBAR.get());
                        output.accept(ModItems.LUCKY_EGG.get());
                        output.accept(ModItems.UNBORN_BABY_EYE.get());
                        output.accept(ModItems.BLOODY_MAIDEN_HEART.get());
                        output.accept(ModItems.FALLEN_LEADER_SPINE.get());
                        output.accept(ModItems.ENTRAIL_FLESH.get());
                        output.accept(ModItems.POOPOO_PILL.get());
                        output.accept(ModItems.DAGON.get());
                        output.accept(ModItems.SUNLIGHT_DROP.get());

                        output.accept(ModItems.STRENGTHENED_STONE.get());
                        output.accept(ModItems.STRENGTHENED_COBBLESTONE.get());
                        output.accept(ModItems.BARBED_WIRE.get());
                        output.accept(ModItems.MESH_FENCE.get());
                        output.accept(ModItems.MESH_FENCE_POLE.get());
                        output.accept(ModItems.DUMP.get());
                        output.accept(ModItems.SOUL_EYE.get());
                        output.accept(ModItems.CHRISTMAS_TREE.get());
                        output.accept(ModItems.PRESENT_BOX.get());

                        output.accept(ModItems.MOB_SPAWNER_ITEM_UNBORN_BABY.get());
                        output.accept(ModItems.MOB_SPAWNER_ITEM_CLIMBER.get());
                        output.accept(ModItems.MOB_SPAWNER_ITEM_ENTRAIL.get());
                        output.accept(ModItems.MOB_SPAWNER_ITEM_FREEZER.get());
                        output.accept(ModItems.MOB_SPAWNER_ITEM_MUTANT_STEVE.get());
                        output.accept(ModItems.MOB_SPAWNER_ITEM_FALLEN_LEADER.get());
                        output.accept(ModItems.MOB_SPAWNER_ITEM_BLOODY_MAIDEN.get());
                        output.accept(ModItems.MOB_SPAWNER_ITEM_ZOMBIE_CHICKEN.get());
                        output.accept(ModItems.MOB_SPAWNER_ITEM_PRESENT.get());
                        output.accept(ModItems.MOB_SPAWNER_ITEM_STRANGER.get());
                        output.accept(ModItems.MOB_SPAWNER_ITEM_HAUNTED_COW.get());
                        output.accept(ModItems.MOB_SPAWNER_ITEM_TOPIELEC.get());

                        output.accept(ModItems.HARPOON_STONE.get());
                        output.accept(ModItems.HARPOON_IRON.get());
                        output.accept(ModItems.HARPOON_DIAMOND.get());
                        output.accept(ModItems.HARPOON_OBSIDIAN.get());
                    })
                    .build()));

    private ModCreativeTabs() {
    }

    public static void initialize() {
    }
}
