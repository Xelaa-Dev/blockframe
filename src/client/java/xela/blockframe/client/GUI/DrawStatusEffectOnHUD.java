package xela.blockframe.client.GUI;

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
                                UIComponents.label(Component.empty().append("testing1")).
                                        id(String.valueOf(UI_COMPONENTS.COLD.name)))
                        .child(
                                UIComponents.label(Component.empty().append("testing2")).
                                        id(String.valueOf(UI_COMPONENTS.SLASH.name)))
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
enum UI_COMPONENTS {
    //These are supposed to identify the UI components for effects, the actual number is gonna be the using the
    // identifier + "_value" id style
    COLD(Identifier.fromNamespaceAndPath("blockframe", "ui_cold")),
    IMPACT(Identifier.fromNamespaceAndPath("blockframe", "ui_impact")),
    PUNCTURE(Identifier.fromNamespaceAndPath("blockframe", "ui_puncture")),
    SLASH(Identifier.fromNamespaceAndPath("blockframe", "ui_slash"));


    public final Identifier name;

    private UI_COMPONENTS(Identifier name) {
        this.name = name;
    }
}