package eu.donyka.mixin;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.minecraft.client.gl.RenderPipelines;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(RenderPipelines.class)
public abstract class MixinRenderPipelines {
    @ModifyArg(
            method = "<clinit>",
            slice = @Slice(
                    from = @At(value = "CONSTANT", args = "stringValue=pipeline/armor_cutout_no_cull"),
                    to = @At(value = "FIELD", target = "Lnet/minecraft/client/gl/RenderPipelines;ARMOR_CUTOUT_NO_CULL:Lcom/mojang/blaze3d/pipeline/RenderPipeline;", opcode = Opcodes.PUTSTATIC)
            ),
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mojang/blaze3d/pipeline/RenderPipeline$Builder;withShaderDefine(Ljava/lang/String;)Lcom/mojang/blaze3d/pipeline/RenderPipeline$Builder;"
            ),
            index = 0
    )
    private static String hitcolor$enableOverlayForArmorCutout(String define) {
        return renameNoOverlayDefine(define);
    }

    @ModifyArg(
            method = "<clinit>",
            slice = @Slice(
                    from = @At(value = "CONSTANT", args = "stringValue=pipeline/armor_decal_cutout_no_cull"),
                    to = @At(value = "FIELD", target = "Lnet/minecraft/client/gl/RenderPipelines;ARMOR_DECAL_CUTOUT_NO_CULL:Lcom/mojang/blaze3d/pipeline/RenderPipeline;", opcode = Opcodes.PUTSTATIC)
            ),
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mojang/blaze3d/pipeline/RenderPipeline$Builder;withShaderDefine(Ljava/lang/String;)Lcom/mojang/blaze3d/pipeline/RenderPipeline$Builder;"
            ),
            index = 0
    )
    private static String hitcolor$enableOverlayForArmorDecal(String define) {
        return renameNoOverlayDefine(define);
    }

    @ModifyArg(
            method = "<clinit>",
            slice = @Slice(
                    from = @At(value = "CONSTANT", args = "stringValue=pipeline/armor_translucent"),
                    to = @At(value = "FIELD", target = "Lnet/minecraft/client/gl/RenderPipelines;ARMOR_TRANSLUCENT:Lcom/mojang/blaze3d/pipeline/RenderPipeline;", opcode = Opcodes.PUTSTATIC)
            ),
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mojang/blaze3d/pipeline/RenderPipeline$Builder;withShaderDefine(Ljava/lang/String;)Lcom/mojang/blaze3d/pipeline/RenderPipeline$Builder;"
            ),
            index = 0
    )
    private static String hitcolor$enableOverlayForArmorTranslucent(String define) {
        return renameNoOverlayDefine(define);
    }

    private static String renameNoOverlayDefine(String define) {
        return "NO_OVERLAY".equals(define) ? "HITCOLOR_ALLOW_ARMOR_OVERLAY" : define;
    }
}
