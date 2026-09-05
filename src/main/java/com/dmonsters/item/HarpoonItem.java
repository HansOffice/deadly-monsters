package com.dmonsters.item;

import java.util.List;

import com.dmonsters.entity.TopielecEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;

/** Reusable fishing/Topielec weapon matching the four original harpoon tiers. */
public final class HarpoonItem extends Item {
    private static final List<Item> FISH = List.of(Items.COD, Items.SALMON, Items.TROPICAL_FISH, Items.PUFFERFISH);
    private final float topielecDamage;

    public HarpoonItem(Properties properties, float topielecDamage) {
        super(properties);
        this.topielecDamage = topielecDamage;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        if (player == null) {
            return InteractionResult.FAIL;
        }
        if (!(context.getLevel() instanceof ServerLevel level)) {
            return InteractionResult.SUCCESS;
        }

        if (player.isInWater()) {
            context.getItemInHand().hurtAndBreak(1, player, context.getHand().asEquipmentSlot());
            if (level.getRandom().nextFloat() < 0.25F) {
                player.spawnAtLocation(level, FISH.get(level.getRandom().nextInt(FISH.size())));
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker.level() instanceof ServerLevel level) {
            float damage = target instanceof TopielecEntity ? this.topielecDamage : 1.0F;
            target.hurtServer(level, target.damageSources().generic(), damage);
        }
        stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
    }
}
