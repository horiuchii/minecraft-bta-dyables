package horiuchi.dyables.mixin;

import horiuchi.dyables.DyablesItems;
import net.minecraft.core.block.*;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.util.helper.DyeColor;
import net.minecraft.core.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

import java.util.function.Supplier;

@Mixin(BlockLogicDoorPainted.class)
public abstract class BlockLogicDoorPaintedMixin extends BlockLogicDoor implements IPainted {
	public BlockLogicDoorPaintedMixin(Block<?> block, Material material, boolean isTop, boolean requireTool, @Nullable Supplier<Item> droppedItem) {
		super(block, material, isTop, requireTool, droppedItem);
	}

	@Override
	public String getLanguageKey(int meta) {
		return super.getLanguageKey(meta) + "." + this.fromMetadata(meta).colorID;
	}

	@Override
	public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int meta, TileEntity tileEntity) {
		return new ItemStack[]{new ItemStack(this.block.getMaterial() == Material.wood ? Items.DOOR_OAK_PAINTED : DyablesItems.DOOR_GLASS_PAINTED, 1, DyeColor.MASK_COLOR - (meta >> 4 & DyeColor.MASK_COLOR))};
	}

	@Override
	public DyeColor fromMetadata(int meta) {
		return DyeColor.colorFromBlockMeta(meta >> 4 & DyeColor.MASK_COLOR);
	}

	@Override
	public int toMetadata(DyeColor color) {
		return color.blockMeta << 4;
	}

	@Override
	public int stripColorFromMetadata(int meta) {
		return meta & DyeColor.MASK_COLOR;
	}

	@Override
	public void removeDye(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		int top_block_id = this.block.getMaterial() == Material.wood ? Blocks.DOOR_PLANKS_OAK_TOP.id() : Blocks.DOOR_GLASS_TOP.id();
		int bottom_block_id = this.block.getMaterial() == Material.wood ? Blocks.DOOR_PLANKS_OAK_BOTTOM.id() : Blocks.DOOR_GLASS_BOTTOM.id();
		world.setBlockAndMetadataWithNotify(x, y, z, this.isTop ? top_block_id : bottom_block_id, meta & DyeColor.MASK_COLOR);
		if (this.isTop) {
			world.setBlockAndMetadataWithNotify(x, y - 1, z, bottom_block_id, meta & DyeColor.MASK_COLOR);
		} else {
			world.setBlockAndMetadataWithNotify(x, y + 1, z, top_block_id, meta & DyeColor.MASK_COLOR);
		}

	}
}
