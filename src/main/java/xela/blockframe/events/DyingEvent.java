package xela.blockframe.events;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import xela.blockframe.BlockFrame;
import xela.blockframe.networking.ChannelRegistrar;
import xela.blockframe.networking.payloads.records.EffectEndPayload;

public class DyingEvent {
    public static void init() {
        ServerPlayerEvents.AFTER_RESPAWN.register(DyingEvent::onEntityDeath);
    }


    private static void onEntityDeath(ServerPlayer before, ServerPlayer after, boolean b) {

            if (EffectFinishingEvent.effectRendererMap.containsKey(after.getUUID())) {
                BlockFrame.LOGGER.info("Forcing render disable for " + after.getName().getString());
                ChannelRegistrar.NET_CHANNEL.serverHandle(after).
                        send(new EffectEndPayload("COLD", "DISABLE_RENDER"));
                EffectFinishingEvent.effectRendererMap.remove(after.getUUID());
            }
    }


}
