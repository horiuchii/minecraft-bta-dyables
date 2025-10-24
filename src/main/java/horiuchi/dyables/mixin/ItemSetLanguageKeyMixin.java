package horiuchi.dyables.mixin;

import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.DyeColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemSetLanguageKeyMixin {
	@Shadow
	private String key;

	// This feels like a shitty way to go about it, but whatever.
	@Inject(method = "getLanguageKey", at = @At("HEAD"), cancellable = true, remap = false)
	public void replaceLanguageKey(ItemStack itemstack, CallbackInfoReturnable<String> cir) {
		String newKey = null;

		if (this.key.equals("item.seat"))
			newKey = "tile.seat.";
		else if (this.key.equals("item.bed"))
			newKey = "tile.bed.";

		if (newKey != null)
			cir.setReturnValue(newKey + DyeColor.colorFromBlockMeta(itemstack.getMetadata() & DyeColor.MASK_COLOR).colorID);
	}
}
