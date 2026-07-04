package xela.blockframe.events;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;

public class TickEvent {
    public static void init(){
        ServerTickEvents.END_SERVER_TICK.register(TickEvent::TickRemoveDamageStack);
    }

    //TODO: Check over players that are affected by a mods effect and scale back the intensity
    private static void TickRemoveDamageStack(MinecraftServer minecraftServer) {

    }
}
