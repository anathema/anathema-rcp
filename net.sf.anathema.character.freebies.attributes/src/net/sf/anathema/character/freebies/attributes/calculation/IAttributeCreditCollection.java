package net.sf.anathema.character.freebies.attributes.calculation;

import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator.Priority;

public interface IAttributeCreditCollection {

  public int getCredit(Priority priorityGroup);

}