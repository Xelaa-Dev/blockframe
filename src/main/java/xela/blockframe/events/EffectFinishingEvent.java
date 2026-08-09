package xela.blockframe.events;

import net.fabricmc.fabric.api.entity.event.v1.effect.FabricMobEffect;
import net.fabricmc.fabric.api.entity.event.v1.effect.ServerMobEffectEvents;
import net.minecraft.world.entity.player.Player;
import xela.blockframe.effects.ColdEffect;
import xela.blockframe.effects.EffectsRegistrar;
import xela.blockframe.networking.ChannelRegistrar;
import xela.blockframe.networking.payloads.records.EffectEndPayload;

public class EffectFinishingEvent {
    public static void registerEffectFinishingEvent(){
        ServerMobEffectEvents.BEFORE_REMOVE.register((effectInstance, entity, ctx) ->{
            if (effectInstance.is(EffectsRegistrar.COLD)){
                var player = (Player) entity;
                //FROM the server to the client
                ChannelRegistrar.NET_CHANNEL.serverHandle(player).send(new EffectEndPayload("COLD", "DISABLE_RENDER"));
            }
        });
    }

    public static void registerEffectStartingEvent(){
        ServerMobEffectEvents.BEFORE_ADD.register((effectInstance, entity, ctx) ->{
            if (effectInstance.is(EffectsRegistrar.COLD)){
                var player = (Player) entity;
                //FROM the server to the client
                ChannelRegistrar.NET_CHANNEL.serverHandle(player).send(new EffectEndPayload("COLD", "ENABLE_RENDER"));
            }
        });
    }


}

