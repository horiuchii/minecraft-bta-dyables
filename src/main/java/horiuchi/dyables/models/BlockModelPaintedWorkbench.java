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
public class BlockModelPaintedWorkbench<T extends BlockLogic> extends BlockModelStandard<T> {
	public static final IconCoordinate[][] texCoords = new IconCoordinate[DyeColor.COLOR_AMOUNT][4];

	public BlockModelPaintedWorkbench(Block<T> block) {
		super(block);
	}

	public IconCoordinate getBlockTextureFromSideAndMetadata(Side side, int data) {
		switch (side) {
			case TOP:
				return texCoords[data & DyeColor.MASK_COLOR][2];
			case BOTTOM:
				return texCoords[data & DyeColor.MASK_COLOR][3];
			case NORTH:
			case SOUTH:
				return texCoords[data & DyeColor.MASK_COLOR][0];
			case EAST:
			case WEST:
			default:
				return texCoords[data & DyeColor.MASK_COLOR][1];
		}
	}

	static {
		for (DyeColor color : DyeColor.blockOrderedColors()) {
			texCoords[color.blockMeta] = new IconCoordinate[4];
			texCoords[color.blockMeta][0] = TextureRegistry.getTexture(MOD_ID + ":block/workbench/" + color.colorID + "/front");
			texCoords[color.blockMeta][1] = TextureRegistry.getTexture(MOD_ID + ":block/workbench/" + color.colorID + "/side");
			texCoords[color.blockMeta][2] = TextureRegistry.getTexture(MOD_ID + ":block/workbench/" + color.colorID + "/top");
			texCoords[color.blockMeta][3] = TextureRegistry.getTexture("minecraft:block/planks/" + color.colorID);
		}
	}
}
