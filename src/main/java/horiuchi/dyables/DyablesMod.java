package horiuchi.dyables;

import net.fabricmc.api.ModInitializer;
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
