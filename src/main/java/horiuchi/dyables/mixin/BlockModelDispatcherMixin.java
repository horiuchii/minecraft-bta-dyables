package horiuchi.dyables.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.block.model.BlockModel;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.util.dispatch.Dispatcher;
import net.minecraft.core.block.Block;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Environment(EnvType.CLIENT)
@Mixin(BlockModelDispatcher.class)
public abstract class BlockModelDispatcherMixin extends Dispatcher<@NotNull Block<?>, @NotNull BlockModel<?>> {
	//Bypass the exception that occurs when trying to assign a dispatch that already exists.
	public void addDispatch(@NotNull Block<?> key, @NotNull BlockModel<?> value) {
		this.dispatches.put(key, value);
	}
}
