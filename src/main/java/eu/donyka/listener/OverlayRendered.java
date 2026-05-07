package eu.donyka.listener;

import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.util.math.MatrixStack;

public interface OverlayRendered<T> {
    void hitcolor$renderWithOverlay(
            MatrixStack matrices,
            OrderedRenderCommandQueue queue,
            int light,
            T state,
            float yaw,
            float pitch,
            int overlay
    );
}
