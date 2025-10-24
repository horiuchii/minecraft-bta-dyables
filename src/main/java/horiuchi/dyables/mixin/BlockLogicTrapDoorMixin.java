package horiuchi.dyables.mixin;

import horiuchi.dyables.DyablesBlocks;
import net.minecraft.core.block.*;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.util.helper.DyeColor;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockLogicTrapDoor.class)
public class BlockLogicTrapDoorMixin extends BlockLogic implements IPaintable {
	public BlockLogicTrapDoorMixin(Block<?> block, Material material) {
		super(block, material);
	}

	@Override
	public boolean canBePainted() {
		return this.material == Material.wood || this.material == Material.glass;
	}

	@Override
	public void setColor(World world, int x, int y, int z, DyeColor color) {
		int meta = world.getBlockMetadata(x, y, z);
		world.setBlockAndMetadataWithNotify(x, y, z, this.material == Material.wood ? Blocks.TRAPDOOR_PLANKS_PAINTED.id() : DyablesBlocks.GLASS_TRAPDOOR_PAINTED.id(), meta & 15 | color.blockMeta << 4);
	}
}
