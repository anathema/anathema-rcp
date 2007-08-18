package net.sf.anathema.character.attributes.points;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.freebies.configuration.IFreebiesHandler;

public class TertiaryAttributePoints extends AbstractExecutableExtension implements IFreebiesHandler {

  @Override
  public int getPoints(ICharacterId id) {
    return new PrioritylessFreebiesHandler().getPoints(id, AttributeFreebiesCalculator.TERTIARY);
  }
}