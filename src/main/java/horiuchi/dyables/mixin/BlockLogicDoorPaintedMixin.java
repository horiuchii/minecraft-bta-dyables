package horiuchi.dyables.mixin;

import horiuchi.dyables.DyablesBlocks;
import horiuchi.dyables.DyablesItems;
import net.minecraft.core.block.*;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.material.Materials;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.util.helper.DyeColor;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePos;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockLogicDoorPainted.class)
public class BlockLogicDoorPaintedMixin extends BlockLogicDoor implements IPainted {
	public BlockLogicDoorPaintedMixin(@NotNull Block<?> block, @NotNull Material material, boolean isTop) {
		super(block, material, isTop, false, null);
	}

	public @NotNull ItemStack @Nullable [] getBreakResult(@NotNull World world, @NotNull EnumDropCause dropCause, int data, @Nullable TileEntity tileEntity) {
		return new ItemStack[]{new ItemStack(this.material == Materials.WOOD ? Items.DOOR_OAK_PAINTED : DyablesItems.DOOR_GLASS_PAINTED, 1, DyeColor.MASK_COLOR - (data >> BlockLogicDoor.MASK_OPENED & DyeColor.MASK_COLOR))};
	}

	public @NotNull DyeColor fromMetadata(int meta) {
		return DyeColor.colorFromBlockMeta(meta >> BlockLogicDoor.MASK_OPENED & DyeColor.MASK_COLOR);
	}

	public int toMetadata(@NotNull DyeColor color) {
		return color.blockMeta << BlockLogicDoor.MASK_OPENED;
	}

	public int stripColorFromMetadata(int meta) {
		return meta & DyeColor.MASK_COLOR;
	}

	public void removeDye(@NotNull World world, @NotNull TilePosc tilePos) {
		int data = world.getBlockData(tilePos);
		Block<?> topBlock = this.material == Materials.WOOD ? Blocks.DOOR_PLANKS_PAINTED_TOP : DyablesBlocks.GLASS_DOOR_PAINTED_TOP;
		Block<?> bottomBlock = this.material == Materials.WOOD ? Blocks.DOOR_PLANKS_PAINTED_BOTTOM : DyablesBlocks.GLASS_DOOR_PAINTED_BOTTOM;

		world.setBlockTypeData(tilePos, this.isTop ? topBlock : bottomBlock, data & DyeColor.MASK_COLOR);
		if (this.isTop) {
			world.setBlockTypeDataNotify(tilePos.down(new TilePos()), bottomBlock, data & DyeColor.MASK_COLOR);
		} else {
			world.setBlockTypeDataNotify(tilePos.up(new TilePos()), topBlock, data & DyeColor.MASK_COLOR);
		}

		world.notifyBlockChange(tilePos, this.block);
	}
}
