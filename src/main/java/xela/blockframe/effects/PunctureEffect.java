package xela.blockframe.effects;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

/// The <Code>Puncture</Code> effect should bypass all armor and go to the main health pool.
/// We basically need 0 code, we could have made it a simple data attachment to the player
/// but for the sake of knowing whats hurting us we need to set it as a effect
/// TODO: I have to do a custom ui anyways, move these effects to there?
public class PunctureEffect extends MobEffect {
    protected PunctureEffect() {
        super(MobEffectCategory.HARMFUL, 0xf3f4f4);
    }

    public boolean applyEffectTick(ServerLevel serverLevel, LivingEntity entity, int amplification) {
        return super.applyEffectTick(serverLevel, entity, amplification);
    }
}
