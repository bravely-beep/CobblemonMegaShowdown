package com.github.yajatkaul.mega_showdown.mixin;

import com.cobblemon.mod.common.pokemon.Pokemon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Pokemon.class, remap = false)
public class PokemonMixin {
    @Inject(method = "isAlpha", at = @At("RETURN"), cancellable = true)
    private void alphaInject(CallbackInfoReturnable<Boolean> cir) {
        Pokemon self = (Pokemon) (Object) this;

        if (self.getAspects().contains("msd:dmax") || self.getAspects().contains("gmax")) {
            cir.setReturnValue(false);
        }
    }
}
