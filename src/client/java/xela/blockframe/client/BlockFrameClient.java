package xela.blockframe.client;

import net.fabricmc.api.ClientModInitializer;
import xela.blockframe.BlockFrame;
import xela.blockframe.client.events.FallEvent;
import xela.blockframe.client.events.KeyHandler;
import xela.blockframe.config.BlockFrameConfigWrapper;

public class BlockFrameClient implements ClientModInitializer {
	public static final BlockFrameConfigWrapper CONFIG = BlockFrame.CONFIG;
	@Override
	public void onInitializeClient() {

		KeyHandler.init();
		FallEvent.init();
	}
}