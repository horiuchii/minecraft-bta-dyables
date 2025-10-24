package horiuchi.dyables.mixin;

import horiuchi.dyables.DyablesBlocks;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.BlockLogicBookshelf;
import net.minecraft.core.block.IPaintable;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.util.helper.DyeColor;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockLogicBookshelf.class)
public abstract class BlockLogicBookshelfPaintableMixin extends BlockLogic implements IPaintable {
	public BlockLogicBookshelfPaintableMixin(Block<?> block, Material material) { super(block, material); }

	public void setColor(World world, int x, int y, int z, DyeColor color) {
		world.setBlock(x, y, z, DyablesBlocks.BOOKSHELF_PLANKS_OAK_PAINTED.id());
		DyablesBlocks.BOOKSHELF_PLANKS_OAK_PAINTED.getLogic().setColor(world, x, y, z, color);
	}
}
