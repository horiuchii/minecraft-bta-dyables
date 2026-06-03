package horiuchi.dyables;

import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemDoorPainted;
import turniplabs.halplibe.helper.ItemBuilder;

import static horiuchi.dyables.DyablesMod.MOD_ID;

public final class DyablesItems {
	private static int ITEM_IDS_START = 18500;

	public static Item DOOR_GLASS_PAINTED;
	public static Item SPONGE_BRUSH;

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
	}
}
