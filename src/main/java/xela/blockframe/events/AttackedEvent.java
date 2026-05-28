package xela.blockframe.events;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import xela.blockframe.effects.EffectsRegistrar;

public class AttackedEvent {
    public static void attackEventRegistrar() {
        ServerLivingEntityEvents.AFTER_DAMAGE.register(AttackedEvent::registerAttackedEvent);
    }

    public static void registerAttackedEvent(LivingEntity entity, DamageSource source, float baseDamageTaken,
                                             float damageTaken, boolean blocked) {
        //TODO: damage by type of mob?
        if (entity instanceof Player player && source.getEntity() instanceof Entity) {
            //TODO: scale with server time / player level?
            if (Math.random() > 0.5){
                player.addEffect(new MobEffectInstance(EffectsRegistrar.SLASH, 10*20, 1, true,
                        true,true));
            }

            /*
            BlockFrame.LOGGER.info("Entity {} took damage from {}. Base damage: {}, Actual damage: {}, Blocked: {}",
                    entity.getName().getString(),
                    source.getEntity() != null ? source.getEntity().getName().getString() : "unknown",
                    baseDamageTaken,
                    damageTaken,
                    blocked);

             */
        }
    }
}
