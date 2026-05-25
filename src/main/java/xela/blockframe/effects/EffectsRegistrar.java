package xela.blockframe.effects;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import xela.blockframe.BlockFrame;

public class EffectsRegistrar {
    public static final Holder<MobEffect> SLASH = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT,
            Identifier.fromNamespaceAndPath(BlockFrame.MOD_ID, "slash"), new SlashEffect());

    public static void init(){

    }
}
