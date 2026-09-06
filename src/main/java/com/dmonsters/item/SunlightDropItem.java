package com.dmonsters.item;

import com.dmonsters.registry.ModSounds;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.GameRules;

/** Moves the Overworld clock to day when used at night. */
public final class SunlightDropItem extends Item {
    public SunlightDropItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!(level instanceof ServerLevel playerLevel)) {
            return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
        }

        ServerLevel overworld = playerLevel.getServer().overworld();
        long time = overworld.getDayTime() % 24000L;
        if (time < 13000L) {
            return InteractionResultHolder.fail(stack);
        }

        if (overworld.getGameRules().getBoolean(GameRules.RULE_DAYLIGHT)) {
            long delta = (24000L - time) % 24000L;
            overworld.setDayTime(overworld.getDayTime() + delta);
        }

        playerLevel.playSound(null, player.getX(), player.getY(), player.getZ(),
                ModSounds.SUNLIGHTDROP_USE.get(), SoundSource.AMBIENT, 1.0F, 1.0F);
        playerLevel.sendParticles(ParticleTypes.ENCHANT,
                player.getX(), player.getY() + 1.5D, player.getZ(),
                32, 1.0D, 1.0D, 1.0D, 0.02D);

        stack.shrink(1);
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }
}
