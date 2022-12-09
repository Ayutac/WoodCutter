package tfar.woodcutter;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;

public class Woodcutter implements ModInitializer, ClientModInitializer {

	public static final String MOD_ID = "woodcutter";

	public static final Identifier ID = new Identifier(MOD_ID, MOD_ID);

	public static final Identifier INTERACT_WITH_WOODCUTTER = registerStat("interact_with_"+ MOD_ID, StatFormatter.DEFAULT);

	public static final Block WOODCUTTER = new WoodcutterBlock(AbstractBlock.Settings.of(Material.WOOD).strength(2.5F).sounds(BlockSoundGroup.WOOD));
	public static final ScreenHandlerType<WoodcutterScreenHandler> WOODCUTTER_SCREEN_HANDLER = new ScreenHandlerType<>(WoodcutterScreenHandler::new);
	public static final RecipeSerializer<WoodcuttingRecipe> WOODCUTTING_SERIALIZER= new WoodcuttingRecipe.Serializer2<>(WoodcuttingRecipe::new);

	@Override
	public void onInitialize() {
		// initialize the class
		WoodcuttingRecipe.TYPE.hashCode();

		Registry.register(Registries.BLOCK,ID, WOODCUTTER);
		Registry.register(Registries.ITEM,ID,new BlockItem(WOODCUTTER,new FabricItemSettings()));
		Registry.register(Registries.SCREEN_HANDLER,ID, WOODCUTTER_SCREEN_HANDLER);
		RecipeSerializer.register(Woodcutter.MOD_ID +":woodcutting", WOODCUTTING_SERIALIZER);
	}

	private static Identifier registerStat(String string, StatFormatter statFormatter) {
		Identifier identifier = new Identifier(string);
		Registry.register(Registries.CUSTOM_STAT, string, identifier);
		Stats.CUSTOM.getOrCreateStat(identifier, statFormatter);
		return identifier;
	}

	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlock(WOODCUTTER, RenderLayer.getCutout());
		ScreenRegistry.register(WOODCUTTER_SCREEN_HANDLER, WoodcutterScreen::new);
	}
}
