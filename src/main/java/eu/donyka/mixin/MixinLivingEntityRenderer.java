package eu.donyka.mixin;

import eu.donyka.listener.OverlayRendered;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LivingEntityRenderer.class)
public abstract class MixinLivingEntityRenderer<T extends LivingEntity, M extends EntityModel<T>> {
    @Redirect(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/entity/feature/FeatureRenderer;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/entity/Entity;FFFFFF)V"
            )
    )
    private void hitcolor$renderFeatureWithOverlay(
            FeatureRenderer<T, M> feature,
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light,
            Entity entity,
            float limbAngle,
            float limbDistance,
            float tickDelta,
            float animationProgress,
            float headYaw,
            float headPitch
    ) {
        T livingEntity = (T) entity;
        if (feature instanceof OverlayRendered) {
            int overlay = LivingEntityRenderer.getOverlay(livingEntity, tickDelta);
            ((OverlayRendered<T>) feature).hitcolor$renderWithOverlay(
                    matrices, vertexConsumers, light, livingEntity, limbAngle, limbDistance, tickDelta, animationProgress, headYaw, headPitch, overlay
            );
            return;
        }

        feature.render(matrices, vertexConsumers, light, livingEntity, limbAngle, limbDistance, tickDelta, animationProgress, headYaw, headPitch);
    }
}
