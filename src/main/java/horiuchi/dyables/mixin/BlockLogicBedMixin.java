package horiuchi.dyables.mixin;

import net.minecraft.core.block.*;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Materials;
import net.minecraft.core.block.support.FullSupport;
import net.minecraft.core.block.support.ISupport;
import net.minecraft.core.block.support.ISupportable;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.util.PackedField;
import net.minecraft.core.util.helper.DyeColor;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePos;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(BlockLogicBed.class)
public class BlockLogicBedMixin extends BlockLogic implements ISupportable, IPainted {
	@Unique
	private static final PackedField COLOR = new PackedField(4, 4);

	public BlockLogicBedMixin(@NotNull Block<?> block) {
		super(block, Materials.WOOD);
	}

	@Shadow
	public static @Nullable TilePos otherHalf(@NotNull World world, @NotNull TilePosc tilePos, int data) { throw new AssertionError(); }

	public @NotNull ISupport getSupportConstraint(@NotNull World world, @NotNull TilePosc tilePos, @NotNull Side side) {
		return FullSupport.INSTANCE;
	}

	@Override
	public @NotNull String getLanguageKey(int meta) {
		return super.getLanguageKey(meta) + "." + this.fromMetadata(meta).colorID;
	}

	public ItemStack[] getBreakResult(@NotNull World world, @NotNull EnumDropCause dropCause, int data, @Nullable TileEntity tileEntity) {
		return new ItemStack[]{new ItemStack(Items.BED, 1, fromMetadata(data).itemMeta)};
	}

	public @NotNull DyeColor fromMetadata(int meta) {
		return DyeColor.colorFromBlockMeta(COLOR.get(meta));
	}

	public int toMetadata(DyeColor color) {
		return (color.blockMeta & DyeColor.MASK_COLOR) << COLOR.mask;
	}

	public int stripColorFromMetadata(int meta) {
		return meta & DyeColor.MASK_COLOR;
	}

	public void setColor(@NotNull World world, @NotNull TilePosc tilePos, @NotNull DyeColor color) {
		int data = world.getBlockData(tilePos);
		TilePosc otherHalfPos = otherHalf(world, tilePos, data);
		int otherHalfData = world.getBlockData(otherHalfPos);
		world.setBlockDataNotify(tilePos, COLOR.set(data, color.blockMeta));
		world.setBlockDataNotify(otherHalfPos, COLOR.set(otherHalfData, color.blockMeta));
	}

	public void removeDye(@NotNull World world, @NotNull TilePosc tilePos) {
		setColor(world, tilePos, DyeColor.WHITE);
	}
}
