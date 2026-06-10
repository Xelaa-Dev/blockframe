package xela.blockframe.network.payloads.handlers;

import net.minecraft.network.protocol.PacketType;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import xela.blockframe.BlockFrame;
import xela.blockframe.effects.EffectsRegistrar;
import xela.blockframe.enums.BlockframePacketType;
import xela.blockframe.network.ChannelRegistrar;
import xela.blockframe.network.payloads.records.MovementVectorPacket;

import java.util.UUID;

public class MovementPacketsHandler {
    public static void registerMovementPackets(){
        ChannelRegistrar.SERVERBOUND_CHANNEL.registerServerbound(MovementVectorPacket.class, (message, access) ->{
            Vec3 newVec3;
            Entity entity = access.player().level().getEntity(UUID.fromString(message.UUID()));
            ServerLevel level = access.runtime().getLevel(entity.level().dimension());

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

                if (message.typeof() != null && message.typeof().equals(BlockframePacketType.DOUBLE_JUMP.name) &&
                        BlockFrame.CONFIG.play_double_jump_sound()){
                    ((ServerPlayer) entity).connection.send(new ClientboundSoundPacket(SoundEvents.ARMOR_EQUIP_WOLF, SoundSource.PLAYERS,
                            entity.position().x,
                            entity.position().y,
                            entity.position().z,
                            2f,
                            1f,
                            0
                    ));
                }
                else {
                    throw new RuntimeException("Unknown or malformed packet type");
                }
            }
            //Because of how sound is synced, it needs to be OUTSIDE ifs that include checking if we are on the server (nvm lol)
            entity.playSound(SoundEvents.ARMOR_EQUIP_WOLF.value(), 2f,0.7f);

        });
    }
}
