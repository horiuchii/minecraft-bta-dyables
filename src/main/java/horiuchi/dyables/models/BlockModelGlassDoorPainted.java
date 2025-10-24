package horiuchi.dyables.models;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.block.model.BlockModelDoor;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicDoor;
import net.minecraft.core.util.helper.Axis;
import net.minecraft.core.util.helper.DyeColor;
import net.minecraft.core.util.helper.Side;

import static horiuchi.dyables.DyablesMod.MOD_ID;

@Environment(EnvType.CLIENT)
public class BlockModelGlassDoorPainted<T extends BlockLogicDoor> extends BlockModelDoor<T> {
	public static final IconCoordinate[] doorBottomTextures = new IconCoordinate[DyeColor.COLOR_AMOUNT];
	public static final IconCoordinate[] doorTopTextures = new IconCoordinate[DyeColor.COLOR_AMOUNT];
	public static final IconCoordinate[] frameTopTextures = new IconCoordinate[DyeColor.COLOR_AMOUNT];
	private final boolean isTop;

	public BlockModelGlassDoorPainted(Block<T> block, boolean isTop) {
		super(block);
		this.isTop = isTop;
	}

	public IconCoordinate getBlockTextureFromSideAndMetadata(Side side, int data) {
		int color = data >> 4 & DyeColor.MASK_COLOR;
		if (side.getAxis() == Axis.Y) {
			return frameTopTextures[color];
		} else {
			return this.isTop ? doorTopTextures[color] : doorBottomTextures[color];
		}
	}

	protected boolean shouldFlipTexture(Side side, int meta) {
		return false;
	}

	static {
		for(DyeColor c : DyeColor.blockOrderedColors()) {
			doorTopTextures[c.blockMeta] = TextureRegistry.getTexture(MOD_ID + ":block/door/glass_" + c.colorID + "/top");
			doorBottomTextures[c.blockMeta] = TextureRegistry.getTexture(MOD_ID + ":block/door/glass_" + c.colorID + "/bottom");
			frameTopTextures[c.blockMeta] = TextureRegistry.getTexture(MOD_ID + ":block/glass/glass_" + c.colorID);
		}

	}
}
