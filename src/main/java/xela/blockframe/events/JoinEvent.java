package xela.blockframe.events;


import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.chat.Component;

public class JoinEvent {
    public static void registerJoinEvent(){
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            String ver = FabricLoader.getInstance().getModContainer("blockframe").get().getMetadata().getVersion().getFriendlyString();
           server.sendSystemMessage(Component.literal(ver));
        });
    }
}
