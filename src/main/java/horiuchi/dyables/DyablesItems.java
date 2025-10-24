package horiuchi.dyables;

import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemDoorPainted;
import turniplabs.halplibe.helper.ItemBuilder;

import static horiuchi.dyables.DyablesMod.MOD_ID;

public final class DyablesItems {

	private static int ITEM_IDS_START = 18500;

	public static Item DOOR_GLASS_PAINTED;
//	public static Item[] ARMOR_HELMET_LEATHER_PAINTED = new Item[DyeColor.COLOR_AMOUNT];
//	public static Item[] ARMOR_CHESTPLATE_LEATHER_PAINTED = new Item[DyeColor.COLOR_AMOUNT];
//	public static Item[] ARMOR_LEGGINGS_LEATHER_PAINTED = new Item[DyeColor.COLOR_AMOUNT];
//	public static Item[] ARMOR_BOOTS_LEATHER_PAINTED = new Item[DyeColor.COLOR_AMOUNT];

	private static boolean hasInit = false;

	public static void init() {
		if (hasInit)
			return;

		hasInit = true;
		initializeItems();
	}

	private static String formatItemId(String name) {
		return String.format("%s:item/%s", MOD_ID, name);
	}

	public static void initializeItems() {
		DOOR_GLASS_PAINTED = new ItemBuilder(MOD_ID)
			.build(new ItemDoorPainted("door.glass.painted", formatItemId("door_glass_painted"), ITEM_IDS_START++, DyablesBlocks.GLASS_DOOR_PAINTED_BOTTOM, DyablesBlocks.GLASS_DOOR_PAINTED_TOP));

//		for (DyeColor c : DyeColor.itemOrderedColors())
//		{
//			ARMOR_HELMET_LEATHER_PAINTED[c.itemMeta] = new ItemBuilder(MOD_ID)
//				.build(new ItemArmor("armor.helmet.leather." + c.colorID, formatItemId("armor_helmet_leather_" + c.colorID), ITEM_IDS_START++, ArmorMaterial.LEATHER, IArmorItem.PIECE_HEAD));
//			ARMOR_CHESTPLATE_LEATHER_PAINTED[c.itemMeta] = new ItemBuilder(MOD_ID)
//				.build(new ItemArmor("armor.chestplate.leather." + c.colorID, formatItemId("armor_chestplate_leather_" + c.colorID), ITEM_IDS_START++, ArmorMaterial.LEATHER, IArmorItem.PIECE_CHEST));
//			ARMOR_LEGGINGS_LEATHER_PAINTED[c.itemMeta] = new ItemBuilder(MOD_ID)
//				.build(new ItemArmor("armor.leggings.leather." + c.colorID, formatItemId("armor_leggings_leather_" + c.colorID), ITEM_IDS_START++, ArmorMaterial.LEATHER, IArmorItem.PIECE_LEGS));
//			ARMOR_BOOTS_LEATHER_PAINTED[c.itemMeta] = new ItemBuilder(MOD_ID)
//				.build(new ItemArmor("armor.boots.leather." + c.colorID, formatItemId("armor_boots_leather_" + c.colorID), ITEM_IDS_START++, ArmorMaterial.LEATHER, IArmorItem.PIECE_BOOTS));
//		}
	}
}
