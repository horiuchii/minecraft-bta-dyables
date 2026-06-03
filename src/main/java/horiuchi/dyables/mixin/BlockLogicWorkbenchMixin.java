package horiuchi.dyables.mixin;

import horiuchi.dyables.DyablesBlocks;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.BlockLogicWorkbench;
import net.minecraft.core.block.IPaintable;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.util.helper.DyeColor;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockLogicWorkbench.class)
public class BlockLogicWorkbenchMixin extends BlockLogic implements IPaintable {
	public BlockLogicWorkbenchMixin(@NotNull Block<?> block, @NotNull Material material) {
		super(block, material);
	}

	@Override
	public void setColor(@NotNull World world, @NotNull TilePosc tilePosc, @NotNull DyeColor dyeColor) {
		world.setBlockType(tilePosc, DyablesBlocks.WORKBENCH_PAINTED);
		(DyablesBlocks.WORKBENCH_PAINTED.getLogic()).setColor(world, tilePosc, dyeColor);
	}
}
