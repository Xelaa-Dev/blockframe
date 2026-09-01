package xela.blockframe.client.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class TranslationsGen extends FabricLanguageProvider {

    public TranslationsGen(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder translationBuilder) {
        /*
        KEYBINDS
         */
        translationBuilder.add("key.blockframe.double_jump", "Double jump");
        translationBuilder.add("key.blockframe.roll", "Roll");
        translationBuilder.add("key.category.blockframe.blockframe", "Blockframe");
        translationBuilder.add("key.category.blockframe.movement", "Movement");
        /*
        EFFECTS
         */
        translationBuilder.add("effect.blockframe.slash", "Slash");
        translationBuilder.add("effect.blockframe.cold", "Cold");
        translationBuilder.add("effect.blockframe.puncture", "Puncture");
        translationBuilder.add("effect.blockframe.impact", "Impact");

         /*
        MOD SPECIFIC (MOD MENU VIA OWOLIB)
         */
        translationBuilder.add("text.config.Blockframe.title", "Blockframe Config");
        translationBuilder.add("text.config.Blockframe.option.should_roll_for_status_effects_on_player_damaged",
                "Should roll for status effects on player damaged");
        translationBuilder.add("text.config.Blockframe.option.chanche_for_status_effects_to_apply",
                "Chanche for status effects to apply");
        translationBuilder.add("text.config.Blockframe.option.force_applied_on_movment",
                "Force applied on special movements");
        /*
        ENCHANTMENTS
         */
        translationBuilder.add("enchantment.blockframe.cold", "Cold");
        translationBuilder.add("enchantment.blockframe.bleed", "Slash");
    }
}
