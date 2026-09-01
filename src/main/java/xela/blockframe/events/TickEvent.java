package xela.blockframe.events;

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
import net.minecraft.world.level.block.NoteBlock;
import xela.blockframe.data.DataAttachments;
import xela.blockframe.data.StatusData;
import xela.blockframe.data.typeof.EntityStatusAttachment;

import java.util.Iterator;
import java.util.Map;

public class TickEvent {
    private static final int DECAY_INTERVAL = 100;

    public static void init() {
        ServerTickEvents.END_SERVER_TICK.register(TickEvent::TickRemoveDamageStack);
    }

    private static void TickRemoveDamageStack(MinecraftServer server) {
        for (ServerPlayer player : server.getPlayerList().getPlayers()) {

            //If the player has nothing attached skip
            if (!player.hasAttached(DataAttachments.STATUS_ATTACHMENT)) continue;

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
                //the effect decades
                if (!player.hasEffect(effectHolder)) {
                    iterator.remove();
                    continue;
                }

                if (currentTick - data.getLastTickApplied() >= DECAY_INTERVAL) {
                    //now we decay if we reach an arbitrary decay interval
                    int newStacks = data.getStacks() - 1;

                    if (newStacks <= 0) {
                        iterator.remove();
                        player.removeEffect(effectHolder);
                    } else {
                        data.setStacks(newStacks);
                        data.setLastTickApplied(currentTick);

                        //Remove the effect and add it back with the new stack since minecraft doesn't do that for us
                        player.removeEffect(effectHolder);

                        player.connection.send(new ClientboundSoundPacket(SoundEvents.UI_BUTTON_CLICK, SoundSource.PLAYERS,
                                player.position().x,
                                player.position().y,
                                player.position().z,
                                2f,
                                7f,
                                0
                                ));

                        player.addEffect(new MobEffectInstance(
                                effectHolder,
                                600,
                                newStacks - 1,
                                false, true, true
                        ));
                    }
                }
            }
        }
    }
}
