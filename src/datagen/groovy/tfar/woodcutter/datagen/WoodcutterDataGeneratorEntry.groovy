package tfar.woodcutter.datagen

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import org.slf4j.LoggerFactory
import tfar.woodcutter.Woodcutter

class WoodcutterDataGeneratorEntry implements DataGeneratorEntrypoint {

    @Override
    void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        def pack = fabricDataGenerator.createPack()

        def add = { FabricDataGenerator.Pack.RegistryDependentFactory factory ->
            pack.addProvider factory
        }
        add CraftingRecipesProvider::new
    }

    @Override
    String getEffectiveModId() {
        return Woodcutter.MOD_ID
    }
}