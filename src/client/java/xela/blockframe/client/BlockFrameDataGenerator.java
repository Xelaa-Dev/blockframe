package xela.blockframe.client;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageType;
import xela.blockframe.client.datagen.TagGen;
import xela.blockframe.client.datagen.TranslationsGen;
import xela.blockframe.data.DamageSources;


public class BlockFrameDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(TranslationsGen::new);
		pack.addProvider(TagGen::new);
	}

	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
		//Bootstrap the registry
		registryBuilder.add(Registries.DAMAGE_TYPE, context -> {
			context.register(
					DamageSources.SLASH_DAMAGE,
					//Match the already genned files for damagetypes
					new DamageType("magic", DamageScaling.NEVER, 0)
			);
		});
	}
}
