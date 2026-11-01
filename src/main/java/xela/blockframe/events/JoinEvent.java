package xela.blockframe.events;


import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;


public class JoinEvent {
    public static void registerJoinEvent() {
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            //TODO: On rejoin check if player has effects so to keep the ui synchronized
        });
    }
}
