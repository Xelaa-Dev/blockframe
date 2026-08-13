package xela.blockframe.effects;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

///The <Code>Impact</Code> effect makes the armor damage double / triple and so on, leverages mixins
/// (refer to to do in <File>EffectsRegistrar</File>)
public class ImpactEffect extends MobEffect {
    protected ImpactEffect() {
        super(MobEffectCategory.HARMFUL, 0xb3b3b3);
    }

    public boolean applyEffectTick(ServerLevel serverLevel, LivingEntity entity, int amplification) {
        return super.applyEffectTick(serverLevel, entity, amplification);
    }
}
