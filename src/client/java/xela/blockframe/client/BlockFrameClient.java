package xela.blockframe.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import xela.blockframe.BlockFrame;
import xela.blockframe.client.GUI.DrawColdEffect;
import xela.blockframe.client.events.FallEvent;
import xela.blockframe.client.events.KeyHandler;
import xela.blockframe.config.BlockFrameConfigWrapper;

public class BlockFrameClient implements ClientModInitializer {
	public static final BlockFrameConfigWrapper CONFIG = BlockFrame.CONFIG;
	public static final boolean RENDER_COLD_HUD = false;
	@Override
	public void onInitializeClient() {
		BlockFrame.LOGGER.info("Hello Fabric world!");
		HudElementRegistry.attachElementBefore(VanillaHudElements.CHAT, Identifier.fromNamespaceAndPath(BlockFrame.MOD_ID, "before_chat"), DrawColdEffect::extract);

		KeyHandler.init();
		FallEvent.init();
		
	}
}