package horiuchi.dyables.mixin;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.BlockLogicBed;
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
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import static net.minecraft.core.block.BlockLogicBed.*;

@Mixin(BlockLogicBed.class)
public abstract class BlockLogicBedMixin extends BlockLogic implements IPainted {
	@Unique
	private static final int MASK_COLOR = 0b11110000;
	@Unique
	private static final int SHIFT_COLOR = 4;

	public BlockLogicBedMixin(Block<?> block, Material material) {
		super(block, material);
	}

	@Override
	public String getLanguageKey(int meta) {
		return super.getLanguageKey(meta) + "." + this.fromMetadata(meta).colorID;
	}

	@Unique
	protected void paintOtherHalf(World world, int x, int y, int z, DyeColor color) {
		int metadata = world.getBlockMetadata(x, y, z);
		int direction = getDirection(metadata);
		boolean footOfBed = isBlockFootOfBed(metadata);

		int otherHalfX = footOfBed ? x - headBlockToFootBlockMap[direction].getOffsetX() : x + headBlockToFootBlockMap[direction].getOffsetX();
		int otherHalfZ = footOfBed ? z - headBlockToFootBlockMap[direction].getOffsetZ() : z + headBlockToFootBlockMap[direction].getOffsetZ();

		if (world.getBlockId(otherHalfX, y, otherHalfZ) == this.block.id())
		{
			world.setBlockMetadataWithNotify(otherHalfX, y, otherHalfZ, (world.getBlockMetadata(otherHalfX, y, otherHalfZ) & ~MASK_COLOR) | ((color.blockMeta & 0x0F) << SHIFT_COLOR));
		}
	}

	public int getPlacedBlockMetadata(@Nullable Player player, ItemStack stack, World world, int x, int y, int z, Side side, double xPlaced, double yPlaced) {
		return stack.getMetadata() & DyeColor.MASK_COLOR;
	}

	public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int meta, TileEntity tileEntity) {
		return new ItemStack[]{new ItemStack(Items.BED, 1, fromMetadata(meta).blockMeta)};
	}

	public DyeColor fromMetadata(int meta) {
		return DyeColor.colorFromBlockMeta((meta >> SHIFT_COLOR) & 0x0F);
	}

	public int toMetadata(DyeColor color) { return (color.blockMeta & 0x0F) << SHIFT_COLOR; }

	public int stripColorFromMetadata(int meta) {
		return meta & DyeColor.MASK_COLOR;
	}

	public void setColor(World world, int x, int y, int z, DyeColor color) {
		int meta = world.getBlockMetadata(x, y, z);
		world.setBlockMetadataWithNotify(x, y, z, (meta & ~MASK_COLOR) | ((color.blockMeta & 0x0F) << SHIFT_COLOR));
		this.paintOtherHalf(world, x, y, z, color);
	}

	public void removeDye(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		world.setBlockMetadataWithNotify(x, y, z, meta & DyeColor.MASK_COLOR);
	}
}
