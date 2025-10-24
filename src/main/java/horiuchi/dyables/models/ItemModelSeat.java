package horiuchi.dyables.models;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.DyeColor;
import org.jetbrains.annotations.NotNull;

import static horiuchi.dyables.DyablesMod.MOD_ID;

@Environment(EnvType.CLIENT)
public class ItemModelSeat extends ItemModelStandard {
	public static IconCoordinate[] seatIcons = new IconCoordinate[DyeColor.COLOR_AMOUNT];

	public ItemModelSeat(Item item) {
		super(item, MOD_ID);
	}

	public @NotNull IconCoordinate getIcon(Entity entity, ItemStack itemStack) {
		int meta = itemStack.getMetadata();
		return seatIcons[DyeColor.colorFromBlockMeta(meta).itemMeta & DyeColor.MASK_COLOR];
	}

	static {
		for(DyeColor c : DyeColor.itemOrderedColors()) {
			seatIcons[c.itemMeta] = TextureRegistry.getTexture(c == DyeColor.RED ? "minecraft:item/seat" : MOD_ID + ":item/seat_" + c.colorID);
		}

	}
}
