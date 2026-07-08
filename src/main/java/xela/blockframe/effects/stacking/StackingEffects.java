package xela.blockframe.effects.stacking;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

///Since not all of the effects work in the same way this is used as a "cookiecutter" template, the logic will be
/// overriden for each effect
public interface StackingEffects {
    void AddDamageStack();

    void RemoveDamageStack();

    void ResetDamageStack();

    void EvalDamageStacks(ServerPlayer player);
}
