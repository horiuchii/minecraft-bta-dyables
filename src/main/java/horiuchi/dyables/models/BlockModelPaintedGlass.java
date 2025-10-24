package horiuchi.dyables.models;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.block.model.BlockModelTransparent;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.util.helper.DyeColor;
import net.minecraft.core.util.helper.Side;

import static horiuchi.dyables.DyablesMod.MOD_ID;

@Environment(EnvType.CLIENT)
public class BlockModelPaintedGlass<T extends BlockLogic> extends BlockModelTransparent<T> {
	public static final IconCoordinate[] texCoords = new IconCoordinate[DyeColor.COLOR_AMOUNT];

	public BlockModelPaintedGlass(Block<T> block, boolean renderInside) {
		super(block, renderInside);
	}

	public IconCoordinate getBlockTextureFromSideAndMetadata(Side side, int data) {
		return texCoords[data & DyeColor.MASK_COLOR];
	}

	static {
		for (DyeColor color : DyeColor.blockOrderedColors()) {
			texCoords[color.blockMeta] = TextureRegistry.getTexture(MOD_ID + ":block/glass/glass_" + color.colorID);
		}
	}
}
