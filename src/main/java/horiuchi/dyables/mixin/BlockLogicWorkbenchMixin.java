package horiuchi.dyables.mixin;

import horiuchi.dyables.DyablesBlocks;
import net.minecraft.core.block.*;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.util.helper.Direction;
import net.minecraft.core.util.helper.DyeColor;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(BlockLogicWorkbench.class)
public abstract class BlockLogicWorkbenchMixin extends BlockLogicRotatable implements IPaintable {

	@Unique
	private static final int MASK_DIRECTION = 0b01110000;
	@Unique
	private static final int DIRECTION_SHIFT = 4;

	public BlockLogicWorkbenchMixin(@NotNull Block<?> block, @NotNull Material material) {
		super(block, material);
	}

	@Override
	public void onPlacedByMob(@NotNull World world, @NotNull TilePosc tilePos, @NotNull Side side, @NotNull Mob mob, double xHit, double yHit) {
		world.setBlockDataNotify(tilePos, (world.getBlockData(tilePos) & ~MASK_DIRECTION) | (mob.getHorizontalPlacementDirection(side).opposite().ordinal() << DIRECTION_SHIFT));
	}

	@Override
	public void onPlacedOnSide(@NotNull World world, @NotNull TilePosc tilePos, @NotNull Side side, double xHit, double yHit) {
		if (!side.isHorizontal()) {
			side = Side.SOUTH;
		}

		world.setBlockDataNotify(tilePos, (world.getBlockData(tilePos) & ~MASK_DIRECTION) | (side.direction().id << DIRECTION_SHIFT));
	}

	@Override
	public void setColor(@NotNull World world, @NotNull TilePosc tilePosc, @NotNull DyeColor dyeColor) {
		world.setBlockTypeData(tilePosc, DyablesBlocks.WORKBENCH_PAINTED, world.getBlockData(tilePosc));
		DyablesBlocks.WORKBENCH_PAINTED.getLogic().setColor(world, tilePosc, dyeColor);
	}
}
