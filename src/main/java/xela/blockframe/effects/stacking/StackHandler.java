package xela.blockframe.effects.stacking;

import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import xela.blockframe.BlockFrame;
import xela.blockframe.data.DataAttachments;
import xela.blockframe.data.StatusData;
import xela.blockframe.data.typeof.EntityStatusAttachment;
import xela.blockframe.effects.EffectsRegistrar;

import java.util.ArrayList;
import java.util.List;

public class StackHandler {
    static int tickDecadeTime = 600;
    public static void applyOrIncrementStack(LivingEntity entity, Holder<MobEffect> effect, int maxStacks) {
        if (entity.level().isClientSide()) return;

        int currentTick = entity.tickCount;
        EntityStatusAttachment attachment = entity.getAttachedOrCreate(DataAttachments.STATUS_ATTACHMENT);

        attachment.addStack(effect, currentTick, maxStacks);

        StatusData data = attachment.getStatus(effect);
        if (data != null) {
            int amplifier = data.getStacks() - 1; // Stack 1 = Amplifier 0 (Effetto I)

            entity.addEffect(new MobEffectInstance(effect, tickDecadeTime, amplifier, false, true, true));
        }
    }
}
