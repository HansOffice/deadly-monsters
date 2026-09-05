package com.dmonsters.item;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.clock.ClockTimeMarkers;
import net.minecraft.world.clock.WorldClock;
import net.minecraft.world.clock.WorldClocks;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;

/** Moves the Overworld clock to day when used at night. */
public final class SunlightDropItem extends Item {
    public SunlightDropItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (!(level instanceof ServerLevel playerLevel)) {
            return InteractionResult.SUCCESS;
        }

        ServerLevel overworld = playerLevel.getServer().overworld();
        long time = overworld.getOverworldClockTime() % 24000L;
        if (time < 13000L) {
            return InteractionResult.FAIL;
        }

        if (overworld.getGameRules().get(GameRules.ADVANCE_TIME)) {
            Registry<WorldClock> clocks = overworld.registryAccess().lookupOrThrow(Registries.WORLD_CLOCK);
            Holder<WorldClock> overworldClock = clocks.getOrThrow(WorldClocks.OVERWORLD);
            overworld.clockManager().moveToTimeMarker(overworldClock, ClockTimeMarkers.DAY);
        }

        ItemStack stack = player.getItemInHand(hand);
        stack.shrink(1);
        return InteractionResult.SUCCESS;
    }
}
