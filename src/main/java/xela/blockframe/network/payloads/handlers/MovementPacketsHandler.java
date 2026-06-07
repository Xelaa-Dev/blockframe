package xela.blockframe.network.payloads.handlers;

import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import xela.blockframe.effects.EffectsRegistrar;
import xela.blockframe.network.ChannelRegistrar;
import xela.blockframe.network.payloads.records.MovementVectorPacket;

import java.util.UUID;

public class MovementPacketsHandler {
    public static void registerMovementPackets(){
        ChannelRegistrar.SERVERBOUND_CHANNEL.registerServerbound(MovementVectorPacket.class, (message, access) ->{
            Vec3 newVec3;
            Entity entity = access.player().level().getEntity(UUID.fromString(message.UUID()));

            if (entity instanceof ServerPlayer && !entity.level().isClientSide()){

                if (((ServerPlayer) entity).hasEffect(EffectsRegistrar.COLD)){
                    var effect = ((ServerPlayer) entity).getEffect(EffectsRegistrar.COLD);
                    var dampening = 1- (effect.getAmplifier() * (Math.pow(10,-1)));


                    newVec3 = message.pushVector().multiply(dampening,dampening,dampening);

                    entity.push(newVec3);
                }else{
                    entity.push(message.pushVector());
                }


                //Get the player entity as a generic Entity class to get connection (?)
                ((ServerPlayer)entity).connection.send(new ClientboundSetEntityMotionPacket(entity));

                entity.playSound(SoundEvents.SAND_PLACE, 10.0F, 1.0F);
            }
        });
    }
}
