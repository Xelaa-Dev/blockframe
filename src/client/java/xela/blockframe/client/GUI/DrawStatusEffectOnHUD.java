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


public class DrawStatusEffectOnHUD extends BaseOwoScreen<FlowLayout> {

    @Override
    protected @NotNull OwoUIAdapter<FlowLayout> createAdapter() {
        BlockFrame.LOGGER.warn("adapter");
        return OwoUIAdapter.create(this, UIContainers::verticalFlow);
    }

    @Override
    protected void build(FlowLayout rootComponent) {
        BlockFrame.LOGGER.warn("build");

        Hud.add(Identifier.fromNamespaceAndPath("blockframe", "ui"), () ->
                UIComponents.label(Component.empty().append("Blockframe")).positioning(Positioning.relative(100,100)));
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
        var component = Hud.getComponent(Identifier.fromNamespaceAndPath("blockframe", "ui"));
        if (component == null){
            BlockFrame.LOGGER.warn("component is null");
        }
    }

    //I HATE THIS
    @Override
    public boolean isPauseScreen(){
        return false;
    }
}
