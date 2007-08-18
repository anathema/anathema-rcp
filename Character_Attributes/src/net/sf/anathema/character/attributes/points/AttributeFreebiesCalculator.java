package net.sf.anathema.character.attributes.points;

import net.sf.anathema.character.attributes.model.IAttributes;
import net.sf.anathema.character.trait.IBasicTrait;


public class AttributeFreebiesCalculator {

  public static final String PRIMARY = "Primary"; //$NON-NLS-1$
  public static final String SECONDARY = "Secondary"; //$NON-NLS-1$
  public static final String TERTIARY = "Tertiary"; //$NON-NLS-1$
  private final IAttributes attributes;

  public AttributeFreebiesCalculator(IAttributes attributes) {
    this.attributes = attributes;
  }

  public AttributeGroupPoints calculate(String groupId) {
    IBasicTrait[] traits = attributes.getTraits();
    if (PRIMARY.equals(groupId) && traits.length > 0) {
      return new AttributeGroupPoints(traits[0].getCreationModel().getValue());
    }
    return new AttributeGroupPoints(0);
  }
}