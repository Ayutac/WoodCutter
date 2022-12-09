package tfar.woodcutter;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CuttingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class WoodcuttingRecipe extends CuttingRecipe {

	public static final RecipeType<WoodcuttingRecipe> TYPE = RecipeType.register(Woodcutter.MOD_ID +":"+ Woodcutter.MOD_ID);

	public WoodcuttingRecipe(Identifier id, String group, Ingredient input, ItemStack output) {
		super(TYPE, Woodcutter.WOODCUTTING_SERIALIZER, id, group, input, output);
	}

	@Override
	public boolean matches(Inventory inv, World world) {
		return this.input.test(inv.getStack(0));
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(Woodcutter.WOODCUTTER);
	}

	public static void load() {
		/* Loads the class. */
	}

	public static class Serializer2<T extends CuttingRecipe> extends Serializer<T> {

		public Serializer2(RecipeFactory<T> recipeFactory) {
			super(recipeFactory);
		}
	}
}
