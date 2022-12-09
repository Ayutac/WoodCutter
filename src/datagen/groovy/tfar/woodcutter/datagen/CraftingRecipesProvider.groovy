package tfar.woodcutter.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.advancement.criterion.CriterionConditions
import net.minecraft.data.server.recipe.RecipeJsonProvider
import net.minecraft.data.server.recipe.SingleItemRecipeJsonBuilder
import net.minecraft.item.ItemConvertible
import net.minecraft.item.Items
import net.minecraft.recipe.Ingredient
import net.minecraft.recipe.book.RecipeCategory
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier
import tfar.woodcutter.Woodcutter

import java.util.concurrent.CompletableFuture
import java.util.function.Consumer
import java.util.function.Function

class CraftingRecipesProvider extends FabricRecipeProvider {

    protected Consumer<RecipeJsonProvider> exporter

    CraftingRecipesProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output)
    }

    @Override
    void generate(Consumer<RecipeJsonProvider> exporter) {
        this.exporter = exporter
        offerSlabRecipeWoodcutter(Items.ACACIA_PLANKS, Items.ACACIA_SLAB)
    }

    def static createWoodcutterRecipe(def input, ItemConvertible output, int outputAmount = 1, RecipeCategory category = RecipeCategory.BUILDING_BLOCKS) {
        return new SingleItemRecipeJsonBuilder(category, Woodcutter.WOODCUTTING_SERIALIZER, createIngredient(input), output, outputAmount)
                .criterion(getCriterionName(input), getCriterionConditions(input))
    }

    def offerSlabRecipeWoodcutter(def material, ItemConvertible output, String prefix = "") {
        createWoodcutterRecipe(material, output, 2)
                .offerTo(this.exporter, new Identifier(Woodcutter.MOD_ID, materialTypeString(prefix, material, "slab", CraftingRecipesProvider::getName) + "_woodcutter"))
    }

    // utility methods copied from TR

    static Ingredient createIngredient(def input) {
        if (input instanceof Ingredient) {
            return input
        }
        if (input instanceof ItemConvertible) {
            return Ingredient.ofItems(input)
        } else if (input instanceof TagKey) {
            return Ingredient.fromTag(input)
        }

        throw new IllegalArgumentException()
    }

    static String getCriterionName(def input) {
        if (input instanceof ItemConvertible) {
            return hasItem(input)
        } else if (input instanceof TagKey) {
            return "has_tag_" + input.id().toUnderscoreSeparatedString()
        }

        throw new IllegalArgumentException()
    }

    static CriterionConditions getCriterionConditions(def input) {
        if (input instanceof ItemConvertible) {
            return conditionsFromItem(input)
        } else if (input instanceof TagKey) {
            return conditionsFromTag(input)
        }

        throw new IllegalArgumentException()
    }

    static String getName(def input) {
        if (input instanceof ItemConvertible) {
            return getItemPath(input)
        } else if (input instanceof TagKey) {
            String name = input.id().toString()
            if (name.contains(":"))
                name = name.substring(name.indexOf(":")+1)
            return name
        }

        throw new IllegalArgumentException()
    }

    def static materialTypeString(String prefix, def material, String type, Function<?, String> modifier) {
        StringBuilder s = new StringBuilder()
        s.append(prefix)
        s.append(modifier.apply(material))
        s.append('_')
        s.append(type)
        return s.toString()
    }

}
