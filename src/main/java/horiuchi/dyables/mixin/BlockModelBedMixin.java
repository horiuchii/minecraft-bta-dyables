package horiuchi.dyables.mixin;

import net.minecraft.client.render.block.model.BlockModelBed;
import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.BlockLogicBed;
import net.minecraft.core.util.helper.DyeColor;
import net.minecraft.core.util.helper.Side;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import static horiuchi.dyables.DyablesMod.MOD_ID;

@Mixin(BlockModelBed.class)
public abstract class BlockModelBedMixin <T extends BlockLogic> extends BlockModelStandard<T> {
	@Unique
	private static final IconCoordinate[][] bedTextures = new IconCoordinate[DyeColor.COLOR_AMOUNT][5];
	@Unique
	private static final IconCoordinate bottomTexture = TextureRegistry.getTexture("minecraft:block/planks/oak");
	@Unique
	private static final IconCoordinate headFrontTexture = TextureRegistry.getTexture("minecraft:block/bed/head_front");

	@Unique
	private static final int FOOT_FRONT = 0;
	@Unique
	private static final int FOOT_SIDE = 1;
	@Unique
	private static final int FOOT_TOP = 2;
	@Unique
	private static final int HEAD_SIDE = 3;
	@Unique
	private static final int HEAD_TOP = 4;

	public BlockModelBedMixin(Block<T> block) {
		super(block);
	}

	@Override
	public IconCoordinate getBlockTextureFromSideAndMetadata(Side side, int data) {
		if (side == Side.BOTTOM) {
			return bottomTexture;
		} else {
			DyeColor color = DyeColor.colorFromBlockMeta((data >> 4) & 0x0F);
			int k = BlockLogicBed.getDirection(data);
			int l = BlockLogicBed.bedDirection[k][side.getId()];
			if (BlockLogicBed.isBlockFootOfBed(data)) {
				if (l == 2) {
					return headFrontTexture;
				} else {
					return l != 5 && l != 4 ? bedTextures[color.blockMeta][HEAD_TOP] : bedTextures[color.blockMeta][HEAD_SIDE];
				}
			} else if (l == 3) {
				return bedTextures[color.blockMeta][FOOT_FRONT];
			} else {
				return l != 5 && l != 4 ? bedTextures[color.blockMeta][FOOT_TOP] : bedTextures[color.blockMeta][FOOT_SIDE];
			}
		}
	}

	static {
		for(DyeColor c : DyeColor.blockOrderedColors()) {
			bedTextures[c.blockMeta] = new IconCoordinate[5];
			bedTextures[c.blockMeta][FOOT_FRONT] = TextureRegistry.getTexture(c == DyeColor.RED ? "minecraft:block/bed/foot_front" : MOD_ID + ":block/bed/" + c.colorID + "/foot_front");
			bedTextures[c.blockMeta][FOOT_SIDE] = TextureRegistry.getTexture(c == DyeColor.RED ? "minecraft:block/bed/foot_side" : MOD_ID + ":block/bed/" + c.colorID + "/foot_side");
			bedTextures[c.blockMeta][FOOT_TOP] = TextureRegistry.getTexture(c == DyeColor.RED ? "minecraft:block/bed/foot_top" : MOD_ID + ":block/bed/" + c.colorID + "/foot_top");
			bedTextures[c.blockMeta][HEAD_SIDE] = TextureRegistry.getTexture(c == DyeColor.RED ? "minecraft:block/bed/head_side" : MOD_ID + ":block/bed/" + c.colorID + "/head_side");
			bedTextures[c.blockMeta][HEAD_TOP] = TextureRegistry.getTexture(c == DyeColor.RED ? "minecraft:block/bed/head_top" : MOD_ID + ":block/bed/" + c.colorID + "/head_top");
		}
	}
}
