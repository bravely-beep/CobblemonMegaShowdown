package com.github.yajatkaul.mega_showdown.render.accessories;

import com.github.yajatkaul.mega_showdown.item.MegaShowdownItems;
import io.wispforest.accessories.api.client.AccessoriesRendererRegistry;

public class AccessoriesRegisterRenderer {
    public static void register() {
        AccessoriesRendererRegistry.registerRenderer(MegaShowdownItems.ARCHIE_ANCHOR.get(), ChestRenderer::new);
        AccessoriesRendererRegistry.registerRenderer(MegaShowdownItems.LISIA_MEGA_TIARA.get(), HeadRenderer::new);
        AccessoriesRendererRegistry.registerRenderer(MegaShowdownItems.DIANTHA_MEGA_CHARM.get(), ChestRenderer::new);
        AccessoriesRendererRegistry.registerRenderer(MegaShowdownItems.ZINNIA_MEGA_ANKLET.get(), RightLegRenderer::new);

        AccessoriesRendererRegistry.registerRenderer(MegaShowdownItems.TERA_ORB.get(), BeltRenderer::new);

        AccessoriesRendererRegistry.registerRenderer(MegaShowdownItems.LIKOS_PENDANT.get(), ChestRenderer::new);
    }
}
