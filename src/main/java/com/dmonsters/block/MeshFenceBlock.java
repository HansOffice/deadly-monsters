package com.dmonsters.block;

import com.dmonsters.registry.ModBlocks;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;

/** Mesh fence segment that must remain linked to a pole within eight blocks. */
public final class MeshFenceBlock extends FenceBlock {
    private static final int MAX_POLE_DISTANCE = 8;

    public MeshFenceBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public boolean connectsTo(BlockState state, boolean faceSolid, Direction direction) {
        return state.is(ModBlocks.MESH_FENCE.get()) || state.is(ModBlocks.MESH_FENCE_POLE.get());
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        if (!this.hasPolePath(context.getLevel(), context.getClickedPos())) {
            Player player = context.getPlayer();
            if (player != null && !context.getLevel().isClientSide()) {
                player.displayClientMessage(
                        Component.translatable("msg.dmonsters.mesh_fence.too_far_from_pole").withStyle(ChatFormatting.DARK_RED),
                        true);
            }
            return null;
        }
        return super.getStateForPlacement(context);
    }

    @Override
    protected BlockState updateShape(
            BlockState state,
            LevelReader level,
            ScheduledTickAccess ticks,
            BlockPos pos,
            Direction directionToNeighbour,
            BlockPos neighbourPos,
            BlockState neighbourState,
            RandomSource random) {
        BlockState updated = super.updateShape(state, level, ticks, pos, directionToNeighbour, neighbourPos, neighbourState, random);
        if (directionToNeighbour.getAxis().isHorizontal() && !this.hasPolePath(level, pos)) {
            ticks.scheduleTick(pos, this, 1);
        }
        return updated;
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!this.hasPolePath(level, pos)) {
            level.destroyBlock(pos, true);
        }
    }

    private boolean hasPolePath(BlockGetter level, BlockPos pos) {
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            if (this.reachesPole(level, pos, direction)) {
                return true;
            }
        }
        return false;
    }

    private boolean reachesPole(BlockGetter level, BlockPos pos, Direction direction) {
        BlockPos cursor = pos;
        for (int distance = 1; distance <= MAX_POLE_DISTANCE; distance++) {
            cursor = cursor.relative(direction);
            BlockState state = level.getBlockState(cursor);
            if (state.is(ModBlocks.MESH_FENCE_POLE.get())) {
                return true;
            }
            if (!state.is(ModBlocks.MESH_FENCE.get())) {
                return false;
            }
        }
        return false;
    }
}
