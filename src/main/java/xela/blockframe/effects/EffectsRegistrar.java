package xela.blockframe.effects;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import xela.blockframe.BlockFrame;

import java.util.List;

//TODO:Implement effect stacking
public class EffectsRegistrar {
    public static final Holder<MobEffect> SLASH = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT,
            Identifier.fromNamespaceAndPath(BlockFrame.MOD_ID, "slash"), new SlashEffect());

    public static final Holder<MobEffect> COLD = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT,
            Identifier.fromNamespaceAndPath(BlockFrame.MOD_ID, "cold"), new ColdEffect());

    public static final Holder<MobEffect> PUNCTURE = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT,
            Identifier.fromNamespaceAndPath(BlockFrame.MOD_ID, "puncture"), new PunctureEffect());

    public static final Holder<MobEffect> IMPACT = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT,
            Identifier.fromNamespaceAndPath(BlockFrame.MOD_ID,"impact"), new ImpactEffect());


    //This will be used purely to get a random effect from the effect pool
    public static final List<Holder<MobEffect>> EFFECTS = List.of(SLASH,
                                                                COLD);
    public static void init(){}
}
