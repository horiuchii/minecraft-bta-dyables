package horiuchi.dyables;

import horiuchi.dyables.blocks.BlockLogicPaintedBlock;
import horiuchi.dyables.blocks.BlockLogicPaintedGlass;
import horiuchi.dyables.blocks.BlockLogicPaintedWorkbench;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicDoorPainted;
import net.minecraft.core.block.BlockLogicTrapDoorPainted;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.sound.BlockSounds;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.util.BlockInitEntrypoint;

import static horiuchi.dyables.DyablesMod.MOD_ID;

public class DyablesBlocks implements BlockInitEntrypoint {

	private static int BLOCK_IDS_START = 2100;

	public static Block<BlockLogicPaintedBlock> BOOKSHELF_PLANKS_OAK_PAINTED;
	public static Block<BlockLogicPaintedGlass> GLASS_PAINTED;
	public static Block<BlockLogicTrapDoorPainted> GLASS_TRAPDOOR_PAINTED;
	public static Block<BlockLogicDoorPainted> GLASS_DOOR_PAINTED_BOTTOM;
	public static Block<BlockLogicDoorPainted> GLASS_DOOR_PAINTED_TOP;
	public static Block<BlockLogicPaintedWorkbench> WORKBENCH_PAINTED;

	private static boolean hasInit = false;

	public static void init() {
		if (hasInit)
			return;

		hasInit = true;
		initializeItems();
	}

	public static void initializeItems() {
		BOOKSHELF_PLANKS_OAK_PAINTED = new BlockBuilder(MOD_ID)
			.setBlockSound(BlockSounds.WOOD)
			.setHardness(2.0f)
			.setResistance(5.0f)
			.setFlammability(20, 5)
			.setTags(BlockTags.MINEABLE_BY_AXE, BlockTags.FENCES_CONNECT)
			.build("bookshelf.planks.oak.painted", "bookshelf_planks_oak_painted", BLOCK_IDS_START++, b -> new BlockLogicPaintedBlock(b, Material.wood, () -> Blocks.BOOKSHELF_PLANKS_OAK));
		GLASS_PAINTED = new BlockBuilder(MOD_ID)
			.setBlockSound(BlockSounds.GLASS)
			.setHardness(0.3f)
			.setTags(BlockTags.MINEABLE_BY_PICKAXE, BlockTags.EXTENDS_MOTION_SENSOR_RANGE)
			.build("glass.painted", "glass_painted", BLOCK_IDS_START++, b -> new BlockLogicPaintedGlass(b, Material.glass, () -> Blocks.GLASS));
		GLASS_TRAPDOOR_PAINTED = new BlockBuilder(MOD_ID)
			.setBlockSound(BlockSounds.GLASS)
			.setHardness(0.3f)
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.build("trapdoor.glass.painted", "trapdoor_glass_painted", BLOCK_IDS_START++, b -> new BlockLogicTrapDoorPainted(b, Material.glass));
		GLASS_DOOR_PAINTED_BOTTOM = new BlockBuilder(MOD_ID)
			.setBlockSound(BlockSounds.GLASS)
			.setHardness(0.3f)
			.setTags(BlockTags.NOT_IN_CREATIVE_MENU, BlockTags.MINEABLE_BY_PICKAXE)
			.setVisualUpdateOnMetadata()
			.<BlockLogicDoorPainted>build("door.glass.bottom.painted", "door_glass_bottom_painted", BLOCK_IDS_START++, b -> new BlockLogicDoorPainted(b, Material.glass, false))
			.setStatParent(() -> DyablesItems.DOOR_GLASS_PAINTED);
		GLASS_DOOR_PAINTED_TOP = new BlockBuilder(MOD_ID)
			.setBlockSound(BlockSounds.GLASS)
			.setHardness(0.3f)
			.setTags(BlockTags.NOT_IN_CREATIVE_MENU, BlockTags.MINEABLE_BY_PICKAXE)
			.setVisualUpdateOnMetadata()
			.<BlockLogicDoorPainted>build("door.glass.top.painted", "door_glass_top_painted", BLOCK_IDS_START++, b -> new BlockLogicDoorPainted(b, Material.glass, true))
			.setStatParent(() -> DyablesItems.DOOR_GLASS_PAINTED);
		WORKBENCH_PAINTED = new BlockBuilder(MOD_ID)
			.setBlockSound(BlockSounds.WOOD)
			.setHardness(2.5f)
			.setTags(BlockTags.FENCES_CONNECT, BlockTags.MINEABLE_BY_AXE)
			.build("workbench.painted", "workbench_painted", BLOCK_IDS_START++, b -> new BlockLogicPaintedWorkbench(b, Material.wood, () -> Blocks.WORKBENCH));
	}

	@Override
	public void afterBlockInit() {
		init();
	}
}
