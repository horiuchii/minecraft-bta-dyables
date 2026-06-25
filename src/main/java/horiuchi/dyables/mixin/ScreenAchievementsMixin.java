package horiuchi.dyables.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.gui.achievements.ScreenAchievements;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.util.helper.DyeColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ScreenAchievements.class)
public class ScreenAchievementsMixin {
	@ModifyExpressionValue(
		method = "drawAchievementIcons(IIDD)Lnet/minecraft/core/achievement/Achievement;",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/core/item/Item;getDefaultStack()Lnet/minecraft/core/item/ItemStack;")
	)
	private ItemStack replaceItemWithOriginalTexture(ItemStack original) {
		if (original.itemID == Items.BED.id || original.itemID == Items.SEAT.id) {
			original.setMetadata(DyeColor.RED.itemMeta);
		}
		return original;
	}
}
