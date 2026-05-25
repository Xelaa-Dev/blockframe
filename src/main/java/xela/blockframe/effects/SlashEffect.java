package xela.blockframe.effects;

import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import xela.blockframe.BlockFrame;
import xela.blockframe.data.DamageSources;


public class SlashEffect extends MobEffect {
    private static int ticks_passed = 0;
    protected SlashEffect() {
        //Super calls the parent class MobEffects, the first argument is the type of Category, the second is the color as a int
        super(MobEffectCategory.HARMFUL, 0xF4320B);
    }

    //Apply effect regardless
    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier){
        return true;
    }

    @Override
    public boolean applyEffectTick(ServerLevel serverLevel, LivingEntity entity, int amplification) {

        if (entity instanceof LivingEntity && ticks_passed == 0){
            DamageSource source =  new DamageSource(serverLevel.registryAccess()
                    .lookupOrThrow(Registries.DAMAGE_TYPE)
                    .get(DamageSources.SLASH_DAMAGE.identifier()).orElseThrow());
            double bleed_damage = Math.random();
            
            entity.hurtServer(serverLevel, source, (float) (bleed_damage * amplification));

            ticks_passed++;
        }
        if (ticks_passed > 60){
            ticks_passed = 0;
        }else {
            ticks_passed++;
        }


        return super.applyEffectTick(serverLevel, entity, amplification);
    }
}