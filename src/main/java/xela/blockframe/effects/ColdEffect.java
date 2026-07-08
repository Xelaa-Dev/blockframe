package xela.blockframe.effects;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import xela.blockframe.BlockFrame;
import xela.blockframe.data.DataAttachments;
import xela.blockframe.data.typeof.DamageStackAttachment;
import xela.blockframe.effects.stacking.StackingEffects;

///The <Code>Cold</Code> effect should just slow down the player, nothing else
public class ColdEffect extends MobEffect implements StackingEffects {

    private double scalableSlownessByStack = 0.15;
    private int ticks_passed = 0;
    protected ColdEffect() {

        super(MobEffectCategory.HARMFUL, 0x7690AC);

    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier){return true;}

    @Override
    public boolean applyEffectTick(ServerLevel serverLevel, LivingEntity entity, int amplification){
        super.addAttributeModifier(Attributes.MOVEMENT_SPEED, Identifier.withDefaultNamespace("effect.slowness"),
                -scalableSlownessByStack, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        if (entity instanceof ServerPlayer player){
            onTick(serverLevel, player, amplification);

            return super.applyEffectTick(serverLevel, player, amplification);
        }else{
            return super.applyEffectTick(serverLevel, entity, amplification);
        }
    }

    private void onTick(ServerLevel serverLevel, ServerPlayer player, int amplification) {
        if (ticks_passed > 20){
            EvalDamageStacks(player);
        }else {
            ticks_passed++;
        }
    }

    @Override
    public void AddDamageStack() {

    }

    @Override
    public void RemoveDamageStack() {

    }

    @Override
    public void ResetDamageStack() {

    }

    @Override
    public void EvalDamageStacks(ServerPlayer player) {
        DamageStackAttachment stacks = null;
        if (player.getAttached(DataAttachments.DAMAGE_STACK) == null){
            player.setAttached(DataAttachments.DAMAGE_STACK, new DamageStackAttachment(1, player.tickCount,
                    EffectsRegistrar.COLD));
        }else{
            stacks = player.getAttached(DataAttachments.DAMAGE_STACK);
        }
        if (player.tickCount - stacks.lastTickApplied > 5){
            BlockFrame.LOGGER.info("Eval");
        }
    }
}
