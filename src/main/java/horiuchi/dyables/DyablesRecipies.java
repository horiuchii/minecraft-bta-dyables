package horiuchi.dyables;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.data.registry.recipe.RecipeSymbol;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryDyeing;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryUndyeing;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.util.helper.DyeColor;
import turniplabs.halplibe.helper.RecipeBuilder;

import java.util.List;

import static horiuchi.dyables.DyablesMod.MOD_ID;
import static net.minecraft.core.util.helper.DyeColor.colorFromBlockMeta;
import static net.minecraft.core.util.helper.DyeColor.colorFromItemMeta;

public class DyablesRecipies {
	private static void registerItemGroup(String groupName, List<ItemStack> groupList)
	{
		Registries.ITEM_GROUPS.register(MOD_ID + ":" + groupName, groupList);
	}

	private static void registerBlockDyeRecipes(String recipeKey, String groupName, Block<?> blockDyed, Block<?> blockUndyed, boolean useUpperMeta)
	{
		Registries.RECIPES.addCustomRecipe(
			MOD_ID + ":workbench/" + recipeKey + "_dyeing",
			new RecipeEntryDyeing(
				new RecipeSymbol(MOD_ID + ":" + groupName),
				blockDyed.getDefaultStack(), useUpperMeta, false
			)
		);
		Registries.RECIPES.addCustomRecipe(
			MOD_ID + ":workbench/" + recipeKey + "_undyeing",
			new RecipeEntryUndyeing(
				new RecipeSymbol(MOD_ID + ":" + groupName),
				blockUndyed.getDefaultStack()
			)
		);
	}

	private static void registerItemDyeRecipes(String recipeKey, String groupName, Item itemDyed, Item itemUndyed, boolean useItemMeta)
	{
		Registries.RECIPES.addCustomRecipe(
			MOD_ID + ":workbench/" + recipeKey + "_dyeing",
			new RecipeEntryDyeing(
				new RecipeSymbol(MOD_ID + ":" + groupName),
				itemDyed.getDefaultStack(), false, useItemMeta
			)
		);
		Registries.RECIPES.addCustomRecipe(
			MOD_ID + ":workbench/" + recipeKey + "_undyeing",
			new RecipeEntryUndyeing(
				new RecipeSymbol(MOD_ID + ":" + groupName),
				itemUndyed.getDefaultStack()
			)
		);
	}

	public static void initializeNamespaces()
	{
		RecipeBuilder.initNameSpace(MOD_ID);
		/* create the groups for the new colored blocks, start off the
		list with the undyed variant and then add all the colored ones */

		List<ItemStack> bookshelves = Registries.stackListOf(Blocks.BOOKSHELF_PLANKS_OAK);
		List<ItemStack> workbenches = Registries.stackListOf(Blocks.WORKBENCH);
		List<ItemStack> glass = Registries.stackListOf(Blocks.GLASS);
		List<ItemStack> glassDoors = Registries.stackListOf(Items.DOOR_GLASS);
		List<ItemStack> glassTrapdoors = Registries.stackListOf(Blocks.TRAPDOOR_GLASS);
		List<ItemStack> beds = Registries.stackListOf(Items.BED);
		List<ItemStack> seats = Registries.stackListOf(Items.SEAT);

		for (DyeColor c : DyeColor.values())
		{
			bookshelves.add(new ItemStack(DyablesBlocks.BOOKSHELF_PLANKS_OAK_PAINTED, 1, c.blockMeta));
			workbenches.add(new ItemStack(DyablesBlocks.WORKBENCH_PAINTED, 1, c.blockMeta));
			glass.add(new ItemStack(DyablesBlocks.GLASS_PAINTED, 1, c.blockMeta));
			glassDoors.add(new ItemStack(DyablesItems.DOOR_GLASS_PAINTED, 1, c.itemMeta));
			glassTrapdoors.add(new ItemStack(DyablesBlocks.GLASS_TRAPDOOR_PAINTED, 1, c.blockMeta << 4));
			beds.add(new ItemStack(Items.BED, 1, c.itemMeta));
			seats.add(new ItemStack(Items.SEAT, 1, c.itemMeta));
		}

		registerItemGroup("bookshelves", bookshelves);
		registerItemGroup("workbench", workbenches);
		registerItemGroup("glass", glass);
		registerItemGroup("glass_doors", glassDoors);
		registerItemGroup("glass_trapdoors", glassTrapdoors);
		registerItemGroup("beds", beds);
		registerItemGroup("seats", seats);
	}

	public static void initializeRecipes()
	{
		// remove vanilla recipes

		RecipeBuilder.ModifyWorkbench("minecraft").removeRecipe("bookshelf");
		RecipeBuilder.ModifyWorkbench("minecraft").removeRecipe("workbench");

		// create dying & undying recipes

		registerBlockDyeRecipes("bookshelf", "bookshelves", DyablesBlocks.BOOKSHELF_PLANKS_OAK_PAINTED, Blocks.BOOKSHELF_PLANKS_OAK, false);
		registerBlockDyeRecipes("workbench", "workbench", DyablesBlocks.WORKBENCH_PAINTED, Blocks.WORKBENCH, false);
		registerBlockDyeRecipes("glass", "glass", DyablesBlocks.GLASS_PAINTED, Blocks.GLASS, false);
		registerItemDyeRecipes("glass_door", "glass_doors", DyablesItems.DOOR_GLASS_PAINTED, Items.DOOR_GLASS, true);
		registerBlockDyeRecipes("glass_trapdoor", "glass_trapdoors", DyablesBlocks.GLASS_TRAPDOOR_PAINTED, Blocks.TRAPDOOR_GLASS, true);
		registerItemDyeRecipes("bed", "beds", Items.BED, Items.BED, false);
		registerItemDyeRecipes("seat", "seats", Items.SEAT, Items.SEAT, false);

		RecipeBuilder.Shaped(MOD_ID)
			.setShape("xx","xx")
			.addInput('x', Blocks.PLANKS_OAK)
			.create("workbench", new ItemStack(Blocks.WORKBENCH, 1));
		RecipeBuilder.Shaped(MOD_ID)
			.setShape("xxx","ppp","xxx")
			.addInput('x', Blocks.PLANKS_OAK)
			.addInput('p', Items.BOOK)
			.create("bookshelf", new ItemStack(Blocks.BOOKSHELF_PLANKS_OAK, 1));

		for (DyeColor c : DyeColor.itemOrderedColors())
		{
			RecipeBuilder.Shaped(MOD_ID)
				.setShape("xx","xx")
				.addInput('x', Blocks.PLANKS_OAK_PAINTED, c.itemMeta)
				.create(colorFromBlockMeta(c.itemMeta).colorID + "_workbench", new ItemStack(DyablesBlocks.WORKBENCH_PAINTED, 1, c.itemMeta));
			RecipeBuilder.Shaped(MOD_ID)
				.setShape("xxx","ppp","xxx")
				.addInput('x', Blocks.PLANKS_OAK_PAINTED, c.itemMeta)
				.addInput('p', Items.BOOK)
				.create(colorFromBlockMeta(c.itemMeta).colorID + "_bookshelf", new ItemStack(DyablesBlocks.BOOKSHELF_PLANKS_OAK_PAINTED, 1, c.itemMeta));
			RecipeBuilder.Shaped(MOD_ID)
				.setShape("xx","xx","xx")
				.addInput('x', DyablesBlocks.GLASS_PAINTED, c.blockMeta)
				.create(colorFromItemMeta(c.itemMeta).colorID + "_glass_door", new ItemStack(DyablesItems.DOOR_GLASS_PAINTED, 1, c.itemMeta));
			RecipeBuilder.Shaped(MOD_ID)
				.setShape("xxx")
				.addInput('x', DyablesBlocks.GLASS_PAINTED, c.blockMeta)
				.create(colorFromItemMeta(c.itemMeta).colorID + "_glass_trapdoor", new ItemStack(DyablesBlocks.GLASS_TRAPDOOR_PAINTED, 1, c.blockMeta << 4));
		}
	}
}
