package xela.blockframe.client.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import xela.blockframe.data.DamageSources;

import java.util.concurrent.CompletableFuture;

///To add tags to bypass armor or other stuff, we need to generate JSON files, so we can leverage <Code>DataGeneration</Code>.
///This (AFAIK) can be applied to anything as long as i put the type in the <> tags (again AFAIK it applies to tags)
public class TagGen extends FabricTagsProvider<DamageType> {

    /**
     * Constructs a new {@link FabricTagsProvider} with the default computed path.
     *
     * <p>Common implementations of this class are provided.
     *
     * @param output               the {@link FabricPackOutput} instance
     * @param registryLookupFuture the backing registry for the tag type
     */
    public TagGen(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, Registries.DAMAGE_TYPE, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        DamageSources.init();

        this.builder(DamageTypeTags.BYPASSES_ARMOR).add(DamageSources.SLASH_DAMAGE);

        //this.builder(DamageTypeTags.BYPASSES_ARMOR).add(DamageSources.PUNCTURE_DAMAGE);
    }
}
