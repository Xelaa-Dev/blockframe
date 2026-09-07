package xela.blockframe.events;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityDataRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypeIds;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import xela.blockframe.BlockFrame;
import xela.blockframe.effects.EffectsRegistrar;
import xela.blockframe.effects.stacking.StackHandler;

import java.util.Random;

//This event was made with the help of an ai but still i looked closely for problems
//(its a concept that i originlly had in mind but couldn't make tyit into realis it myself
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
            var type = EntityType.getKey(attacker.getType()).toString();

            switch (type){
                case "minecraft:zombie" -> {
                    StackHandler.applyOrIncrementStack(player, EffectsRegistrar.IMPACT, 10);
                }
                case "minecraft:skeleton" -> {
                    StackHandler.applyOrIncrementStack(player, EffectsRegistrar.PUNCTURE, 10);
                }
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
