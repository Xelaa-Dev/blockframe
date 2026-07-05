package xela.blockframe.effects;

import net.minecraft.advancements.criterion.MobEffectsPredicate;
import net.minecraft.network.protocol.game.ClientboundUpdateMobEffectPacket;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.InsideBlockEffectType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.gamerules.GameRules;

///The <Code>Cold</Code> effect should just slow down the player, nothing else
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
        if (entity instanceof ServerPlayer player){
        }

        return super.applyEffectTick(serverLevel, entity, amplification);
    }
}
