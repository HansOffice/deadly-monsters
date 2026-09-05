package com.dmonsters.registry;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.block.BarbedWireBlock;
import com.dmonsters.block.MeshFenceBlock;
import com.dmonsters.block.MeshFencePoleBlock;
import com.dmonsters.block.StrengthenedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

/** Block registry retaining the original 1.12.2 registry IDs. */
public final class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(DeadlyMonsters.MOD_ID);

    public static final DeferredBlock<StrengthenedBlock> STRENGTHENED_STONE = BLOCKS.registerBlock(
            "strengthened_stone",
            properties -> new StrengthenedBlock(properties.strength(10.0F, 25.0F), Blocks.STONE.defaultBlockState()));

    public static final DeferredBlock<StrengthenedBlock> STRENGTHENED_COBBLESTONE = BLOCKS.registerBlock(
            "strengthened_cobblestone",
            properties -> new StrengthenedBlock(properties.strength(10.0F, 25.0F), Blocks.COBBLESTONE.defaultBlockState()));

    public static final DeferredBlock<BarbedWireBlock> BARBED_WIRE = BLOCKS.registerBlock(
            "barbed_wire",
            properties -> new BarbedWireBlock(properties.strength(1.0F, 1.0F).noOcclusion()));

    public static final DeferredBlock<MeshFenceBlock> MESH_FENCE = BLOCKS.registerBlock(
            "mesh_fence",
            properties -> new MeshFenceBlock(properties.strength(5.0F, 5.0F).noOcclusion()));

    public static final DeferredBlock<MeshFencePoleBlock> MESH_FENCE_POLE = BLOCKS.registerBlock(
            "mesh_fence_pole",
            properties -> new MeshFencePoleBlock(properties.strength(5.0F, 5.0F).noOcclusion()));

    public static final DeferredBlock<Block> DUMP = simple("dump");
    public static final DeferredBlock<Block> SOUL_EYE = simple("soul_eye");
    public static final DeferredBlock<Block> PRESENT_BLOCK = simple("present_block");
    public static final DeferredBlock<Block> CHRISTMAS_TREE = simple("christmas_tree");
    public static final DeferredBlock<Block> PRESENT_BOX = simple("present_box");

    private ModBlocks() {
    }

    private static DeferredBlock<Block> simple(String name) {
        return BLOCKS.registerSimpleBlock(name, properties -> properties);
    }
}
