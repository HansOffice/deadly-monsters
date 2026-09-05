package com.dmonsters.entity;

import com.dmonsters.registry.ModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.chicken.Chicken;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

/**
 * First fully ported Deadly Monsters mob.
 *
 * Core 1.12.2 behavior retained here:
 * - hostile melee AI targeting players and normal chickens
 * - converts chickens it successfully attacks into Zombie Chickens
 * - burns in direct daylight
 */
public final class ZombieChickenEntity extends Chicken {
    public ZombieChickenEntity(EntityType<? extends Chicken> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Chicken.class, true,
                target -> !(target instanceof ZombieChickenEntity)));
    }

    @Override
    public boolean doHurtTarget(ServerLevel level, Entity target) {
        if (!super.doHurtTarget(level, target)) {
            return false;
        }

        if (target instanceof Chicken chicken && !(target instanceof ZombieChickenEntity) && chicken.isAlive()) {
            double x = chicken.getX();
            double y = chicken.getY();
            double z = chicken.getZ();
            float yaw = chicken.getYRot();
            float pitch = chicken.getXRot();

            chicken.discard();

            ZombieChickenEntity converted = new ZombieChickenEntity(ModEntities.ZOMBIE_CHICKEN.get(), level);
            converted.snapTo(x, y, z, yaw, pitch);
            level.addFreshEntity(converted);
        }

        return true;
    }

    @Override
    public void aiStep() {
        if (!this.level().isClientSide()
                && this.level().isDay()
                && this.level().canSeeSky(this.blockPosition())
                && this.getLightLevelDependentMagicValue() > 0.5F
                && this.getRandom().nextFloat() < 0.05F) {
            this.igniteForSeconds(8.0F);
        }

        super.aiStep();
    }
}
