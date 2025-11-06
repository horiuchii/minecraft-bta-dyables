package horiuchi.dyables.mixin;

import net.minecraft.core.block.*;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.entity.TileEntitySeat;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.util.helper.DyeColor;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockLogicSeat.class)
public abstract class BlockLogicSeatMixin extends BlockLogic implements IPainted {
	public BlockLogicSeatMixin(Block<?> block, Material material) {
		super(block, Material.wood);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5625F, 1.0F);
		block.withEntity(() -> new TileEntitySeat(block));
	}

	public int getPlacedBlockMetadata(@Nullable Player player, ItemStack stack, World world, int x, int y, int z, Side side, double xPlaced, double yPlaced) {
		return stack.getMetadata() & DyeColor.MASK_COLOR;
	}

	public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int meta, TileEntity tileEntity) {
		return dropCause != EnumDropCause.IMPROPER_TOOL ? new ItemStack[]{new ItemStack(Items.SEAT, 1, meta)} : null;
	}

	@Override
	public String getLanguageKey(int meta) {
		return super.getLanguageKey(meta) + "." + this.fromMetadata(meta).colorID;
	}

	public DyeColor fromMetadata(int meta) {
		return DyeColor.colorFromBlockMeta(meta);
	}

	public int toMetadata(DyeColor color) {
		return color.blockMeta;
	}

	public int stripColorFromMetadata(int meta) {
		return 0;
	}

	public void removeDye(World world, int x, int y, int z) {
		world.setBlockMetadataWithNotify(x, y, z, 0);
	}
}
