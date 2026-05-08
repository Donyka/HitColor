package eu.donyka.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import eu.donyka.listener.OverlayRendered;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LivingEntityRenderer.class)
public abstract class MixinLivingEntityRenderer<S extends LivingEntityRenderState, M extends EntityModel<? super S>> {
    @Redirect(
            method = "submit(Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/renderer/state/level/CameraRenderState;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/entity/layers/RenderLayer;submit(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/client/renderer/entity/state/EntityRenderState;FF)V"
            )
    )
    @SuppressWarnings({"rawtypes", "unchecked"})
    private void hitcolor$submitFeatureWithOverlay(
            RenderLayer feature,
            PoseStack matrices,
            SubmitNodeCollector queue,
            int light,
            EntityRenderState state,
            float yaw,
            float pitch
    ) {
        if (feature instanceof OverlayRendered overlayRendered && state instanceof LivingEntityRenderState livingState) {
            int overlay = LivingEntityRenderer.getOverlayCoords(livingState, 0.0F);
            overlayRendered.hitcolor$submitWithOverlay(matrices, queue, light, state, yaw, pitch, overlay);
            return;
        }

        feature.submit(matrices, queue, light, state, yaw, pitch);
    }
}
