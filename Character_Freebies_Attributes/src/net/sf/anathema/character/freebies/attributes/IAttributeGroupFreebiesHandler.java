package net.sf.anathema.character.freebies.attributes;

import net.sf.anathema.character.attributes.points.AttributePointCalculator;
import net.sf.anathema.character.attributes.points.AttributePointCalculator.PriorityGroup;
import net.sf.anathema.character.freebies.configuration.IFreebiesHandler;

public interface IAttributeGroupFreebiesHandler extends IFreebiesHandler {

  public PriorityGroup getPriority();
}