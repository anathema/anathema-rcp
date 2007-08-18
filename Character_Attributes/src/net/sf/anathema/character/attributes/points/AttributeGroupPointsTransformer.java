package net.sf.anathema.character.attributes.points;

import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.character.attributes.model.IAttributes;
import net.sf.anathema.character.trait.group.ITraitGroup;

public final class AttributeGroupPointsTransformer implements ITransformer<ITraitGroup, AttributeGroupPoints> {

  private final IAttributes attributes;

  public AttributeGroupPointsTransformer(IAttributes attributes) {
    this.attributes = attributes;
  }

  @Override
  public AttributeGroupPoints transform(ITraitGroup group) {
    int pointsSpent = 0;
    for (String traitId : group.getTraitIds()) {
      int creationValue = attributes.getTrait(traitId).getCreationModel().getValue();
      pointsSpent += Math.max(0, creationValue - IAttributes.ATTRIBUTE_CALCULATION_BASE);
    }
    return new AttributeGroupPoints(pointsSpent);
  }
}