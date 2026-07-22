package horiuchi.dyables;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.block.BlockLogicFire;
import net.minecraft.core.crafting.LookupFuelFurnace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.HalpLibe;
import turniplabs.halplibe.event.defs.ClientEvents;
import turniplabs.halplibe.event.defs.CommonEvents;
import turniplabs.halplibe.util.dependency.Key;

public class DyablesMod implements ModInitializer {
	public static final String MOD_ID = HalpLibe.registerMod("dyables", true);
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		CommonEvents.BEFORE_GAME_START.listen(Key.of(MOD_ID), this::beforeGameStart);
		CommonEvents.AFTER_GAME_START.listen(Key.of(MOD_ID), this::afterGameStart);
		CommonEvents.RECIPES_READY.listen(Key.of(MOD_ID), this::onRecipesReady);
		CommonEvents.RECIPES_NAMESPACE_INIT.listen(Key.of(MOD_ID), this::initNamespaces);
		ClientEvents.BLOCK_MODEL_RELOAD.listen(Key.of(MOD_ID), (t) -> new DyablesModels().initBlockModels(t));
		ClientEvents.ITEM_MODEL_RELOAD.listen(Key.of(MOD_ID), (t) -> new DyablesModels().initItemModels(t));
		LOGGER.info("Extra Dyables initialized.");
	}

	public void beforeGameStart() {
		DyablesBlocks.init();
		DyablesItems.init();
	}

	public void afterGameStart() {
		LookupFuelFurnace.instance.addFuelEntry(DyablesBlocks.BOOKSHELF_PLANKS_OAK_PAINTED.id(), 300);
		LookupFuelFurnace.instance.addFuelEntry(DyablesBlocks.WORKBENCH_PAINTED.id(), 300);
		BlockLogicFire.setFlammable(DyablesBlocks.BOOKSHELF_PLANKS_OAK_PAINTED, 30, 20);
	}

	public void onRecipesReady() {
		DyablesRecipies.initializeRecipes();
	}

	public void initNamespaces() {
		DyablesRecipies.initializeNamespaces();
	}
}
