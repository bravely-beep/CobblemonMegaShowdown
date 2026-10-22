({
  name: 'Rusted Shield',
  spritenum: 699,
  onSwitchIn(pokemon) {
    if (!pokemon.isActive || pokemon.baseSpecies.baseSpecies !== "Zamazenta") return;
    if (pokemon.species.name !== "Zamazenta-Crowned") {
      pokemon.formeChange("Zamazenta-Crowned", this.effect, true);
    }
    for (const moveSlot of pokemon.moveSlots) {
      if (moveSlot.id !== 'ironhead') continue;
      const oldMove = this.dex.moves.get(moveSlot.id);
      const newMove = this.dex.moves.get('behemothbash');
      if (!newMove.exists) continue;
      const ppRatio = oldMove.pp ? moveSlot.maxpp / oldMove.pp : 1;
      const newMaxPP = Math.floor(newMove.pp * ppRatio);
      moveSlot.id = newMove.id;
      moveSlot.pp = newMaxPP;
      moveSlot.maxpp = newMaxPP;
    }
  },
  onTakeItem(item, pokemon, source) {
    if (
      (source && source.baseSpecies.num === 889) || pokemon.baseSpecies.num === 889) {
      return false;
    }
    return true;
  },
  itemUser: ['Zamazenta'],
  num: 1104,
  gen: 8
})