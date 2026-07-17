package xela.blockframe.events;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import xela.blockframe.BlockFrame;
import xela.blockframe.effects.EffectsRegistrar;

import java.util.Random;
import java.util.function.Predicate;

public class AttackedEvent {
    public static void attackEventRegistrar() {
        ServerLivingEntityEvents.AFTER_DAMAGE.register(AttackedEvent::TickCheckTypeofDamage);
    }

    @Deprecated(since = "Mobs will each have its own damage type accordingly")
    private static void TickApplyRandomEffect(LivingEntity entity, DamageSource source, float baseDamageTaken,
                                             float damageTaken, boolean blocked) {
        Random rand = new Random();
        if (entity instanceof Player player && source.getEntity() instanceof Entity) {
            if (Math.random() > (1 - BlockFrame.CONFIG.chanche_for_status_effects_to_apply()) && BlockFrame.CONFIG.should_roll_for_status_effects_on_player_damaged()){
                player.addEffect(new MobEffectInstance(
                        EffectsRegistrar.EFFECTS.get(rand.nextInt(EffectsRegistrar.EFFECTS.size())),
                        10*20,
                        1,
                        true,
                        true,
                        true));
            }
        }
    }

    /*
    Logically the first arrow that hits will not have a puncture effect. for now it will stay like this (see below)
    TODO: predict the hit from a arrow
     */
    private static void TickCheckTypeofDamage(Entity entity, DamageSource source, float baseDamageTaken,
                                             float damageTaken, boolean blocked) {
        if (entity instanceof Player player && source.getEntity() instanceof Entity attacker) {
            var type = attacker.getType();
            //TODO: adapt as a switch statement

            if (type.equals(EntityType.SKELETON) && !player.hasEffect(EffectsRegistrar.PUNCTURE)) {
                player.addEffect(new MobEffectInstance(
                        EffectsRegistrar.PUNCTURE,
                        5*20,
                        1,
                        true,
                        true,
                        true));
            }
            //This should be always last, if the player has the effect we dont need to apply it again,
            //but we try with t
            else{

            }
        }
    }
}
