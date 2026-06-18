package xela.blockframe.client;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import xela.blockframe.client.datagen.TagGen;
import xela.blockframe.client.datagen.TranslationsGen;


public class BlockFrameDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(TranslationsGen::new);
		pack.addProvider(TagGen::new);
	}

	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
	}
}
