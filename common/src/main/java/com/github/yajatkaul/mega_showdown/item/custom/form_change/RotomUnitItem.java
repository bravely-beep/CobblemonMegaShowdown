package com.github.yajatkaul.mega_showdown.item.custom.form_change;

import com.cobblemon.mod.common.pokemon.Pokemon;
import com.github.yajatkaul.mega_showdown.advancement.AdvancementHelper;
import com.github.yajatkaul.mega_showdown.api.codec.Effect;
import com.github.yajatkaul.mega_showdown.item.custom.PokemonSelectingBlockItem;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class RotomUnitItem extends PokemonSelectingBlockItem {
    private final String form;

    public RotomUnitItem(Block block, Properties properties, String form) {
        super(block, properties);
        this.form = form;
    }

    @Override
    public @Nullable InteractionResultHolder<ItemStack> applyToPokemon(@NotNull ServerPlayer serverPlayer, @NotNull ItemStack itemStack, @NotNull Pokemon pokemon) {
        if (!canUseOnPokemon(itemStack, pokemon)) {
            return InteractionResultHolder.fail(itemStack);
        }

        if (pokemon.getAspects().contains(this.form + "-appliance")) {
            Effect.getEffect("mega_showdown:rotom_effect").applyEffects(pokemon, List.of("appliance=none"), Optional.empty(), null);
        } else {
            Effect.getEffect("mega_showdown:rotom_" + form + "_effect").applyEffects(pokemon, List.of(String.format("appliance=%s", this.form)), Optional.empty(), null);
        }
        AdvancementHelper.grantAdvancement(serverPlayer, "rotom/rotom_form_change");
        return InteractionResultHolder.success(itemStack);
    }

    @Override
    public boolean canUseOnPokemon(@NotNull ItemStack stack, @NotNull Pokemon pokemon) {
        return pokemon.getSpecies().getName().equals("Rotom") &&
                !pokemon.getPersistentData().contains("form_changing");
    }
}
