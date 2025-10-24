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
public class ItemModelLeatherArmorPainted extends ItemModelStandard {
	public static IconCoordinate[] armorIcons;
	private final int armorType;
	private final DyeColor dyeColor;

	public ItemModelLeatherArmorPainted(Item item, int armorType, DyeColor dyeColor) {
		super(item, MOD_ID);
		this.armorType = armorType;
		this.dyeColor = dyeColor;
	}

	public @NotNull IconCoordinate getIcon(Entity entity, ItemStack itemStack) {
		return armorIcons[armorType];
	}

	@Override
	public int getColor(ItemStack stack) {
		return dyeColor.color.getARGB();
	}

	static {
		armorIcons = new IconCoordinate[4];
		armorIcons[0] = TextureRegistry.getTexture(MOD_ID + ":item/armor_boots_leather_painted");
		armorIcons[1] = TextureRegistry.getTexture(MOD_ID + ":item/armor_leggings_leather_painted");
		armorIcons[2] = TextureRegistry.getTexture(MOD_ID + ":item/armor_chestplate_leather_painted");
		armorIcons[3] = TextureRegistry.getTexture(MOD_ID + ":item/armor_helmet_leather_painted");
	}
}
