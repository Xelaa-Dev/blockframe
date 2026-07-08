package xela.blockframe.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import xela.blockframe.BlockFrame;
import xela.blockframe.effects.EffectsRegistrar;

@Mixin(Player.class)

public abstract class ApplyAmplifiedArmorDamageMixin {
    @WrapOperation(method = "hurtArmor", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;doHurtEquipment(Lnet/minecraft/world/damagesource/DamageSource;F[Lnet/minecraft/world/entity/EquipmentSlot;)V"))
    public void amplifyArmorHurt(Player instance, DamageSource damageSource, float v, EquipmentSlot[] equipmentSlots, Operation<Void> original) {
        if (instance.hasEffect(EffectsRegistrar.IMPACT)){
            v = v * 2;
            original.call(instance, damageSource, v, equipmentSlots);
        }
    }
}
