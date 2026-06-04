package xela.blockframe.enchantment.effect;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;
import xela.blockframe.BlockFrame;
import xela.blockframe.effects.EffectsRegistrar;

import java.util.Random;

public record ColdEnchantmentEffect(LevelBasedValue amount) implements EnchantmentEntityEffect {

    public static final MapCodec<ColdEnchantmentEffect> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    LevelBasedValue.CODEC.fieldOf("amount").forGetter(ColdEnchantmentEffect::amount)
            ).apply(instance, ColdEnchantmentEffect::new)
    );

    @Override
    public void apply(ServerLevel serverLevel, int enchantmentLevel, EnchantedItemInUse item, Entity entity, Vec3 position) {

        if (entity instanceof LivingEntity target)
        {
            if (item.owner() != null && item.owner() instanceof Player player){
                    target.addEffect(new MobEffectInstance(
                            EffectsRegistrar.COLD,
                            10*20,
                            (int) this.amount.calculate(enchantmentLevel),
                            true,
                            true,
                            true));
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
