package horiuchi.dyables.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.block.model.generic.BlockModelGeneric;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.util.helper.DyeColor;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Unique;
import org.useless.dragonfly.models.block.StaticBlockModel;

import static horiuchi.dyables.DyablesMod.MOD_ID;

@Environment(EnvType.CLIENT)
public class BlockModelGenericSeat<T extends BlockLogic> extends BlockModelGeneric<T> {
	@Unique
	private StaticBlockModel[] models = new StaticBlockModel[DyeColor.COLOR_AMOUNT];

	public BlockModelGenericSeat(Block<T> block) {
		super(block, BlockModelDispatcher.loadDataModel("minecraft:block/seat"));

		for(DyeColor color : DyeColor.blockOrderedColors()) {
			this.models[color.blockMeta] = BlockModelDispatcher.loadDataModel(color == DyeColor.RED ? "minecraft:block/seat" : MOD_ID + ":block/seat/%s".formatted(color.colorID)).asModel();
		}
	}

	public @NotNull StaticBlockModel getModelFromData(int data) {
		return models[data & DyeColor.MASK_COLOR];
	}
}
