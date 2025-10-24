package horiuchi.dyables.mixin;

import net.minecraft.client.render.block.model.BlockModelSeat;
import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.util.helper.DyeColor;
import net.minecraft.core.util.helper.Side;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import static horiuchi.dyables.DyablesMod.MOD_ID;

@Mixin(BlockModelSeat.class)
public abstract class BlockModelSeatMixin <T extends BlockLogic> extends BlockModelStandard<T> {
	@Unique
	private static final IconCoordinate[][] texCoords = new IconCoordinate[DyeColor.COLOR_AMOUNT][2];
	@Unique
	private static final IconCoordinate bottomCoordinate = TextureRegistry.getTexture("minecraft:block/planks/oak");

	public BlockModelSeatMixin(Block<T> block) { super(block); }

	public IconCoordinate getBlockTextureFromSideAndMetadata(Side side, int data) {
		if(side == Side.TOP)
			return texCoords[data & DyeColor.MASK_COLOR][0];
		if(side == Side.BOTTOM)
			return bottomCoordinate;
		else
			return texCoords[data & DyeColor.MASK_COLOR][1];
	}

	static {
		for(DyeColor c : DyeColor.blockOrderedColors()) {
			texCoords[c.blockMeta] = new IconCoordinate[2];
			texCoords[c.blockMeta][0] = TextureRegistry.getTexture(c == DyeColor.RED ? "minecraft:block/seat/top" : MOD_ID + ":block/seat/top_" + c.colorID);
			texCoords[c.blockMeta][1] = TextureRegistry.getTexture(c == DyeColor.RED ? "minecraft:block/bed/foot_front" : MOD_ID + ":block/bed/" + c.colorID + "/foot_front");
		}
	}
}
