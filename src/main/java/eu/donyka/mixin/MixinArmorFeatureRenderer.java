package eu.donyka.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import eu.donyka.config.HCScreen;
import eu.donyka.listener.ArmorOverlayContext;
import eu.donyka.listener.OverlayRendered;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(HumanoidArmorLayer.class)
public abstract class MixinArmorFeatureRenderer
        extends RenderLayer<HumanoidRenderState, HumanoidModel<HumanoidRenderState>>
        implements OverlayRendered<HumanoidRenderState> {

    protected MixinArmorFeatureRenderer(RenderLayerParent<HumanoidRenderState, HumanoidModel<HumanoidRenderState>> context) {
        super(context);
    }

    @Override
    public void hitcolor$submitWithOverlay(
            PoseStack matrices,
            SubmitNodeCollector queue,
            int light,
            HumanoidRenderState state,
            float yaw,
            float pitch,
            int overlay
    ) {
        if (HCScreen.getConfig().enableArmor && state.hasRedOverlay) {
            ArmorOverlayContext.set(overlay);
        } else {
            ArmorOverlayContext.clear();
        }

        try {
            this.submit(matrices, queue, light, state, yaw, pitch);
        } finally {
            ArmorOverlayContext.clear();
        }
    }
}
