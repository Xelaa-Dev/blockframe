package xela.blockframe.data.typeof;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.effect.MobEffect;
import xela.blockframe.data.StatusData;

import java.util.*;

public class EntityStatusAttachment {
    private final Map<Holder<MobEffect>, StatusData> statusMap;

    public EntityStatusAttachment() {
        this.statusMap = new HashMap<>();
    }

    public EntityStatusAttachment(Map<Holder<MobEffect>, StatusData> map) {
        this.statusMap = new HashMap<>(map);
    }

    public Map<Holder<MobEffect>, StatusData> getStatusMap() {
        return statusMap;
    }

    public void addStack(Holder<MobEffect> effect, int currentTick, int maxStacks) {
        //The map is made from a key and value, the key will be the status effect
        StatusData data = statusMap.computeIfAbsent(effect, k -> new StatusData(0, currentTick));
        data.setStacks(Math.min(data.getStacks() + 1, maxStacks));
        data.setLastTickApplied(currentTick);
    }

    public void removeStatus(Holder<MobEffect> effect) {
        statusMap.remove(effect);
    }

    public StatusData getStatus(Holder<MobEffect> effect) {
        return statusMap.get(effect);
    }

    public record Entry(Holder<MobEffect> effect, StatusData data) {
        public static final Codec<Entry> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        MobEffect.CODEC.fieldOf("effect").forGetter(Entry::effect),
                        StatusData.CODEC.fieldOf("data").forGetter(Entry::data)
                ).apply(instance, Entry::new)
        );

        public static final StreamCodec<RegistryFriendlyByteBuf, Entry> STREAM_CODEC = StreamCodec.composite(
                MobEffect.STREAM_CODEC, Entry::effect,
                StatusData.STREAM_CODEC, Entry::data,
                Entry::new
        );
    }

    private static List<Entry> toList(EntityStatusAttachment attachment) {
        List<Entry> list = new ArrayList<>();
        attachment.statusMap.forEach((effect, data) -> list.add(new Entry(effect, data)));
        return list;
    }

    private static EntityStatusAttachment fromList(List<Entry> list) {
        Map<Holder<MobEffect>, StatusData> map = new HashMap<>();
        for (Entry entry : list) {
            map.put(entry.effect(), entry.data());
        }
        return new EntityStatusAttachment(map);
    }

    public static final Codec<EntityStatusAttachment> CODEC = Entry.CODEC.listOf()
            .xmap(EntityStatusAttachment::fromList, EntityStatusAttachment::toList);

    public static final StreamCodec<RegistryFriendlyByteBuf, EntityStatusAttachment> STREAM_CODEC =
            Entry.STREAM_CODEC.apply(ByteBufCodecs.list()).map(EntityStatusAttachment::fromList, EntityStatusAttachment::toList);
}