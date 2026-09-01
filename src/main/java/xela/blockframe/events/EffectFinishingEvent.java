package xela.blockframe.events;

import net.fabricmc.fabric.api.entity.event.v1.effect.FabricMobEffect;
import net.fabricmc.fabric.api.entity.event.v1.effect.ServerMobEffectEvents;
import net.minecraft.world.entity.player.Player;
import xela.blockframe.effects.ColdEffect;
import xela.blockframe.effects.EffectsRegistrar;
import xela.blockframe.networking.ChannelRegistrar;
import xela.blockframe.networking.payloads.records.EffectEndPayload;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EffectFinishingEvent {
    public static Map<UUID, Boolean> effectRendererMap = new HashMap<>();

    public static void registerEffectFinishingEvent(){
        ServerMobEffectEvents.BEFORE_REMOVE.register((effectInstance, entity, ctx) ->{
            if (effectInstance.is(EffectsRegistrar.COLD)){
                var player = (Player) entity;
                //FROM the server to the client
                ChannelRegistrar.NET_CHANNEL.serverHandle(player).send(new EffectEndPayload("COLD", "DISABLE_RENDER"));
                effectRendererMap.put(player.getUUID(), false);
            }
        });
    }

    public static void registerEffectStartingEvent(){
        ServerMobEffectEvents.BEFORE_ADD.register((effectInstance, entity, ctx) ->{
            if (effectInstance.is(EffectsRegistrar.COLD)){
                var player = (Player) entity;
                //FROM the server to the client
                ChannelRegistrar.NET_CHANNEL.serverHandle(player).send(new EffectEndPayload("COLD", "ENABLE_RENDER"));
                effectRendererMap.put(player.getUUID(), true);

            }
        });
    }


}

