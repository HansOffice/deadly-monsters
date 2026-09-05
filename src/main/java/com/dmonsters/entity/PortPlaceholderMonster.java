package com.dmonsters.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

/**
 * Temporary server-safe implementation used only to reserve the original
 * Deadly Monsters entity registry IDs while each monster is ported properly.
 *
 * These placeholders are deliberately not given natural spawns or gameplay
 * behavior. Each registry entry will be replaced with its real implementation
 * without changing the registry name.
 */
public final class PortPlaceholderMonster extends Monster {
    public PortPlaceholderMonster(EntityType<? extends PortPlaceholderMonster> type, Level level) {
        super(type, level);
    }
}
