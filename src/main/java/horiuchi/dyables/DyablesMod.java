package horiuchi.dyables;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.CreativeHelper;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;

public class DyablesMod implements ModInitializer, RecipeEntrypoint, GameStartEntrypoint {
    public static final String MOD_ID = "dyables";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    @Override
    public void onInitialize() {
		LOGGER.info("Dyables Mod initialized.");
    }

	@Override
	public void onRecipesReady() {
		DyablesRecipies.initializeRecipes();
	}

	@Override
	public void initNamespaces() {
		DyablesRecipies.initializeNamespaces();
	}

	@Override
	public void beforeGameStart() {
		DyablesBlocks.init();
		DyablesItems.init();
	}

	@Override
	public void afterGameStart() {
		CreativeHelper.setPriority(DyablesBlocks.WORKBENCH_PAINTED.getLogic(), 1, 1);
	}
}

