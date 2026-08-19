package xela.blockframe.networking.payloads.handlers;

import io.wispforest.owo.network.ServerAccess;
import xela.blockframe.BlockFrame;
import xela.blockframe.networking.ChannelRegistrar;
import xela.blockframe.networking.payloads.records.GenericStringMessagePacket;

public class GenericStringMessagePacketsHandler {
    public static void registerGenericStringMessagePackets() {
        ChannelRegistrar.NET_CHANNEL.registerServerbound(GenericStringMessagePacket.class, GenericStringMessagePacketsHandler::handler);
    }

    private static void handler(GenericStringMessagePacket message, ServerAccess access) {
        switch (message.payload()) {
            case "FALL_DAMAGE_DISABLE":
                BlockFrame.CALC_FALL_DAMAGE = false;
                break;

            case "FALL_DAMAGE_ENABLE":
                BlockFrame.CALC_FALL_DAMAGE = true;
                break;

        }
    }
}
