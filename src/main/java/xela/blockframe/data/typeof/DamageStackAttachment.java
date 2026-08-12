package xela.blockframe.data.typeof;


import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.effect.MobEffect;

public class DamageStackAttachment {
    public DamageStackAttachment(int stacks,int lastTickApplied, Holder<MobEffect> effect){
        this.stacks = stacks;
        this.effect = effect;
        this.lastTickApplied = 0;
    }
    public int stacks;
    public int lastTickApplied;

    public Holder<MobEffect> effect;

    /*
    (For future clarity and since as of this comment im not home for vacation(ì)
    The stacks are what will define how "bad" the effect itself is
    The last tick applied will help to define when the effect should decade and (so for example every 10 seconds 200 ticks
    stacks = stacks -1
    The effect is used to ident which effect has x stacks
     */
    public static final Codec<DamageStackAttachment> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(Codec.INT.fieldOf("stacks").forGetter((DamageStackAttachment e) -> e.stacks),
                    Codec.INT.fieldOf("lastTickApplied").forGetter((DamageStackAttachment e) -> e.lastTickApplied),
                    MobEffect.CODEC.fieldOf("effect").forGetter((DamageStackAttachment e) -> e.effect)
            ).apply(instance, (stacksC, lasTickAppliedC, effectC) ->
                    new DamageStackAttachment(stacksC,lasTickAppliedC,effectC))

    );

    public static final StreamCodec<RegistryFriendlyByteBuf, DamageStackAttachment> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, (DamageStackAttachment e) -> e.stacks,
            ByteBufCodecs.VAR_INT, (DamageStackAttachment e) -> e.lastTickApplied,
            MobEffect.STREAM_CODEC, (DamageStackAttachment e) -> e.effect,
            DamageStackAttachment::new
    );
}
