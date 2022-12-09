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

public class WoodCutter implements ModInitializer, ClientModInitializer {

	public static final String MODID = "woodcutter";

	public static final Identifier ID = new Identifier(MODID,MODID);

	public static final Identifier INTERACT_WITH_WOODCUTTER = register("interact_with_"+MODID, StatFormatter.DEFAULT);

	public static Block woodcutter;
	public static ScreenHandlerType<WoodCutterContainer> woodCutterContainer;
	public static RecipeSerializer<WoodcuttingRecipe> WOODCUTTING;

	@Override
	public void onInitialize() {
		// initialize the class
		WoodcuttingRecipe.TYPE.hashCode();

		woodcutter = Registry.register(Registries.BLOCK,ID,new WoodCutterBlock(AbstractBlock.Settings.of(Material.WOOD).strength(2.5F).sounds(BlockSoundGroup.WOOD)));
		Registry.register(Registries.ITEM,ID,new BlockItem(woodcutter,new FabricItemSettings()));
		woodCutterContainer = Registry.register(Registries.SCREEN_HANDLER,ID, new ScreenHandlerType<>(WoodCutterContainer::new));
		WOODCUTTING = RecipeSerializer.register(WoodCutter.MODID +":woodcutting", new WoodcuttingRecipe.Serializer2<>(WoodcuttingRecipe::new));
	}

	private static Identifier register(String string, StatFormatter statFormatter) {
		Identifier identifier = new Identifier(string);
		Registry.register(Registries.CUSTOM_STAT, string, identifier);
		Stats.CUSTOM.getOrCreateStat(identifier, statFormatter);
		return identifier;
	}

	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlock(woodcutter, RenderLayer.getCutout());
		ScreenRegistry.register(woodCutterContainer,WoodCutterScreen::new);
	}
}
