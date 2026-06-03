package horiuchi.dyables.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import horiuchi.dyables.DyablesBlocks;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.player.inventory.menu.MenuCrafting;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MenuCrafting.class)
public abstract class MenuCraftingMixin {
	@ModifyExpressionValue(
		method = "stillValid(Lnet/minecraft/core/entity/player/Player;)Z",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/core/world/World;getBlockType(Lnet/minecraft/core/world/pos/TilePosc;)Lnet/minecraft/core/block/Block;"
		)
	)
	private Block<?> shouldBlockReturnWorkbench(@NotNull Block<?> original) {
		return original == DyablesBlocks.WORKBENCH_PAINTED ? Blocks.WORKBENCH : original;
	}
}
