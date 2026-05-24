package xela.blockframe.network.payloads.movement;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import xela.blockframe.BlockFrame;
import xela.blockframe.network.payloads.records.ServerBoundMovementPayload;

import java.util.UUID;

public class DoubleJump {
    static public void registerDoubleJump(){
        ServerPlayNetworking.registerGlobalReceiver(ServerBoundMovementPayload.TYPE, ((payload, context) -> {

            BlockFrame.LOGGER.debug("[SERVER] received packet");
            Entity entity = context.player().level().getEntity(UUID.fromString(payload.vecPayload().UUID));

            if (entity instanceof ServerPlayer && !entity.level().isClientSide()){
                BlockFrame.LOGGER.debug("[SERVER] pushing with {} Typeof:{}", payload.vecPayload().pushVector.toString(), payload.vecPayload().typeof);
                entity.push(payload.vecPayload().pushVector);

                //Get the player entity as a generic Entity class to get connection (?)
                ((ServerPlayer)entity).connection.send(new ClientboundSetEntityMotionPacket(entity));
                BlockFrame.LOGGER.debug("[SERVER] delta movement {} ",  entity.getDeltaMovement());

                entity.playSound(SoundEvents.SAND_PLACE, 10.0F, 1.0F);
            }
        }));
    }
}
