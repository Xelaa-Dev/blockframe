package xela.blockframe.events;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import xela.blockframe.BlockFrame;
import xela.blockframe.effects.EffectsRegistrar;
import xela.blockframe.effects.stacking.StackHandler;

import java.util.Random;
import java.util.function.Predicate;

public class AttackedEvent {
    public static DamageSource lastAttack = null;
    public static int lastAttackTick = 0;
    private static final Random RAND = new Random();
    public static void attackEventRegistrar() {
    ServerLivingEntityEvents.AFTER_DAMAGE.register(AttackedEvent::tickCheckTypeofDamage);
    }

    private static void tickCheckTypeofDamage(LivingEntity entity, DamageSource source, float baseDamageTaken,
                                              float damageTaken, boolean blocked) {
        if (entity.level().isClientSide()) return;

        lastAttack = source;

        if (entity instanceof Player player && source.getEntity() instanceof Entity attacker) {
            lastAttackTick = player.tickCount;
            EntityType<?> type = attacker.getType();

            if (type.equals(EntityType.SKELETON)) {
                StackHandler.applyOrIncrementStack(player, EffectsRegistrar.PUNCTURE, 10);
            }

            if (BlockFrame.CONFIG.should_roll_for_status_effects_on_player_damaged()) {
                if (RAND.nextFloat() <= BlockFrame.CONFIG.chanche_for_status_effects_to_apply()) {
                    Holder<MobEffect> randomEffect = EffectsRegistrar.EFFECTS.get(RAND.nextInt(EffectsRegistrar.EFFECTS.size()));

                    StackHandler.applyOrIncrementStack(player, randomEffect, 10);
                }
            }
        }
    }
}
