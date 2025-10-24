package horiuchi.dyables.models;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.util.helper.DyeColor;
import net.minecraft.core.util.helper.Side;

import static horiuchi.dyables.DyablesMod.MOD_ID;

@Environment(EnvType.CLIENT)
public class BlockModelPaintedBookshelf<T extends BlockLogic> extends BlockModelStandard<T> {
	public static final IconCoordinate[][] texCoords = new IconCoordinate[DyeColor.COLOR_AMOUNT][2];

	public BlockModelPaintedBookshelf(Block<T> block) {
		super(block);
	}

	public IconCoordinate getBlockTextureFromSideAndMetadata(Side side, int data) {
		return texCoords[data & DyeColor.MASK_COLOR][side == Side.TOP || side == Side.BOTTOM ? 1 : 0];
	}

	static {
		for (DyeColor color : DyeColor.blockOrderedColors()) {
			texCoords[color.blockMeta] = new IconCoordinate[2];
			texCoords[color.blockMeta][0] = TextureRegistry.getTexture(MOD_ID + ":block/bookshelf/side_" + color.colorID);
			texCoords[color.blockMeta][1] = TextureRegistry.getTexture("minecraft:block/planks/" + color.colorID);
		}
	}
}
