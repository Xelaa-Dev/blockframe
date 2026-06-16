package xela.blockframe.networking.payloads.handlers;

import com.jcraft.jorbis.Block;
import io.wispforest.owo.network.ServerAccess;
import xela.blockframe.BlockFrame;
import xela.blockframe.networking.ChannelRegistrar;
import xela.blockframe.networking.payloads.records.GenericStringMessagePacket;

public class GenericStringMessagePacketsHandler {
    public static void registerGenericStringMessagePackets(){
        ChannelRegistrar.SERVERBOUND_CHANNEL.registerServerbound(GenericStringMessagePacket.class, GenericStringMessagePacketsHandler::handler);
    }

    private static void handler(GenericStringMessagePacket message, ServerAccess access) {
        switch (message.payload()){
            case "FALL_DAMAGE_DISABLE":
                BlockFrame.LOGGER.info("Fall damage disabled");
                BlockFrame.CALC_FALL_DAMAGE = false;
                break;

            case "FALL_DAMAGE_ENABLE":
                BlockFrame.LOGGER.info("Fall damage enabled");
                BlockFrame.CALC_FALL_DAMAGE = true;
                break;

        }
    }
}
