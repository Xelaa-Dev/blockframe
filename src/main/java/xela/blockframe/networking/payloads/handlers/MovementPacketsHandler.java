package xela.blockframe.networking.payloads.handlers;

import io.wispforest.owo.network.ServerAccess;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import xela.blockframe.BlockFrame;
import xela.blockframe.effects.EffectsRegistrar;
import xela.blockframe.enums.BlockframePacketType;
import xela.blockframe.networking.ChannelRegistrar;
import xela.blockframe.networking.payloads.records.MovementVectorPacket;

import java.util.Random;
import java.util.UUID;

public class MovementPacketsHandler {
    public static Random randomPitch = new Random();

    public static void registerMovementPackets() {
        ChannelRegistrar.NET_CHANNEL.registerServerbound(MovementVectorPacket.class, MovementPacketsHandler::handler);
    }

    private static void handler(MovementVectorPacket message, ServerAccess access) {
        Vec3 newVec3;
        Entity entity = access.player().level().getEntity(UUID.fromString(message.UUID()));

        if (entity instanceof ServerPlayer && !entity.level().isClientSide()) {

            if (((ServerPlayer) entity).hasEffect(EffectsRegistrar.COLD)) {
                var effect = ((ServerPlayer) entity).getEffect(EffectsRegistrar.COLD);
                var dampening = 1 - (effect.getAmplifier() * (Math.pow(10, -1)));

                if (dampening < 0) {
                    BlockFrame.LOGGER.warn("Dampening a negative integer!");
                }

                newVec3 = message.pushVector().multiply(dampening, dampening, dampening);

                entity.push(newVec3);


            } else {
                entity.push(message.pushVector());
            }


            //Get the player entity as a generic Entity class to get connection (?)
            ((ServerPlayer) entity).connection.send(new ClientboundSetEntityMotionPacket(entity));

            if (message.typeof() != null
                    && (message.typeof().equals(BlockframePacketType.DOUBLE_JUMP.name) || message.typeof().equals(BlockframePacketType.ROLL.name))
                    && BlockFrame.CONFIG.play_double_jump_sound()) {
                ((ServerPlayer) entity).connection.send(new ClientboundSoundPacket(SoundEvents.ARMOR_EQUIP_WOLF, SoundSource.PLAYERS,
                        entity.position().x,
                        entity.position().y,
                        entity.position().z,
                        2f,
                        /* a pitch of 0 would not be nice so we up the base to 0.5 since
                        nextFloat returns from 0.0 to 1.0 */
                        randomPitch.nextFloat() * 0.5f,
                        0
                ));
            } else if (message.typeof() == null) {
                throw new RuntimeException("Malformed packet type received! Expected a typeof movement payload");
            }
        }
    }
}
