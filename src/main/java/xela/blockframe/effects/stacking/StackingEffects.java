package xela.blockframe.effects.stacking;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import xela.blockframe.data.DamageSources;

///Since not all of the effects work in the same way this is used as a "cookiecutter" template, the logic will be
/// overriden for each effect
public interface StackingEffects {
    void AddDamageStack(DamageSource source);

    void RemoveDamageStack();

    void ResetDamageStack();

    void EvalDamageStacks(ServerPlayer player);
}
