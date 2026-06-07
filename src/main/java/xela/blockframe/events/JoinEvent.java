package xela.blockframe.events;


import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;


public class JoinEvent {
    public static void registerJoinEvent(){
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {

        });
    }
}
