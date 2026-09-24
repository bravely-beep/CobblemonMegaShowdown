package com.github.yajatkaul.mega_showdown.render.accessories;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import io.wispforest.accessories.api.client.AccessoryRenderer;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class RightLegRenderer implements AccessoryRenderer {
    @Override
    public <M extends LivingEntity> void render(
            ItemStack stack,
            SlotReference reference,
            PoseStack poseStack,
            EntityModel<M> model,
            MultiBufferSource multiBufferSource,
            int light,
            float limbSwing,
            float limbSwingAmount,
            float partialTicks,
            float ageInTicks,
            float netHeadYaw,
            float headPitch
    ) {
        LivingEntity entity = reference.entity();

        poseStack.pushPose();

        if (model instanceof HumanoidModel<?> humanoidModel) {
            humanoidModel.rightLeg.translateAndRotate(poseStack);
        }

        poseStack.mulPose(Axis.ZP.rotationDegrees(180));

        if (!entity.getItemBySlot(EquipmentSlot.LEGS).isEmpty()) {
            poseStack.scale(1.2f, 1.2f, 1.2f);
        }

        BakedModel resolvedModel = AccessoryModelResolver.resolveItemId3d(stack);
        Minecraft.getInstance().getItemRenderer().render(
                stack, ItemDisplayContext.NONE, false, poseStack, multiBufferSource, light, OverlayTexture.NO_OVERLAY, resolvedModel
        );

        poseStack.popPose();
    }
}