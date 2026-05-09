package eu.donyka.mixin;

import eu.donyka.config.HCScreen;
import eu.donyka.listener.ArmorOverlayContext;
import eu.donyka.listener.OverlayRendered;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ArmorFeatureRenderer.class)
public abstract class MixinArmorFeatureRenderer
        extends FeatureRenderer<BipedEntityRenderState, BipedEntityModel<BipedEntityRenderState>>
        implements OverlayRendered<BipedEntityRenderState> {

    protected MixinArmorFeatureRenderer(FeatureRendererContext<BipedEntityRenderState, BipedEntityModel<BipedEntityRenderState>> context) {
        super(context);
    }

    @Override
    public void hitcolor$renderWithOverlay(
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light,
            BipedEntityRenderState state,
            float yaw,
            float pitch,
            int overlay
    ) {
        if (HCScreen.getConfig().enableArmor && state.hurt) {
            ArmorOverlayContext.set(overlay);
        } else {
            ArmorOverlayContext.clear();
        }

        try {
            this.render(matrices, vertexConsumers, light, state, yaw, pitch);
        } finally {
            ArmorOverlayContext.clear();
        }
    }
}
