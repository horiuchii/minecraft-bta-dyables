package horiuchi.dyables.mixin;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.BlockLogicSeat;
import net.minecraft.core.block.IPainted;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.util.helper.DyeColor;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockLogicSeat.class)
public class BlockLogicSeatMixin extends BlockLogic implements IPainted {
	public BlockLogicSeatMixin(@NotNull Block<?> block, @NotNull Material material) {
		super(block, material);
	}

	@Override
	public int getPlacedData(@Nullable Player player, @NotNull ItemStack itemStack, @NotNull World world, @NotNull TilePosc tilePos, @NotNull Side side, double xHit, double yHit) {
		return DyeColor.colorFromItemMeta(itemStack.getMetadata()).blockMeta;
	}

	@Override
	public @NotNull String getLanguageKey(int meta) {
		return super.getLanguageKey(meta) + "." + this.fromMetadata(meta).colorID;
	}

	@Override
	public ItemStack[] getBreakResult(@NotNull World world, @NotNull EnumDropCause dropCause, int data, @Nullable TileEntity tileEntity) {
		return dropCause != EnumDropCause.IMPROPER_TOOL ? new ItemStack[]{new ItemStack(Items.SEAT, 1, fromMetadata(data).itemMeta)} : null;
	}

	public @NotNull DyeColor fromMetadata(int meta) {
		return DyeColor.colorFromBlockMeta(meta);
	}

	public int toMetadata(DyeColor color) {
		return color.blockMeta;
	}

	public int stripColorFromMetadata(int meta) {
		return meta & DyeColor.MASK_COLOR;
	}

	public void setColor(@NotNull World world, @NotNull TilePosc tilePos, @NotNull DyeColor color) {
		world.setBlockDataNotify(tilePos, color.blockMeta);
	}

	public void removeDye(@NotNull World world, @NotNull TilePosc tilePos) {
		setColor(world, tilePos, DyeColor.WHITE);
	}
}
