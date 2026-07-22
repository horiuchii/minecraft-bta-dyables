package horiuchi.dyables.model;

import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.block.model.generic.BlockModelGeneric;
import net.minecraft.client.render.tessellator.TessellatorGeneral;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.BlockLogicRotatable;
import net.minecraft.core.util.helper.Direction;
import net.minecraft.core.util.helper.DyeColor;
import net.minecraft.core.world.WorldSource;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.useless.dragonfly.data.block.BlockModelData;
import org.useless.dragonfly.models.block.StaticBlockModel;

import static horiuchi.dyables.DyablesMod.MOD_ID;

public class BlockModelGenericWorkbenchPainted<T extends BlockLogic> extends BlockModelGeneric<T> {
	public final StaticBlockModel[] models = new StaticBlockModel[DyeColor.COLOR_AMOUNT];

	public BlockModelGenericWorkbenchPainted(@NotNull Block<T> block, @NotNull BlockModelData staticModel) {
		super(block, staticModel);

		for(DyeColor c : DyeColor.blockOrderedColors()) {
			this.models[c.blockMeta] = BlockModelDispatcher.loadDataModel(MOD_ID + ":block/workbench/" + c.colorID).asModel();
		}
	}

	public boolean renderAttached(@NotNull TessellatorGeneral tessellator, @NotNull WorldSource worldSource, @NotNull TilePosc tilePos, boolean cullFaces, @Nullable IconCoordinate overrideTexture) {
		final int meta = worldSource.getBlockData(tilePos);
		Direction direction = BlockLogicRotatable.getDirectionFromMeta((meta & 0b01110000) >> 4);
		int rotY = switch (direction) {
			case EAST, WEST -> 1;
			default -> 0;
		};

		return this.getModelFromData(meta).renderAttached(this, tessellator, worldSource, tilePos, 0, rotY, 0, 0.0F, 0.0F, 0.0F, false, cullFaces, overrideTexture);
	}

	public @NotNull StaticBlockModel getModelFromData(int data) {
		return this.models[data & DyeColor.MASK_COLOR];
	}
}
