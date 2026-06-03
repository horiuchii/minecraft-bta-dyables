package horiuchi.dyables.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.block.model.generic.BlockModelGenericDoor;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.BlockLogicDoor;
import net.minecraft.core.util.helper.DyeColor;
import org.jetbrains.annotations.NotNull;
import org.useless.dragonfly.models.block.StaticBlockModel;

import static horiuchi.dyables.DyablesMod.MOD_ID;

@Environment(EnvType.CLIENT)
public class BlockModelGenericGlassDoorPainted<T extends BlockLogic> extends BlockModelGenericDoor<T> {
	public final @NotNull StaticBlockModel[] left = new StaticBlockModel[DyeColor.COLOR_AMOUNT];
	public final @NotNull StaticBlockModel[] left_open = new StaticBlockModel[DyeColor.COLOR_AMOUNT];
	public final @NotNull StaticBlockModel[] right = new StaticBlockModel[DyeColor.COLOR_AMOUNT];
	public final @NotNull StaticBlockModel[] right_open = new StaticBlockModel[DyeColor.COLOR_AMOUNT];

	public BlockModelGenericGlassDoorPainted(@NotNull Block<T> block, boolean bottom) {
		super(block, MOD_ID + ":block/door/glass/white", bottom);
		String side = bottom ? "bottom" : "top";

		for(DyeColor color : DyeColor.blockOrderedColors()) {
			this.left[color.blockMeta] = BlockModelDispatcher.loadDataModel(MOD_ID + ":block/door/glass/%s/%s_left".formatted(color.colorID, side)).asModel();
			this.left_open[color.blockMeta] = BlockModelDispatcher.loadDataModel(MOD_ID + ":block/door/glass/%s/%s_left_open".formatted(color.colorID, side)).asModel();
			this.right[color.blockMeta] = BlockModelDispatcher.loadDataModel(MOD_ID + ":block/door/glass/%s/%s_right".formatted(color.colorID, side)).asModel();
			this.right_open[color.blockMeta] = BlockModelDispatcher.loadDataModel(MOD_ID + ":block/door/glass/%s/%s_right_open".formatted(color.colorID, side)).asModel();
		}
	}

	public @NotNull StaticBlockModel getModelFromData(int data) {
		int color = data >> BlockLogicDoor.MASK_OPENED & DyeColor.MASK_COLOR;
		boolean isLeft = (data & BlockLogicDoor.MASK_HINGE) != 0;
		boolean isOpen = (data & BlockLogicDoor.MASK_OPENED) != 0;
		if (isLeft) {
			return isOpen ? this.left_open[color] : this.left[color];
		} else {
			return isOpen ? this.right[color] : this.right_open[color];
		}
	}
}
