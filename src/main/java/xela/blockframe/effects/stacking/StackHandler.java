package xela.blockframe.effects.stacking;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import xela.blockframe.data.DataAttachments;
import xela.blockframe.data.StatusData;
import xela.blockframe.data.typeof.EntityStatusAttachment;

public class StackHandler {
    static int tickDecadeTime = 600;

    //TODO:effect stacks but doesnt decrease correctly
    public static void applyOrIncrementStack(LivingEntity entity, Holder<MobEffect> effect, int maxStacks) {
        if (entity.level().isClientSide()) return;

        int currentTick = entity.tickCount;
        EntityStatusAttachment attachment = entity.getAttachedOrCreate(DataAttachments.STATUS_ATTACHMENT);

        //THIS IS WHERE WE ACTUALLY UPDATE, WE GET THE DATA ATTACHMENT HANDLE FROM ABOVE AND UPDATE DIRECTLY
        attachment.addStack(effect, currentTick, maxStacks);

        StatusData data = attachment.getStatus(effect);
        if (data != null) {
            int amplifier = data.getStacks() - 1;

            //You cant apply a lower effect if you have a higher one applied already
            entity.removeEffect(effect);
            entity.addEffect(new MobEffectInstance(effect, tickDecadeTime, amplifier, false, true, true));
        }
    }
}
