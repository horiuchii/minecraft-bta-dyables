package horiuchi.dyables;

import horiuchi.dyables.models.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.EntityRenderDispatcher;
import net.minecraft.client.render.TileEntityRenderDispatcher;
import net.minecraft.client.render.block.color.BlockColorDispatcher;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.item.model.ItemModelDispatcher;
import net.minecraft.core.item.Items;
import turniplabs.halplibe.util.ModelEntrypoint;

@Environment(EnvType.CLIENT)
public class DyablesModels implements ModelEntrypoint {
	@Override
	public void initBlockModels(BlockModelDispatcher dispatcher) {
		dispatcher.addDispatch(new BlockModelPaintedBookshelf<>(DyablesBlocks.BOOKSHELF_PLANKS_OAK_PAINTED));
		dispatcher.addDispatch(new BlockModelPaintedGlass<>(DyablesBlocks.GLASS_PAINTED, false));
		dispatcher.addDispatch(new BlockModelGlassTrapDoorPainted<>(DyablesBlocks.GLASS_TRAPDOOR_PAINTED));
		dispatcher.addDispatch(new BlockModelGlassDoorPainted<>(DyablesBlocks.GLASS_DOOR_PAINTED_BOTTOM, false));
		dispatcher.addDispatch(new BlockModelGlassDoorPainted<>(DyablesBlocks.GLASS_DOOR_PAINTED_TOP, true));
		dispatcher.addDispatch(new BlockModelPaintedWorkbench<>(DyablesBlocks.WORKBENCH_PAINTED));
	}

	@Override
	public void initItemModels(ItemModelDispatcher dispatcher) {
		dispatcher.addDispatch(new ItemModelGlassDoorPainted(DyablesItems.DOOR_GLASS_PAINTED));
		dispatcher.addDispatch(new ItemModelSeat(Items.SEAT));
		dispatcher.addDispatch(new ItemModelBed(Items.BED));

//		for (DyeColor c : DyeColor.itemOrderedColors())
//		{
//			dispatcher.addDispatch((new ItemModelLeatherArmorPainted(DyablesItems.ARMOR_HELMET_LEATHER_PAINTED[c.itemMeta], IArmorItem.PIECE_HEAD, c)));
//			dispatcher.addDispatch((new ItemModelLeatherArmorPainted(DyablesItems.ARMOR_CHESTPLATE_LEATHER_PAINTED[c.itemMeta], IArmorItem.PIECE_CHEST, c)));
//			dispatcher.addDispatch((new ItemModelLeatherArmorPainted(DyablesItems.ARMOR_LEGGINGS_LEATHER_PAINTED[c.itemMeta], 1, c)));
//			dispatcher.addDispatch((new ItemModelLeatherArmorPainted(DyablesItems.ARMOR_BOOTS_LEATHER_PAINTED[c.itemMeta], IArmorItem.PIECE_BOOTS, c)));
//		}
	}

	@Override
	public void initEntityModels(EntityRenderDispatcher dispatcher) {

	}

	@Override
	public void initTileEntityModels(TileEntityRenderDispatcher dispatcher) {

	}

	@Override
	public void initBlockColors(BlockColorDispatcher dispatcher) {

	}
}
