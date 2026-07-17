package xela.blockframe.effects.stacking;

import net.minecraft.server.level.ServerPlayer;
import xela.blockframe.BlockFrame;
import xela.blockframe.data.DataAttachments;
import xela.blockframe.data.typeof.DamageStackAttachment;
import xela.blockframe.effects.EffectsRegistrar;

public class StackHandler {
    public static void evalStack(ServerPlayer player){

        DamageStackAttachment stacks = null;
        if (player.getAttached(DataAttachments.DAMAGE_STACK) == null){
            player.setAttached(DataAttachments.DAMAGE_STACK, new DamageStackAttachment(1, player.tickCount,
                    EffectsRegistrar.COLD));
        }else{
            stacks = player.getAttached(DataAttachments.DAMAGE_STACK);
        }
        if (player.tickCount - stacks.lastTickApplied > 5){
            BlockFrame.LOGGER.info("Eval");
        }
    }
}
