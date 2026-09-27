package xela.blockframe.client.events;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import xela.blockframe.networking.ChannelRegistrar;
import xela.blockframe.networking.payloads.records.GenericStringMessagePacket;

public class TickEvent {
    public static void init() {
        ClientTickEvents.END_CLIENT_TICK.register(TickEvent::TickCheckForFallDamage);
    }

    private static void TickCheckForFallDamage(Minecraft client) {

            if (DoubleJumpRegistrar.fallingFromDoubleJump && client.player.onGround()) {
                DoubleJumpRegistrar.fallingFromDoubleJump = false;
                ChannelRegistrar.NET_CHANNEL.clientHandle().send(new GenericStringMessagePacket(
                        "FALL_DAMAGE_ENABLE", client.player.getStringUUID()));
            }

    }


}
