package horiuchi.dyables.mixin;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.enums.EnumBlockSoundEffectType;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemPlaceable;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ItemPlaceable.class, remap = false)
public class ItemPlaceableMixin extends Item {
	@Unique
	public Block<?> blockToPlace;

	public ItemPlaceableMixin(String name, String namespaceId, int id, Block<?> blockToPlace) {
		super(name, namespaceId, id);
		this.blockToPlace = blockToPlace;
	}

	@Inject(method = "onUseItemOnBlock", at = @At("RETURN"))
	private void replacePlacedItem(ItemStack stack, Player player, World world, int blockX, int blockY, int blockZ, Side side, double xPlaced, double yPlaced, CallbackInfoReturnable<Boolean> cir) {
		// We can place this block AND its a seat
		if(cir.getReturnValue() && stack.itemID == Items.SEAT.id)
			world.setBlockAndMetadataWithNotify(blockX, blockY, blockZ, Blocks.SEAT.id(), stack.getMetadata());

	}
}
