package xela.blockframe.client.HUD;

import com.jcraft.jorbis.Block;
import io.wispforest.owo.braid.widgets.label.Label;
import io.wispforest.owo.ui.base.BaseOwoScreen;
import io.wispforest.owo.ui.component.UIComponents;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.container.UIContainers;
import io.wispforest.owo.ui.core.*;
import io.wispforest.owo.ui.hud.Hud;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;
import xela.blockframe.BlockFrame;
import xela.blockframe.enums.UiComponentsEnum;

import java.util.ArrayList;
import java.util.List;


public class DrawStatusEffectOnHUD {

    private static final List<Identifier> ids = new ArrayList<>(){
        {
            add(Identifier.fromNamespaceAndPath("blockframe", "ui"));
            add(Identifier.fromNamespaceAndPath("blockframe", "ui_test"));
        }
    };

    public static void init() {
        BlockFrame.LOGGER.warn("build");

        Hud.add(Identifier.fromNamespaceAndPath("blockframe", "ui"), () ->
                UIContainers.horizontalFlow(Sizing.content(), Sizing.content())
                        .child(
                                UIComponents.label(Component.empty().append("testing1   ")).
                                        id(String.valueOf(UiComponentsEnum.COLD.name)))
                        .child(
                                UIComponents.label(Component.empty().append("testing2   ")).
                                        id(String.valueOf(UiComponentsEnum.SLASH.name)))
                        .positioning(Positioning.relative(50, 70)));
    }

       /*
        Hud.add(Identifier.fromNamespaceAndPath("blockframe", "ui"), () ->
                UIContainers.verticalFlow(Sizing.content(), Sizing.content())
                        .child(UIComponents.label(
                                Component.empty()
                                        .append(Component.literal("! ")
                                                .append(" Press ")
                                                .append(" to\nbegin owo-ui Academy")
                                        )).horizontalTextAlignment(HorizontalAlignment.CENTER).shadow(true))
                        .surface(Surface.flat(0x77000000).and(Surface.outline(0xFF121212)))
                        .padding(Insets.of(5))
                        .positioning(Positioning.relative(100, 100))
        );
        */
}
