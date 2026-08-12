package xela.blockframe.effects.stacking;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import xela.blockframe.BlockFrame;
import xela.blockframe.data.DataAttachments;
import xela.blockframe.data.typeof.DamageStackAttachment;
import xela.blockframe.effects.EffectsRegistrar;

import java.util.ArrayList;
import java.util.List;

public class StackHandler {
    public static void evalStack(Player player, DamageSource source){

        var effectList = player.getActiveEffects().stream().toList();
        List<DamageStackAttachment> attachedData = new ArrayList<>();
        var typeofDamage = source.type();

        for (var effect : effectList){
            /*
            TODO:for each effect check if we have a stack, if we dont apply the corresponding one
             */
            if (player.getAttachedOrThrow(DamageStackAttachment)){

            }
        }

        /*
        if (player.getAttached(DataAttachments.DAMAGE_STACK) == null){
            player.setAttached(DataAttachments.DAMAGE_STACK, new DamageStackAttachment(1, player.tickCount,
                    EffectsRegistrar.COLD));
        }else{
            stacks = player.getAttached(DataAttachments.DAMAGE_STACK);
        }
        if (player.tickCount - stacks.lastTickApplied > 5){
            BlockFrame.LOGGER.info("Eval");
        }
        */

    }
}
