package horiuchi.dyables.blocks;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.IPainted;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;

import java.util.function.Supplier;

public class BlockLogicPaintedGlass extends BlockLogicPaintedBlock implements IPainted {
	public BlockLogicPaintedGlass(Block<?> block, Material material, Supplier<Block<?>> unpaintedVariant) {
		super(block, material, unpaintedVariant);
	}

	public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int meta, TileEntity tileEntity) {
		switch (dropCause) {
			case PICK_BLOCK:
			case SILK_TOUCH:
				return new ItemStack[]{new ItemStack(this, 1, meta)};
			default:
				return null;
		}
	}

	public boolean isSolidRender() {
		return false;
	}
}
