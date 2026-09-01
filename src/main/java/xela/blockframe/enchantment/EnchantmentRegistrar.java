package xela.blockframe.enchantment;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import xela.blockframe.BlockFrame;
import xela.blockframe.enchantment.effect.BleedEnchantmentEffect;
import xela.blockframe.enchantment.effect.ColdEnchantmentEffect;

public class EnchantmentRegistrar {
    public static MapCodec<ColdEnchantmentEffect> COLD_ENCHANTMENT = register("cold_enchantment", ColdEnchantmentEffect.CODEC);
    public static MapCodec<BleedEnchantmentEffect> BLEED_ENCHANTMENT = register("bleed_enchantment", BleedEnchantmentEffect.CODEC);

    private static <T extends EnchantmentEntityEffect> MapCodec<T> register(String id, MapCodec<T> codec) {
        return Registry.register(BuiltInRegistries.ENCHANTMENT_ENTITY_EFFECT_TYPE,
                Identifier.fromNamespaceAndPath(BlockFrame.MOD_ID, id),
                codec);
    }

    public static void init() {
    }
}
