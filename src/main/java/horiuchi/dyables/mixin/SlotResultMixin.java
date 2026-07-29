package horiuchi.dyables.mixin;

import horiuchi.dyables.DyablesBlocks;
import net.minecraft.core.achievement.Achievements;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.slot.SlotResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SlotResult.class)
public abstract class SlotResultMixin {
	@Shadow
	private Player thePlayer;

	@Inject(method = "onTake", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/item/ItemStack;onCrafting(Lnet/minecraft/core/world/World;Lnet/minecraft/core/entity/player/Player;)V", shift = At.Shift.AFTER))
	private void onTakeAchievement(ItemStack itemStack, CallbackInfo ci) {
		Item item = itemStack.getItem();
		if (item.id == DyablesBlocks.WORKBENCH_PAINTED.id()) {
			this.thePlayer.addStat(Achievements.BUILD_WORKBENCH, 1);
		}
	}
}
