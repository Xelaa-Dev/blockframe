package xela.blockframe.events;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;

public class AttackEvent {
    public static void registerAttackEvent(){
        AttackEntityCallback.EVENT.register((player, level, hand, entity, hitResult) -> {

            //ALWAYS DO THIS! this should tick only on the server
            if (!player.isSpectator() && level instanceof ServerLevel serverLevel){
                //TODO:For each tick, apply a kind of damage
            }

            return InteractionResult.PASS;
        });

        
    }
}
