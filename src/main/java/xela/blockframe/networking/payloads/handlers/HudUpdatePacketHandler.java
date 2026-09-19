package xela.blockframe.networking.payloads.handlers;

import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import xela.blockframe.enums.BlockframePacketType;
import xela.blockframe.networking.ChannelRegistrar;
import xela.blockframe.networking.payloads.records.HudUpdatePayload;

public class HudUpdatePacketHandler {
    public static void registerHudUpdatePacketHandler(Player player, BlockframePacketType operation, int amount) {
        ChannelRegistrar.NET_CHANNEL.serverHandle(player).send(new HudUpdatePayload(Identifier.fromNamespaceAndPath("blockframe", "ui")
        ,operation, amount));
    }
}
