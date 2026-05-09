package eu.donyka.mixin;

import eu.donyka.listener.OverlayRendered;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LivingEntityRenderer.class)
public abstract class MixinLivingEntityRenderer<S extends LivingEntityRenderState, M extends EntityModel<? super S>> {
    @Redirect(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/entity/feature/FeatureRenderer;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/command/OrderedRenderCommandQueue;ILnet/minecraft/client/render/entity/state/EntityRenderState;FF)V"
            )
    )
    @SuppressWarnings({"rawtypes", "unchecked"})
    private void hitcolor$renderFeatureWithOverlay(
            FeatureRenderer feature,
            MatrixStack matrices,
            OrderedRenderCommandQueue queue,
            int light,
            EntityRenderState state,
            float yaw,
            float pitch
    ) {
        if (feature instanceof OverlayRendered overlayRendered && state instanceof LivingEntityRenderState livingState) {
            int overlay = LivingEntityRenderer.getOverlay(livingState, 0.0F);
            overlayRendered.hitcolor$renderWithOverlay(matrices, queue, light, state, yaw, pitch, overlay);
            return;
        }

        feature.render(matrices, queue, light, state, yaw, pitch);
    }
}
