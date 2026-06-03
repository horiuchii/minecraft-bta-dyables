package horiuchi.dyables.model;

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
public class ItemModelSeatBed extends ItemModelStandard {
	public IconCoordinate[] icons = new IconCoordinate[DyeColor.COLOR_AMOUNT];

	public ItemModelSeatBed(Item item, String rootKey) {
		super(item, false);
		for (DyeColor c : DyeColor.itemOrderedColors()) {
			icons[c.itemMeta] = TextureRegistry.getTexture(c == DyeColor.RED ? "minecraft:item/" + rootKey : MOD_ID + ":item/" + rootKey + "_" + c.colorID);
		}
	}

	public @NotNull IconCoordinate getIcon(Entity entity, @NotNull ItemStack itemStack) {
		int meta = itemStack.getMetadata();
		return icons[meta & DyeColor.MASK_COLOR];
	}
}
