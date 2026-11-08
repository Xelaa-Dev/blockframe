package xela.blockframe.statusEngine;

//Most of this code was ai generated and verified by me, i had the idea but couldnt wrap my head around it

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.core.Holder;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import xela.blockframe.data.DataAttachments;
import xela.blockframe.effects.EffectsRegistrar;
import xela.blockframe.enums.BlockframePacketType;
import xela.blockframe.enums.UiComponentsEnum;
import xela.blockframe.events.EffectFinishingEvent;
import xela.blockframe.networking.ChannelRegistrar;
import xela.blockframe.networking.payloads.handlers.HudUpdatePacketHandler;
import xela.blockframe.networking.payloads.records.EffectEndPayload;

import java.util.Iterator;
import java.util.Map;

public class EngineTick {
    private static final int DECAY_INTERVAL = 100;

    public static void init() {
        ServerTickEvents.END_SERVER_TICK.register(EngineTick::TickRemoveDamageStack);
    }

    //TODO: Convert this so that a player is tracked on damage not to track all players
    // Damaged event triggers -> add player to a list -> run that map in the for below
    private static void TickRemoveDamageStack(MinecraftServer server) {
        StacksHandler stacksHandler = new StacksHandler();
        for (ServerPlayer player : server.getPlayerList().getPlayers()) {

            //If the player has nothing attached skip the player
            if (!player.hasAttached(DataAttachments.STATUS_ATTACHMENT)){
                continue;
            }

            //Get the attachment
            EntityStatusAttachment attachment = player.getAttached(DataAttachments.STATUS_ATTACHMENT);

            //Get the current tick to track when to decay
            int currentTick = player.tickCount;
            Iterator<Map.Entry<Holder<MobEffect>, StatusData>> iterator = attachment.getStatusMap().entrySet().iterator();

            //We get the iterator for the map and check if we have a next
            while (iterator.hasNext()) {
                //get the next element
                Map.Entry<Holder<MobEffect>, StatusData> entry = iterator.next();
                Holder<MobEffect> effectHolder = entry.getKey();
                StatusData data = entry.getValue();

                //if the player hasn't got that specific effect, we remove it from the list. This is a check for when
                //the effect decades naturally or we do it with the mod
                if (!player.hasEffect(effectHolder)) {
                    iterator.remove();
                    continue;
                }

                if (currentTick - data.getLastTickApplied() >= DECAY_INTERVAL) {
                    //now we decay if we reach an arbitrary decay interval
                    int newStacks = data.getStacks() - 1;

                    if (newStacks <= 0) {
                        stacksHandler.HandleRemoveFinalStack(player, iterator);
                    } else {
                        stacksHandler.HandleRemoveStack(player,iterator);
                    }
                }
            }
        }
    }

}
