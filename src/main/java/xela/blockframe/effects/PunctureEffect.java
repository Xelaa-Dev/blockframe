package xela.blockframe.effects;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagAppender;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import xela.blockframe.data.DamageSources;

///The <Code>Puncture</Code> effect should bypass all armor and go to the main health pool
public class PunctureEffect extends MobEffect {
    protected PunctureEffect(MobEffectCategory category, int color) {
        super(category, color);
    }
    private static int ticks_passed = 0;
    private static int ticks_max = 0;
    public boolean applyEffectTick(ServerLevel serverLevel, LivingEntity entity, int amplification) {

        if (amplification == 1){
            ticks_max = 60;
        }else {
            ticks_max = Math.toIntExact(
                    Math.round(
                            (60 / Math.log10(amplification)) / 3.845)
            );
        }

        if (entity instanceof LivingEntity && ticks_passed == 0){
            DamageSource source =  new DamageSource(serverLevel.registryAccess()
                    .lookupOrThrow(Registries.DAMAGE_TYPE)
                    .get(DamageSources.PUNCTURE_DAMAGE.identifier()).orElseThrow());
            
            entity.hurtServer(serverLevel, source, 0.5f);

            ticks_passed++;
        }
        if (ticks_passed > ticks_max){
            ticks_passed = 0;
        }else {
            ticks_passed++;
        }


        return super.applyEffectTick(serverLevel, entity, amplification);
    }
}
