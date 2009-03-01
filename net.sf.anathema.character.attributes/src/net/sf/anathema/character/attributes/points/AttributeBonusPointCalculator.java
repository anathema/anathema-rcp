package net.sf.anathema.character.attributes.points;

import static net.sf.anathema.character.attributes.points.IAttributeConstants.*;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

public class AttributeBonusPointCalculator {

  private final ITraitCollectionModel attributes;

  public AttributeBonusPointCalculator(ITraitCollectionModel attributes) {
    this.attributes = attributes;
  }

  public int calculate() {
    int sum = 0;
    for (IBasicTrait trait : attributes.getAllTraits()) {
      sum += calculate(trait);
    }
    return sum;
  }

  private int calculate(IBasicTrait trait) {
    int paidIncrement = trait.getCreationModel().getValue() - ATTRIBUTE_CALCULATION_BASE;
    int dotCosts = trait.getStatusManager().getStatus().isCheap() ? FAVORED_BONUS_POINT_COST : BONUS_POINT_COST;
    return Math.max(0, paidIncrement * dotCosts);
  }
}