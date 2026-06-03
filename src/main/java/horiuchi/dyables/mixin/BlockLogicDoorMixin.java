package horiuchi.dyables.mixin;

import horiuchi.dyables.DyablesBlocks;
import net.minecraft.core.block.*;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.material.Materials;
import net.minecraft.core.util.helper.DyeColor;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePos;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BlockLogicDoor.class)
public class BlockLogicDoorMixin extends BlockLogic implements IPaintable {
	@Shadow
	@Final
	public boolean isTop;

	public BlockLogicDoorMixin(@NotNull Block<?> block, @NotNull Material material) {
		super(block, material);
	}

	public boolean canBePainted() {
		return this.material == Materials.WOOD || this.material == Materials.GLASS;
	}

	public void setColor(@NotNull World world, @NotNull TilePosc tilePos, @NotNull DyeColor color) {
		TilePos queryPos = new TilePos();
		int data = world.getBlockData(tilePos);
		Block<?> topBlock = this.material == Materials.WOOD ? Blocks.DOOR_PLANKS_PAINTED_TOP : DyablesBlocks.GLASS_DOOR_PAINTED_TOP;
		Block<?> bottomBlock = this.material == Materials.WOOD ? Blocks.DOOR_PLANKS_PAINTED_BOTTOM : DyablesBlocks.GLASS_DOOR_PAINTED_BOTTOM;

		if (this.isTop) {
			world.setBlockTypeDataRaw(tilePos,topBlock, data);
			((BlockLogicDoorPainted)topBlock.getLogic()).setColor(world, tilePos, color);
			world.setBlockTypeDataRaw(tilePos.down(queryPos), bottomBlock, data);
			((BlockLogicDoorPainted)bottomBlock.getLogic()).setColor(world, tilePos.down(queryPos), color);
		} else {
			world.setBlockTypeDataRaw(tilePos, bottomBlock, data);
			((BlockLogicDoorPainted)bottomBlock.getLogic()).setColor(world, tilePos, color);
			world.setBlockTypeDataRaw(tilePos.up(queryPos), topBlock, data);
			((BlockLogicDoorPainted)topBlock.getLogic()).setColor(world, tilePos.up(queryPos), color);
		}

	}
}
