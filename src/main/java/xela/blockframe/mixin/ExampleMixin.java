package xela.blockframe.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.LivingEntity;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import xela.blockframe.BlockFrame;

@Mixin(LivingEntity.class)
public abstract class ExampleMixin {
	@ModifyReturnValue(method = "calculateFallDamage", at = @At(value = "TAIL"))
	private int ignoreFallDamage(int original) {
		if (!BlockFrame.CALC_FALL_DAMAGE){
			return 0;
		}else if (BlockFrame.CALC_FALL_DAMAGE){
			return original;
		}
		BlockFrame.LOGGER.error("Mixin fail @ ignoreFallDamage!");
		return original;
	}
}

