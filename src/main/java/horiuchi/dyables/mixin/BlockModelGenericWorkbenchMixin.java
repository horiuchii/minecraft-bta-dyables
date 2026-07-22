package horiuchi.dyables.mixin;

import net.minecraft.client.render.block.model.generic.BlockModelGenericWorkbench;
import net.minecraft.core.block.BlockLogicRotatable;
import net.minecraft.core.util.helper.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BlockModelGenericWorkbench.class)
public abstract class BlockModelGenericWorkbenchMixin {
	@Redirect(method = "renderAttached", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/block/BlockLogicRotatable;getDirectionFromMeta(I)Lnet/minecraft/core/util/helper/Direction;"))
	private Direction redirectToUpperBits(int meta) {
		return BlockLogicRotatable.getDirectionFromMeta((meta & 0b01110000) >> 4);
	}
}
