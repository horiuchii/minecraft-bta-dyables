package horiuchi.dyables;

import horiuchi.dyables.blocklogic.BlockLogicBookshelfPainted;
import horiuchi.dyables.blocklogic.BlockLogicGlassPainted;
import horiuchi.dyables.blocklogic.BlockLogicWorkbenchPainted;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicDoorPainted;
import net.minecraft.core.block.BlockLogicTrapDoorPainted;
import net.minecraft.core.block.material.Materials;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.sound.BlockSounds;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.util.BlockInitEntrypoint;

import static horiuchi.dyables.DyablesMod.MOD_ID;

public class DyablesBlocks implements BlockInitEntrypoint {
	private static int BLOCK_IDS_START = 2100;

	public static Block<BlockLogicBookshelfPainted> BOOKSHELF_PLANKS_OAK_PAINTED;
	public static Block<BlockLogicGlassPainted> GLASS_PAINTED;
	public static Block<BlockLogicTrapDoorPainted> GLASS_TRAPDOOR_PAINTED;
	public static Block<BlockLogicDoorPainted> GLASS_DOOR_PAINTED_BOTTOM;
	public static Block<BlockLogicDoorPainted> GLASS_DOOR_PAINTED_TOP;
	public static Block<BlockLogicWorkbenchPainted> WORKBENCH_PAINTED;

	private static boolean hasInit = false;

	@Override
	public void afterBlockInit() {
		init();
	}

	public static void init() {
		if (hasInit)
			return;

		hasInit = true;
		initializeBlocks();
	}

	public static void initializeBlocks() {
		BOOKSHELF_PLANKS_OAK_PAINTED = new BlockBuilder(MOD_ID)
			.setBlockSound(BlockSounds.WOOD)
			.setHardness(1.5F)
			.setTags(BlockTags.FENCES_CONNECT, BlockTags.MINEABLE_BY_AXE)
			.build("bookshelf.planks.oak.painted", "bookshelf_planks_oak_painted", BLOCK_IDS_START++, BlockLogicBookshelfPainted::new);
		GLASS_PAINTED = new BlockBuilder(MOD_ID)
			.setBlockSound(BlockSounds.GLASS)
			.setHardness(0.3F)
			.setTags(BlockTags.MINEABLE_BY_PICKAXE, BlockTags.EXTENDS_MOTION_SENSOR_RANGE)
			.build("glass.painted", "glass_painted", BLOCK_IDS_START++, BlockLogicGlassPainted::new);
		GLASS_TRAPDOOR_PAINTED = new BlockBuilder(MOD_ID)
			.setBlockSound(BlockSounds.GLASS)
			.setHardness(0.3F)
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.build("trapdoor.glass.painted", "trapdoor_glass_painted", BLOCK_IDS_START++, b -> new BlockLogicTrapDoorPainted(b, Materials.GLASS));
		GLASS_DOOR_PAINTED_BOTTOM = new BlockBuilder(MOD_ID)
			.setBlockSound(BlockSounds.GLASS)
			.setHardness(0.3F)
			.setStatParent(() -> DyablesItems.DOOR_GLASS_PAINTED)
			.setTags(BlockTags.NOT_IN_CREATIVE_MENU, BlockTags.MINEABLE_BY_PICKAXE)
			.build("door.glass.bottom.painted", "door_glass_bottom_painted", BLOCK_IDS_START++, b -> new BlockLogicDoorPainted(b, Materials.GLASS, false));
		GLASS_DOOR_PAINTED_TOP = new BlockBuilder(MOD_ID)
			.setBlockSound(BlockSounds.GLASS)
			.setHardness(0.3F)
			.setStatParent(() -> DyablesItems.DOOR_GLASS_PAINTED)
			.setTags(BlockTags.NOT_IN_CREATIVE_MENU, BlockTags.MINEABLE_BY_PICKAXE)
			.build("door.glass.top.painted", "door_glass_top_painted", BLOCK_IDS_START++, b -> new BlockLogicDoorPainted(b, Materials.GLASS, true));
		WORKBENCH_PAINTED = new BlockBuilder(MOD_ID)
			.setBlockSound(BlockSounds.WOOD)
			.setHardness(2.5F)
			.setTags(BlockTags.FENCES_CONNECT, BlockTags.MINEABLE_BY_AXE)
			.build("workbench.painted", "block/workbench_painted", BLOCK_IDS_START++, BlockLogicWorkbenchPainted::new);
	}
}
