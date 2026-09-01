package xela.blockframe.networking;

import io.wispforest.owo.network.OwoNetChannel;
import net.minecraft.resources.Identifier;
import xela.blockframe.networking.payloads.handlers.GenericStringMessagePacketsHandler;
import xela.blockframe.networking.payloads.handlers.MovementPacketsHandler;

public class ChannelRegistrar {
    public static final OwoNetChannel NET_CHANNEL = OwoNetChannel.create(Identifier.fromNamespaceAndPath("blockframe", "main_serverbound"));


    public static void init() {
        MovementPacketsHandler.registerMovementPackets();
        GenericStringMessagePacketsHandler.registerGenericStringMessagePackets();
    }
}
