package horiuchi.dyables.mixin;

import horiuchi.dyables.DyablesBlocks;
import horiuchi.dyables.DyablesItems;
import net.minecraft.core.block.BlockLogicTrapDoor;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.player.inventory.CreativeMenuContents;
import net.minecraft.core.util.helper.DyeColor;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(CreativeMenuContents.class)
public class CreativeMenuContentsMixin {
	@Redirect(method = "addPlaceables(Ljava/util/List;)V", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z"))
	private static boolean a(List<ItemStack> instance, Object e) {
		if (e instanceof ItemStack stack) {
			if(stack.getItem() == Items.BED || stack.getItem() == Items.SEAT) {
				return false;
			}
		}
		return instance.add((ItemStack) e);
	}

	@Inject(method = "addPainted", at = @At("TAIL"))
	private static void addDyablesItems(@NotNull List<ItemStack> list, DyeColor color, CallbackInfo ci) {
		list.add(new ItemStack(DyablesBlocks.BOOKSHELF_PLANKS_OAK_PAINTED, 1, color.blockMeta));
		list.add(new ItemStack(DyablesBlocks.WORKBENCH_PAINTED, 1, color.blockMeta));
		list.add(new ItemStack(DyablesBlocks.GLASS_PAINTED, 1, color.blockMeta));
		list.add(new ItemStack(DyablesBlocks.GLASS_TRAPDOOR_PAINTED, 1, color.blockMeta << BlockLogicTrapDoor.MASK_OPEN));
		list.add(new ItemStack(DyablesItems.DOOR_GLASS_PAINTED, 1, color.itemMeta));
		list.add(new ItemStack(Items.BED, 1, color.itemMeta));
		list.add(new ItemStack(Items.SEAT, 1, color.itemMeta));
	}
}
