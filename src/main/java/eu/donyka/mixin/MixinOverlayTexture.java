package eu.donyka.mixin;

import eu.donyka.config.HConfig;
import eu.donyka.config.HCScreen;
import eu.donyka.listener.OverlayReloadListener;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OverlayTexture.class)
public abstract class MixinOverlayTexture implements OverlayReloadListener {
    @Shadow
    @Final
    private NativeImageBackedTexture texture;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void hitcolor$init(CallbackInfo ci) {
        this.hitcolor$reloadOverlay();
        OverlayReloadListener.register(this);
    }

    @Override
    public void hitcolor$reloadOverlay() {
        NativeImage image = this.texture.getImage();
        if (image == null) {
            return;
        }

        int color = getOverlayColor();
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 16; x++) {
                image.setColorArgb(x, y, color);
            }
        }
        this.texture.upload();
    }

    private static int getOverlayColor() {
        HConfig config = HCScreen.getConfig();
        if (!config.enable) {
            return 0xB3000000;
        }

        int alpha = 255 - config.alpha & 0xFF;
        return alpha << 24 | config.color & 0xFFFFFF;
    }
}
