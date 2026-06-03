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
public class ItemModelGlassDoorPainted extends ItemModelStandard {
	public static IconCoordinate[] doorIcons = new IconCoordinate[DyeColor.COLOR_AMOUNT];

	public ItemModelGlassDoorPainted(Item item) {
		super(item, false);
	}

	public @NotNull IconCoordinate getIcon(Entity entity, @NotNull ItemStack itemStack) {
		int meta = itemStack.getMetadata();
		return doorIcons[meta & DyeColor.MASK_COLOR];
	}

	static {
		for(DyeColor c : DyeColor.itemOrderedColors()) {
			doorIcons[c.itemMeta] = TextureRegistry.getTexture(MOD_ID + ":item/door_glass_" + c.colorID);
		}

	}
}
