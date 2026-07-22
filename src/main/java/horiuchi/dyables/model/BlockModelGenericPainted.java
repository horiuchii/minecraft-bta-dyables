package horiuchi.dyables.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.block.model.generic.BlockModelGeneric;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.util.helper.DyeColor;
import org.jetbrains.annotations.NotNull;
import org.useless.dragonfly.data.block.BlockModelData;
import org.useless.dragonfly.models.block.StaticBlockModel;

@Environment(EnvType.CLIENT)
public class BlockModelGenericPainted<T extends BlockLogic> extends BlockModelGeneric<T> {
	public final String rootKey;
	public final StaticBlockModel[] models = new StaticBlockModel[DyeColor.COLOR_AMOUNT];

	public BlockModelGenericPainted(@NotNull Block<T> block, @NotNull BlockModelData staticModel, @NotNull String rootKey) {
		super(block, staticModel);
		this.rootKey = rootKey;

		for(DyeColor c : DyeColor.blockOrderedColors()) {
			this.models[c.blockMeta] = BlockModelDispatcher.loadDataModel(this.rootKey + c.colorID).asModel();
		}
	}

	public @NotNull StaticBlockModel getModelFromData(int data) {
		return this.models[data & DyeColor.MASK_COLOR];
	}
}
