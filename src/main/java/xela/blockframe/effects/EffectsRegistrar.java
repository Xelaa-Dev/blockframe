package xela.blockframe.effects;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import xela.blockframe.BlockFrame;

import java.util.List;

public class EffectsRegistrar {
    public static final Holder<MobEffect> SLASH = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT,
            Identifier.fromNamespaceAndPath(BlockFrame.MOD_ID, "slash"), new SlashEffect());

    public static final Holder<MobEffect> COLD = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT,
            Identifier.fromNamespaceAndPath(BlockFrame.MOD_ID, "cold"), new ColdEffect());

    //This will be used purely to get a random effects from the effect pool
    public static final List<Holder<MobEffect>> EFFECTS = List.of(SLASH,COLD);
    public static void init(){

    }
}
