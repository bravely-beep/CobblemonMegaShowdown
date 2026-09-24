package com.github.yajatkaul.mega_showdown.render.accessories;

import com.github.yajatkaul.mega_showdown.api.codec.item.ItemRenderingCodec;
import com.github.yajatkaul.mega_showdown.render.ItemRenderingLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.function.Function;

public class AccessoryModelResolver {
    public static BakedModel resolveItemId3d(ItemStack stack) {
        return resolve(stack, ItemRenderingCodec::itemId_3d);
    }

    private static BakedModel resolve(ItemStack stack, Function<ItemRenderingCodec, ResourceLocation> pick) {
        var itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemRenderingCodec codec = ItemRenderingLoader.get(BuiltInRegistries.ITEM.getKey(stack.getItem()));
        if (codec != null) {
            return itemRenderer.getItemModelShaper().getModelManager().getModel(ModelResourceLocation.inventory(pick.apply(codec)));
        }
        return itemRenderer.getModel(stack, null, null, 0);
    }
}
