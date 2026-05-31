package xela.blockframe.effects;

import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class ColdEffect extends MobEffect {

    protected ColdEffect() {
        super(MobEffectCategory.HARMFUL, 0x7690AC);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier){return true;}

    @Override
    public boolean applyEffectTick(ServerLevel serverLevel, LivingEntity entity, int amplification){
        super.addAttributeModifier(Attributes.MOVEMENT_SPEED, Identifier.withDefaultNamespace("effect.slowness"),
                (double) -0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);

        return super.applyEffectTick(serverLevel, entity, amplification);
    }
}
