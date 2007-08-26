package net.sf.anathema.character.attributes.points;

import net.sf.anathema.character.attributes.model.IAttributes;
import net.sf.anathema.character.trait.IBasicTrait;

public class AttributeBonusPointCalculator {

  private final IAttributes attributes;

  public AttributeBonusPointCalculator(IAttributes attributes) {
    this.attributes = attributes;
  }

  public int calculate() {
    int sum = 0;
    for (IBasicTrait trait : attributes.getTraits()) {
      sum += calculate(trait);
    }
    return sum;
  }

  private int calculate(IBasicTrait trait) {
    return Math.max(0, (trait.getCreationModel().getValue() - IAttributeConstants.ATTRIBUTE_CALCULATION_BASE)
        * IAttributeConstants.BONUS_POINT_COST);
  }
}