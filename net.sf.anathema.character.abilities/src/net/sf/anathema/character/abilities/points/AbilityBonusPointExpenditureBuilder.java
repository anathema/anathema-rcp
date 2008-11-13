package net.sf.anathema.character.abilities.points;

import net.sf.anathema.character.trait.IBasicTrait;

public class AbilityBonusPointExpenditureBuilder {
  int cheapDotCount = 0;
  int expensiveDotCount = 0;

  public void addTrait(IBasicTrait trait) {
    if (trait.getStatusManager().getStatus().isCheap()) {
      cheapDotCount += trait.getCreationModel().getValue();
    }
    else {
      expensiveDotCount += trait.getCreationModel().getValue();
    }
  }

  public int getCost() {
    return cheapDotCount + expensiveDotCount * 2;
  }
}