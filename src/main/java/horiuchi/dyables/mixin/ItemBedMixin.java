package horiuchi.dyables.mixin;

import net.minecraft.core.block.Block;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemBed;
import net.minecraft.core.item.ItemPlaceablePair;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.collection.NamespaceID;
import net.minecraft.core.util.helper.DyeColor;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemBed.class)
public abstract class ItemBedMixin extends ItemPlaceablePair {
	public ItemBedMixin(@NotNull NamespaceID namespaceId, @NotNull String translationKey, int id, @NotNull Block<?> blockA, @NotNull Block<?> blockB) {
		super(namespaceId, translationKey, id, blockA, blockB);
	}

	public @NotNull String getLanguageKey(@NotNull ItemStack selfStack) {
		return super.getKey() + "." + DyeColor.colorFromItemMeta(selfStack.getMetadata()).colorID;
	}

	@Inject(method = "getPlacement", at = @At("RETURN"))
	public void setBedColor(ItemStack selfStack, World world, Player player, TilePosc blockPos, Side side, double xHit, double yHit, ItemPlaceablePair.PlacementResult result, CallbackInfoReturnable<ItemPlaceablePair.PlacementResult> cir) {
		result.dataA |= DyeColor.colorFromItemMeta(selfStack.getMetadata()).blockMeta << 4 & 0b11110000;
		result.dataB |= DyeColor.colorFromItemMeta(selfStack.getMetadata()).blockMeta << 4 & 0b11110000;
	}
}
