package horiuchi.dyables.mixin;

import horiuchi.dyables.DyablesBlocks;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.BlockLogicGlass;
import net.minecraft.core.block.IPaintable;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.util.helper.DyeColor;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockLogicGlass.class)
public abstract class BlockLogicGlassPaintableMixin extends BlockLogic implements IPaintable {
	public BlockLogicGlassPaintableMixin(Block<?> block, Material material) { super(block, material); }

	public void setColor(World world, int x, int y, int z, DyeColor color) {
		world.setBlock(x, y, z, DyablesBlocks.GLASS_PAINTED.id());
		DyablesBlocks.GLASS_PAINTED.getLogic().setColor(world, x, y, z, color);
	}
}
