package horiuchi.dyables.blocklogic;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicWorkbench;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.IPainted;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.DyeColor;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BlockLogicWorkbenchPainted extends BlockLogicWorkbench implements IPainted {
	public BlockLogicWorkbenchPainted(@NotNull Block<?> block) {
		super(block);
	}

	@Override
	public @NotNull String getLanguageKey(int meta) {
		return super.getLanguageKey(meta) + "." + this.fromMetadata(meta).colorID;
	}

	public int getPlacedData(@Nullable Player player, @NotNull ItemStack itemStack, @NotNull World world, @NotNull TilePosc tilePos, @NotNull Side side, double xHit, double yHit) {
		return itemStack.getMetadata();
	}

	public ItemStack[] getBreakResult(@NotNull World world, @NotNull EnumDropCause dropCause, int data, @Nullable TileEntity tileEntity) {
		return new ItemStack[]{new ItemStack(this, 1, data & DyeColor.MASK_COLOR)};
	}

	public @NotNull DyeColor fromMetadata(int meta) {
		return DyeColor.colorFromBlockMeta(meta & DyeColor.MASK_COLOR);
	}

	public int toMetadata(@NotNull DyeColor color) {
		return color.blockMeta;
	}

	public int stripColorFromMetadata(int meta) {
		return 0;
	}

	public void setColor(@NotNull World world, @NotNull TilePosc tilePos, @NotNull DyeColor color) {
		int data = world.getBlockData(tilePos);
		world.setBlockDataNotify(tilePos, data & ~DyeColor.MASK_COLOR | color.blockMeta & DyeColor.MASK_COLOR);
	}

	public void removeDye(@NotNull World world, @NotNull TilePosc tilePos) {
		int data = world.getBlockData(tilePos);
		world.setBlockTypeDataNotify(tilePos, Blocks.WORKBENCH, data & ~DyeColor.MASK_COLOR);
	}
}
