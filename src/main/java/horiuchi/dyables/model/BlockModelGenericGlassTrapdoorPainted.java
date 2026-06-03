package horiuchi.dyables.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.block.model.generic.BlockModelGeneric;
import net.minecraft.client.render.tessellator.TessellatorGeneral;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.BlockLogicTrapDoor;
import net.minecraft.core.util.helper.DyeColor;
import net.minecraft.core.world.WorldSource;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.useless.dragonfly.models.block.StaticBlockModel;

import static horiuchi.dyables.DyablesMod.MOD_ID;


@Environment(EnvType.CLIENT)
public class BlockModelGenericGlassTrapdoorPainted<T extends BlockLogic> extends BlockModelGeneric<T> {
	public final @NotNull StaticBlockModel[] bottom = new StaticBlockModel[DyeColor.COLOR_AMOUNT];
	public final @NotNull StaticBlockModel[] open = new StaticBlockModel[DyeColor.COLOR_AMOUNT];
	public final @NotNull StaticBlockModel[] top = new StaticBlockModel[DyeColor.COLOR_AMOUNT];

	public BlockModelGenericGlassTrapdoorPainted(@NotNull Block<T> block) {
		super(block, BlockModelDispatcher.loadDataModel(MOD_ID + ":block/trapdoor/glass/white/bottom"));

		for(DyeColor color : DyeColor.blockOrderedColors()) {
			this.bottom[color.blockMeta] = BlockModelDispatcher.loadDataModel(MOD_ID + ":block/trapdoor/glass/" + color.colorID + "/bottom").asModel();
			this.open[color.blockMeta] = BlockModelDispatcher.loadDataModel(MOD_ID + ":block/trapdoor/glass/" + color.colorID + "/open").asModel();
			this.top[color.blockMeta] = BlockModelDispatcher.loadDataModel(MOD_ID + ":block/trapdoor/glass/" + color.colorID + "/top").asModel();
		}

	}

	public boolean renderAttached(@NotNull TessellatorGeneral tessellator, @NotNull WorldSource worldSource, @NotNull TilePosc tilePos, boolean cullFaces, @Nullable IconCoordinate overrideTexture) {
		int rotation = worldSource.getBlockData(tilePos) & BlockLogicTrapDoor.MASK_DIRECTION;
		boolean result;
		switch (rotation & BlockLogicTrapDoor.MASK_DIRECTION) {
			case BlockLogicTrapDoor.DIRECTION_NORTH -> result = this.getModel(worldSource, tilePos).renderAttached(this, tessellator, worldSource, tilePos, 0, 2, 0, (double)0.0F, (double)0.0F, (double)0.0F, false, cullFaces, overrideTexture);
			case BlockLogicTrapDoor.DIRECTION_EAST -> result = this.getModel(worldSource, tilePos).renderAttached(this, tessellator, worldSource, tilePos, 0, 1, 0, (double)0.0F, (double)0.0F, (double)0.0F, false, cullFaces, overrideTexture);
			case BlockLogicTrapDoor.DIRECTION_WEST -> result = this.getModel(worldSource, tilePos).renderAttached(this, tessellator, worldSource, tilePos, 0, 3, 0, (double)0.0F, (double)0.0F, (double)0.0F, false, cullFaces, overrideTexture);
			default -> result = this.getModel(worldSource, tilePos).renderAttached(this, tessellator, worldSource, tilePos, 0, 0, 0, (double)0.0F, (double)0.0F, (double)0.0F, false, cullFaces, overrideTexture);
		}

		return result;
	}

	public @NotNull StaticBlockModel getModelFromData(int data) {
		int color = data >> BlockLogicTrapDoor.MASK_OPEN & DyeColor.MASK_COLOR;
		if ((data & BlockLogicTrapDoor.MASK_OPEN) != 0) {
			return this.open[color];
		} else {
			return (data & BlockLogicTrapDoor.MASK_UPPER_HALF) != 0 ? this.top[color] : this.bottom[color];
		}
	}
}
