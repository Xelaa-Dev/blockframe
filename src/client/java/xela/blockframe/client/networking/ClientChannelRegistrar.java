package xela.blockframe.client.networking;

import io.wispforest.owo.ui.component.LabelComponent;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.hud.Hud;
import net.minecraft.client.ComponentCollector;
import net.minecraft.network.chat.Component;
import xela.blockframe.BlockFrame;
import xela.blockframe.client.BlockFrameClient;
import xela.blockframe.networking.ChannelRegistrar;
import xela.blockframe.networking.payloads.records.HudUpdatePayload;

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

        ChannelRegistrar.NET_CHANNEL.registerClientbound(xela.blockframe.networking.payloads.records.HudUpdatePayload.class, (message, access) -> {
            refreshUi(message);
        });
    }

    private static void refreshUi(HudUpdatePayload message) {
        var component = Hud.getComponent(message.ID());
        if (component == null){
            BlockFrame.LOGGER.warn("component is null");
        }else {
            //Cast to label
            var castedComponent = (FlowLayout) component;

            for (var child :  castedComponent.children()) {

            }
            /*
            var original = Integer.parseInt(castedComponent.text().getString());
            switch (message.operation()) {
                case HUD_DECREASE_STACK:
                    castedComponent.text(Component.empty().append(String.valueOf(original - 1)));
                    break;
                case HUD_INCREMENT_STACK:
                    castedComponent.text(Component.empty().append(String.valueOf(original + 1)));
                    break;
            }
            */
        }
    }
}
