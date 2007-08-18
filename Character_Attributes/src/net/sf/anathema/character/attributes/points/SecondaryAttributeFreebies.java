package net.sf.anathema.character.attributes.points;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.freebies.configuration.IFreebiesHandler;

public class SecondaryAttributeFreebies extends AbstractExecutableExtension implements IFreebiesHandler {

  @Override
  public int getPoints(ICharacterId id, int credit) {
    return new PrioritylessAttributeFreebies().getPoints(id, AttributeFreebiesCalculator.SECONDARY, credit);
  }
}