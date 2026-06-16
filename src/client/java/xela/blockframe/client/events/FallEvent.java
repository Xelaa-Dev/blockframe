package xela.blockframe.client.events;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import xela.blockframe.client.events.doublejumpkey.DoubleJumpRegistrar;
import xela.blockframe.networking.ChannelRegistrar;
import xela.blockframe.networking.payloads.records.GenericStringMessagePacket;

public class FallEvent {
    public static void init(){
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (DoubleJumpRegistrar.fallingFromDoubleJump && client.player.onGround()){
                DoubleJumpRegistrar.fallingFromDoubleJump = false;
                ChannelRegistrar.SERVERBOUND_CHANNEL.clientHandle().send(new GenericStringMessagePacket(
                        "FALL_DAMAGE_ENABLE",client.player.getStringUUID() ));
            }
        });
    }
}
