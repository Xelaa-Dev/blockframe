package xela.blockframe.events;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import xela.blockframe.BlockFrame;
import xela.blockframe.effects.EffectsRegistrar;

import java.util.Random;

public class AttackedEvent {
    public static void attackEventRegistrar() {
        ServerLivingEntityEvents.AFTER_DAMAGE.register(AttackedEvent::TickApplyRandomEffect);
    }

    public static void TickApplyRandomEffect(LivingEntity entity, DamageSource source, float baseDamageTaken,
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
}
