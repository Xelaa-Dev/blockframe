package xela.blockframe.client.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class TranslationsGen extends FabricLanguageProvider {

    public TranslationsGen(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup){
        super(dataOutput,"en_us",registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add("key.blockframe.double_jump", "Double jump");
        translationBuilder.add("key.blockframe.roll", "Roll");
        translationBuilder.add("key.category.blockframe.blockframe", "Blockframe");
        translationBuilder.add("effect.blockframe.slash", "Slash");
    }
}
