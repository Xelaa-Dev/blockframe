package xela.blockframe.client;

import com.mojang.authlib.minecraft.client.MinecraftClient;
import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;
import xela.blockframe.BlockFrame;
import xela.blockframe.client.GUI.DrawColdEffect;
import xela.blockframe.client.GUI.DrawStatusEffectOnHUD;
import xela.blockframe.client.events.FallEvent;
import xela.blockframe.client.events.KeyHandler;
import xela.blockframe.client.networking.ClientChannelRegistrar;
import xela.blockframe.config.BlockFrameConfigWrapper;

public class BlockFrameClient implements ClientModInitializer {
	public static boolean HUD_READY = false;
	public static boolean HUD_RENDERED = false;
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
		var clientInstance = Minecraft.getInstance();
		BlockFrame.LOGGER.info("Hello Fabric world!");
		HudElementRegistry.attachElementBefore(VanillaHudElements.HOTBAR, Identifier.fromNamespaceAndPath(BlockFrame.MOD_ID,
				"before_chat"), DrawColdEffect::extract);

		KeyHandler.init();
		FallEvent.init();
		ClientChannelRegistrar.init();


		ClientTickEvents.END_CLIENT_TICK.register( client -> {
			if (client.isGameLoadFinished() && HUD_READY && !HUD_RENDERED){
				clientInstance.setScreenAndShow(new DrawStatusEffectOnHUD());
				HUD_RENDERED = true;
			}
		});
	}
}