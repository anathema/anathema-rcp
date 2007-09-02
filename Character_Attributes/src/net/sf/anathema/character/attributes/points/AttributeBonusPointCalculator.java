package net.sf.anathema.character.attributes.points;

import static net.sf.anathema.character.attributes.points.IAttributeConstants.*;
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
    return Math.max(0, (trait.getCreationModel().getValue() - ATTRIBUTE_CALCULATION_BASE) * BONUS_POINT_COST);
  }
}