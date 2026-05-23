package eu.donyka.mixin;

import eu.donyka.config.HCScreen;
import eu.donyka.listener.OverlayRendered;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ArmorFeatureRenderer.class)
public abstract class MixinArmorFeatureRenderer<T extends LivingEntity, M extends BipedEntityModel<T>, A extends BipedEntityModel<T>>
        extends FeatureRenderer<T, M> implements OverlayRendered<T> {
    private int hitcolor$overlay = OverlayTexture.DEFAULT_UV;
    private T hitcolor$currentEntity;

    protected MixinArmorFeatureRenderer(FeatureRendererContext<T, M> context) {
        super(context);
    }

    @ModifyArg(
            method = "renderArmorParts",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/entity/model/BipedEntityModel;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;III)V"
            ),
            index = 3
    )
    private int hitcolor$useHitOverlayForArmor(int overlay) {
        return HCScreen.getConfig().enableArmor && this.hitcolor$currentEntity != null && this.hitcolor$currentEntity.hurtTime > 0
                ? this.hitcolor$overlay : overlay;
    }

    @Override
    public void hitcolor$renderWithOverlay(
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light,
            T entity,
            float limbAngle,
            float limbDistance,
            float tickDelta,
            float animationProgress,
            float headYaw,
            float headPitch,
            int overlay
    ) {
        this.hitcolor$currentEntity = entity;
        this.hitcolor$overlay = overlay;
        this.render(matrices, vertexConsumers, light, entity, limbAngle, limbDistance, tickDelta, animationProgress, headYaw, headPitch);
        this.hitcolor$currentEntity = null;
        this.hitcolor$overlay = OverlayTexture.DEFAULT_UV;
    }
}
