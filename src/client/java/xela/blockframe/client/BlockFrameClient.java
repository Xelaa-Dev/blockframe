package xela.blockframe.client;

import com.mojang.blaze3d.platform.InputConstants;
import io.wispforest.owo.renderdoc.RenderdocScreen;
import io.wispforest.owo.ui.component.UIComponents;
import io.wispforest.owo.ui.container.UIContainers;
import io.wispforest.owo.ui.core.*;
import io.wispforest.owo.ui.hud.Hud;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;
import xela.blockframe.BlockFrame;
import xela.blockframe.client.GUI.DrawColdEffect;
import xela.blockframe.client.GUI.DrawStatusEffectOnGUI;
import xela.blockframe.client.events.FallEvent;
import xela.blockframe.client.events.KeyHandler;
import xela.blockframe.client.networking.ClientChannelRegistrar;
import xela.blockframe.config.BlockFrameConfigWrapper;

import javax.swing.text.JTextComponent;

public class BlockFrameClient implements ClientModInitializer {
	public static final BlockFrameConfigWrapper CONFIG = BlockFrame.CONFIG;
	public static boolean RENDER_COLD_HUD = false;
	public static final KeyMapping.Category CATEGORY = KeyMapping.Category.register(
			Identifier.fromNamespaceAndPath(BlockFrame.MOD_ID, "ui_debug")
	);
	public static KeyMapping ui_debug = KeyMappingHelper.registerKeyMapping(new KeyMapping(
			"UI_DEBUG",
			InputConstants.Type.KEYSYM,
			GLFW.GLFW_KEY_H,
			CATEGORY
	));
	@Override
	public void onInitializeClient() {
		BlockFrame.LOGGER.info("Hello Fabric world!");
		HudElementRegistry.attachElementBefore(VanillaHudElements.CHAT, Identifier.fromNamespaceAndPath(BlockFrame.MOD_ID,
				"before_chat"), DrawColdEffect::extract);

		Hud.add(Identifier.fromNamespaceAndPath("owo-ui-academy", "hint"), () ->
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

		KeyHandler.init();
		FallEvent.init();
		ClientChannelRegistrar.init();

	}
}