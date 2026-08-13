package xela.blockframe.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.effect.MobEffect;

public class StatusData {
    private int stacks;
    private int lastTickApplied;

    public StatusData(int stacks, int lastTickApplied) {
        this.stacks = stacks;
        this.lastTickApplied = lastTickApplied;
    }

    public int getStacks() { return stacks; }
    public void setStacks(int stacks) { this.stacks = stacks; }

    public int getLastTickApplied() { return lastTickApplied; }
    public void setLastTickApplied(int lastTickApplied) { this.lastTickApplied = lastTickApplied; }

    public static final Codec<StatusData> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.INT.fieldOf("stacks").forGetter(StatusData::getStacks),
                    Codec.INT.fieldOf("lastTickApplied").forGetter(StatusData::getLastTickApplied)
            ).apply(instance, StatusData::new)
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, StatusData> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, StatusData::getStacks,
            ByteBufCodecs.VAR_INT, StatusData::getLastTickApplied,
            StatusData::new
    );
}