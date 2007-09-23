package net.sf.anathema.character.freebies.attributes;

import net.sf.anathema.character.freebies.attributes.AttributePointCalculator.PriorityGroup;
import net.sf.anathema.character.freebies.configuration.IFreebiesHandler;

public interface IAttributeGroupFreebiesHandler extends IFreebiesHandler {

  public PriorityGroup getPriority();
}