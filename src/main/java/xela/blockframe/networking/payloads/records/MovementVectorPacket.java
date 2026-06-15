package xela.blockframe.networking.payloads.records;

import net.minecraft.world.phys.Vec3;

public record MovementVectorPacket(Vec3 pushVector,
                                   String UUID,
                                   String typeof){}