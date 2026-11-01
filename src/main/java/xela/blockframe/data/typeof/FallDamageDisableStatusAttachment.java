package xela.blockframe.data.typeof;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import xela.blockframe.data.StatusData;

public class FallDamageDisableStatusAttachment {

    private boolean calcFallDamage;

    public FallDamageDisableStatusAttachment(boolean calcFallDamage) {
        this.calcFallDamage = calcFallDamage;
    }

    public boolean getCalcFallDamage() { return calcFallDamage; }
    public void setCalcFallDamage(boolean calcFallDamage) { this.calcFallDamage = calcFallDamage; }

    public static final Codec<FallDamageDisableStatusAttachment> CODEC =
            RecordCodecBuilder.create(instance ->
                instance.group(
                       Codec.BOOL.fieldOf("stacks").forGetter(FallDamageDisableStatusAttachment::getCalcFallDamage)
                ).apply(instance, FallDamageDisableStatusAttachment::new)
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, FallDamageDisableStatusAttachment> STREAM_CODEC =
            StreamCodec.composite(
                ByteBufCodecs.BOOL, FallDamageDisableStatusAttachment::getCalcFallDamage,
                FallDamageDisableStatusAttachment::new
    );
}
