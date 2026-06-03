package horiuchi.dyables.blocklogic;

import net.minecraft.core.block.*;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Materials;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.DyeColor;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BlockLogicGlassPainted extends BlockLogicTransparent implements IPainted {
	public BlockLogicGlassPainted(@NotNull Block<?> block) { super(block, Materials.GLASS); }

	@Override
	public @NotNull String getLanguageKey(int meta) {
		return super.getLanguageKey(meta) + "." + this.fromMetadata(meta).colorID;
	}

	public int getPlacedData(@Nullable Player player, @NotNull ItemStack itemStack, @NotNull World world, @NotNull TilePosc tilePos, @NotNull Side side, double xHit, double yHit) {
		return itemStack.getMetadata();
	}

	@Override
	public ItemStack[] getBreakResult(@NotNull World world, @NotNull EnumDropCause dropCause, int data, @Nullable TileEntity tileEntity) {

		return switch (dropCause) {
			case PICK_BLOCK, SILK_TOUCH -> new ItemStack[]{new ItemStack(this, 1, data)};
			default -> null;
		};
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

	public void removeDye(@NotNull World world, @NotNull TilePosc tilePos) {
		world.setBlockTypeNotify(tilePos, Blocks.GLASS);
	}

	@Override
	public boolean isSolidRender() { return false; }
}
