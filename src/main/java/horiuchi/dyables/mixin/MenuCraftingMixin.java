package horiuchi.dyables.mixin;

import horiuchi.dyables.DyablesBlocks;
import net.minecraft.core.InventoryAction;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.player.inventory.menu.MenuAbstract;
import net.minecraft.core.player.inventory.menu.MenuCrafting;
import net.minecraft.core.player.inventory.slot.Slot;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(MenuCrafting.class)
public class MenuCraftingMixin extends MenuAbstract {

	@Shadow
	private World world;
	@Shadow
	private int x;
	@Shadow
	private int y;
	@Shadow
	private int z;

	@Override
	public boolean stillValid(Player entityplayer) {
		int blockId = this.world.getBlockId(this.x, this.y, this.z);
		if (blockId != Blocks.WORKBENCH.id() && blockId != DyablesBlocks.WORKBENCH_PAINTED.id()) {
			return false;
		} else {
			return entityplayer.distanceToSqr((double)this.x + (double)0.5F, (double)this.y + (double)0.5F, (double)this.z + (double)0.5F) <= (double)64.0F;
		}
	}

	//Two functions below are the same as vanilla.

	@Override
	public List<Integer> getMoveSlots(InventoryAction action, Slot slot, int target, Player player) {
		if (slot.index == 0) {
			return this.getSlots(0, 1, false);
		} else if (slot.index >= 1 && slot.index < 9) {
			return this.getSlots(1, 9, false);
		} else {
			if (action == InventoryAction.MOVE_SIMILAR) {
				if (slot.index >= 10 && slot.index <= 45) {
					return this.getSlots(10, 36, false);
				}
			} else {
				if (slot.index >= 10 && slot.index <= 36) {
					return this.getSlots(10, 27, false);
				}

				if (slot.index >= 37 && slot.index <= 45) {
					return this.getSlots(37, 9, false);
				}
			}

			return null;
		}
	}

	@Override
	public List<Integer> getTargetSlots(InventoryAction action, Slot slot, int target, Player player) {
		if (slot.index >= 10 && slot.index <= 45) {
			if (target == 1) {
				return this.getSlots(1, 9, false);
			} else if (slot.index >= 10 && slot.index <= 36) {
				return this.getSlots(37, 9, false);
			} else {
				return slot.index >= 37 && slot.index <= 45 ? this.getSlots(10, 27, false) : null;
			}
		} else {
			return slot.index == 0 ? this.getSlots(10, 36, true) : this.getSlots(10, 36, false);
		}
	}
}
