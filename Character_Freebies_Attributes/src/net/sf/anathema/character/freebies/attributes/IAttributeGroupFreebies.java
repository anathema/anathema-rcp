package net.sf.anathema.character.freebies.attributes;

import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator.PriorityGroup;
import net.sf.anathema.character.freebies.configuration.IFreebiesHandler;

public interface IAttributeGroupFreebies extends IFreebiesHandler {

  public PriorityGroup getPriority();
}