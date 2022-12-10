package tfar.woodcutter.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.advancement.criterion.CriterionConditions
import net.minecraft.data.server.recipe.RecipeJsonProvider
import net.minecraft.data.server.recipe.SingleItemRecipeJsonBuilder
import net.minecraft.item.Item
import net.minecraft.item.ItemConvertible
import net.minecraft.item.Items
import net.minecraft.recipe.Ingredient
import net.minecraft.recipe.book.RecipeCategory
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.ItemTags
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
        [
                (Items.ACACIA_LOG) : Items.ACACIA_WOOD,
                (Items.BIRCH_LOG) : Items.BIRCH_WOOD,
                (Items.DARK_OAK_LOG) : Items.DARK_OAK_WOOD,
                (Items.JUNGLE_LOG) : Items.JUNGLE_WOOD,
                (Items.OAK_LOG) : Items.OAK_WOOD,
                (Items.MANGROVE_LOG) : Items.MANGROVE_WOOD,
                (Items.SPRUCE_LOG) : Items.SPRUCE_WOOD,
                (Items.CRIMSON_STEM) : Items.CRIMSON_HYPHAE,
                (Items.WARPED_STEM) : Items.WARPED_HYPHAE
        ].each {log, wood ->
            offerWoodRecipeWoodcutter(log, wood)
        }
        [
                (Items.ACACIA_LOG) : Items.STRIPPED_ACACIA_LOG,
                (Items.BIRCH_LOG) : Items.STRIPPED_BIRCH_LOG,
                (Items.DARK_OAK_LOG) : Items.STRIPPED_DARK_OAK_LOG,
                (Items.JUNGLE_LOG) : Items.STRIPPED_JUNGLE_LOG,
                (Items.OAK_LOG) : Items.STRIPPED_OAK_LOG,
                (Items.MANGROVE_LOG) : Items.STRIPPED_MANGROVE_LOG,
                (Items.SPRUCE_LOG) : Items.STRIPPED_SPRUCE_LOG,
                (Items.CRIMSON_STEM) : Items.STRIPPED_CRIMSON_STEM,
                (Items.WARPED_STEM) : Items.STRIPPED_WARPED_STEM
        ].each {log, stripped_log ->
            offerStrippedLogRecipeWoodcutter(log, stripped_log)
        }
        [
                (Items.ACACIA_LOG) : Items.STRIPPED_ACACIA_WOOD,
                (Items.BIRCH_LOG) : Items.STRIPPED_BIRCH_WOOD,
                (Items.DARK_OAK_LOG) : Items.STRIPPED_DARK_OAK_WOOD,
                (Items.JUNGLE_LOG) : Items.STRIPPED_JUNGLE_WOOD,
                (Items.OAK_LOG) : Items.STRIPPED_OAK_WOOD,
                (Items.MANGROVE_LOG) : Items.STRIPPED_MANGROVE_WOOD,
                (Items.SPRUCE_LOG) : Items.STRIPPED_SPRUCE_WOOD,
                (Items.CRIMSON_STEM) : Items.STRIPPED_CRIMSON_HYPHAE,
                (Items.WARPED_STEM) : Items.STRIPPED_WARPED_HYPHAE
        ].each {log, stripped_wood ->
            offerStrippedWoodRecipeWoodcutter(log, stripped_wood)
        }
        [
                (Items.ACACIA_WOOD) : Items.STRIPPED_ACACIA_WOOD,
                (Items.BIRCH_WOOD) : Items.STRIPPED_BIRCH_WOOD,
                (Items.DARK_OAK_WOOD) : Items.STRIPPED_DARK_OAK_WOOD,
                (Items.JUNGLE_WOOD) : Items.STRIPPED_JUNGLE_WOOD,
                (Items.OAK_WOOD) : Items.STRIPPED_OAK_WOOD,
                (Items.MANGROVE_WOOD) : Items.STRIPPED_MANGROVE_WOOD,
                (Items.SPRUCE_WOOD) : Items.STRIPPED_SPRUCE_WOOD,
                (Items.CRIMSON_HYPHAE) : Items.STRIPPED_CRIMSON_HYPHAE,
                (Items.WARPED_HYPHAE) : Items.STRIPPED_WARPED_HYPHAE
        ].each {wood, stripped_wood ->
            offerStrippedWoodRecipeWoodcutter(wood, stripped_wood)
        }
        [
                (Items.STRIPPED_ACACIA_LOG) : Items.STRIPPED_ACACIA_WOOD,
                (Items.STRIPPED_BIRCH_LOG) : Items.STRIPPED_BIRCH_WOOD,
                (Items.STRIPPED_DARK_OAK_LOG) : Items.STRIPPED_DARK_OAK_WOOD,
                (Items.STRIPPED_JUNGLE_LOG) : Items.STRIPPED_JUNGLE_WOOD,
                (Items.STRIPPED_OAK_LOG) : Items.STRIPPED_OAK_WOOD,
                (Items.STRIPPED_MANGROVE_LOG) : Items.STRIPPED_MANGROVE_WOOD,
                (Items.STRIPPED_SPRUCE_LOG) : Items.STRIPPED_SPRUCE_WOOD,
                (Items.STRIPPED_CRIMSON_STEM) : Items.STRIPPED_CRIMSON_HYPHAE,
                (Items.STRIPPED_WARPED_STEM) : Items.STRIPPED_WARPED_HYPHAE
        ].each {stripped_log, stripped_wood ->
            offerStrippedWoodRecipeWoodcutter(stripped_log, stripped_wood)
        }
        [
                (ItemTags.ACACIA_LOGS) : Items.ACACIA_PLANKS,
                (ItemTags.BIRCH_LOGS) : Items.BIRCH_PLANKS,
                (ItemTags.DARK_OAK_LOGS) : Items.DARK_OAK_PLANKS,
                (ItemTags.JUNGLE_LOGS) : Items.JUNGLE_PLANKS,
                (ItemTags.OAK_LOGS) : Items.OAK_PLANKS,
                (ItemTags.MANGROVE_LOGS) : Items.MANGROVE_PLANKS,
                (ItemTags.SPRUCE_LOGS) : Items.SPRUCE_PLANKS,
                (ItemTags.CRIMSON_STEMS) : Items.CRIMSON_PLANKS,
                (ItemTags.WARPED_STEMS) : Items.WARPED_PLANKS
        ].each {logs, planks ->
            offerPlanksRecipeWoodcutter(logs, planks)
        }
        [
                (Items.ACACIA_PLANKS) : Items.ACACIA_STAIRS,
                (Items.BIRCH_PLANKS) : Items.BIRCH_STAIRS,
                (Items.DARK_OAK_PLANKS) : Items.DARK_OAK_STAIRS,
                (Items.JUNGLE_PLANKS) : Items.JUNGLE_STAIRS,
                (Items.OAK_PLANKS) : Items.OAK_STAIRS,
                (Items.MANGROVE_PLANKS) : Items.MANGROVE_STAIRS,
                (Items.SPRUCE_PLANKS) : Items.SPRUCE_STAIRS,
                (Items.CRIMSON_PLANKS) : Items.CRIMSON_STAIRS,
                (Items.WARPED_PLANKS) : Items.WARPED_STAIRS
        ].each {planks, stairs ->
            offerStairsRecipeWoodcutter(planks, stairs)
        }
        [
                (Items.ACACIA_PLANKS) : Items.ACACIA_SLAB,
                (Items.BIRCH_PLANKS) : Items.BIRCH_SLAB,
                (Items.DARK_OAK_PLANKS) : Items.DARK_OAK_SLAB,
                (Items.JUNGLE_PLANKS) : Items.JUNGLE_SLAB,
                (Items.OAK_PLANKS) : Items.OAK_SLAB,
                (Items.MANGROVE_PLANKS) : Items.MANGROVE_SLAB,
                (Items.SPRUCE_PLANKS) : Items.SPRUCE_SLAB,
                (Items.CRIMSON_PLANKS) : Items.CRIMSON_SLAB,
                (Items.WARPED_PLANKS) : Items.WARPED_SLAB
        ].each {planks, slab ->
            offerSlabRecipeWoodcutter(planks, slab)
        }
        [
                (Items.ACACIA_PLANKS) : Items.ACACIA_FENCE,
                (Items.BIRCH_PLANKS) : Items.BIRCH_FENCE,
                (Items.DARK_OAK_PLANKS) : Items.DARK_OAK_FENCE,
                (Items.JUNGLE_PLANKS) : Items.JUNGLE_FENCE,
                (Items.OAK_PLANKS) : Items.OAK_FENCE,
                (Items.MANGROVE_PLANKS) : Items.MANGROVE_FENCE,
                (Items.SPRUCE_PLANKS) : Items.SPRUCE_FENCE,
                (Items.CRIMSON_PLANKS) : Items.CRIMSON_FENCE,
                (Items.WARPED_PLANKS) : Items.WARPED_FENCE
        ].each {planks, fence ->
            offerFenceRecipeWoodcutter(planks, fence)
        }
        [
                (Items.ACACIA_PLANKS) : Items.ACACIA_FENCE_GATE,
                (Items.BIRCH_PLANKS) : Items.BIRCH_FENCE_GATE,
                (Items.DARK_OAK_PLANKS) : Items.DARK_OAK_FENCE_GATE,
                (Items.JUNGLE_PLANKS) : Items.JUNGLE_FENCE_GATE,
                (Items.OAK_PLANKS) : Items.OAK_FENCE_GATE,
                (Items.MANGROVE_PLANKS) : Items.MANGROVE_FENCE_GATE,
                (Items.SPRUCE_PLANKS) : Items.SPRUCE_FENCE_GATE,
                (Items.CRIMSON_PLANKS) : Items.CRIMSON_FENCE_GATE,
                (Items.WARPED_PLANKS) : Items.WARPED_FENCE_GATE
        ].each {planks, fenceGate ->
            offerFenceGateRecipeWoodcutter(planks, fenceGate)
        }
        [
                (Items.ACACIA_PLANKS) : Items.ACACIA_DOOR,
                (Items.BIRCH_PLANKS) : Items.BIRCH_DOOR,
                (Items.DARK_OAK_PLANKS) : Items.DARK_OAK_DOOR,
                (Items.JUNGLE_PLANKS) : Items.JUNGLE_DOOR,
                (Items.OAK_PLANKS) : Items.OAK_DOOR,
                (Items.MANGROVE_PLANKS) : Items.MANGROVE_DOOR,
                (Items.SPRUCE_PLANKS) : Items.SPRUCE_DOOR,
                (Items.CRIMSON_PLANKS) : Items.CRIMSON_DOOR,
                (Items.WARPED_PLANKS) : Items.WARPED_DOOR
        ].each {planks, door ->
            offerDoorRecipeWoodcutter(planks, door)
        }
        [
                (Items.ACACIA_PLANKS) : Items.ACACIA_TRAPDOOR,
                (Items.BIRCH_PLANKS) : Items.BIRCH_TRAPDOOR,
                (Items.DARK_OAK_PLANKS) : Items.DARK_OAK_TRAPDOOR,
                (Items.JUNGLE_PLANKS) : Items.JUNGLE_TRAPDOOR,
                (Items.OAK_PLANKS) : Items.OAK_TRAPDOOR,
                (Items.MANGROVE_PLANKS) : Items.MANGROVE_TRAPDOOR,
                (Items.SPRUCE_PLANKS) : Items.SPRUCE_TRAPDOOR,
                (Items.CRIMSON_PLANKS) : Items.CRIMSON_TRAPDOOR,
                (Items.WARPED_PLANKS) : Items.WARPED_TRAPDOOR
        ].each {planks, trapdoor ->
            offerTrapdoorRecipeWoodcutter(planks, trapdoor)
        }
        [
                (Items.ACACIA_PLANKS) : Items.ACACIA_PRESSURE_PLATE,
                (Items.BIRCH_PLANKS) : Items.BIRCH_PRESSURE_PLATE,
                (Items.DARK_OAK_PLANKS) : Items.DARK_OAK_PRESSURE_PLATE,
                (Items.JUNGLE_PLANKS) : Items.JUNGLE_PRESSURE_PLATE,
                (Items.OAK_PLANKS) : Items.OAK_PRESSURE_PLATE,
                (Items.MANGROVE_PLANKS) : Items.MANGROVE_PRESSURE_PLATE,
                (Items.SPRUCE_PLANKS) : Items.SPRUCE_PRESSURE_PLATE,
                (Items.CRIMSON_PLANKS) : Items.CRIMSON_PRESSURE_PLATE,
                (Items.WARPED_PLANKS) : Items.WARPED_PRESSURE_PLATE
        ].each {planks, pressurePlate ->
            offerPressurePlateRecipeWoodcutter(planks, pressurePlate)
        }
        [
                (Items.ACACIA_PLANKS) : Items.ACACIA_SIGN,
                (Items.BIRCH_PLANKS) : Items.BIRCH_SIGN,
                (Items.DARK_OAK_PLANKS) : Items.DARK_OAK_SIGN,
                (Items.JUNGLE_PLANKS) : Items.JUNGLE_SIGN,
                (Items.OAK_PLANKS) : Items.OAK_SIGN,
                (Items.MANGROVE_PLANKS) : Items.MANGROVE_SIGN,
                (Items.SPRUCE_PLANKS) : Items.SPRUCE_SIGN,
                (Items.CRIMSON_PLANKS) : Items.CRIMSON_SIGN,
                (Items.WARPED_PLANKS) : Items.WARPED_SIGN
        ].each {planks, sign ->
            offerSignRecipeWoodcutter(planks, sign)
        }
        [
                (Items.ACACIA_PLANKS) : Items.ACACIA_BUTTON,
                (Items.BIRCH_PLANKS) : Items.BIRCH_BUTTON,
                (Items.DARK_OAK_PLANKS) : Items.DARK_OAK_BUTTON,
                (Items.JUNGLE_PLANKS) : Items.JUNGLE_BUTTON,
                (Items.OAK_PLANKS) : Items.OAK_BUTTON,
                (Items.MANGROVE_PLANKS) : Items.MANGROVE_BUTTON,
                (Items.SPRUCE_PLANKS) : Items.SPRUCE_BUTTON,
                (Items.CRIMSON_PLANKS) : Items.CRIMSON_BUTTON,
                (Items.WARPED_PLANKS) : Items.WARPED_BUTTON
        ].each {planks, button ->
            offerButtonRecipeWoodcutter(planks, button)
        }
        offerSticksRecipeWoodcutter(ItemTags.PLANKS, Items.STICK)
    }

    def static createWoodcutterRecipe(def input, ItemConvertible output, int outputAmount = 1, RecipeCategory category = RecipeCategory.BUILDING_BLOCKS) {
        return new SingleItemRecipeJsonBuilder(category, Woodcutter.WOODCUTTING_SERIALIZER, createIngredient(input), output, outputAmount)
                .criterion(getCriterionName(input), getCriterionConditions(input))
    }

    def offerSingleOutputRecipeWoodcutter(def material, ItemConvertible output, String toWhat) {
        createWoodcutterRecipe(material, output, 1)
                .offerTo(this.exporter, new Identifier(Woodcutter.MOD_ID, materialTypeString("", material, "to_"+toWhat, CraftingRecipesProvider::getName) + "_by_woodcutter"))
    }

    def offerWoodRecipeWoodcutter(def material, ItemConvertible output) {
        offerSingleOutputRecipeWoodcutter(material, output, "wood")
    }

    def offerStrippedLogRecipeWoodcutter(def material, ItemConvertible output) {
        offerSingleOutputRecipeWoodcutter(material, output, "stripped_log")
    }

    def offerStrippedWoodRecipeWoodcutter(def material, ItemConvertible output) {
        offerSingleOutputRecipeWoodcutter(material, output, "stripped_wood")
    }

    def offerPlanksRecipeWoodcutter(def material, ItemConvertible output) {
        createWoodcutterRecipe(material, output, 4)
                .offerTo(this.exporter, new Identifier(Woodcutter.MOD_ID, materialTypeString("", material, "to_planks", CraftingRecipesProvider::getName) + "_by_woodcutter"))
    }

    def offerStairsRecipeWoodcutter(def material, ItemConvertible output) {
        offerSingleOutputRecipeWoodcutter(material, output, "stairs")
    }

    def offerSlabRecipeWoodcutter(def material, ItemConvertible output) {
        createWoodcutterRecipe(material, output, 2)
                .offerTo(this.exporter, new Identifier(Woodcutter.MOD_ID, materialTypeString("", material, "to_slab", CraftingRecipesProvider::getName) + "_by_woodcutter"))
    }

    def offerFenceRecipeWoodcutter(def material, ItemConvertible output) {
        offerSingleOutputRecipeWoodcutter(material, output, "fence")
    }

    def offerFenceGateRecipeWoodcutter(def material, ItemConvertible output) {
        offerSingleOutputRecipeWoodcutter(material, output, "fence_gate")
    }

    def offerDoorRecipeWoodcutter(def material, ItemConvertible output) {
        offerSingleOutputRecipeWoodcutter(material, output, "door")
    }

    def offerTrapdoorRecipeWoodcutter(def material, ItemConvertible output) {
        offerSingleOutputRecipeWoodcutter(material, output, "trapdoor")
    }

    def offerPressurePlateRecipeWoodcutter(def material, ItemConvertible output) {
        offerSingleOutputRecipeWoodcutter(material, output, "pressure_plate")
    }

    def offerSignRecipeWoodcutter(def material, ItemConvertible output) {
        offerSingleOutputRecipeWoodcutter(material, output, "sign")
    }

    def offerButtonRecipeWoodcutter(def material, ItemConvertible output) {
        offerSingleOutputRecipeWoodcutter(material, output, "button")
    }

    def offerSticksRecipeWoodcutter(def material, ItemConvertible output) {
        createWoodcutterRecipe(material, output, 4)
                .offerTo(this.exporter, new Identifier(Woodcutter.MOD_ID, materialTypeString("", material, "to_sticks", CraftingRecipesProvider::getName) + "_by_woodcutter"))
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
