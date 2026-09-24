package com.github.yajatkaul.mega_showdown.render.accessories;

import com.github.yajatkaul.mega_showdown.tag.MegaShowdownTags;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import io.wispforest.accessories.api.client.AccessoryRenderer;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class LowOffHandRenderer implements AccessoryRenderer {
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

        ItemStack handStack = entity.getItemInHand(entity.getUsedItemHand());

        if (handStack.is(MegaShowdownTags.Items.MEGA_BRACELET)) {
            return;
        }
        poseStack.pushPose();

        // Accessories convention: even slot index renders on the right arm, odd on the left
        HumanoidArm renderArm = (reference.slot() % 2 == 0) ? HumanoidArm.RIGHT : HumanoidArm.LEFT;

        if (model instanceof ArmedModel armedModel) {
            armedModel.translateToHand(renderArm, poseStack);

            poseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));
            poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));

            if (renderArm == HumanoidArm.RIGHT) {
                poseStack.translate(1.0F / 16.0F, 1.0F / 8.0F, -10.0F / 16.0F);
            } else {
                poseStack.translate(-1.0F / 16.0F, 1.0F / 8.0F, -10.0F / 16.0F);
            }
        }

        ItemDisplayContext context = (renderArm == HumanoidArm.LEFT)
                ? ItemDisplayContext.THIRD_PERSON_LEFT_HAND
                : ItemDisplayContext.THIRD_PERSON_RIGHT_HAND;

        BakedModel resolvedModel = AccessoryModelResolver.resolveHand3rd(stack);
        Minecraft.getInstance().getItemRenderer().render(
                stack,
                context,
                renderArm == HumanoidArm.LEFT,
                poseStack,
                multiBufferSource,
                light,
                OverlayTexture.NO_OVERLAY,
                resolvedModel
        );

        poseStack.popPose();
    }
}
