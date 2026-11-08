package xela.blockframe.statusEngine;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.core.Holder;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import xela.blockframe.effects.EffectsRegistrar;
import xela.blockframe.enums.BlockframePacketType;
import xela.blockframe.enums.UiComponentsEnum;
import xela.blockframe.events.EffectFinishingEvent;
import xela.blockframe.networking.ChannelRegistrar;
import xela.blockframe.networking.payloads.handlers.HudUpdatePacketHandler;
import xela.blockframe.networking.payloads.records.EffectEndPayload;

import java.util.Iterator;
import java.util.Map;
//TODO: finish this code moving
public class StacksHandler {
    public void HandleRemoveFinalStack(ServerPlayer player,
                                  Iterator<Map.Entry<Holder<MobEffect>, StatusData>> iterator) {
        var effectHolder = iterator.next().getKey();
        //Effect is done, wipe it all
        iterator.remove();
        player.removeEffect(effectHolder);
        MobEffectInstance instance = new MobEffectInstance(effectHolder);

        if (instance.is(EffectsRegistrar.COLD)){
            ChannelRegistrar.NET_CHANNEL.serverHandle(player).send(new EffectEndPayload("COLD", "DISABLE_RENDER"));
            EffectFinishingEvent.effectRendererMap.put(player.getUUID(), false);
            HudUpdatePacketHandler.send(player, BlockframePacketType.HUD_DECREASE_STACK, 1,
                    UiComponentsEnum.COLD.name);
        }
        if (instance.is(EffectsRegistrar.SLASH)){
            HudUpdatePacketHandler.send(player, BlockframePacketType.HUD_DECREASE_STACK, 1,
                    UiComponentsEnum.SLASH.name);
        }
        if (instance.is(EffectsRegistrar.IMPACT)){
            HudUpdatePacketHandler.send(player, BlockframePacketType.HUD_DECREASE_STACK, 1,
                    UiComponentsEnum.IMPACT.name);
        }
    }

    public void HandleRemoveStack(ServerPlayer player, Iterator<Map.Entry<Holder<MobEffect>, StatusData>> iterator) {
        var statusData = iterator.next().getValue();
        var holder = iterator.next().getKey();

        statusData.setStacks(statusData.getStacks() - 1);
        statusData.setLastTickApplied(player.tickCount);

        // Since we need to do all of this magic dancing around effects bc minecraft doesn't inherently
        // have this effect mechanic, I need to manually tell the handler of updating the HUD to not do
        // anything, else when we decrease we also increase and the - 1 gets canceled
        EffectFinishingEvent.suppressNextHudIncrement(player);

        player.removeEffect(holder);

        player.connection.send(new ClientboundSoundPacket(SoundEvents.UI_BUTTON_CLICK, SoundSource.PLAYERS,
                player.position().x,
                player.position().y,
                player.position().z,
                2f,
                7f,
                0
        ));

        MobEffectInstance effect = new MobEffectInstance(
                holder,
                600,
                statusData.getStacks() - 1,
                false, true, true
        ) ;
        player.addEffect(effect);

        //Send a packet to update the clientside hud, we can't rely on the ServerMobEffectEvents.BEFORE_REMOVE
        //since minecraft doesn't track when an effect "decays"
        if (effect.is(EffectsRegistrar.COLD)){
            HudUpdatePacketHandler.send(player, BlockframePacketType.HUD_DECREASE_STACK, 1,
                    UiComponentsEnum.COLD.name);
        }
        if (effect.is(EffectsRegistrar.SLASH)){
            HudUpdatePacketHandler.send(player, BlockframePacketType.HUD_DECREASE_STACK, 1,
                    UiComponentsEnum.SLASH.name);
        }
    }
}

