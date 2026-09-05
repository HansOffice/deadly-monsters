package com.dmonsters.registry;

import com.dmonsters.DeadlyMonsters;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * Block registry scaffold for the 1.12.2 -> 26.2 port.
 *
 * The original registry IDs are kept stable here. Complex behavior and shapes
 * are intentionally migrated in follow-up classes instead of being emulated
 * with legacy Forge APIs.
 */
public final class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(DeadlyMonsters.MOD_ID);

    public static final DeferredBlock<Block> STRENGTHENED_STONE = simple("strengthened_stone");
    public static final DeferredBlock<Block> STRENGTHENED_COBBLESTONE = simple("strengthened_cobblestone");
    public static final DeferredBlock<Block> BARBED_WIRE = simple("barbed_wire");
    public static final DeferredBlock<Block> MESH_FENCE = simple("mesh_fence");
    public static final DeferredBlock<Block> MESH_FENCE_POLE = simple("mesh_fence_pole");
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
