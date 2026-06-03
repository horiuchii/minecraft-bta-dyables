package horiuchi.dyables.mixin;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemPlaceable;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.collection.NamespaceID;
import net.minecraft.core.util.helper.DyeColor;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ItemPlaceable.class)
public abstract class ItemPlaceableMixin extends Item {
	@Final
	@Shadow
	public @NotNull Block<?> block;

	public ItemPlaceableMixin(@NotNull NamespaceID namespaceId, @NotNull String translationKey, int id) {
		super(namespaceId, translationKey, id);
	}

	@Unique
	public @NotNull String getLanguageKey(@NotNull ItemStack selfStack) {
		if (block == Blocks.SEAT)
			return super.getKey() + "." + DyeColor.colorFromItemMeta(selfStack.getMetadata()).colorID;
		else
			return super.getKey();
	}
}
