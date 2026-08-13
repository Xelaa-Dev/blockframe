package xela.blockframe.effects.stacking;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import xela.blockframe.BlockFrame;
import xela.blockframe.data.DataAttachments;
import xela.blockframe.effects.EffectsRegistrar;

import java.util.ArrayList;
import java.util.List;

public class StackHandler {
    public static void evalStack(Player player, DamageSource source){

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
