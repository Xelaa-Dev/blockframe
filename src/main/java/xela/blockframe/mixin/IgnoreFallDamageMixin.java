package xela.blockframe.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import xela.blockframe.BlockFrame;
import xela.blockframe.data.DataAttachments;

@Mixin(LivingEntity.class)
public abstract class IgnoreFallDamageMixin {
	@Shadow
	public abstract @Nullable LivingEntity asLivingEntity();

	@ModifyReturnValue(method = "calculateFallDamage", at = @At(value = "TAIL"))
	private int ignoreFallDamage(int original) {
		if (this.asLivingEntity() instanceof Player &&
				this.asLivingEntity().hasAttached(DataAttachments.FALLDAMAGE_ATTACHMENT) &&
				this.asLivingEntity().getAttached(DataAttachments.FALLDAMAGE_ATTACHMENT).getCalcFallDamage()){
			return 0;
		}else{
			return original;
		}
	}
}