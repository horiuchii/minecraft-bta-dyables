package horiuchi.dyables.mixin;

import horiuchi.dyables.DyablesBlocks;
import horiuchi.dyables.DyablesItems;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.player.inventory.menu.MenuInventoryCreative;
import net.minecraft.core.util.helper.DyeColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Comparator;
import java.util.List;

@Mixin(value = MenuInventoryCreative.class, remap = false)
public abstract class CreativeMenuMixin {
	@Shadow
	public static List<ItemStack> creativeItems;
	@Shadow
	public static int creativeItemsCount;

	@Unique
	private static int RemapCreativeId(int Id)
	{
		if (Id == DyablesBlocks.BOOKSHELF_PLANKS_OAK_PAINTED.id())
			return Blocks.BOOKSHELF_PLANKS_OAK.id();
		if (Id == DyablesBlocks.GLASS_PAINTED.id())
			return Blocks.GLASS.id();
		if (Id == DyablesBlocks.GLASS_TRAPDOOR_PAINTED.id())
			return Blocks.TRAPDOOR_GLASS.id();
		if (Id == DyablesBlocks.WORKBENCH_PAINTED.id())
			return Blocks.WORKBENCH.id();

		if (Id == DyablesItems.DOOR_GLASS_PAINTED.id)
			return Items.DOOR_GLASS.id;

		return Id;
	}

	@Unique
	private static void AddCreativeEntry(int Id, int metadata)
	{
		// Don't add duplicates, we typically already have white or black versions of blocks/items
		if (creativeItems.stream().anyMatch(ItemStack -> ItemStack.getMetadata() == metadata && ItemStack.getItem().id == Id))
			return;

		creativeItems.add(new ItemStack(Id, 1, metadata));
		creativeItemsCount++;
	}

	@Inject(method = "<clinit>()V", at = @At(value = "TAIL"))
	private static void AddDyablesItems(CallbackInfo ci)
	{
		for (DyeColor c : DyeColor.blockOrderedColors())
		{
			AddCreativeEntry(DyablesBlocks.BOOKSHELF_PLANKS_OAK_PAINTED.id(), c.blockMeta);
			AddCreativeEntry(DyablesBlocks.GLASS_PAINTED.id(), c.blockMeta);
			AddCreativeEntry(DyablesBlocks.GLASS_TRAPDOOR_PAINTED.id(),  c.blockMeta << 4);
			AddCreativeEntry(DyablesBlocks.WORKBENCH_PAINTED.id(), c.blockMeta);
			AddCreativeEntry(DyablesItems.DOOR_GLASS_PAINTED.id, c.itemMeta);
			AddCreativeEntry(Items.BED.id, c.itemMeta);
			AddCreativeEntry(Items.SEAT.id, c.itemMeta);
		}
		creativeItems.sort(Comparator.comparingInt((ItemStack a) -> RemapCreativeId(a.getItem().id)).thenComparingInt(ItemStack::getMetadata));
	}
}
