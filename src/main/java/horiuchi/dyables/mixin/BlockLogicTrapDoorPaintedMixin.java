package horiuchi.dyables.mixin;

import horiuchi.dyables.DyablesBlocks;
import net.minecraft.core.block.*;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.DyeColor;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockLogicTrapDoorPainted.class)
public class BlockLogicTrapDoorPaintedMixin extends BlockLogicTrapDoor implements IPainted {
	public BlockLogicTrapDoorPaintedMixin(Block<?> block, Material material) {
		super(block, material);
	}

	@Override
	public String getLanguageKey(int meta) {
		return super.getLanguageKey(meta) + "." + this.fromMetadata(meta).colorID;
	}

	@Override
	public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int meta, TileEntity tileEntity) {
		return new ItemStack[]{new ItemStack(this.block.getMaterial() == Material.wood ? Blocks.TRAPDOOR_PLANKS_PAINTED : DyablesBlocks.GLASS_TRAPDOOR_PAINTED, 1, (meta >> 4 & 15) << 4)};
	}

	@Override
	public DyeColor fromMetadata(int meta) {
		return DyeColor.colorFromBlockMeta(meta >> 4 & 15);
	}

	@Override
	public int toMetadata(DyeColor color) {
		return color.blockMeta << 4;
	}

	@Override
	public int stripColorFromMetadata(int meta) {
		return meta & 15;
	}

	@Override
	public void removeDye(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		world.setBlockAndMetadataWithNotify(x, y, z, this.block.getMaterial() == Material.wood ? Blocks.TRAPDOOR_PLANKS_OAK.id() : DyablesBlocks.GLASS_TRAPDOOR_PAINTED.id(), meta & 15);
	}
}
