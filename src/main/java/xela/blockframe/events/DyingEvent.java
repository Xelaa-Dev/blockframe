package xela.blockframe.events;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;

public class DyingEvent {
    public static void init() {
        ServerLivingEntityEvents.AFTER_DEATH.register(DyingEvent::onEntityDeath);
    }

    private static void onEntityDeath(LivingEntity livingEntity, DamageSource damageSource) {
        if (livingEntity instanceof ServerPlayer player) {
            if (player.level().dimension() == ServerLevel.OVERWORLD) {

            }
        }
    }


}
