package xela.blockframe.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import xela.blockframe.BlockFrame;
import xela.blockframe.effects.EffectsRegistrar;

@Mixin(LivingEntity.class)
public abstract class ApplyDirectDamageMixin {
    @Shadow
    public abstract @Nullable LivingEntity asLivingEntity();

    @WrapOperation(method = "getDamageAfterArmorAbsorb", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/damagesource/DamageSource;is(Lnet/minecraft/tags/TagKey;)Z"))
    private boolean getDamageAfterArmorAbsorb(DamageSource instance, TagKey<DamageType> tag, Operation<Boolean> original) {
        if(this.asLivingEntity() instanceof Player player && instance.getEntity() instanceof LivingEntity target){
            if (player.hasEffect(EffectsRegistrar.PUNCTURE)){
                //BlockFrame.LOGGER.info("Mixin bypasss " + instance.getEntity());
                return true;
            }else {
                return original.call(instance,tag);
            }
        }
        return false;
    }
}
