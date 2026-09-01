package xela.blockframe.effects;

import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import xela.blockframe.data.DamageSources;

/// The <Code>Slash</Code> effect is like a knife cut, inflicts a random hurt to the player,
/// doesnt scale damage but does scales the amount of time sit happens each second
public class SlashEffect extends MobEffect {

    private static int ticks_passed = 0;
    private static int ticks_max = 0;

    protected SlashEffect() {
        //Super calls the parent class MobEffects, the first argument is the type of Category, the second is the color as a int
        super(MobEffectCategory.HARMFUL, 0xF4320B);
    }

    //Apply effect regardless
    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyEffectTick(ServerLevel serverLevel, LivingEntity entity, int amplification) {

        //Since applyEffectTick would apply hurt every tick we manually delay
        if (amplification == 1) {
            ticks_max = 60;
        } else {
            //No idea wtf i wrote
            ticks_max = Math.toIntExact(
                    Math.round(
                            (120 / Math.log10(amplification)) / 3.845)
            );
        }

        if (entity instanceof LivingEntity && ticks_passed == 0) {
            DamageSource source = new DamageSource(serverLevel.registryAccess()
                    .lookupOrThrow(Registries.DAMAGE_TYPE)
                    .get(DamageSources.SLASH_DAMAGE.identifier()).orElseThrow());

            entity.hurtServer(serverLevel, source, 0.1f * amplification);
        }
        if (ticks_passed > ticks_max) {
            ticks_passed = 0;
        } else {
            ticks_passed++;
        }


        return super.applyEffectTick(serverLevel, entity, amplification);
    }
}