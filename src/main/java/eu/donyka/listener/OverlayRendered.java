package eu.donyka.listener;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;

public interface OverlayRendered<T> {
    void hitcolor$submitWithOverlay(
            PoseStack matrices,
            SubmitNodeCollector queue,
            int light,
            T state,
            float yaw,
            float pitch,
            int overlay
    );
}
