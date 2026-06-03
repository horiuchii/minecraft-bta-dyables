package horiuchi.dyables.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.option.GameSettings;
import net.minecraft.client.render.block.model.BlockModelTransparent;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.util.helper.DyeColor;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.WorldSource;
import net.minecraft.core.world.pos.TilePos;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class BlockModelGlassPainted<T extends BlockLogic> extends BlockModelTransparent<T> {
	public static final int UP = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 4;
	public static final int RIGHT = 8;
	protected final IconCoordinate[][] coordinates = new IconCoordinate[DyeColor.COLOR_AMOUNT][16];

	public BlockModelGlassPainted(Block<T> block, @NotNull String rootKey) {
		super(block, false);
		for(DyeColor c : DyeColor.blockOrderedColors()) {
			this.coordinates[c.blockMeta][0] = TextureRegistry.getTexture(rootKey + c.colorID +"/none");
			this.coordinates[c.blockMeta][UP] = TextureRegistry.getTexture(rootKey + c.colorID + "/up");
			this.coordinates[c.blockMeta][DOWN] = TextureRegistry.getTexture(rootKey + c.colorID + "/down");
			this.coordinates[c.blockMeta][LEFT] = TextureRegistry.getTexture(rootKey + c.colorID + "/left");
			this.coordinates[c.blockMeta][RIGHT] = TextureRegistry.getTexture(rootKey + c.colorID + "/right");
			this.coordinates[c.blockMeta][UP + DOWN] = TextureRegistry.getTexture(rootKey + c.colorID + "/up_down");
			this.coordinates[c.blockMeta][UP + LEFT] = TextureRegistry.getTexture(rootKey + c.colorID + "/up_left");
			this.coordinates[c.blockMeta][UP + RIGHT] = TextureRegistry.getTexture(rootKey + c.colorID + "/up_right");
			this.coordinates[c.blockMeta][DOWN + LEFT] = TextureRegistry.getTexture(rootKey + c.colorID + "/down_left");
			this.coordinates[c.blockMeta][DOWN + RIGHT] = TextureRegistry.getTexture(rootKey + c.colorID + "/down_right");
			this.coordinates[c.blockMeta][LEFT + RIGHT] = TextureRegistry.getTexture(rootKey + c.colorID + "/left_right");
			this.coordinates[c.blockMeta][UP + DOWN + LEFT] = TextureRegistry.getTexture(rootKey + c.colorID + "/up_down_left");
			this.coordinates[c.blockMeta][UP + DOWN + RIGHT] = TextureRegistry.getTexture(rootKey + c.colorID + "/up_down_right");
			this.coordinates[c.blockMeta][UP + LEFT + RIGHT] = TextureRegistry.getTexture(rootKey + c.colorID + "/up_left_right");
			this.coordinates[c.blockMeta][DOWN + LEFT + RIGHT] = TextureRegistry.getTexture(rootKey + c.colorID + "/down_left_right");
			this.coordinates[c.blockMeta][UP + DOWN + LEFT + RIGHT] = TextureRegistry.getTexture(rootKey + c.colorID + "/up_down_left_right");
		}
	}

	public @Nullable IconCoordinate getBlockTextureFromSideAndMetadata(@NotNull Side side, int data) {
		return this.coordinates[data & DyeColor.MASK_COLOR][0];
	}

	public @Nullable IconCoordinate getBlockTexture(@NotNull WorldSource source, @NotNull TilePosc tilePos, @NotNull Side side) {
		int colorIndex = source.getBlockData(tilePos) & DyeColor.MASK_COLOR;
		if (GameSettings.CONNECTED_GLASS.value) {
			TilePos queryPos = new TilePos();
			boolean u;
			boolean d;
			boolean l;
			boolean r;
			switch (side) {
				case SOUTH:
					u = source.getBlockType(tilePos.up(queryPos)) == this.block
						&& source.getBlockData(tilePos.up(queryPos)) == colorIndex;
					d = source.getBlockType(tilePos.down(queryPos)) == this.block
						&& source.getBlockData(tilePos.down(queryPos)) == colorIndex;
					l = source.getBlockType(tilePos.west(queryPos)) == this.block
						&& source.getBlockData(tilePos.west(queryPos)) == colorIndex;
					r = source.getBlockType(tilePos.east(queryPos)) == this.block
						&& source.getBlockData(tilePos.east(queryPos)) == colorIndex;
					break;
				case NORTH:
					u = source.getBlockType(tilePos.up(queryPos)) == this.block
						&& source.getBlockData(tilePos.up(queryPos)) == colorIndex;
					d = source.getBlockType(tilePos.down(queryPos)) == this.block
						&& source.getBlockData(tilePos.down(queryPos)) == colorIndex;
					l = source.getBlockType(tilePos.east(queryPos)) == this.block
						&& source.getBlockData(tilePos.east(queryPos)) == colorIndex;
					r = source.getBlockType(tilePos.west(queryPos)) == this.block
						&& source.getBlockData(tilePos.west(queryPos)) == colorIndex;
					break;
				case WEST:
					u = source.getBlockType(tilePos.up(queryPos)) == this.block
						&& source.getBlockData(tilePos.up(queryPos)) == colorIndex;
					d = source.getBlockType(tilePos.down(queryPos)) == this.block
						&& source.getBlockData(tilePos.down(queryPos)) == colorIndex;
					l = source.getBlockType(tilePos.north(queryPos)) == this.block
						&& source.getBlockData(tilePos.north(queryPos)) == colorIndex;
					r = source.getBlockType(tilePos.south(queryPos)) == this.block
						&& source.getBlockData(tilePos.south(queryPos)) == colorIndex;
					break;
				case EAST:
					u = source.getBlockType(tilePos.up(queryPos)) == this.block
						&& source.getBlockData(tilePos.up(queryPos)) == colorIndex;
					d = source.getBlockType(tilePos.down(queryPos)) == this.block
						&& source.getBlockData(tilePos.down(queryPos)) == colorIndex;
					l = source.getBlockType(tilePos.south(queryPos)) == this.block
						&& source.getBlockData(tilePos.south(queryPos)) == colorIndex;
					r = source.getBlockType(tilePos.north(queryPos)) == this.block
						&& source.getBlockData(tilePos.north(queryPos)) == colorIndex;
					break;
				case TOP:
				case BOTTOM:
				default:
					u = source.getBlockType(tilePos.north(queryPos)) == this.block
						&& source.getBlockData(tilePos.north(queryPos)) == colorIndex;
					d = source.getBlockType(tilePos.south(queryPos)) == this.block
						&& source.getBlockData(tilePos.south(queryPos)) == colorIndex;
					l = source.getBlockType(tilePos.west(queryPos)) == this.block
						&& source.getBlockData(tilePos.west(queryPos)) == colorIndex;
					r = source.getBlockType(tilePos.east(queryPos)) == this.block
						&& source.getBlockData(tilePos.east(queryPos)) == colorIndex;
			}
			return this.coordinates[colorIndex][(u ? UP : 0) | (d ? DOWN : 0) | (l ? LEFT : 0) | (r ? RIGHT : 0)];
		} else {
			return this.coordinates[colorIndex][0];
		}
	}
}
