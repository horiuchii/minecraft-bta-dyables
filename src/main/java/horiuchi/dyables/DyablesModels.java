package horiuchi.dyables;

import horiuchi.dyables.model.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.EntityRendererDispatcher;
import net.minecraft.client.render.TileEntityRenderDispatcher;
import net.minecraft.client.render.block.color.BlockColorDispatcher;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.item.model.ItemModelDispatcher;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.item.Items;
import turniplabs.halplibe.util.ModelEntrypoint;

import static horiuchi.dyables.DyablesMod.MOD_ID;

@Environment(EnvType.CLIENT)
public class DyablesModels implements ModelEntrypoint {
	@Override
	public void initBlockModels(BlockModelDispatcher blockModelDispatcher) {
		blockModelDispatcher.addDispatch(new BlockModelGenericPainted<>(DyablesBlocks.BOOKSHELF_PLANKS_OAK_PAINTED, BlockModelDispatcher.loadDataModel(MOD_ID + ":block/bookshelf/white"), MOD_ID + ":block/bookshelf/"));
		blockModelDispatcher.addDispatch(new BlockModelGlassPainted<>(DyablesBlocks.GLASS_PAINTED, MOD_ID + ":block/glass/").onRenderLayer(1));
		blockModelDispatcher.addDispatch(new BlockModelGenericGlassTrapdoorPainted<>(DyablesBlocks.GLASS_TRAPDOOR_PAINTED));
		blockModelDispatcher.addDispatch(new BlockModelGenericGlassDoorPainted<>(DyablesBlocks.GLASS_DOOR_PAINTED_BOTTOM, true));
		blockModelDispatcher.addDispatch(new BlockModelGenericGlassDoorPainted<>(DyablesBlocks.GLASS_DOOR_PAINTED_TOP, false));
		blockModelDispatcher.addDispatch(new BlockModelGenericSeat<>(Blocks.SEAT));
		blockModelDispatcher.addDispatch(new BlockModelGenericPainted<>(DyablesBlocks.WORKBENCH_PAINTED, BlockModelDispatcher.loadDataModel(MOD_ID + ":block/workbench/white"), MOD_ID + ":block/workbench/"));
	}

	@Override
	public void initItemModels(ItemModelDispatcher itemModelDispatcher) {
		itemModelDispatcher.addDispatch(new ItemModelGlassDoorPainted(DyablesItems.DOOR_GLASS_PAINTED));
		itemModelDispatcher.addDispatch(new ItemModelSeatBed(Items.BED, "bed"));
		itemModelDispatcher.addDispatch(new ItemModelSeatBed(Items.SEAT, "seat"));
	}

	@Override
	public void initEntityModels(EntityRendererDispatcher entityRendererDispatcher) {

	}

	@Override
	public void initTileEntityModels(TileEntityRenderDispatcher tileEntityRenderDispatcher) {

	}

	@Override
	public void initBlockColors(BlockColorDispatcher blockColorDispatcher) {

	}
}
