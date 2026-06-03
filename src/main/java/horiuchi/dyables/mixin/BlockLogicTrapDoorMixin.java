package horiuchi.dyables.mixin;

import horiuchi.dyables.DyablesBlocks;
import net.minecraft.core.block.*;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.material.Materials;
import net.minecraft.core.util.helper.DyeColor;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BlockLogicTrapDoor.class)
public class BlockLogicTrapDoorMixin extends BlockLogic implements IPaintable {
	@Shadow
	public static final int MASK_OPEN = 4;
	public BlockLogicTrapDoorMixin(@NotNull Block<?> block, @NotNull Material material) {
		super(block, material);
	}

	@Override
	public boolean canBePainted() {
		return this.material == Materials.WOOD || this.material == Materials.GLASS;
	}

	@Override
	public void setColor(World world, @NotNull TilePosc tilePos, DyeColor color) {
		int meta = world.getBlockData(tilePos);
		world.setBlockTypeDataNotify(tilePos, this.material == Materials.WOOD ? Blocks.TRAPDOOR_PLANKS_PAINTED : DyablesBlocks.GLASS_TRAPDOOR_PAINTED, meta & DyeColor.MASK_COLOR | color.blockMeta << MASK_OPEN);
	}
}
