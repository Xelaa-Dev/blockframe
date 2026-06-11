package xela.blockframe.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

///The <Code>Puncutre</Code> effect should bypass all armor and go to the main health pool
public class PunctureEffect extends MobEffect {
    protected PunctureEffect(MobEffectCategory category, int color) {
        super(category, color);
    }
}
