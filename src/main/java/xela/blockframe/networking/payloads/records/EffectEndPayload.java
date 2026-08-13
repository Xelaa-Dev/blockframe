package xela.blockframe.networking.payloads.records;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;

public record EffectEndPayload(String effect,
                               String action) {}
