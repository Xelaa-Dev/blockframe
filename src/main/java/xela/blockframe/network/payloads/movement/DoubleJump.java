package xela.blockframe.network.payloads.movement;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import xela.blockframe.BlockFrame;
import xela.blockframe.effects.ColdEffect;
import xela.blockframe.effects.EffectsRegistrar;
import xela.blockframe.network.payloads.records.ServerBoundMovementPayload;

import java.util.UUID;

public class DoubleJump {
    //TODO: doubles as double jump logic, rename for code smell?
    static public void registerDoubleJump(){
        ServerPlayNetworking.registerGlobalReceiver(ServerBoundMovementPayload.TYPE, ((payload, context) -> {
            Vec3 newVec3 = null;
            BlockFrame.LOGGER.debug("[SERVER] received packet");
            Entity entity = context.player().level().getEntity(UUID.fromString(payload.vecPayload().UUID));

            if (entity instanceof ServerPlayer && !entity.level().isClientSide()){

                if (((ServerPlayer) entity).hasEffect(EffectsRegistrar.COLD)){
                    var effect = ((ServerPlayer) entity).getEffect(EffectsRegistrar.COLD);
                    var dampening = 1- (effect.getAmplifier() * (Math.pow(10,-1)));

                    BlockFrame.LOGGER.info("Vec before {}", payload.vecPayload().pushVector);

                    newVec3 = payload.vecPayload().pushVector.multiply(dampening,dampening,dampening);

                    BlockFrame.LOGGER.info("Vec after {}", newVec3);
                    entity.push(newVec3);
                }else{
                    entity.push(payload.vecPayload().pushVector);
                }


                //Get the player entity as a generic Entity class to get connection (?)
                ((ServerPlayer)entity).connection.send(new ClientboundSetEntityMotionPacket(entity));

                entity.playSound(SoundEvents.SAND_PLACE, 10.0F, 1.0F);
            }
        }));
    }
}
