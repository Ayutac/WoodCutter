package tfar.woodcutter.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.data.server.recipe.RecipeJsonProvider

import java.util.function.Consumer

class CraftingRecipesProvider extends FabricRecipeProvider {

    CraftingRecipesProvider(FabricDataOutput output) {
        super(output)
    }

    @Override
    void generate(Consumer<RecipeJsonProvider> exporter) {

    }

}
