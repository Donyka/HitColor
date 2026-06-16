package eu.donyka.listener;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;

public interface OverlayRendered<T> {
    void hitcolor$renderWithOverlay(
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light,
            T state,
            float yaw,
            float pitch,
            int overlay
    );
}
