package xela.blockframe.effects;

import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import xela.blockframe.data.DamageSources;

///The <Code>Puncture</Code> effect should bypass all armor and go to the main health pool
public class PunctureEffect extends MobEffect {
    protected PunctureEffect() {
        super(MobEffectCategory.HARMFUL, 0xf3f4f4);
    }

    //This is basically the same as the slash effect, but the hurt gets overridden by a tag
    public boolean applyEffectTick(ServerLevel serverLevel, LivingEntity entity, int amplification) {
        return super.applyEffectTick(serverLevel, entity, amplification);
    }
}
