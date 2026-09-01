package xela.blockframe.client.networking;

import xela.blockframe.BlockFrame;
import xela.blockframe.client.BlockFrameClient;
import xela.blockframe.networking.ChannelRegistrar;

public class ClientChannelRegistrar {
    public static void init() {
        ChannelRegistrar.NET_CHANNEL.registerClientbound(xela.blockframe.networking.payloads.records.EffectEndPayload.class, (message, access) -> {
            BlockFrame.LOGGER.info("Client received: " + message.action());
            switch (message.action()) {
                case "DISABLE_RENDER":
                    BlockFrameClient.RENDER_COLD_HUD = false;
                    break;
                case "ENABLE_RENDER":
                    BlockFrameClient.RENDER_COLD_HUD = true;
                    break;
            }
        });
    }
}
