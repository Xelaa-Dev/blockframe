package xela.blockframe.networking.payloads.records;

import net.minecraft.resources.Identifier;
import xela.blockframe.enums.BlockframePacketType;

public record HudUpdatePayload(Identifier ID,
                               BlockframePacketType operation,
                               int amount){}
