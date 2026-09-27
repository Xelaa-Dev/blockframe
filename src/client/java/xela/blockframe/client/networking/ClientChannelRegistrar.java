package xela.blockframe.client.networking;

import io.wispforest.owo.ui.component.LabelComponent;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.hud.Hud;
import net.minecraft.network.chat.Component;
import xela.blockframe.BlockFrame;
import xela.blockframe.client.BlockFrameClient;
import xela.blockframe.enums.UiComponentsEnum;
import xela.blockframe.networking.ChannelRegistrar;
import xela.blockframe.networking.payloads.records.HudUpdatePayload;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
            refreshHUD(message);
        });
    }

    private static void refreshHUD(HudUpdatePayload message) {
        List<LabelComponent> castedLabelsList = new ArrayList<>();

        var component = Hud.getComponent(UiComponentsEnum.MAIN.name);
        if (component == null){
            BlockFrame.LOGGER.warn("HUD component is null!");
        }else {
            //Cast to label
            var castedComponent = (FlowLayout) component;

            for (var child :  castedComponent.children()) {
                if (child instanceof LabelComponent) {
                    castedLabelsList.add((LabelComponent) child);
                }
            }

            var labelComponent = castedLabelsList.stream().filter( x ->
                    Objects.equals(x.id(), message.ID().toString())).toList().getFirst();
            String[] splittedArray = labelComponent.text().getString().split("\\s");

            switch (message.operation()) {
                case HUD_DECREASE_STACK:
                    labelComponent.text(Component.empty());
                    labelComponent.text(Component.empty().append(splittedArray[0] +
                            " " + (Integer.parseInt(splittedArray[1]) - 1)));

                    break;
                case HUD_INCREMENT_STACK:
                    labelComponent.text(Component.empty());
                    labelComponent.text(Component.empty().append(splittedArray[0] +
                            " " + (Integer.parseInt(splittedArray[1]) + 1)));
                    break;
            }

        }
    }
}
