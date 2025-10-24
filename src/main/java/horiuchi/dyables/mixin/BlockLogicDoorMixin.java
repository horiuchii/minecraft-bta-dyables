package horiuchi.dyables.mixin;

import horiuchi.dyables.DyablesBlocks;
import net.minecraft.core.block.*;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.util.helper.DyeColor;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BlockLogicDoor.class)
public class BlockLogicDoorMixin extends BlockLogic implements IPaintable {

	@Final
	@Shadow
	public boolean isTop;

	public BlockLogicDoorMixin(Block<?> block, Material material) {
		super(block, material);
	}

	@Override
	public boolean canBePainted() {
		return this.material == Material.wood || this.material == Material.glass;
	}

	@Override
	public void setColor(World world, int x, int y, int z, DyeColor color) {
		int meta = world.getBlockMetadata(x, y, z);
		Block<?> top_block = this.block.getMaterial() == Material.wood ? Blocks.DOOR_PLANKS_PAINTED_TOP : DyablesBlocks.GLASS_DOOR_PAINTED_TOP;
		Block<?> bottom_block = this.block.getMaterial() == Material.wood ? Blocks.DOOR_PLANKS_PAINTED_BOTTOM : DyablesBlocks.GLASS_DOOR_PAINTED_BOTTOM;

		if (this.isTop) {
			world.setBlockAndMetadataRaw(x, y, z, top_block.id(), meta);
			((BlockLogicDoorPainted)top_block.getLogic()).setColor(world, x, y, z, color);
			world.setBlockAndMetadataRaw(x, y - 1, z, bottom_block.id(), meta);
			((BlockLogicDoorPainted)bottom_block.getLogic()).setColor(world, x, y - 1, z, color);
		} else {
			world.setBlockAndMetadataRaw(x, y, z, bottom_block.id(), meta);
			((BlockLogicDoorPainted)bottom_block.getLogic()).setColor(world, x, y, z, color);
			world.setBlockAndMetadataRaw(x, y + 1, z, top_block.id(), meta);
			((BlockLogicDoorPainted)top_block.getLogic()).setColor(world, x, y + 1, z, color);
		}

	}
}
