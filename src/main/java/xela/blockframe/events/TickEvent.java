package xela.blockframe.events;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.core.Holder;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import xela.blockframe.data.DataAttachments;
import xela.blockframe.data.StatusData;
import xela.blockframe.data.typeof.EntityStatusAttachment;

import java.util.Iterator;
import java.util.Map;

public class TickEvent {
    private static final int DECAY_INTERVAL = 100;
    public static void init(){
        ServerTickEvents.END_SERVER_TICK.register(TickEvent::TickRemoveDamageStack);
    }

    private static void TickRemoveDamageStack(MinecraftServer server) {
        // Cicliamo su tutti i player connessi al server
        for (ServerPlayer player : server.getPlayerList().getPlayers()) {

            // Se il player non ha l'attachment degli stack, saltiamo subito
            if (!player.hasAttached(DataAttachments.STATUS_ATTACHMENT)) continue;

            EntityStatusAttachment attachment = player.getAttached(DataAttachments.STATUS_ATTACHMENT);
            if (attachment == null || attachment.getStatusMap().isEmpty()) continue;

            int currentTick = player.tickCount;
            Iterator<Map.Entry<Holder<MobEffect>, StatusData>> iterator = attachment.getStatusMap().entrySet().iterator();

            while (iterator.hasNext()) {
                Map.Entry<Holder<MobEffect>, StatusData> entry = iterator.next();
                Holder<MobEffect> effectHolder = entry.getKey();
                StatusData data = entry.getValue();

                // Se l'effetto non è più attivo sul player (es. bevuto il latte), puliamo la memoria
                if (!player.hasEffect(effectHolder)) {
                    iterator.remove();
                    continue;
                }

                // Verifichiamo se sono trascorsi DECAY_INTERVAL tick dall'ultimo stack
                if (currentTick - data.getLastTickApplied() >= DECAY_INTERVAL) {
                    int newStacks = data.getStacks() - 1;

                    if (newStacks <= 0) {
                        // Finiti gli stack: rimuoviamo sia l'attachment che l'effetto nativo di Minecraft
                        iterator.remove();
                        player.removeEffect(effectHolder);
                    } else {
                        // 1. Aggiorniamo il numero di stack e resettiamo il timer
                        data.setStacks(newStacks);
                        data.setLastTickApplied(currentTick);

                        // 2. Scaliamo l'intensità dell'effetto (Amplifier = newStacks - 1)
                        player.addEffect(new MobEffectInstance(
                                effectHolder,
                                600,            // Durata fittizia
                                newStacks - 1,  // Nuovo Amplifier (livello dell'effetto)
                                false, true, true
                        ));
                    }
                }
            }
        }
    }
}
