package horiuchi.dyables.blocks;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.IPainted;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

import java.util.function.Supplier;

public class BlockLogicPaintedWorkbench extends BlockLogicPaintedBlock implements IPainted {
	public BlockLogicPaintedWorkbench(Block<?> block, Material material, Supplier<Block<?>> unpaintedVariant) {
		super(block, material, unpaintedVariant);
	}

	public boolean onBlockRightClicked(World world, int x, int y, int z, Player player, Side side, double xPlaced, double yPlaced) {
		if (!world.isClientSide) {
			player.displayWorkbenchScreen(x, y, z);
		}

		return true;
	}
}
