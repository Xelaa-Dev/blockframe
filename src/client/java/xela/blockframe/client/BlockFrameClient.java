package xela.blockframe.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;
import xela.blockframe.BlockFrame;
import xela.blockframe.client.HUD.DrawColdEffect;
import xela.blockframe.client.HUD.DrawStatusEffectOnHUD;
import xela.blockframe.client.events.ClientEventRegistrar;
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
		BlockFrame.LOGGER.info("Hello Fabric world!");
		HudElementRegistry.attachElementBefore(VanillaHudElements.HOTBAR, Identifier.fromNamespaceAndPath(BlockFrame.MOD_ID,
				"before_chat"), DrawColdEffect::extract);

		ClientEventRegistrar.init();
		ClientChannelRegistrar.init();
		DrawStatusEffectOnHUD.init();
	}
}