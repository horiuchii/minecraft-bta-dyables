package horiuchi.dyables.mixin;

import horiuchi.dyables.DyablesBlocks;
import net.minecraft.core.block.*;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.util.helper.DyeColor;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockLogicGlass.class)
public class BlockLogicGlassMixin extends BlockLogicTransparent implements IPaintable {
	public BlockLogicGlassMixin(@NotNull Block<?> block, @NotNull Material material) {
		super(block, material);
	}

	@Override
	public boolean canBePainted() {
		return this.block != Blocks.GLASS_TINTED;
	}

	@Override
	public void setColor(@NotNull World world, @NotNull TilePosc tilePosc, @NotNull DyeColor dyeColor) {
		world.setBlockType(tilePosc, DyablesBlocks.GLASS_PAINTED);
		(DyablesBlocks.GLASS_PAINTED.getLogic()).setColor(world, tilePosc, dyeColor);
	}
}
