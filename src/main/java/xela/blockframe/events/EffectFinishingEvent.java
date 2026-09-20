package xela.blockframe.events;

import net.fabricmc.fabric.api.entity.event.v1.effect.ServerMobEffectEvents;
import net.minecraft.world.entity.player.Player;
import xela.blockframe.effects.EffectsRegistrar;
import xela.blockframe.enums.BlockframePacketType;
import xela.blockframe.enums.UiComponentsEnum;
import xela.blockframe.networking.ChannelRegistrar;
import xela.blockframe.networking.payloads.handlers.HudUpdatePacketHandler;
import xela.blockframe.networking.payloads.records.EffectEndPayload;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/*
TODO:Used the disk data setup to write to disk when the renderer is enabled, by logic if th eplayer dies
    and the data says his renderer is still on then we need to send a packet on death to disable it
*/

public class EffectFinishingEvent {
    public static Map<UUID, Boolean> effectRendererMap = new HashMap<>();

    public static void registerEffectFinishingEvent() {
        ServerMobEffectEvents.BEFORE_REMOVE.register((effectInstance, entity, ctx) -> {
            var player = (Player) entity;
            if (effectInstance.is(EffectsRegistrar.COLD)) {
                //FROM the server to the client
                ChannelRegistrar.NET_CHANNEL.serverHandle(player).send(new EffectEndPayload("COLD", "DISABLE_RENDER"));
                effectRendererMap.put(player.getUUID(), false);
                HudUpdatePacketHandler.registerHudUpdatePacketHandler(player, BlockframePacketType.HUD_DECREASE_STACK, -1, UiComponentsEnum.COLD.name);
            }

        });
    }

    public static void registerEffectStartingEvent() {
        ServerMobEffectEvents.BEFORE_ADD.register((effectInstance, entity, ctx) -> {
            var player = (Player) entity;
            if (effectInstance.is(EffectsRegistrar.COLD)) {
                //FROM the server to the client
                ChannelRegistrar.NET_CHANNEL.serverHandle(player).send(new EffectEndPayload("COLD", "ENABLE_RENDER"));
                effectRendererMap.put(player.getUUID(), true);
                HudUpdatePacketHandler.registerHudUpdatePacketHandler(player, BlockframePacketType.HUD_INCREMENT_STACK, 1, UiComponentsEnum.COLD.name);
            }
        });
    }
}

