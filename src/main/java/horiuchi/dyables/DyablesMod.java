package horiuchi.dyables;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.block.BlockLogicFire;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.crafting.LookupFuelFurnace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.HalpLibe;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;

public class DyablesMod implements ModInitializer, GameStartEntrypoint, RecipeEntrypoint {
	public static final String MOD_ID = HalpLibe.registerMod("dyables", true);
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Extra Dyables initialized.");
	}

	@Override
	public void beforeGameStart() {
		DyablesBlocks.init();
		DyablesItems.init();
	}

	public void afterGameStart() {
		LookupFuelFurnace.instance.addFuelEntry(DyablesBlocks.BOOKSHELF_PLANKS_OAK_PAINTED.id(), 300);
		LookupFuelFurnace.instance.addFuelEntry(DyablesBlocks.WORKBENCH_PAINTED.id(), 300);
		BlockLogicFire.setFlammable(DyablesBlocks.BOOKSHELF_PLANKS_OAK_PAINTED, 30, 20);
	}

	@Override
	public void onRecipesReady() {
		DyablesRecipies.initializeRecipes();
	}

	@Override
	public void initNamespaces() {
		DyablesRecipies.initializeNamespaces();
	}
}
