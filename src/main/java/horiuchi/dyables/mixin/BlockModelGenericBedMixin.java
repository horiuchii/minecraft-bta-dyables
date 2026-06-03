package horiuchi.dyables.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.block.model.generic.BlockModelGenericBed;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicBed;
import net.minecraft.core.util.helper.DyeColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.useless.dragonfly.models.block.StaticBlockModel;

@Environment(EnvType.CLIENT)
@Mixin(BlockModelGenericBed.class)
public class BlockModelGenericBedMixin {
	@Unique
	private StaticBlockModel[] headModels = new StaticBlockModel[DyeColor.COLOR_AMOUNT];
	@Unique
	private StaticBlockModel[] footModels = new StaticBlockModel[DyeColor.COLOR_AMOUNT];

	@Inject(method = "<init>", at = @At("TAIL"))
	public void initColoredBedModels(Block<?> block, CallbackInfo ci) {
		for(DyeColor color : DyeColor.blockOrderedColors()) {
			this.headModels[color.blockMeta] = BlockModelDispatcher.loadDataModel(color == DyeColor.RED ? "minecraft:block/bed/head" : "dyables:block/bed/%s/head".formatted(color.colorID)).asModel();
			this.footModels[color.blockMeta] = BlockModelDispatcher.loadDataModel(color == DyeColor.RED ? "minecraft:block/bed/foot" : "dyables:block/bed/%s/foot".formatted(color.colorID)).asModel();
		}
	}

	@Inject(method = "getModelFromData", at = @At("HEAD"), cancellable = true)
	public void injectColoredBedModel(int data, CallbackInfoReturnable<StaticBlockModel> cir) {
		cir.cancel();
		int colorIndex = (data & 0b11110000) >> 4;
		cir.setReturnValue(BlockLogicBed.IS_HEAD.bool(data) ? this.headModels[colorIndex] : this.footModels[colorIndex]);
	}
}
