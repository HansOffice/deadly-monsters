package com.dmonsters.registry;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.block.BarbedWireBlock;
import com.dmonsters.block.ChristmasTreeBlock;
import com.dmonsters.block.DumpBlock;
import com.dmonsters.block.MeshFenceBlock;
import com.dmonsters.block.MeshFencePoleBlock;
import com.dmonsters.block.PresentBlock;
import com.dmonsters.block.PresentBoxBlock;
import com.dmonsters.block.SoulEyeBlock;
import com.dmonsters.block.StrengthenedBlock;
import java.util.function.Function;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

public final class ModBlocks {
    public static final RegistryRef<StrengthenedBlock> STRENGTHENED_STONE = register(
            "strengthened_stone",
            properties -> new StrengthenedBlock(properties.strength(10.0F, 25.0F), Blocks.STONE.defaultBlockState()),
            BlockBehaviour.Properties.of());

    public static final RegistryRef<StrengthenedBlock> STRENGTHENED_COBBLESTONE = register(
            "strengthened_cobblestone",
            properties -> new StrengthenedBlock(properties.strength(10.0F, 25.0F), Blocks.COBBLESTONE.defaultBlockState()),
            BlockBehaviour.Properties.of());

    public static final RegistryRef<BarbedWireBlock> BARBED_WIRE = register(
            "barbed_wire", BarbedWireBlock::new, BlockBehaviour.Properties.of().strength(1.0F, 1.0F).noOcclusion());

    public static final RegistryRef<MeshFenceBlock> MESH_FENCE = register(
            "mesh_fence", MeshFenceBlock::new, BlockBehaviour.Properties.of().strength(5.0F, 5.0F).noOcclusion());

    public static final RegistryRef<MeshFencePoleBlock> MESH_FENCE_POLE = register(
            "mesh_fence_pole", MeshFencePoleBlock::new, BlockBehaviour.Properties.of().strength(5.0F, 5.0F).noOcclusion());

    public static final RegistryRef<DumpBlock> DUMP = register(
            "dump", DumpBlock::new, BlockBehaviour.Properties.of().strength(1.0F, 1.0F).noOcclusion());

    public static final RegistryRef<SoulEyeBlock> SOUL_EYE = register(
            "soul_eye", SoulEyeBlock::new, BlockBehaviour.Properties.of().strength(3.0F, 3.0F).randomTicks().noOcclusion());

    public static final RegistryRef<PresentBlock> PRESENT_BLOCK = register(
            "present_block", PresentBlock::new, BlockBehaviour.Properties.of().strength(3.0F, 50.0F).randomTicks());

    public static final RegistryRef<ChristmasTreeBlock> CHRISTMAS_TREE = register(
            "christmas_tree", ChristmasTreeBlock::new, BlockBehaviour.Properties.of().strength(2.0F, 50.0F).randomTicks().noOcclusion());

    public static final RegistryRef<PresentBoxBlock> PRESENT_BOX = register(
            "present_box", PresentBoxBlock::new, BlockBehaviour.Properties.of().strength(1.0F, 50.0F).noOcclusion());

    private ModBlocks() {
    }

    public static void initialize() {
    }

    private static <T extends Block> RegistryRef<T> register(
            String name,
            Function<BlockBehaviour.Properties, T> factory,
            BlockBehaviour.Properties properties) {
        ResourceKey<Block> key = ResourceKey.create(
                Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(DeadlyMonsters.MOD_ID, name));
        T block = factory.apply(properties);
        Registry.register(BuiltInRegistries.BLOCK, key, block);
        return new RegistryRef<>(block);
    }
}
