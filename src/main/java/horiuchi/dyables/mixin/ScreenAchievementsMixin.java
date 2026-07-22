package horiuchi.dyables.mixin;

import net.minecraft.client.gui.achievements.ScreenAchievements;
import net.minecraft.core.achievement.Achievement;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.util.helper.DyeColor;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ScreenAchievements.class)
public class ScreenAchievementsMixin {
	@Redirect(
		method = "drawAchievementIcons(IIDD)Lnet/minecraft/core/achievement/Achievement;",
		at = @At(value = "FIELD", target = "Lnet/minecraft/core/achievement/Achievement;iconStack:Lnet/minecraft/core/item/ItemStack;",
		opcode = Opcodes.GETFIELD)
	)
	private ItemStack replaceItemWithOriginalTexture(Achievement achievement) {
		ItemStack achievementItem = achievement.iconStack;
		if (achievementItem.itemID == Items.BED.id || achievementItem.itemID == Items.SEAT.id) {
			achievementItem.setMetadata(DyeColor.RED.itemMeta);
		}
		return achievementItem;
	}
}
