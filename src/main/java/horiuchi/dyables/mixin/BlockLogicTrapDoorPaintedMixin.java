package horiuchi.dyables.mixin;

import horiuchi.dyables.DyablesBlocks;
import net.minecraft.core.block.*;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.material.Materials;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.DyeColor;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockLogicTrapDoorPainted.class)
public class BlockLogicTrapDoorPaintedMixin extends BlockLogicTrapDoor implements IPainted {
	public BlockLogicTrapDoorPaintedMixin(@NotNull Block<?> block, @NotNull Material material) {
		super(block, material);
	}

	@Override
	public @NotNull String getLanguageKey(int meta) {
		return super.getLanguageKey(meta) + "." + this.fromMetadata(meta).colorID;
	}

	public ItemStack[] getBreakResult(@NotNull World world, @NotNull EnumDropCause dropCause, int data, @Nullable TileEntity tileEntity) {
		return new ItemStack[]{new ItemStack(this.material == Materials.WOOD ? Blocks.TRAPDOOR_PLANKS_PAINTED : DyablesBlocks.GLASS_TRAPDOOR_PAINTED, 1, (data >> BlockLogicTrapDoor.MASK_OPEN & DyeColor.MASK_COLOR) << BlockLogicTrapDoor.MASK_OPEN)};
	}

	public @NotNull DyeColor fromMetadata(int meta) {
		return DyeColor.colorFromBlockMeta(meta >> BlockLogicTrapDoor.MASK_OPEN & DyeColor.MASK_COLOR);
	}

	public int toMetadata(@NotNull DyeColor color) {
		return color.blockMeta << BlockLogicTrapDoor.MASK_OPEN;
	}

	public int stripColorFromMetadata(int meta) {
		return meta & DyeColor.MASK_COLOR;
	}

	public void removeDye(World world, @NotNull TilePosc tilePos) {
		int meta = world.getBlockData(tilePos);
		world.setBlockTypeDataNotify(tilePos, this.material == Materials.WOOD ? Blocks.TRAPDOOR_PLANKS_OAK : Blocks.TRAPDOOR_GLASS, meta & DyeColor.MASK_COLOR);
	}
}
