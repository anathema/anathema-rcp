package net.sf.anathema.character.freebies.attributes;

import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator.Priority;
import net.sf.anathema.character.freebies.configuration.IFreebiesHandler;

public interface IAttributeGroupFreebies extends IFreebiesHandler {

  public Priority getPriority();
}